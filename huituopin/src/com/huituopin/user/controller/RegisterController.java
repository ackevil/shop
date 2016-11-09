package com.huituopin.user.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.aigou.service.IAiGouService;
import com.huituopin.common.constants.ClothStatus;
import com.huituopin.common.utils.HeadImgUtil;
import com.huituopin.common.utils.MD5;
import com.huituopin.common.utils.StringUtil;
import com.huituopin.common.utils.StringUtils;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;
import com.huituopin.user.entity.User;
import com.huituopin.user.sendsms.Sendsms;
import com.huituopin.user.service.IUserService;
import com.huituopin.yjya.service.IYjyaService;
import com.ibm.db2.jcc.b.p;

/**
 * 
 * @author guoqingshan
 * 2016-4-20
 *
 */
@Controller
//请求路径加上模块名 ，，，，， 请求时候路径为   huituopin/register/checkPhone, 建议这个类下面的方法都放到userController里面，因为注册属于user的行为，
//一个模块里面尽量只用一个controller
@RequestMapping(value = "/register")
public class RegisterController {
	
	@Autowired
	private IUserService userService ;
	
	@Autowired
	private IYjyaService yjyaService;
	
	@Autowired
	private IAiGouService aigouService;
	
	private int mobile_code;
	
	/**
	 * 默认控制器  返回到主页页面
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "wechat/jsp/index";
	}
	
	//url中出现register/signin即跳到本控制器，注册页面
	@RequestMapping(value="/signin")
	public String signin(HttpServletRequest request, HttpServletResponse response){
		return "wechat/jsp/user/register";
	}
	
	/**
	 * 到主页 点击我 跳转到本控制器
	 */
	@RequestMapping(value="/userInfo")
	public ModelAndView userInfo(HttpSession session,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		//request.setAttribute("phone",request.getParameter("phone"));
		String userWcId = (String) session.getAttribute("userWcId");
//		if(userWcId=="" || userWcId == null){
//			userWcId = "oh6A0wl3Um0bw2V7DPtRviejwfN8";
//		}
		System.out.println("userWcId:" + userWcId);
		User user = userService.searchByWid(userWcId);
		if(user == null){
//			session.setAttribute("userWcId","oh6A0wl3Um0bw2V7DPtRviejwfN8");
//			session.setAttribute("userWcNickname","EmpLin");
//			session.setAttribute("userWcAvatar","http://wx.qlogo.cn/mmopen/IDcl8nYLVSyFdGehfw6qSMXU5sq3xEbuj8icviaGAfCm7TGmhwyHoyLZNkydMfhnUNmqicbXKuzEQtlmdQEQZuhr0iblnVIR604K/0");
//			session.setAttribute("userWcGender","true");
			mav.setViewName("redirect:/register/signin");
		}else{
			session.setAttribute("user",user);
			if(user.getUserType()){
				
				mav.setViewName("wechat/jsp/user/poorPeople/poorPeopleInfo");
			}else{
				//返回待邮寄个数
				int waitPostNum=0;
				if(yjyaService.getClothsByUserIDAndStatus(user.getUserId(), ClothStatus.RECEIVED)!=null)
					waitPostNum = yjyaService.getClothsByUserIDAndStatus(user.getUserId(), ClothStatus.RECEIVED).size();
				mav.addObject("waitPostNum", waitPostNum);
				//返回购物车里的商品数量
				int scNum=0;
				List<Object> list = aigouService.getSCByUserId(user.getUserId());
				if(list!=null){
					scNum=list.size();
				}
				mav.addObject("scNum", scNum);
				mav.setViewName("wechat/jsp/user/lovingPeople/lovingPeopleInfo");
			}
		}
		return mav;
	}
	
	
	/**
	 * 点击获取验证码按钮 先检查  手机号 是否可用
	 * 不可用直接弹出 号码已经注册信息
	 * 可用则调用发送验证码的方法
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
	public @ResponseBody
	String checkPhone(String phone) throws UnsupportedEncodingException {
		if(StringUtil.isEmpty(phone)){
			return "error1";
		}
		if(userService.phoneExists(phone)){
			return "error1"; //手机已经被注册
		}else{
			mobile_code = (int)((Math.random()*9+1)*100000);
			String code = Sendsms.semdsms(phone, mobile_code);
			if("2".equals(code)){ //验证码发送失败
				return "success";
			}else if("4085".equals(code)){
				return "error3";//剩余条数不足
			}else{
				return "error2";
			}
		}
	}
	/**
	 * 点击下一步按钮 
	 * 先检查验证码是否正确 然后跳转到设置密码的页面
	 * @param checkCode
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkCode")
	public String checkCode(String phone,HttpServletRequest request){
		//验证码相关操作
		request.setAttribute("phone",phone);
		return "wechat/jsp/user/chooseUserType";
	}
	
	
	@RequestMapping(value = "/checkCodeAction", method = RequestMethod.POST)
	public @ResponseBody
	Boolean checkCodeAction(String code) {
		int codeint = Integer.parseInt(code.toString().trim());
		if(mobile_code == codeint){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	/**
	 * 设置密码确认后  先把密码加密 
	 * 然后跳转到选择类型页面
	 * @param phone
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setPassword")
	public String setPassword(String phone,String password,HttpServletRequest request){
		password = MD5.md5(password); //对密码进行MD5加密
		request.setAttribute("phone", phone);
		request.setAttribute("password", password);
		return "wechat/jsp/chooseUserType";
	}
	
	
	/**
	 * 爱心人士个人信息修改
	 * @return
	 */
	@RequestMapping(value="/lpInfoSetting")
	public String lpInfoSetting(String userId,String userWcNickname){
		User user = userService.getUserByUserId(Integer.parseInt(userId));
		user.setUserWcNickname(userWcNickname);
		userService.updataUserInfo(user);
		return "redirect:/register/userInfo";
	}
	/**
	 * 贫困人士个人信息修改
	 * @return
	 */
	@RequestMapping(value="/ppInfoSetting")
	public String ppInfoSetting(String userId,String name,String userSex,String userBirthDay,String userNative,String userNation,String userAddress,String userPostal){
		User user = userService.getUserByUserId(Integer.parseInt(userId));
		user.setUserWcNickname(name.toString().trim());
		user.setUserWcGender(Boolean.parseBoolean(userSex));
		userService.updataUserInfo(user);
		
		PoorPeople poorPeople = userService.searchPpInfoByUserID(Integer.parseInt(userId));
		poorPeople.setUserId(Integer.parseInt(userId));
		poorPeople.setPoorAge((userBirthDay.toString().trim()));
		poorPeople.setPoorAddress(userNative.toString().trim());
		poorPeople.setPoorDetailAddress(userAddress.toString().trim());
		poorPeople.setPoorNation(userNation.toString().trim());
		poorPeople.setPoorPostal(userPostal);
		poorPeople.setPoorState(1); //1：未审核，2：审核通过，3：不通过
		poorPeople.setPoorIsDelete(false);//true：删除，false：未删除
		userService.updataPoorPeopleDetailInfo(poorPeople);
		return "redirect:/register/userInfo";
	}
	
	/**
	 * 选择类型之后  点击确认执行注册操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/userLogin")
	public String userRegister(HttpServletRequest request,String type){
		if(userService.searchByWid(request.getParameter("userWcId"))!=null){ //防止用户重复点击
			return "redirect:/register/userInfo";
		}
		User user = new User();
		user.setUserPhone(request.getParameter("userPhone"));
		user.setUserPwd(request.getParameter("userPwd"));
		user.setUserWcId(request.getParameter("userWcId"));
		user.setUserWcNickname(request.getParameter("userWcNickname"));
		String url1 = request.getServletContext().getRealPath("/");

		String url2="resource/headImg/"+StringUtils.randomString(64)+".jpg";
		HeadImgUtil.wechatImgToLocal(request.getParameter("userWcAvatar"), url1+url2);
		user.setUserWcAvatar(url2);
		if("1".equals(request.getParameter("userWcGender"))){
			user.setUserWcGender(true);
		}else{
			user.setUserWcGender(false);
		}
		user.setUserType(Boolean.parseBoolean(request.getParameter("userType")));
		user.setUserIsOnline(Boolean.parseBoolean(request.getParameter("userIsOnline")));
		user.setUserIsDelete(false);
		userService.insertUser(user);
		user = userService.searchByWid(request.getParameter("userWcId"));
		request.getSession().setAttribute("user", user);
		if("false".equals(request.getParameter("userType"))){//爱心人士
			LovingPeople lovingPeople = new LovingPeople();
			lovingPeople.setUserId(user.getUserId()); //爱心人士的userID
			lovingPeople.setLoveAddress("暂无");		  // 爱心人士地址
			lovingPeople.setLoveDetailAddress("暂无");//爱心人士详细地址
			lovingPeople.setLoveAge((short)0);		//爱心人士的年纪
			lovingPeople.setLoveCredicts((short) 0);//爱心人士的积分
			lovingPeople.setLoveIsDelete(false);	//爱心人士删除标记
			userService.insertLovingPeopleDetailInfo(lovingPeople);
			return "wechat/jsp/user/lovingPeople/lpInfoSetting";
		}else{ //贫困人士
			PoorPeople poorPeople = new PoorPeople();
			poorPeople.setUserId(user.getUserId());  //贫困人士的userID
			poorPeople.setPoorAge("0");				//贫困人士年龄
			poorPeople.setPoorAddress("null");		//贫困人士地址
			poorPeople.setPoorDetailAddress("null");//贫困人士详细地址
			poorPeople.setPoorNation("null");		//贫困人士民族
			poorPeople.setPoorPostal("null");		//贫困人士邮编
			poorPeople.setPoorState(1);				//贫困人士 审核状态
			poorPeople.setPoorIsDelete(false);		//贫困人士删除标记
			userService.insertPoorPeopelDetailInfo(poorPeople);
			return "wechat/jsp/user/poorPeople/ppInfoSetting";
		}
		
		
	}
	
	
	/*----------------------点击跳转到其他子模块（六个子模块）---------------------------*/
	/**
	 * 请求类型为  userRegister?moduleName='moduleName'
	 * @param moduleName
	 * @return
	 */
	@RequestMapping(value="/userRegister")
	public String  nextodule(String moduleName){
		if(moduleName.equals("dishuizhien"))
		{
			return "redirect:/dsze/dsze";
		}
		if(moduleName.equals("yijiuyouai"))
		{
			return "redirect:/wechat/yjya";
		}
		if(moduleName.equals("zhongdajiuzhu"))
		{
			return "redirect:/zdjz/signficant";
		}
		if(moduleName.equals("aigou"))
		{
			return "redirect:/wechat/aigou";
		}
		if(moduleName.equals("aixinfengcai"))
		{
			return "/wechat/jsp/error";
		}
		if(moduleName.equals("zhongchoukongjian"))
		{
			return "/wechat/jsp/error";
		}
		return "wechat/jsp/"+moduleName+"/index";
	}
	
}

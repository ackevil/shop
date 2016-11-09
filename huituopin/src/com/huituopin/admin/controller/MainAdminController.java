package com.huituopin.admin.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.activity.service.IActivityService;
import com.huituopin.activity.service.IActivitySupportService;
import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.service.IAiGouService;
import com.huituopin.aigou.service.ICollectionService;
import com.huituopin.bank.entity.Credicts;
import com.huituopin.bank.service.ICredictsService;
import com.huituopin.common.constants.CommentStatus;
import com.huituopin.common.utils.MD5;
import com.huituopin.common.utils.OutPrint;
import com.huituopin.common.utils.Page;
import com.huituopin.common.utils.StringUtils;
import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.dsze.service.IDszeService;
import com.huituopin.dsze.service.IDszeSupportService;
import com.huituopin.user.entity.Adminuser;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IAdminuserService;
import com.huituopin.user.service.IUserService;
import com.huituopin.yjya.entity.ClothType;
import com.huituopin.yjya.service.IYjyaService;

@RequestMapping("/admin")
@Controller
public class MainAdminController  extends OutPrint {

	@Autowired
	public  IAdminuserService adminuserService;
	
	@Autowired
	public  IUserService userService;
	
	@Autowired
	public IYjyaService yjyaService;
	
	@Autowired
	private ICollectionService collectionService;
	
	@Autowired
	private IAiGouService aigouService;
	
	
	@Autowired
	private IActivitySupportService activitySupportService;
	
	@Autowired
	private IActivityService activityService;
	
	@Autowired
	private IDszeSupportService dszeSupportService;
	
	@Autowired
	private IDszeService dszeService;
	
	@Autowired
	public ICredictsService credictsService;
	/**
	 * 后台登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		
		return "admin/login";
	}
	@RequestMapping("/checkUserName")
	public String checkUserName(String userName,String userPwd,HttpSession session,HttpServletRequest request){
		userPwd = MD5.md5(userPwd);
		Adminuser adminuser = adminuserService.searchAdminuserByName(userName);
		if(adminuser==null || !adminuser.getAdminuserPwd().equals(userPwd)){
			session.setAttribute("type", 1);
			return "redirect:/admin/login";
		}
		else{
			session.setAttribute("adminuser",adminuser);
			session.removeAttribute("type");
			return "admin/main";
		}
	}
	/**
	 * 后台跳转到修改密码界面
	 * @return
	 */
	
	@RequestMapping(value="/changePassword")
	public String changePassword(String adminuserName,HttpSession session){
		return "admin/user/changePassword";
	}
	
	/**
	 * 后台跳转到修改密码界面
	 * @return
	 */
	
	@RequestMapping(value="/changePasswordAction")
	public String changePasswordAction(String password,HttpSession session){
		Adminuser adminuser = (Adminuser) session.getAttribute("adminuser");
		adminuser.setAdminuserPwd(MD5.md5(password));
		adminuserService.updateAdminuser(adminuser);
		session.setAttribute("type",3);
		return "redirect:/admin/login";
	}
	
	/**
	 * 查询所有的爱心人士
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getAllLP")
	public ModelAndView getAllLP(String param,Page page) throws ParseException{
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/user/lpUserList");
		String params[] = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date activityLaunchTime = null;
		Date activityStopTime = null;
		Integer gender = null;
		Integer state = null;
		String keyWord = null;
		if(param!=null && !param.equals(""))
		{
			params = param.split("\\+");
			if(!params[0].equals("")){
				activityLaunchTime = dateFormat.parse(params[0]);
			}
			if(!params[1].equals("")){
				activityStopTime = dateFormat.parse(params[1]);
			}
			gender = Integer.valueOf(params[2]);
			state = Integer.valueOf(params[3]);
			if(params.length>4){
				keyWord = params[4]; 
			}
		}
		List<Object>  userList = new ArrayList<Object>();
		if(activityLaunchTime!=null || activityStopTime!=null || gender != null || state !=  null || keyWord != null){
			userList = adminuserService.searchAllLovingPeopleInfo(page,activityLaunchTime,activityStopTime,gender,state,keyWord);
		}else{
			userList = adminuserService.searchAllLovingPeopleInfo(page,null,null,3,0,null);
		}
		model.addObject("userList", userList);
		model.addObject("count", page.maxRows);
		model.addObject("pages", page);
		if(activityLaunchTime==null || activityLaunchTime==null){
			model.addObject("activityLaunchTime", activityLaunchTime);
			model.addObject("activityStopTime", activityStopTime);
		}else{
			model.addObject("activityLaunchTime", dateFormat.format(activityLaunchTime));
			model.addObject("activityStopTime", dateFormat.format(activityStopTime));
		}
		model.addObject("gender", gender);
		model.addObject("state", state);
		model.addObject("keyWord", keyWord);
		model.addObject("myparam", param);
		return model;
	}
	
	/**
	 * 查询所有的贫困人士
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getAllPP")
	public ModelAndView getAllPP(String param,Page page) throws ParseException{
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/user/ppUserList");
		String params[] = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date activityLaunchTime = null;
		Date activityStopTime = null;
		Integer gender = null;
		Integer state = null;
		String keyWord = null;
		if(param!=null && !param.equals(""))
		{
			params = param.split("\\+");
			if(!params[0].equals("")){
				activityLaunchTime = dateFormat.parse(params[0]);
			}
			if(!params[1].equals("")){
				activityStopTime = dateFormat.parse(params[1]);
			}
			gender = Integer.valueOf(params[2]);
			state = Integer.valueOf(params[3]);
			if(params.length>4){
				keyWord = params[4]; 
			}
		}
		List<User> userList = new ArrayList<User>();
		if(activityLaunchTime!=null || activityStopTime!=null || gender != null || state !=  null || keyWord != null){
			userList = adminuserService.searchAllPoorPeopel(page,activityLaunchTime,activityStopTime,gender,state,keyWord);
		}else{
			userList = adminuserService.searchAllPoorPeopel(page,null,null,3,0,null);
		}
		model.addObject("userList", userList);
		model.addObject("count", page.maxRows);
		model.addObject("pages", page);
		if(activityLaunchTime!=null || activityStopTime!=null){
			model.addObject("activityLaunchTime", dateFormat.format(activityLaunchTime));
			model.addObject("activityStopTime", dateFormat.format(activityStopTime));
		}else{
			model.addObject("activityLaunchTime", activityLaunchTime);
			model.addObject("activityStopTime", activityStopTime);
		}
		model.addObject("gender", gender);
		model.addObject("state", state);
		model.addObject("keyWord", keyWord);
		model.addObject("myparam", param);
		return model;
	}
	
	/**
	 * 查看贫困人士详细信息
	 * @return
	 */
	@RequestMapping(value="/ppDetailInfo")
	public ModelAndView ppDetailInfo(HttpServletRequest request,String userId){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/user/ppDetailInfo");
		//------------------------------------------------------------------------------------
		User user = userService.getUserByUserId(Integer.parseInt(userId));
		PoorPeople poorPeople = userService.searchPpInfoByUserID(Integer.parseInt(userId));
		mav.addObject("user", user);
		mav.addObject("poorPeople", poorPeople);
		//------------------------------------------------------------------------------------
		
		//获取我的领取信息
		List<Object> list = yjyaService.getClothByPoorId(user.getUserId());
		mav.addObject("clothInfoList", list);
		int getNum;
		if(list!=null){
			getNum = list.size();
		}else{
			getNum = 0;
		}
		mav.addObject("getNum", getNum);
		//获取我的评价信息
		List<Object> commentList = yjyaService.getCommentInfoByUserIdAdmin(user.getUserId());
		mav.addObject("commentList", commentList);
		mav.addObject("clothInfoList", list);
		int commentNum;
		if(list!=null){
			commentNum = list.size();
		}else{
			commentNum = 0;
		}
		mav.addObject("commentNum", commentNum);
		return mav;
	}
	/**
	 * 选中评价审核通过
	 */
	@RequestMapping(value="/commentPass")
	public void commentPass(String commentIds,HttpServletResponse res){
		if(yjyaService.setStautsComments(commentIds,CommentStatus.PASS)){
			write("true",res);
		}else{
			write("fail",res);
		}
	}
	/**
	 * 选中评价审核未通过
	 */
	@RequestMapping(value="/commentNoPass")
	public void commentNoPass(String commentIds,HttpServletResponse res){
		if(yjyaService.setStautsComments(commentIds,CommentStatus.NOPASS)){
			write("true",res);
		}else{
			write("fail",res);
		}
	}
	
	/**
	 * 查看爱心人士详细信息
	 * @return
	 */
	@RequestMapping(value="/lpDetailInfo")
	public String lpDetailInfo(HttpServletRequest request,Model model ,String userId){
		//获取爱心人士 收藏的商品
		List<Object> collectionList =  collectionService.searchInfoByUserId(Integer.parseInt(userId));
		request.setAttribute("collectionList", collectionList);
		
		//-----------------------------------------------------------------------------
		User user = userService.getUserByUserId(Integer.parseInt(userId));
		//User user = userService.getUserByUserId(132);
		LovingPeople lovingPeople = userService.searchLpInfoByUserID(Integer.parseInt(userId));
		request.setAttribute("user",user);
		request.setAttribute("lovingPeople",lovingPeople);
		//----------------------------------------------------------------------------------
		//获取爱心人士 --捐助记录--捐助的物品相关信息
		List<Object> list = yjyaService.getLpDonateInfo(null,null,null,0,5,"",userId);
		request.setAttribute("clothInfoList", list);
		int donateNum=0;
		if(list!=null){
			donateNum=list.size();
		}
		request.setAttribute("donateNum", donateNum);
		
		/*--捐助金额 start--*/
		
		List<ActivitySupport> list1=activitySupportService.getActivitySupportByUserId(Integer.parseInt(userId.toString().trim()));
		List<DszeSupport> list2=dszeSupportService.getDszeSupportByUserId(Integer.parseInt(userId.toString().trim()));
		ArrayList<HashMap<String, Object>> maplist1=new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> maplist2=new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map=null;
		
		int donatemoneyitems=0;
		
		if(list1!=null){
			donatemoneyitems+=list1.size();
			for(ActivitySupport activitySupport :list1){
				int id =activitySupport.getActivityId();
				Activity activity=activityService.getActivityById(id);
				map=new HashMap<String, Object>();
				map.put("activitySupport", activitySupport);
				map.put("activity", activity);
				maplist1.add(map);
			}
		}
		if(list2!=null){
			donatemoneyitems+=list2.size();
			for(DszeSupport dszeSupport :list2){
				int id =dszeSupport.getDszeId();
				Dsze dsze=dszeService.getDszeById(id);
				map=new HashMap<String, Object>();
				map.put("dszeSupport", dszeSupport);
				map.put("dsze", dsze);
				maplist2.add(map);
			}
		}
		
		model.addAttribute("maplist1", maplist1);
		model.addAttribute("maplist2", maplist2);
		model.addAttribute("donatemoneyitems", donatemoneyitems);
		
		/*end*/

		
		//获取类别信息
		String ctInfo = "";
		List<ClothType> ctList = yjyaService.getAllClothType();
		if(ctList!=null){
			ctInfo = ctList.size()  + "|";
			for(ClothType clothType : ctList){
				ctInfo += clothType.getClothTypeId() + "├" + clothType.getClothTypeName() + "|";
			}
		}
		request.setAttribute("ctInfo", ctInfo);
		//----end------
		//获取消费记录
		List<ProductOrder> payList = aigouService.getPayedByUserId(user.getUserId());
		request.setAttribute("payList", payList);
		int payListNum = 0;
		float count=0;
		if(payList!=null){
			payListNum=payList.size();
			for(int i=0;i<payListNum;i++){
				count+=payList.get(i).getProductOrderAllCount();
			}
		}
		request.setAttribute("payListNum", payListNum);
		DecimalFormat df = new DecimalFormat();
		String style="0.00";
		df.applyPattern(style);
		request.setAttribute("count",df.format(count));
		//订单信息
		List<HashMap<String,Object>> poList = aigouService.getPOByUserIdAndStatus(user.getUserId(), (short)-1);
		request.setAttribute("poList", poList);
		int poListNum=0;
		if(poList!=null){
			poListNum=poList.size();
		}
		request.setAttribute("poListNum", poListNum);
		//兑换记录
		List<Credicts> CredictlistOut = credictsService.searchInfoByType(Integer.parseInt(userId), 1);
		request.setAttribute("CredictlistOut", CredictlistOut);
		//评价信息
		List<HashMap<String,Object>> pclist = aigouService.getProductCommentByUserId(user.getUserId());
		request.setAttribute("pclist", pclist);
		int pcListNum=0;
		if(pclist!=null){
			pcListNum=pclist.size();
		}
		request.setAttribute("pcListNum", pcListNum);
		return "admin/user/lpDetailInfo";
	}
	/**
	 * 后台管理员 添加贫困人士
	 */
	@RequestMapping(value="/ppUserAdd")
	public String ppUserAdd(){
		return "admin/user/ppAddUser";
	}
	/**
	 * 后台管理员 添加贫困人士  添加操作
	 * @return
	 */
	@RequestMapping(value="/ppAddUserAction")
	public String ppAddUserAction(HttpServletRequest request){
		User user = new User();
		user.setUserPhone(request.getParameter("userPhone").toString().trim()); //电话号码
		user.setUserPwd("123456"); //密码
		user.setUserType(true); //类型
		user.setUserWcAvatar("http://www.ackevil.com/wechat/images/logo.png");// 头像
		user.setUserWcGender(Boolean.parseBoolean(request.getParameter("userSex").toString().trim())); // 性别
		user.setUserWcId(StringUtils.getUUID_32()); //微信ID
		user.setUserWcNickname(request.getParameter("userName").toString().trim());//  姓名
		user.setUserSigntime(new Date()); //注册时间
		user.setUserIsDelete(false); //删除标记
		user.setUserIsOnline(false);//在线状态
		user.setUserStates((short)1);
		userService.insertUser(user);
		user = userService.getUserByPhone(request.getParameter("userPhone").toString().trim());
		
		PoorPeople poorPeople = new PoorPeople();
		poorPeople.setUserId(user.getUserId());
		String addr = request.getParameter("province").toString().trim();
		addr += request.getParameter("city").toString().trim();
		addr += request.getParameter("area").toString().trim();
		poorPeople.setPoorAddress(addr); //地址
		poorPeople.setPoorDetailAddress(request.getParameter("userDetailAddr").toString().trim()); //详细地址
		poorPeople.setPoorAge(request.getParameter("userAge").toString().trim()); //年龄
		poorPeople.setPoorNation(request.getParameter("userAge").toString().trim());//民族
		poorPeople.setPoorPostal(request.getParameter("userPost").toString().trim());//邮编
		poorPeople.setPoorState(Integer.parseInt(request.getParameter("userType").toString().trim()));//状态 审核
		userService.insertPoorPeopelDetailInfo(poorPeople);
		return "redirect:/admin/getAllPP";
	}
	
	/**
	 * 后台管理员  修改贫困人士信息
	 */
	@RequestMapping(value="/ppUpdataInfo")
	public @ResponseBody 
	boolean ppUpdataInfo(String type,String param,String userId){
		User user = userService.getUserByUserId(Integer.parseInt(userId));
		PoorPeople poorPeople = userService.searchPpInfoByUserID(Integer.parseInt(userId));
		if(".changeName".equals(type)){ //姓名
			user.setUserWcNickname(param);
			return userService.updataUserInfo(user);
			
		}
		if(".changePhone".equals(type)){//电话号码
			user.setUserPhone(param);
			return userService.updataUserInfo(user);
		}
		if(".changeGender".equals(type)){ //性别
			if("男".equals(param)) user.setUserWcGender(true);
			else user.setUserWcGender(false);
			return userService.updataUserInfo(user);
		}
		if(".changeState".equals(type)){//审核状态
			if("待审核".equals(param))poorPeople.setPoorState(1);
			if("已通过".equals(param))poorPeople.setPoorState(2);
			if("不通过".equals(param))poorPeople.setPoorState(3);
			return userService.updataPoorPeopleDetailInfo(poorPeople);
		}
		if(".changeAddress".equals(type)){//地址
			poorPeople.setPoorAddress(param);
			return userService.updataPoorPeopleDetailInfo(poorPeople);
		}
		if(".changeDetailAddress".equals(type)){//详细地址
			poorPeople.setPoorDetailAddress(param);
			return userService.updataPoorPeopleDetailInfo(poorPeople);
		}
		return false;
	}
	
	
	
	/**
	 * 后台管理员 添加爱心人士
	 */
	@RequestMapping(value="/lpUserAdd")
	public String lpUserAdd(){
		return "admin/user/lpAddUser";
	}
	/**
	 * 后台管理员 添加爱心人士操作
	 */
	@RequestMapping(value="/lpAddUserAction")
	public String lpAddUserAction(HttpServletRequest request){
		User user = new User();
		user.setUserPhone(request.getParameter("userPhone").toString().trim());//电话号码
		user.setUserPwd("123456"); //密码
		user.setUserType(false);//类型  爱心人士
		user.setUserWcNickname(request.getParameter("userName").toString().trim());//  姓名
		user.setUserWcAvatar("wechat/images/logo.png");//头像
		user.setUserWcGender(Boolean.parseBoolean(request.getParameter("userSex").toString().trim()));//性别
		user.setUserWcId(StringUtils.getUUID_32()); //微信ID
		user.setUserSigntime(new Date());//注册时间
		user.setUserIsDelete(false); //删除标记
		user.setUserIsOnline(false);//在线状态
		user.setUserStates((short) 1);//用户状态
		userService.insertUser(user);
		user = userService.getUserByPhone(request.getParameter("userPhone").toString().trim());
		
		LovingPeople lovingPeople = new LovingPeople();
		lovingPeople.setUserId(user.getUserId());
		String addr = request.getParameter("province").toString().trim();
		addr += request.getParameter("city").toString().trim();
		addr += request.getParameter("area").toString().trim();
		
		lovingPeople.setLoveAddress(addr);
		lovingPeople.setLoveAge((short) 0);
		lovingPeople.setLoveCredicts((short) 0);
		lovingPeople.setLoveDetailAddress(request.getParameter("userDetailAddr").toString().trim());
		lovingPeople.setLoveIsDelete(false);
		userService.insertLovingPeopleDetailInfo(lovingPeople);
		return "redirect:/admin/getAllLP";
	}
	
	/**
	 * 后台管理员  修改贫困人士信息
	 */
	@RequestMapping(value="/lpUpdataInfo")
	public @ResponseBody 
	boolean lpUpdataInfo(String type,String param,String userId){
		User user = userService.getUserByUserId(Integer.parseInt(userId));
		LovingPeople lovingPeople = userService.searchLpInfoByUserID(Integer.parseInt(userId));
		if(".changeName".equals(type)){ //姓名
			user.setUserWcNickname(param);
			return userService.updataUserInfo(user);
			
		}
		if(".changePhone".equals(type)){//电话号码
			user.setUserPhone(param);
			return userService.updataUserInfo(user);
		}
		if(".changeGender".equals(type)){ //性别
			if("男".equals(param)) user.setUserWcGender(true);
			else user.setUserWcGender(false);
			return userService.updataUserInfo(user);
		}
		if(".changeAddress".equals(type)){//地址
			lovingPeople.setLoveAddress(param);
			return userService.updataLovingPeopleDetailInfo(lovingPeople);
		}
		if(".changeDetailAddress".equals(type)){//详细地址
			lovingPeople.setLoveDetailAddress(param);
			return userService.updataLovingPeopleDetailInfo(lovingPeople);
		}
		return false;
	}
}

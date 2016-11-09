package com.huituopin.pc.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huituopin.common.utils.StringUtil;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;
import com.huituopin.user.entity.User;
import com.huituopin.user.sendsms.Sendsms;
import com.huituopin.user.service.IUserService;
@Controller
@RequestMapping(value="/")
public class PcUserController {
	@Autowired
	private IUserService userService ;
	private int mobile_code; //登录用的验证码
	private int mobile_code2;//注册用的验证码
	/**
	 * 进入首页
	 * @param session
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpSession session){
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		return "pc/index";
	}
	/**
	 * 点击注册按钮
	 * @return
	 */
	@RequestMapping("/sign")
	public String sign(String userType){
		
		return "pc/userType";
	}
	/**
	 * 注册 --选择用户类型
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/userType")
	public String chooesUserType(String userType,HttpServletRequest request){
		request.setAttribute("type", userType);
		return "pc/signPhone";
	}
	/**
	 * 注册点击获取验证码按钮
	 * @param phone
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/registerCheckPhone")
	public @ResponseBody String registerCheckPhone(String phone) throws UnsupportedEncodingException{
		if(StringUtil.isEmpty(phone)){
			return "error1";
		}
		if(userService.phoneExists(phone)){
			return "error1"; //该手机号码已经注册过了
		}else{
			mobile_code2 = (int)((Math.random()*9+1)*100000);
			String code = Sendsms.semdsms(phone, mobile_code2);
			if("2".equals(code)){ //验证码发送失败
				return "success";
			}else if("4085".equals(code)){
				return "error3";//剩余条数不足
			}else{
				return "error2";
			}
		}
	}
	@RequestMapping("/registerCheckCodeAction")
	public @ResponseBody Boolean registerCheckCodeAction(String code){
		int codeint = Integer.parseInt(code.toString().trim());
		if(mobile_code2 == codeint){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping("/signAction")
	public String signAction(String phone,String type,HttpSession session,HttpServletRequest request){
		User user = userService.getUserByPhone(phone);
		if(user != null){
			session.setAttribute("user", user);
			return "redirect:/";
		}
		user = new User();
		user.setUserPhone(phone);//电话号码
		user.setUserPwd("123456");//密码
		user.setUserWcId("1");//微信ID
		user.setUserWcNickname("default");
		user.setUserWcAvatar("default");
		user.setUserWcGender(true);
		user.setUserType(Boolean.parseBoolean(request.getParameter("type")));
		user.setUserIsOnline(false);
		user.setUserIsDelete(false);
		userService.insertUser(user);
		user = userService.getUserByPhone(phone);
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
			return "redirect:/";
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
			return "redirect:/";
		}
	}
	
	//------------------------登录部分-------------------
	/**
	 * 点击登录按钮
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "pc/login";
	}
	/**
	 * 点击获取验证码
	 * @param phone
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
	public @ResponseBody String checkPhone(String phone) throws UnsupportedEncodingException{
		if(StringUtil.isEmpty(phone)){
			return "error1";
		}
		if(userService.phoneExists(phone)){
			mobile_code = (int)((Math.random()*9+1)*100000);
			String code = Sendsms.semdsms(phone, mobile_code);
			if("2".equals(code)){ //验证码发送失败
				return "success";
			}else if("4085".equals(code)){
				return "error3";//剩余条数不足
			}else{
				return "error2";
			}
		}else{
			return "error1"; //您为注册
		}
	}
	/**
	 * 点击登录按钮
	 * @param code
	 * @param phone
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/checkCodeAction",method=RequestMethod.POST)
	public @ResponseBody Boolean checkCodeAction(String code,String phone,HttpSession session){
		int codeint = Integer.parseInt(code.toString().trim());
		if(mobile_code == codeint){
			User user = userService.getUserByPhone(phone);
			session.setAttribute("user", user);
			return true;
		}else{
			return false;
		}
	}
	//-------------------登录部分end----------------------
	@RequestMapping(value="/userInfo")
	public String userInfo(){
		return "pc/userInfo";
	}
	@RequestMapping(value="/exit")
	public String exit(HttpSession session){
		session.removeAttribute("user");
		return "pc/index";
	}
}

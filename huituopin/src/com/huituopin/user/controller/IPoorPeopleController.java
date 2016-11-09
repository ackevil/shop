package com.huituopin.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.common.constants.ClothStatus;
import com.huituopin.common.utils.OutPrint;
import com.huituopin.user.entity.ShippingAddress;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.wechat.core.service.IWechatService;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.yjya.service.IYjyaService;

@Controller
@RequestMapping(value="/pp")
public class IPoorPeopleController extends OutPrint{
	
	@Autowired
	private IUserService userService ;
	
	@Autowired
	private IYjyaService yjyaService;
	
	@Autowired
	private IWechatService wechatService;
	/**
	 * 详细信息
	 * @return
	 */
	@RequestMapping(value="ppDetailInfo")
	public String ppDetailInfo(){
		return "wechat/jsp/user/ppDetailInfo";
	}
	
	
	/**
	 * 收货信息
	 * @param userId
	 * @return clothId
	 */
	@RequestMapping(value="/reciveInfo")
	public String reciveInfo(String userId,HttpServletRequest request){
		String type = request.getParameter("type").toString().trim();
		List<ShippingAddress> list = userService.searchShipingAddressByUserId(Integer.parseInt(userId));
		request.setAttribute("shippingAddressList",list);
		request.setAttribute("type",type);	
		return "wechat/jsp/user/reciveInfo";
	}
	
	/**
	 * 我的评价
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/comment")
	public ModelAndView comment(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/user/poorPeople/comment");
		//获取该用户的头像信息
		User user = (User)request.getSession().getAttribute("user");
		//User user = userService.getUserByUserId(109);
		mav.addObject("user", user);
		//获取所有的评价
		Map<String,Object> map = yjyaService.getAllCommentByUserId(user.getUserId());
		mav.addObject("commentInfoList", map.get("commentInfoList"));
		mav.addObject("list", map.get("list"));
		return mav;
	}
	/**
	 * 我的领取
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getting")
	public ModelAndView getting(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/user/poorPeople/getting/getting");
		User user = (User)request.getSession().getAttribute("user");
		//User user = userService.getUserByUserId(109);
		//获取已领取信息
		List<Object> clothReceived = yjyaService.getReceivedOrderInfoByPoorUserID(user.getUserId());
		mav.addObject("clothReceived", clothReceived);
		//获取已收货信息
		List<Object> clothReception = yjyaService.getReceptionOrderInfoByPoorUserID(user.getUserId());
		mav.addObject("clothReception", clothReception);
		return mav;
	}
	/**
	 * 确认收货
	 * @param coId
	 * @param response
	 */
	@RequestMapping(value="/confirmRecept_do")
	public void confirmRecept_do(String coId,HttpServletResponse response){
		String result="";
		if(yjyaService.updateClothOrderStatus(Integer.valueOf(coId), ClothStatus.RECEPTION))
			result="true";
		else
			result="false";
		write(result,response);
		
	}
	/**
	 * 点击评价跳转到此页面
	 * @param coId
	 * @param clothId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rating")
	public ModelAndView rating(String coId,String clothId,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/user/poorPeople/getting/rating");
		//获取JSSDK配置信息
		String url = request.getRequestURL().toString();
		if (request.getQueryString() != null){
			url += "?" + request.getQueryString();
		}
		Map<String,String> map = wechatService.getJsConfig(url);
		mav.addObject("appId", map.get("appId"));
		mav.addObject("timestamp", map.get("timestamp"));
		mav.addObject("noncestr", map.get("noncestr"));
		mav.addObject("signature", map.get("signature"));
		Cloth cloth = yjyaService.getClothById(Integer.valueOf(clothId));
		mav.addObject("clothPic", cloth.getClothMainpicPath());
		mav.addObject("coId", coId);
		return mav;
	}
	/**
	 * 提交衣物评价
	 * @param coId
	 * @param detailInfo
	 * @param picPaths
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/submmitComment_do")
	public void submmitComment_do(String coId,String detailInfo,String picPaths,HttpServletRequest request,HttpServletResponse response){
		String result="";
		if(yjyaService.insertClothComment(Integer.valueOf(coId), detailInfo, picPaths))
			result="true";
		else
			result="false";
		write(result,response);
	}
	@RequestMapping(value="/deleteOrder_do")
	public void deleteOrder_do(String coId,HttpServletResponse response){
		String result="";
		if(yjyaService.deleteClothOrder(Integer.valueOf(coId)))
			result="true";
		else
			result="false";
		write(result,response);
	}
	
	/**
	 * 收件信息-->添加地址
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/ppAddressAdd")
	public String ppAddressAdd(HttpServletRequest request){
		String type = request.getParameter("type").toString().trim();
		request.setAttribute("type", type);
		return "wechat/jsp/user/ppAddressAdd";
	}
	/**
	 * 收件信息-->添加地址-->确认添加操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ppAddressAddAction")
	public String ppAddressAddAction(HttpServletRequest request){
		ShippingAddress shippingAddress = new ShippingAddress();
		String type = request.getParameter("type").toString().trim();
		String userId = request.getParameter("userId").toString().trim();
		shippingAddress.setUserId(Integer.parseInt(request.getParameter("userId").toString().trim()));
		shippingAddress.setShippingAddName(request.getParameter("shippingAddName").toString().trim());
		shippingAddress.setShippingAddPhone(request.getParameter("shippingAddPhone").toString().trim());
		shippingAddress.setShippingAddProvince(request.getParameter("shippingAddProvince").toString().trim());
		shippingAddress.setShippingAddDetail(request.getParameter("shippingAddDetail").toString().trim());
		shippingAddress.setShippingAddCity("defalut");
		shippingAddress.setShippingAddDistrict("default");
		shippingAddress.setShippingAddIsMain(false);
		shippingAddress.setShippingAddIsDelete(false);
		System.out.println(shippingAddress);
		boolean flag = userService.addShipingAddress(shippingAddress);
//		if(flag){
//			return "";
//		}else{
//			return null;
//		}
		return "redirect:/pp/reciveInfo?userId="+userId+"&type="+type;
	}
	
	/**
	 * 收件信息-->编辑地址
	 * @return
	 */
	@RequestMapping(value="/ppAddressEdit")
	public String ppAddressEdit(HttpServletRequest request,String shipId,String type){
		request.setAttribute("type", type);
		ShippingAddress shippingAddress = userService.searchShipingAddressById(Integer.parseInt(shipId.toString().trim()));
		request.setAttribute("shippingAddressList",shippingAddress);
		return "wechat/jsp/user/ppAddressEdit";
	}
	/**
	 * 收件信息--编辑地址--确认操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ppAddressEditAction")
	public String ppAddressEditAction(HttpServletRequest request,String type){
		ShippingAddress shippingAddress = new ShippingAddress();
		String userId = request.getParameter("userId").toString().trim();
		shippingAddress.setShippingAddId((Integer.parseInt(request.getParameter("shippingAddId").toString().trim())));
		shippingAddress.setUserId(Integer.parseInt(request.getParameter("userId").toString().trim()));
		shippingAddress.setShippingAddName(request.getParameter("shippingAddName").toString().trim());
		shippingAddress.setShippingAddPhone(request.getParameter("shippingAddPhone").toString().trim());
		shippingAddress.setShippingAddProvince(request.getParameter("shippingAddProvince").toString().trim());
		shippingAddress.setShippingAddDetail(request.getParameter("shippingAddDetail").toString().trim());
		shippingAddress.setShippingAddCity("defalut");
		shippingAddress.setShippingAddDistrict("default");
		shippingAddress.setShippingAddIsMain(false);
		shippingAddress.setShippingAddIsDelete(false);
		System.out.println(shippingAddress);
		userService.updataShipingAddress(shippingAddress);
		return "redirect:/pp/reciveInfo?userId="+userId+"&type="+type;
		
	}
	/**
	 * 收件信息-->删除地址
	 * @return
	 */
	@RequestMapping(value="/ppAddressDelete")
	public String ppAddressDelete(String userId,String shipId,String type,HttpServletRequest request){
		System.out.println("userid:"+userId+",shipid:"+shipId);
		userService.deleteShipingAddress(Integer.parseInt(shipId.toString().trim()));
		return "redirect:/pp/reciveInfo?userId="+userId+"&type="+type;
	}
	/**
	 * 把默认地址修改为普通地址
	 * @param shipId
	 * @return
	 */
	@RequestMapping(value="/deleteDefaultShipAddress")
	public String deleteDefaultShipAddress(String userId,String shipId,String type,HttpServletRequest request){
		userService.deleteDefaultShipAddress(Integer.parseInt(shipId.toString().trim()));
		return "redirect:/pp/reciveInfo?userId="+userId+"&type="+type;
	}
	/**s
	 * 把普通地址改为默认地址
	 * @param userId
	 * @param shipId
	 * @return
	 */
	@RequestMapping(value="/changeShipAddressToDefault")
	public String changeShipAddressToDefault(String userId,String shipId,String type,HttpServletRequest request){
		userService.changeShipAddressToDefault(Integer.parseInt(userId), Integer.parseInt(shipId));
		return "redirect:/pp/reciveInfo?userId="+userId+"&type="+type;
	}
	
	
	
}

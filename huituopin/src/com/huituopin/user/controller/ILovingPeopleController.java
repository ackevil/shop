package com.huituopin.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.String;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.ShippingAddress;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.wechat.core.service.IWechatService;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.yjya.entity.ClothType;
import com.huituopin.yjya.service.IYjyaService;
import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.activity.service.IActivityService;
import com.huituopin.activity.service.IActivitySupportService;
import com.huituopin.aigou.service.IAiGouService;
import com.huituopin.aigou.service.ICollectionService;
import com.huituopin.common.constants.ClothStatus;
import com.huituopin.common.constants.ProductOrderStatus;
import com.huituopin.common.utils.OutPrint;
import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.dsze.service.IDszeService;
import com.huituopin.dsze.service.IDszeSupportService;
@Controller

@RequestMapping(value="/lp")
public class ILovingPeopleController extends OutPrint{
	@Autowired
	private IUserService userService ;
	
	@Autowired
	private IActivitySupportService activitySupportService;
	
	@Autowired
	private IActivityService activityService;
	
	@Autowired
	private IDszeSupportService dszeSupportService;
	
	@Autowired
	private IDszeService dszeService;
	
	@Autowired
	private IWechatService wechatService;
	
	@Autowired
	private IYjyaService yjyaService;
	
	@Autowired
	private IAiGouService aigouService;
	
	@Autowired
	private ICollectionService collectionService;
	/**
	 * 详细信息
	 * @return
	 */
	@RequestMapping(value="lpDetailInfo")
	public String lpDetailInfo(String userId,HttpServletRequest request){
		LovingPeople lovingPeople = userService.searchLpInfoByUserID(Integer.parseInt(userId.toString().trim()));
		request.setAttribute("lovingPeople",lovingPeople);
		return "wechat/jsp/user/lpDetailInfo";
	}
	/**
	 * 我的购物车
	 * @return
	 */
	@RequestMapping(value="shopCart")
	public ModelAndView getShopCart(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		model.setViewName("wechat/jsp/user/lovingPeople/shoppingCart");
		User user = (User)request.getSession().getAttribute("user");
		//User user = userService.getUserByUserId(159);
		List<Object> list = aigouService.getSCByUserId(user.getUserId());
		model.addObject("list", list);
		return model;
	}
	@RequestMapping(value="deleteSC")
	public void deleteSC(String scId,HttpServletResponse res){
		String result="";
		if(aigouService.deleteSCBySCId(Integer.valueOf(scId))){
			result="true";
		}else{
			result="fail";
		}
		write(result,res);
	}
	/**
	 * 收件信息
	 * @return
	 */
	@RequestMapping(value="lpReciveInfo")
	public String reciveInfo(String userId,HttpServletRequest request){
		String type = request.getParameter("type").toString().trim();
		List<ShippingAddress> list = userService.searchShipingAddressByUserId(Integer.parseInt(userId));
		request.setAttribute("shippingAddressList",list);
		request.setAttribute("type",type);	
		return "wechat/jsp/user/lpReciveInfo";
	}
	/**
	 * 我的捐助
	 * @return
	 */
	@RequestMapping(value="donate")
	public ModelAndView wantDonate(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/user/lovingPeople/donate");
		User user = (User)request.getSession().getAttribute("user");
		//我的捐助-->衣旧有爱
		List<Cloth> clothlist = yjyaService.getClothsByUserId(user.getUserId());
		mav.addObject("clothList", clothlist);
		//我的捐助-->滴水之恩
		List<DszeSupport> dszeSupportList =dszeSupportService.getDszeSupportByUserId(user.getUserId());
		Map<String,Object> map1 =null; 
		Dsze dsze =null;
		List<Map<String,Object>> list3=new ArrayList<Map<String,Object>>();
		if(dszeSupportList!=null){
			for(DszeSupport dszeSupport :dszeSupportList){
				dsze=dszeService.getDszeById(dszeSupport.getDszeId());
				map1=new HashMap<String,Object>();
				map1.put("dszeSupport", dszeSupport);
				map1.put("dsze", dsze);
				list3.add(map1);
			}
		}
		
		mav.addObject("dszeSupportList", list3);
		
		
		
		
		//我的捐助-->重大救助
		List<ActivitySupport> activitySupportList =activitySupportService.getActivitySupportByUserId(user.getUserId());
		Map<String,Object> map =null; 
		Activity activity =null;
		List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
		if(activitySupportList!=null){
			for(ActivitySupport activitySupport :activitySupportList){
				activity=activityService.getActivityById(activitySupport.getActivityId());
				map=new HashMap<String,Object>();
				map.put("activitySupport", activitySupport);
				map.put("activity", activity);
				list2.add(map);
			}
		}
		mav.addObject("activitySupportList", list2);
		
		return mav;
	}
	/**s
	 * 我的订单
	 * @return
	 */
	@RequestMapping(value="orders")
	public ModelAndView orders(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		model.setViewName("wechat/jsp/user/lovingPeople/orders");
		
		 /***微信支付所需参数**/
	    String url = request.getRequestURL().toString();
		if (request.getQueryString() != null){
			url += "?" + request.getQueryString();
		}
		Map<String,String> map = wechatService.getJsConfig(url);
		model.addObject("map", map);
	    /*****/
		
		
		User user = (User)request.getSession().getAttribute("user");
		//User user = userService.getUserByUserId(132);
		//获取全部订单
		List<HashMap<String,Object>> list = aigouService.getPOByUserIdAndStatus(user.getUserId(), (short)-1);
		model.addObject("list", list);
		//获取待付款的订单
		List<HashMap<String,Object>> list1 = aigouService.getPOByUserIdAndStatus(user.getUserId(), ProductOrderStatus.UNPAY);
		model.addObject("list1", list1);
		//获取待发货的订单
		List<HashMap<String,Object>> list2 = aigouService.getPOByUserIdAndStatus(user.getUserId(), ProductOrderStatus.PAIED);
		model.addObject("list2", list2);
		//获取待收货的订单
		List<HashMap<String,Object>> list3 = aigouService.getPOByUserIdAndStatus(user.getUserId(), ProductOrderStatus.ONPOST);
		model.addObject("list3", list3);
		//获取待评价的订单
		List<HashMap<String,Object>> list4 = aigouService.getPOByUserIdAndStatus(user.getUserId(), ProductOrderStatus.RECEPTION);
		model.addObject("list4", list4);
		return model;
	}
	@RequestMapping(value="cancelPO")
	public void cancelPO(String poId,HttpServletResponse response){
		String result="";
		if(aigouService.cancelOrder(Integer.valueOf(poId))){
			result="true";
		}else{
			result="fail";
		}
		write(result,response);
	}
	@RequestMapping(value="checkStock")
	public void checkStock(String poId,HttpServletResponse response){
		String result = aigouService.getStockInfoByPOId(Integer.valueOf(poId));
		write(result,response);
	}
	@RequestMapping(value="recept")
	public void receptDo(String poId,HttpServletResponse response){
		String result="";
		if(aigouService.updateProductOrderStatus(Integer.valueOf(poId), ProductOrderStatus.RECEPTION)){
			result="true";
		}else{
			result="fail";
		}
		
		write(result,response);
	}
	@RequestMapping(value="payment_cancel")
	public void paymentCancel_do(String poId,HttpServletResponse res){
		String result="";
		result = aigouService.payment_order_cancel(Integer.valueOf(poId));
		write(result,res);
	}
	@RequestMapping(value="rating")
	public ModelAndView rating(String podId,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		model.setViewName("wechat/jsp/user/lovingPeople/rating");
		String url = request.getRequestURL().toString();
		if (request.getQueryString() != null){
			url += "?" + request.getQueryString();
		}
		Map<String,String> map = wechatService.getJsConfig(url);
		model.addObject("appId", map.get("appId"));
		model.addObject("timestamp", map.get("timestamp"));
		model.addObject("noncestr", map.get("noncestr"));
		model.addObject("signature", map.get("signature"));
		List<Object> list = aigouService.getPODInfoByPODId(Integer.valueOf(podId));
		model.addObject("productInfo", list.get(0));
		return model;
	}
	@RequestMapping(value="submmitComment_do")
	public void submmitCommentDo(String pId,String podId,String detailInfo,String picPaths,HttpServletRequest request,HttpServletResponse response){
		String result="";
		User user = (User)request.getSession().getAttribute("user");
		//User user = userService.getUserByUserId(132);
		if(aigouService.insertProductComment(user.getUserId(),pId, podId, detailInfo, picPaths)){
			result="true";
		}else{
			result="fail";
		}
		write(result,response);
	}
	/**
	 * 待邮寄
	 * @return
	 */
	@RequestMapping(value="waitPost")
	public ModelAndView waitPost(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/user/lovingPeople/waitPost/waitPost");
		User user = (User)request.getSession().getAttribute("user");
		//User user = userService.getUserByUserId(44);
		//待发货
		List<Object> clothReceived = yjyaService.getOrderInfoByUserIDAndStatus(user.getUserId(),ClothStatus.RECEIVED);
		mav.addObject("clothReceived", clothReceived);
		//待收货
		List<Object> clothOnPost = yjyaService.getOrderInfoByUserIDAndStatus(user.getUserId(),ClothStatus.ONPOST);
		mav.addObject("clothOnPost", clothOnPost);
		//待评价
		List<Object> clothReception = yjyaService.getOrderInfoByUserIDAndStatus(user.getUserId(),ClothStatus.RECEPTION);
		mav.addObject("clothReception", clothReception);
		//已完成
		List<Object> clothCommented = yjyaService.getOrderInfoByUserIDAndStatus(user.getUserId(),ClothStatus.COMMENTED);
		mav.addObject("clothCommented", clothCommented);
		return mav;
	}
	/**
	 * 点击详情，跳转到收件人信息页
	 * @param saId
	 * @return
	 */
	@RequestMapping(value="receiverMsg")
	public ModelAndView receiverMsg(String saId){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/user/lovingPeople/waitPost/receiverMsg");
		ShippingAddress sa = userService.searchShipingAddressById(Integer.valueOf(saId));
		mav.addObject("sa", sa);
		return mav;
	}
	/**
	 * 点击发货，跳转到填写快递信息页面
	 * @param coId
	 * @return
	 */
	@RequestMapping(value="expressageMsg")
	public ModelAndView expressageMsg(String coId){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/user/lovingPeople/waitPost/expressageMsg");
		mav.addObject("coId", coId);
		return mav;
	}
	@RequestMapping(value="expressageMsg_do")
	public void  expressageMsg_do(String coId,String expressNameId,String expressNum,HttpServletResponse response){
		String result="";
		if(yjyaService.sendCloth(Integer.valueOf(coId), Short.valueOf(expressNameId), expressNum)){
			result = "true";
		}else{
			result = "fail";
		}
		write(result,response);
	}
	
	/**
	 * 我要捐助
	 * @return
	 */
	@RequestMapping(value="wantDonate")
	public ModelAndView donate(HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		ModelAndView model = new ModelAndView();
		model.setViewName("wechat/jsp/user/lovingPeople/wantDonate");

		String url = request.getRequestURL().toString();
		if (request.getQueryString() != null){
			url += "?" + request.getQueryString();
		}
		
		Map<String,String> map = wechatService.getJsConfig(url);
		model.addObject("appId", map.get("appId"));
		model.addObject("timestamp", map.get("timestamp"));
		model.addObject("noncestr", map.get("noncestr"));
		model.addObject("signature", map.get("signature"));
		model.addObject("jsapi_ticket", map.get("jsapi_ticket"));
		model.addObject("url", map.get("url"));
		model.addObject("userId", user.getUserId());
		
		//获取衣服类别信息
		List<ClothType> ctList = yjyaService.getAllClothType();
		String ctInfo="";
		if(ctList!=null){
			ctInfo = ctList.size()  + "|";
			for(ClothType clothType : ctList){
				ctInfo += clothType.getClothTypeId() + "├" + clothType.getClothTypeName() + "|";
			}
		}
		model.addObject("ctInfo", ctInfo);
		return model;
	}
	/**
	 * 捐赠衣物主方法
	 * @param userId
	 * @param clothName
	 * @param type1
	 * @param type2
	 * @param type3
	 * @param detailInfo
	 * @param picPaths
	 * @param request
	 * @param response
	 */
	
	@RequestMapping(value="donateCloth_do")
	public void donateCloth_do(String userId,String clothName,String type1,String type2,String type3,String detailInfo,String picPaths,HttpServletRequest request,HttpServletResponse response) {
		String result="";
		if(yjyaService.donateCloth(Integer.valueOf(userId), clothName, Short.valueOf(type1), Short.valueOf(type2), Short.valueOf(type3), detailInfo, picPaths)){
			result="true";
		}else{
			result="fail";
		}
		write(result, response);
	}
	/**
	 * 我收藏的商品
	 * @return
	 */
	@RequestMapping(value="goodCollect")
	public String goodCollect(String userId,HttpServletRequest request){
		List<Object> list =  collectionService.searchInfoByUserId(Integer.parseInt(userId));
		request.setAttribute("collectionList", list);
		System.out.println(list.toString());
		return "wechat/jsp/user/goodCollect";
	}
	/**
	 * 我的评论
	 * @return
	 */
	@RequestMapping(value="lpComment")
	public ModelAndView comment(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		model.setViewName("wechat/jsp/user/lovingPeople/lpComment");
		User user = (User)request.getSession().getAttribute("user");
		//User user = userService.getUserByUserId(132);
		List<HashMap<String,Object>> list = aigouService.getProductCommentByUserId(user.getUserId());
		model.addObject("list", list);
		return model;
	}
	/*---------------------------收件信息--------------------------------*/
	/**
	 * 收件信息-->添加地址
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/lpAddressAdd")
	public String lpAddressAdd(HttpServletRequest request){
		String type = request.getParameter("type").toString().trim();
		request.setAttribute("type", type);
		return "wechat/jsp/user/lpAddressAdd";
	}
	
	/**
	 * 收件信息-->添加地址-->确认添加操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/lpAddressAddAction")
	public String lpAddressAddAction(HttpServletRequest request){
		String type = request.getParameter("type").toString().trim();
		
		ShippingAddress shippingAddress = new ShippingAddress();
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
		userService.addShipingAddress(shippingAddress);
//		if(flag){
//			return "";
//		}else{
//			return null;
//		}
		return "redirect:/lp/lpReciveInfo?userId="+userId+"&type="+type;
	}
	
	
	/**
	 * 收件信息-->编辑地址
	 * @return
	 */
	@RequestMapping(value="/lpAddressEdit")
	public String ppAddressEdit(HttpServletRequest request,String shipId,String type){
		request.setAttribute("type", type);
		ShippingAddress shippingAddress = userService.searchShipingAddressById(Integer.parseInt(shipId.toString().trim()));
		request.setAttribute("shippingAddressList",shippingAddress);
		return "wechat/jsp/user/lpAddressEdit";
	}
	/**
	 * 收件信息--编辑地址--确认操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/lpAddressEditAction")
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
		return "redirect:/lp/lpReciveInfo?userId="+userId+"&type="+type;
		
	}
	/**
	 * 收件信息-->删除地址
	 * @return
	 */
	@RequestMapping(value="/lpAddressDelete")
	public String ppAddressDelete(String userId,String shipId,String type,HttpServletRequest request){
		if(type!="" || type !=null){
			request.setAttribute("type", type);
		}
		System.out.println("userid:"+userId+",shipid:"+shipId);
		userService.deleteShipingAddress(Integer.parseInt(shipId.toString().trim()));
		return "redirect:/lp/lpReciveInfo?userId="+userId+"&type="+type;
	}
	/**
	 * 把默认地址修改为普通地址
	 * @param shipId
	 * @return
	 */
	@RequestMapping(value="/deleteDefaultShipAddress")
	public String deleteDefaultShipAddress(String userId,String shipId,String type,HttpServletRequest request){
		userService.deleteDefaultShipAddress(Integer.parseInt(shipId.toString().trim()));
		return "redirect:/lp/lpReciveInfo?userId="+userId+"&type="+type;
	}
	/**
	 * 把普通地址改为默认地址
	 * @param userId
	 * @param shipId
	 * @return
	 */
	@RequestMapping(value="/changeShipAddressToDefault")
	public String changeShipAddressToDefault(String userId,String shipId,String type,HttpServletRequest request){
		userService.changeShipAddressToDefault(Integer.parseInt(userId), Integer.parseInt(shipId));
		return "redirect:/lp/lpReciveInfo?userId="+userId+"&type="+type;
	}
	
	
	
	
}

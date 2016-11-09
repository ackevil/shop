package com.huituopin.aigou.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.aigou.entity.Collection;
import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductType;
import com.huituopin.aigou.service.IAiGouService;
import com.huituopin.aigou.service.ICollectionService;
import com.huituopin.common.constants.UserType;
import com.huituopin.common.utils.OutPrint;
import com.huituopin.user.entity.ShippingAddress;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.wechat.core.service.IWechatService;

@Controller

@RequestMapping(value="/wechat/aigou")
public class AiGouController extends OutPrint{
	@Autowired
	IAiGouService aigouService;
	
	@Autowired
	ICollectionService collectionService;
	
	@Autowired
	private IWechatService wechatService;
	
	@Autowired
	IUserService userService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 默认控制器
	 * @return
	 */
	@RequestMapping()
	public ModelAndView index(String type,String keyword){
		ModelAndView model = new ModelAndView();
		model.setViewName("/wechat/jsp/aigou/index");
		//获取爱购商品类别信息
		List<ProductType> ptList = aigouService.getAllProductType();
		String ptInfo="";
		try {
			ptInfo = objectMapper.writeValueAsString(ptList);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addObject("ptInfo", ptInfo);
		//获取商品列表
		List<Object> productList;
		if(type==null && keyword == null){
			type="0";
		}
		/*if(start!=null && num!=null){
			productList = aigouService.getAllProduct(Integer.valueOf(type),keyword,Integer.valueOf(start),Integer.valueOf(num));
		}else{
			productList = aigouService.getAllProduct(Integer.valueOf(type),keyword,1,2);
		}*/
		productList = aigouService.getAllProduct(Integer.valueOf(type),keyword);
		String pInfoList="";
		try {
			pInfoList = objectMapper.writeValueAsString(productList);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addObject("pInfoList", pInfoList);
		model.addObject("type", type);
		model.addObject("keyword", keyword);
		return model;
	}
	/*@SuppressWarnings("unused")
	@RequestMapping("/getProduct_do")
	private void getProductDo(String type,String keyword,String start,String num,HttpServletResponse res){
		//获取商品列表
		List<Object> productList;
		if(type==null && keyword == null){
			type="0";
		}
		if(start!=null && num!=null){
			productList = aigouService.getAllProduct(Integer.valueOf(type),keyword,Integer.valueOf(start),Integer.valueOf(num));
		}else{
			productList = aigouService.getAllProduct(Integer.valueOf(type),keyword,0,1);
		}
		String pInfoList="";
		try {
			pInfoList = objectMapper.writeValueAsString(productList);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		write(pInfoList,res);
	}*/
	/**
	 * 商品详情
	 * @return
	 */
	@RequestMapping("/detail")
	public ModelAndView detail(String pId,HttpSession session){
		ModelAndView model = new ModelAndView();
		model.setViewName("/wechat/jsp/aigou/detail");
		Product product = aigouService.getProductById(Integer.valueOf(pId));
		/**检测用户类型**/
		String result="";
		User user = (User)session.getAttribute("user");
		if(user==null){
			String userWcId = (String) session.getAttribute("userWcId");
			user = userService.searchByWid(userWcId);
			if(user == null) {
				result = "null";
			}else{
				if(user.getUserType()==UserType.LOVINGPEOPLE)
					result="loving";
				else
					result="poor";
				session.setAttribute("user",user);
			}
		}else{
			if(user.getUserType()==UserType.LOVINGPEOPLE)
				result="loving";
			else
				result="poor";
		}
		model.addObject("userType", result);
		model.addObject("product", product);
		model.addObject("productRemain", product.getProductStock()-product.getProductSaleNum());
		return model;
	}
	/**
	 * 评价
	 * @return
	 */
	@RequestMapping("/rating")
	public ModelAndView rating(String pId){
		ModelAndView model = new ModelAndView();
		model.setViewName("/wechat/jsp/aigou/rating");
		List<HashMap<String,Object>> list = aigouService.getProductCommentByPId(Integer.valueOf(pId));
		model.addObject("list", list);
		return model;
	}
	
	/**
	 * 点击立即购买时，检测是否是我们的注册用户
	 */
	@RequestMapping("/checkUser")
	public void checkUser(HttpSession session,HttpServletResponse response){
		String result="";
		User user = (User)session.getAttribute("user");
		//User user = userService.getUserByUserId(183);
		if(user==null){
			String userWcId = (String) session.getAttribute("userWcId");
			user = userService.searchByWid(userWcId);
			if(user == null) {
				result = "null";
			}else{
				result="true";
				session.setAttribute("user",user);
			}
		}else{
			result="true"; 
		}
		
		write(result,response);
	}
	/**
	 * 确认订单-立即购买
	 * @return
	 */
	@RequestMapping("/payment")
	  public ModelAndView payment(String pId,String pNum,String allNum,String count,String resource,String credict,HttpSession session,String shippingAddId,HttpServletRequest request){
	    System.out.println("-------------------"+pId+"-------------------");
		ModelAndView model = new ModelAndView();
	    
	   
	    
	    /***微信支付所需参数**/
	    String url = request.getRequestURL().toString();
		if (request.getQueryString() != null){
			url += "?" + request.getQueryString();
		}
		Map<String,String> map = wechatService.getJsConfig(url);
		model.addObject("map", map);
	    /*****/
			    
	    model.setViewName("/wechat/jsp/aigou/payment");
	    User user = (User)session.getAttribute("user");
	    if(user==null){
			String userWcId = (String) session.getAttribute("userWcId");
			user = userService.searchByWid(userWcId);
			if(user == null) {
				return new ModelAndView("/register/signin");
			}else{
				session.setAttribute("user",user);
			}
		}
	    
	   // User user = userService.getUserByUserId(183);
	    
	    /*--第一次进来把参数存session里，下次点击地址返回的时候再从session里把值取出来,到最后点击确定的时候把session里对应的值删掉--*/
		if(pNum != null ){
			session.setAttribute("pNum", pNum);
			session.setAttribute("allNum",allNum );
			session.setAttribute("count",count );
			session.setAttribute("resource",resource );
			session.setAttribute("pId", pId);
			session.setAttribute("pCredict", credict);
			
		}else{
			pNum = (String) session.getAttribute("pNum");
			allNum = (String) session.getAttribute("allNum");
			count = (String) session.getAttribute("count");
			resource = (String) session.getAttribute("resource");
			pId = (String) session.getAttribute("pId");
			credict = (String) session.getAttribute("pCredict");
			
		}
		/*--第一次进来把参数存session里，下次点击地址返回的时候再从session里把值取出来,到最后点击确定的时候把session里对应的值删掉--*/
	    
	    //获取收货地址
	    ShippingAddress msa = null;
	    if(shippingAddId==null || "".equals(shippingAddId))
	      msa = userService.searchDefaultShippingAddressByUserId(user.getUserId());
	    else
	      msa = userService.searchShipingAddressById(Integer.parseInt(shippingAddId.toString().trim()));
	    model.addObject("msa",msa);
	    //获取商品信息
	    List<HashMap<String,Object>> list = aigouService.generatePOByPIdAndPNum(pId, pNum);
	    model.addObject("list", list);
	    model.addObject("allNum", allNum);
	    model.addObject("count", count);
	    model.addObject("pId", pId);
	    model.addObject("pNum", pNum);
	    model.addObject("resource", resource);
	    model.addObject("pCredict", credict);
	    model.addObject("uCredict", userService.getCredictByUserId(user.getUserId()));
	    
	    
	    return model;

	  }
	  @RequestMapping("/payment_do")
	  public void paymentDo(String saId,String pId,String pNum,String pCount,String resource,HttpServletResponse response,HttpSession session){
	    Integer result;
	    User user = (User)session.getAttribute("user");
	    //User user = userService.getUserByUserId(183);
	    result=aigouService.insertProductOrder(user.getUserId(), saId, pId, pNum, pCount,resource);
	    JSONObject jsonObject=new JSONObject();
	    jsonObject.put("code", result);	
	    	
	    write(jsonObject.toString(),response);
	  }
	  /**
	 * 完全兑换
	 */
	@RequestMapping("/duihuan_credictdo")
	public void duihuanCredict(String saId,String pId,String pPrice,String pRealCount,String pCredict,HttpSession session,HttpServletResponse res){
		int result;
		//User user = userService.getUserByUserId(183);
		User user = (User)session.getAttribute("user");
		result=aigouService.duihuan(user.getUserId(), saId, pId, pPrice, pRealCount, pCredict);
		JSONObject jsonObject=new JSONObject();
	    jsonObject.put("code", result);	
		write(jsonObject.toString(),res);
	}
	  
	 
	/**
	 * 确认兑换
	 */
	@RequestMapping("/duihuan_do")
	public void duihuan(String saId,String pId,String pPrice,String pRealCount,String pCredict,HttpSession session,HttpServletResponse res){
		int result;
		//User user = userService.getUserByUserId(183);
		User user = (User)session.getAttribute("user");
		result=aigouService.duihuan(user.getUserId(), saId, pId, pPrice, pRealCount, pCredict);
		JSONObject jsonObject=new JSONObject();
	    jsonObject.put("code", result);	
		write(jsonObject.toString(),res);
	}
	/**
	 * 加入购物车
	 */
	@RequestMapping("/putInShopCar")
	public void putInShopCar(String pId,String pNum,HttpServletResponse response,HttpSession session){
		String result="";
		User user = (User)session.getAttribute("user");
		if(user==null){
			String userWcId = (String) session.getAttribute("userWcId");
			user = userService.searchByWid(userWcId);
			if(user == null) {
				result = "null";
				write(result,response);
				return;
			}else{
				session.setAttribute("user",user);
			}
		}
		//User user = userService.getUserByUserId(132);
		if(aigouService.insertShoppingCart(user.getUserId(), Integer.valueOf(pId), Integer.valueOf(pNum))){
			result="true";
		}else{
			result="false";
		}
		write(result,response);
	}
	/**
	 * 收藏
	 * @return
	 */
	@RequestMapping("/addCollection")
	public void addCollection(HttpSession session,String productId,HttpServletResponse res){
		String result="";
		User user = (User)session.getAttribute("user");
		//User user = userService.getUserByUserId(144);
		if(user==null){
			String userWcId = (String) session.getAttribute("userWcId");
			user = userService.searchByWid(userWcId);
			if(user == null) {
				result = "null";
				write(result,res);
				return;
			}else{
				session.setAttribute("user",user);
			}
		}
		int userId = user.getUserId();
		int productID = Integer.parseInt(productId);
		Collection collection = collectionService.searchInfoByUserIdAndProductId(userId, productID);
		if(collection != null){
			result="fail";
		}else{
			collection = new Collection();
			collection.setUserId(userId);
			collection.setProductId(productID);
			collection.setCollectionIsDelete(false);
			collection.setCollectionDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"");
			collectionService.insertCollection(collection);
			result="true";
		}
		write(result,res);
	}
	@RequestMapping("/duihuan_payCancel")
	public void payCancel_do(String pId,HttpServletResponse res){
		String result="";
		result = aigouService.duihuan_paycancel(Integer.valueOf(pId));
		write(result,res);
	}
	@RequestMapping("/payment_cancel")
	public void paymentCancel_do(String pId,String pNum,HttpServletResponse res){
		String result="";
		result = aigouService.payment_cancel(pId,pNum);
		write(result,res);
	}
}

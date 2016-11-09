package com.huituopin.yjya.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.huituopin.common.constants.ClothStatus;
import com.huituopin.common.constants.UserType;
import com.huituopin.common.utils.GenerateOrderNumber;
import com.huituopin.common.utils.OutPrint;
import com.huituopin.user.entity.ShippingAddress;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.yjya.entity.ClothOrder;
import com.huituopin.yjya.entity.ClothPicture;
import com.huituopin.yjya.entity.ClothType;
import com.huituopin.yjya.service.IYjyaService;

@RequestMapping("/wechat/yjya")
@Controller
public class ClothController extends OutPrint{
	@Autowired
	private IYjyaService yjyaService;
	@Autowired
	private IUserService userService;
	
	/**
	 * 默认控制器  返回到主页页面
	 */
	
	@RequestMapping()
	public ModelAndView index(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/yijiuyouai/index");
		//获取衣服列表信息
		List<Cloth> list = yjyaService.getClothList();
		String clothInfo = "";
		String ctInfo = "";
		if(list!=null){
			clothInfo=list.size() + "|";
			for (Cloth cloth : list) {
				clothInfo += cloth.getClothId() + "├" + cloth.getClothMainpicPath() + "├" + cloth.getClothName() + "├" + cloth.getClothState() + "|";
			}
			//获取类别信息
			List<ClothType> ctList = yjyaService.getAllClothType();
			if(ctList!=null){
				ctInfo = ctList.size()  + "|";
				for(ClothType clothType : ctList){
					ctInfo += clothType.getClothTypeId() + "├" + clothType.getClothTypeName() + "|";
				}
			}
		}
		mav.addObject("clothInfo", clothInfo);
		mav.addObject("ctInfo", ctInfo);
		//检测一下用户类型，如果是爱心人士则在首页下方直接显示“我要捐赠”
		String userType=null;
		User user = (User)session.getAttribute("user");
		if(user!=null && user.getUserType() == UserType.LOVINGPEOPLE){
			userType="loving";
		}
		mav.addObject("userType", userType);
		return mav;
	}
	/**
	 * 类别切换时，刷新List
	 * @param type1 
	 * @param type2
	 * @param type3
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/refreshList_do",method = RequestMethod.POST)
	public void refreshList_do(String type1,String type2,String type3,HttpServletRequest request,HttpServletResponse response) {
		List<Cloth> list = yjyaService.getClothsByTypes(type1, type2, type3);
		String result = "";
		if(list ==  null){
			result = "null";
		}else{
			String clothInfo = list.size() + "|";
			for (Cloth cloth : list) {
				clothInfo += cloth.getClothId() + "├" + cloth.getClothMainpicPath() + "├" + cloth.getClothName() + "├" + cloth.getClothState() + "|";
			}
			result = clothInfo;
		}
		write(result,response);
	}
	/**
	 * 详情页
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
	public ModelAndView updateClothType(int id){
		Cloth cloth = yjyaService.getClothById(id);
		List<ClothPicture> detailPics = yjyaService.getDetailPicsByClothId(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/yijiuyouai/detail");
		mav.addObject("detailPics", detailPics);
		mav.addObject("cloth", cloth);
		
		return mav;
	}
	/**
	 * 评论
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/rating")
	public ModelAndView rating(String clothId){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/yijiuyouai/rating");
		Map<String, Object> map = yjyaService.getCommentInfoByClothId(Integer.valueOf(clothId));
		List<Object> commentInfoList = (List<Object>)map.get("commentInfoList");
		List<ClothPicture> commentPicList = (List<ClothPicture>)map.get("commentPicList");
		mav.addObject("commentInfoList", commentInfoList);
		mav.addObject("commentPicList", commentPicList);
		return mav;
	}
	/**
	 * 1.从session中获取user对象
		    判断得到的user对象是否为空，
		2.如果为空，获取该用户的微信id查看是否在我们的user中，如果在，则根据微信id获取该user对象，否则则提示该用户请先注册
	 * @param response
	 * @param session
	 */
	@RequestMapping("/checkUserType")
	public void checkUserType(HttpServletResponse response,HttpSession session) {
		User user = (User)session.getAttribute("user");
		//User user = userService.getUserByUserId(109);
		if(user==null){
			String userWcId = (String) session.getAttribute("userWcId");
			user = userService.searchByWid(userWcId);
			if(user == null) 
				write("请先注册！",response);
				return;
		}
		session.setAttribute("user",user);
		if(user.getUserType()==UserType.LOVINGPEOPLE)
			write("loving",response);
		else{
			if(yjyaService.isUpTheLimit(user.getUserId())){
				write("poor_no",response);
			}else{
				write("poor",response);
			}
		}
	}
	/**
	 * 确认领取页面
	 * @param clothId
	 * @param session
	 * @return
	 */
	@RequestMapping("/confirmReceive")
	public ModelAndView confirmReceive(String clothId,HttpSession session,String shippingAddId){
		User user = (User)session.getAttribute("user");
		//User user = userService.getUserByUserId(132);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wechat/jsp/yijiuyouai/confirmReceive");
		mav.addObject("user", user);
		//获取默认收货地址信息
		ShippingAddress msa =null;
		
		if(shippingAddId==null || "".equals(shippingAddId))
			msa = userService.searchDefaultShippingAddressByUserId(user.getUserId());
		else
			msa = userService.searchShipingAddressById(Integer.parseInt(shippingAddId.toString().trim()));
		
		mav.addObject("msa",msa);
		//获取衣物信息
		Cloth cloth = yjyaService.getClothById(Integer.valueOf(clothId));
		mav.addObject("cloth", cloth);
		//获取类别信息
		ClothType clothType = yjyaService.getClothTypeById(cloth.getClothType1());
		mav.addObject("clothType", clothType.getClothTypeName());
		return mav;
	}
	/**
	 * 生成领取订单
	 * @param clothId
	 * @param saId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/receiveCloth_do")
	public void receiveCloth_do(String clothId,String saId,HttpServletRequest request,HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");
		String result = "";
		result = yjyaService.receiveCloth(clothId, saId, user.getUserId());
		write(result,response);
	}
	//动态加载List使用
	@RequestMapping("/getClothData_do")
	public @ResponseBody Map<String,Object> getClothData_do(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		int start = Integer.valueOf(new String(request.getParameter("begin").getBytes("iso8859-1"),"utf-8"));
//		int end = Integer.valueOf(new String(request.getParameter("end").getBytes("iso8859-1"),"utf-8"));
		List<Cloth> list = yjyaService.getClothList();
		String clothInfo = list.size() + "|";
		for (Cloth cloth : list) {
			clothInfo += cloth.getClothId() + "├" + cloth.getClothMainpicPath() + "├" + cloth.getClothName() + "|";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("clothInfo", clothInfo);
		return map;
	}
	
	

}

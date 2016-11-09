package com.huituopin.admin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.aigou.entity.Logistics;
import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.aigou.entity.ProductType;
import com.huituopin.aigou.entity.SelectOption;
import com.huituopin.aigou.service.ICommentService;
import com.huituopin.aigou.service.ILogisticsService;
import com.huituopin.aigou.service.IOrderService;
import com.huituopin.aigou.service.IProductService;
import com.huituopin.common.constants.ProductOrderStatus;
import com.huituopin.common.utils.OutPrint;
import com.huituopin.common.utils.Page;
import com.huituopin.user.entity.ShippingAddress;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
@RequestMapping("/admin/aigou")
@Controller
public class AiGouAdminController extends OutPrint{
	
	@Autowired
	private IProductService productService;
	@Autowired
	private IOrderService orderService;
	@Autowired	
	private ILogisticsService logisticsService;
	@Autowired
	private ICommentService commentService;
	@Autowired
	private IUserService userService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	@RequestMapping("/goodsManager")
	public ModelAndView getAllCloths(Page page,Date productLaunchTime,Date productStopTime,Integer type,Integer state,String key){
		ModelAndView model = new ModelAndView();
		List<Product> list1=null;
		//List<Product> list2=null;
		//List<Product> list3=null;
		if(productLaunchTime!=null||productStopTime!=null||type!=null||key!=null){
			
			if(state==null){
				list1 = productService.getSellingProductByOptions(page,productLaunchTime,productStopTime,type,key);
			}else{
				if(state==1){
					list1 = productService.getSellingProductByOptions(page,productLaunchTime,productStopTime,type,key);
				}else if(state==2){
					list1 = productService.getSelledProductByOptions(page,productLaunchTime,productStopTime,type,key);
				}else{
					list1 = productService.getSellProductByOptions(page,productLaunchTime,productStopTime,type,key);
				}
			}
			
			
			
			
		}else{
			if(state==null){
				list1=productService.getSellingProduct(page);
			}else{
				if(state==1){
					list1=productService.getSellingProduct(page);
				}
				if(state==2){
					list1=productService.getSelledProduct(page);
				}
				if(state==3){
					list1=productService.getSellProduct(page);
				}
			}
			
		
		}
		
		//list = productService.getProduct(page);
		//List<Product> list1=productService.getSellingProduct(page);
		////List<Product> list2=productService.getSelledProduct(page);
		//List<Product> list3=productService.getSellProduct(page);
		List<ProductType> typeList= productService.getProductTypeList(null, new Page());
		
		model.setViewName("admin/aigou/goodsManager");
		model.addObject("productList1", list1);
		//model.addObject("productList2", list2);
		//model.addObject("productList3", list3);
		model.addObject("typeList", typeList);
		model.addObject("productLaunchTime",productLaunchTime);
		model.addObject("productStopTime",productStopTime);
		model.addObject("typeSelect",type);
		model.addObject("state",state);
		model.addObject("key",key);
		model.addObject("pages", page);
		
		return model;
	}
	@RequestMapping("/releaseGoods")
	public ModelAndView releaseGoods(){
		//获取产品类型
		List<ProductType> typeList= productService.getProductTypeList(null, new Page());
		
		
		ModelAndView model = new ModelAndView();
		model.addObject("typeList", typeList);
		model.setViewName("admin/aigou/releaseGoods");
		return model;
	}
////////////////////////////////产品类别 start///////////////////////////////////////////////////////////
	@RequestMapping("/goodCategory")
	public ModelAndView goodCategory(String txtKeywords,Page page, HttpServletRequest request, HttpServletResponse res){
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/aigou/goodCategory");
		List<ProductType> list = productService.getProductTypeList(txtKeywords, page);
		model.addObject("productTypeList", list);
		model.addObject("pages", page);
		model.addObject("txtKeywords", txtKeywords);
		return model;
	}
	@RequestMapping("/addProductType")
	public ModelAndView addGoodCategory(){
		return new ModelAndView("admin/aigou/addProductType");
	} 
	@RequestMapping("/addGoodCategory_do")
	public void addGoodCategoryDo(String name, HttpServletRequest request,HttpServletResponse response){
		ProductType pt = new ProductType(name);
		String reuslt = "";
		if(productService.insertProductType(pt)!=true){
		    reuslt = "fail";
		}else{
		    reuslt = "true";
		}
		write(reuslt, response);
	}
	@RequestMapping("updateGoodCategory")
	public ModelAndView updateGoodCategory(int id){
		ProductType productType = productService.getProductTypeById((short)id);
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/aigou/updateProductType");
		model.addObject("productType", productType);
		return model;
	}
	@RequestMapping("updateGoodCategory_do")
	public void updateGoodCategoryDo(String ptId,String name,String num,HttpServletResponse response){
		String result;
		ProductType pt = productService.getProductTypeById(Short.valueOf(ptId));
		pt.setProductTypeName(name);
		pt.setProductTypeNum(Integer.valueOf(num));
		if(productService.updateProductType(pt)){
			result = "true";
		}else{
			result = "fail";
		}
		write(result,response);
	}
	@RequestMapping("/deleteProductType_do")
	public void deleteProductTypeDo(String ptId,HttpServletRequest request,HttpServletResponse response){
		String result;
		if(productService.deleteProductTypeById(Short.valueOf(ptId))){
			result = "true";
		}else{
			result = "fail";
		}
		write(result,response);
	}
	@RequestMapping("/goodCategorysDelete")
	public void productTypesDelete(String productTypesIds,HttpServletResponse res){
		if(productService.deleteProductTypes(productTypesIds)){
			write("true",res);
		}else{
			write("fail",res);
		}
	}
///////////////////////产品类别 end //////////////////////////////////////////////////////
	@RequestMapping("/orderManager")
	public ModelAndView orderManager(Page page,SelectOption option){
		ModelAndView model = new ModelAndView();
		//List<LogisticsCompany> logisticsList=logisticsService.s
		List<ProductOrder> list=orderService.getAllOrder(option,page);
		HashMap<String, Object> map =null;
		List<HashMap<String,Object>> list1=new ArrayList<HashMap<String,Object>>();
		if(list!=null){
			for(ProductOrder productOrder :list){
				String orderNum= productOrder.getProductOrderNum();
				Integer userId=productOrder.getUserId();
				User user=userService.getUserByUserId(userId);
				ShippingAddress address=userService.searchDefaultShippingAddressByUserId(userId);
				map=new HashMap<String, Object>();
				List<Object> list2=orderService.getAllOrderProductByOrderNum(orderNum);
				map.put("po", productOrder);
				map.put("obejctList", list2);
				map.put("orderUser", user);
				map.put("address", address);
				list1.add(map);
			}
		}
		model.addObject("pages", page);
		model.addObject("option", option);
		model.addObject("list1", list1);
		
		
		
		
		model.setViewName("admin/aigou/orderManager");
		return model;
	}
	@RequestMapping("/commentManager")
	public ModelAndView commentManager(Page page){
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/aigou/commentManager");
		List<Object> list = commentService.getAllComment(page);
		model.addObject("list", list);
		model.addObject("num", page.maxRows);
		model.addObject("pages", page);
		return model;
	}
	/*----------------------------------------------------------------*/
	/**
	 * 进入物流工具
	 * @return
	 */
	@RequestMapping("/logisticsTool")
	public ModelAndView logisticsTool(){
		ModelAndView model = new ModelAndView();
		List<Logistics> logisticsList = logisticsService.searchAllData();
		model.addObject("logisticsList", logisticsList);
		model.setViewName("admin/aigou/logisticsTool");
		return model;
	}
	/**
	 * 添加物流工具
	 * @return
	 */
	@RequestMapping("/addLogisticsTool")
	public ModelAndView addLogisticsTool(){
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/aigou/addLogisticsTool");
		return model;
	}
	/**
	 * 添加物流工具 确认操作
	 * @param request
	 * @return
	 */
	@RequestMapping("/addLogisticsToolAction")
	public ModelAndView addLogisticsToolAction(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		Logistics logistics = new Logistics();
		logistics.setLogisticsName(request.getParameter("logisticsName").toString().trim());
		logistics.setLogisticsDatatime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"");
		logistics.setLogisticsAreas(request.getParameter("logisticsAreas").toString().trim());
		logistics.setLogisticsFirstcount(Integer.parseInt(request.getParameter("logisticsFirstcount").toString().trim()));
		logistics.setLogisticsFirstprice(Double.parseDouble(request.getParameter("logisticsFirstprice").toString().trim()));
		logistics.setLogisticsLastconut(Integer.parseInt(request.getParameter("logisticsLastconut").toString().trim()));
		logistics.setLogisticsLastprice(Double.parseDouble(request.getParameter("logisticsLastprice").toString().trim()));
		logistics.setLogisticsIsDelete(false);
		logisticsService.insertOneData(logistics);
		model.setViewName("redirect:/admin/aigou/logisticsTool");
		return model;
	}
	/**
	 * 删除一条物流信息
	 * @param logisticsId
	 * @return
	 */
	@RequestMapping("/deleteLogisticsTool")
	public ModelAndView deleteLogisticsTool(String logisticsId){
		ModelAndView model = new ModelAndView();
		logisticsService.deleteOneData(Integer.parseInt(logisticsId));
		model.setViewName("redirect:/admin/aigou/logisticsTool");
		return model;
	}
	/**
	 * 编辑物流信息
	 * @param logisticsId
	 * @return
	 */
	@RequestMapping("/editLogisticsTool")
	public ModelAndView editLogisticsTool(String logisticsId){
		ModelAndView model = new ModelAndView();
		Logistics logistics = logisticsService.searchDataById(Integer.parseInt(logisticsId));
		model.addObject("logistics", logistics);
		model.setViewName("admin/aigou/editLogisticsTool");
		return model;
	}
	/**
	 * 编辑物流信息  执行操作
	 * @param logisticsId
	 * @return
	 */
	@RequestMapping("/editLogisticsToolAction")
	public ModelAndView editLogisticsToolAction(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		String logisticsId = request.getParameter("logisticsId").toString().trim();
		System.out.println(logisticsId);
		Logistics logistics = logisticsService.searchDataById(Integer.parseInt(logisticsId));
		
		logistics.setLogisticsName(request.getParameter("logisticsName").toString().trim());
		logistics.setLogisticsDatatime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"");
		logistics.setLogisticsAreas(request.getParameter("logisticsAreas").toString().trim());
		logistics.setLogisticsFirstcount(Integer.parseInt(request.getParameter("logisticsFirstcount").toString().trim()));
		logistics.setLogisticsFirstprice(Double.parseDouble(request.getParameter("logisticsFirstprice").toString().trim()));
		logistics.setLogisticsLastconut(Integer.parseInt(request.getParameter("logisticsLastconut").toString().trim()));
		logistics.setLogisticsLastprice(Double.parseDouble(request.getParameter("logisticsLastprice").toString().trim()));
		logistics.setLogisticsIsDelete(false);
		
		logisticsService.updataOneData(logistics);
		model.setViewName("redirect:/admin/aigou/logisticsTool");
		return model;
	}
	
	
	
	/*----------------------------------------------------------------*/
	

	@RequestMapping("/saveGoods")
	@ResponseBody
	public String saveGoods(Product product){
		
		JSONObject jsonObject=new JSONObject();
		product.setProductIntime(new Date());
		if(productService.addProduct(product)){
			//添加成功
			ProductType type=productService.getProductTypeById(product.getProductCategoryId());
			type.setProductTypeNum(type.getProductTypeNum()+1);
			productService.updateProductType(type);
			jsonObject.put("error", 0);
			
		}else{
			jsonObject.put("error", 1);
		}
		
		 return jsonObject.toString();
		 
	}

	@RequestMapping("/goodsDelete")
	public void productsDelete(String productIds,HttpServletResponse res){
		if(productService.deleteProducts(productIds)){
			write("true",res);
		}else{
			write("fail",res);
		}
	}
	@RequestMapping("/goodsChange")
	public void productsChange(String productIds,HttpServletResponse res){
		if(productService.changeProducts(productIds)){
			write("true",res);
		}else{
			write("fail",res);
		}
	}
	
	@RequestMapping("/delGoods")
	public String delGoods(Integer id){
		
		Product product=productService.getProductById(id);
		if(product!=null){
			productService.delProduct(product);
		}
		return "redirect:/admin/aigou/goodsManager";
	}
	@RequestMapping("/editGoods")
	public String editGoods(Integer id,Model model){
		List<ProductType> typeList= productService.getProductTypeList(null, new Page());
		Product product=productService.getProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("typeList", typeList);
		return "admin/aigou/editGoods";
	}
	
	@RequestMapping("/updateGoods")
	@ResponseBody
	public String updateGoods(Product product){
		
		JSONObject jsonObject=new JSONObject();
		/*
		Product productOld=productService.getProductById(product.getProductId());
		if(product.getProductCategoryId()!=productOld.getProductCategoryId()){
			ProductType type1=productService.getProductTypeById(product.getProductCategoryId());
			ProductType type2=productService.getProductTypeById(productOld.getProductCategoryId());
			type1.setProductTypeNum(type1.getProductTypeNum()+1);
			type1.setProductTypeNum(type2.getProductTypeNum()-1);
			productService.updateProductType(type1);
			productService.updateProductType(type2);
		}
		*/
		if(productService.updateProduct(product)){
			jsonObject.put("error", 0);
			
		}else{
			jsonObject.put("error", 1);
		}
		
		 return jsonObject.toString();
		 
	}
	
	@RequestMapping("/changeState")
	public String changeState(Integer id){
		Product product=productService.getProductById(id);
		if(product.getProductOnsaleTimeType()==1){
			product.setProductOnsaleTimeType((short)3);
		}else if(product.getProductOnsaleTimeType()==3){
			product.setProductOnsaleTimeType((short)1);
		}
		productService.updateProduct(product);
		return "redirect:/admin/aigou/goodsManager";
	}
	
	@RequestMapping("/delOrder")
	public String delOrder(Integer id){
		ProductOrder productOrder=orderService.getProductOrderById(id);
		productOrder.setProductOrderIsDelete(true);
		if(orderService.update(productOrder))
		{
			return "redirect:/admin/aigou/orderManager";
		}
		return "error";
	}
	
	@RequestMapping("/releaseOrder")
	public String releaseOrder(Integer productOrderId ,boolean IsNeedLogistics,String logisticsCompanyName,String logisticsNum){
		
		ProductOrder productOrder=orderService.getProductOrderById(productOrderId);
		productOrder.setLogisticsCompanyName(logisticsCompanyName);
		productOrder.setProductOrderIsNeedLogistics(IsNeedLogistics);
		productOrder.setLogisticsNum(logisticsNum);
		productOrder.setProductOrderState(ProductOrderStatus.ONPOST);
		orderService.update(productOrder);
		return "redirect:/admin/aigou/orderManager";
	}
	
	
	
	
	
}

package com.huituopin.aigou.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.aigou.entity.ProductType;
import com.huituopin.aigou.service.IAiGouService;
import com.huituopin.aigou.service.IAiGouServicePC;
import com.huituopin.common.utils.OutPrint;

@Controller
@RequestMapping(value="/pc/aigou")
public class AiGouControllerPC extends OutPrint{

	@Autowired
	IAiGouService aigouService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private IAiGouServicePC aigouServicePC;
	
	@RequestMapping()
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pc/aigou");
		//获取商品类别信息
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
		mav.addObject("ptInfo", ptInfo);
		//获取爱购栏目总页数
		int pageNum = aigouServicePC.getProductPage(6);
		mav.addObject("pageNum", pageNum);
		//获取指定页数的商品
		List<Object> productList = aigouServicePC.getProductList(1,6,0);
		String productInfo = "";
		try {
			productInfo = objectMapper.writeValueAsString(productList);
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
		mav.addObject("productList", productInfo);
		return mav;
	}
	
	@RequestMapping("/refreshList_do")
	public void refresh(String pageNo,String ct1, HttpServletResponse response){
		if(pageNo == null){
			pageNo = "1";
			ct1 = "0";
		}
		List<Object> productList = aigouServicePC.getProductList(Integer.valueOf(pageNo), 6, Integer.valueOf(ct1));
		String productInfo = "";
		try {
			productInfo = objectMapper.writeValueAsString(productList);
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
		JSONObject jsonObject = new JSONObject();
		jsonObject.putOpt("productInfo", productInfo);
		int num = aigouServicePC.getProductNum(ct1);
		int pageNum_int = 0;
		pageNum_int = (int) Math.ceil(num*1.0/6);
		jsonObject.put("pageNum", pageNum_int);
		write(jsonObject.toString(), response);
	}
	
}

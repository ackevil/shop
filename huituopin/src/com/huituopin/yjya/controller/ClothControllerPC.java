package com.huituopin.yjya.controller;

import java.io.IOException;
import java.util.List;

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

import com.huituopin.common.utils.OutPrint;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.yjya.entity.ClothType;
import com.huituopin.yjya.service.IYjyaService;
import com.huituopin.yjya.service.IYjyaServicePC;

@RequestMapping("/pc/yjya")
@Controller
public class ClothControllerPC extends OutPrint{
	
	@Autowired
	IYjyaService yjyaService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private IYjyaServicePC yjyaServicePC;
	
	@RequestMapping()
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pc/yjya");
		List<ClothType> ctList = yjyaService.getAllClothType();
		String ctInfo = "";
		try {
			ctInfo=objectMapper.writeValueAsString(ctList);
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
		mav.addObject("ctInfo", ctInfo);
		//获取衣旧有爱栏目总页数信息
		int pageNum = yjyaServicePC.getClothPage(6);
		mav.addObject("pageNum", pageNum);
		//获取指定页数的衣物
		List<Cloth> clothList = yjyaServicePC.getClothList(1, 6, 0,0, 0);
		String clothInfo = "";
		try {
			clothInfo = objectMapper.writeValueAsString(clothList);
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
		mav.addObject("clothList", clothInfo);
		
		return mav;
	}
	
	@RequestMapping("/refreshList_do")
	public void refresh(String pageNo, String ct1, String ct2, String ct3, HttpServletResponse response){
		//获取指定页数的衣物列别
		if(pageNo == null){
			pageNo = "1";
			ct1 = "0";
			ct2 = "0";
			ct3 = "0";
		}
		List<Cloth> clothList = yjyaServicePC.getClothList(Integer.valueOf(pageNo), 6, Integer.valueOf(ct1), Integer.valueOf(ct2), Integer.valueOf(ct3));
		String clothInfo = "";
		try {
			clothInfo = objectMapper.writeValueAsString(clothList);
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
		jsonObject.putOpt("clothInfo", clothInfo);
		int num = yjyaServicePC.getClothNum(ct1, ct2, ct3);
		int pageNum_int = 0;
		pageNum_int = (int) Math.ceil(num*1.0/6);
		jsonObject.put("pageNum", pageNum_int);
		write(jsonObject.toString(), response);
	}
}

package com.huituopin.activity.controller;

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

import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.service.IActivityService;
import com.huituopin.activity.service.IActivityServicePC;
import com.huituopin.common.utils.OutPrint;


@Controller
@RequestMapping(value="/pc/zdjz")
public class ActivityControllerPC extends OutPrint{
	@Autowired
	IActivityService activityService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private IActivityServicePC activityServicePC;
	
	@RequestMapping()
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pc/zhongdajiuzhu");
		//获取重大救助项目总页数
		int pageNum = activityServicePC.getActivityPage(6);
		mav.addObject("pageNum", pageNum);
		//获取指定页码的项目列表
		List<Object> activityList = activityServicePC.getActivityList(1,6);
		String activityInfo = "";
		try {
			activityInfo = objectMapper.writeValueAsString(activityList);
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
		mav.addObject("activityList", activityInfo);
		return mav;
	}
	
	@RequestMapping("/refreshList_do")
	public void refresh(String pageNo, HttpServletResponse response){
		if(pageNo == null){
			pageNo = "1";
		}
		List<Object> activityList = activityServicePC.getActivityList(Integer.valueOf(pageNo), 6);
		String activityInfo = "";
		try {
			activityInfo = objectMapper.writeValueAsString(activityList);
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
		jsonObject.putOpt("activityList", activityInfo);
		int num = activityServicePC.getActivityPage(6);
		jsonObject.put("pageNum", num);
		write(jsonObject.toString(), response);
	}
}

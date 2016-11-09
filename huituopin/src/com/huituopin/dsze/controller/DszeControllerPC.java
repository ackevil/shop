package com.huituopin.dsze.controller;

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

import com.huituopin.dsze.service.IDszeServicePC;
import com.huituopin.common.utils.OutPrint;


@Controller
@RequestMapping(value="/pc/dsze")
public class DszeControllerPC extends OutPrint{

	@Autowired
	IDszeServicePC dszeServicePC;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping()
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pc/dishuizhien");
		//获取重大救助项目总页数
		int pageNum = dszeServicePC.getDszePage(6);
		mav.addObject("pageNum", pageNum);
		//获取指定页码的项目列表
		List<Object> dszeList = dszeServicePC.getDszeList(1,6);
		String dszeInfo = "";
		try {
			dszeInfo = objectMapper.writeValueAsString(dszeList);
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
		mav.addObject("dszeList", dszeInfo);
		return mav;
	}
	
	@RequestMapping("/refreshList_do")
	public void refresh(String pageNo, HttpServletResponse response){
		if(pageNo == null){
			pageNo = "1";
		}
		List<Object> dszeList = dszeServicePC.getDszeList(Integer.valueOf(pageNo), 6);
		String dszeInfo = "";
		try {
			dszeInfo = objectMapper.writeValueAsString(dszeList);
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
		jsonObject.putOpt("dszeList", dszeInfo);
		int num = dszeServicePC.getDszePage(6);
		jsonObject.put("pageNum", num);
		write(jsonObject.toString(), response);
	}
}

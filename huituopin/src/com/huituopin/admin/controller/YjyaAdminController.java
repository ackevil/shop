package com.huituopin.admin.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.common.constants.CommentStatus;
import com.huituopin.common.utils.OutPrint;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.entity.ClothType;
import com.huituopin.yjya.service.IYjyaService;
@RequestMapping("/admin/yjya")
@Controller
public class YjyaAdminController extends OutPrint{
	@Autowired
	private IYjyaService yjyaService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	@RequestMapping("/clothTypeList")
	public ModelAndView getAllClothTypes(String param,Page page, HttpServletRequest request, HttpServletResponse res){
		List<ClothType> list = yjyaService.getClothTypeList(param,page);
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/yjya/clothTypeList");
		model.addObject("clothTypeList", list);
		model.addObject("pages", page);
		model.addObject("txtKeywords", param);
		model.addObject("myparam", param);
		return model;
	}
	
	@RequestMapping("/clothTypesDelete")
	public void clothTypesDelete(String clothTypeIds,HttpServletResponse res){
		if(yjyaService.deleteClothTypes(clothTypeIds)){
			write("true",res);
		}else{
			write("fail",res);
		}
	}
	
	@RequestMapping("/addClothType")
	public ModelAndView addClothType(){
		return new ModelAndView("admin/yjya/addClothType");
	}
	@RequestMapping("/addClothType_do")
	public void addClothTypeDo(String name, HttpServletRequest request,HttpServletResponse response) {
		ClothType clothType = new ClothType(name);
		String reuslt = "";
		if(yjyaService.insertClothType(clothType)!=true){
		    reuslt = "fail";
		}else{
		    reuslt = "true";
		}
		write(reuslt, response);
	}
	
	@RequestMapping("/updateClothType")
	public ModelAndView updateClothType(int id){
		ClothType clothType = yjyaService.getClothTypeById((short) id);
		return new ModelAndView("admin/yjya/updateClothType","clothType",clothType);
	}
	@RequestMapping("/updateClothType_do")
	public @ResponseBody Map<String,Object> updateClothTypeDo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String clothTypeId = new String(request.getParameter("clothTypeId").getBytes("iso8859-1"),"utf-8");
		String clothTypeName = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
		String clothTypeNum = new String(request.getParameter("num").getBytes("iso8859-1"),"utf-8");
		Map<String,Object> map = new HashMap<String,Object>();
		ClothType clothType = yjyaService.getClothTypeById(Short.valueOf(clothTypeId));
		clothType.setClothTypeName(clothTypeName);
		clothType.setClothTypeNum(Integer.valueOf(clothTypeNum));
		if(yjyaService.updateClothType(clothType)==true){
			map.put("msg", "成功");
		}else{
			map.put("msg", "失敗");
		}
		return map;
	}
	/**
	 * 删除类别需谨慎，因为cloth表里参考了类别信息
	 * @param clothTypeId
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteClothType_do")
	public @ResponseBody Map<String,Object> deleteClothTypeDo(String clothTypeId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		if(yjyaService.deleteClothTypeById(Short.valueOf(clothTypeId))==true){
			map.put("msg", "成功");
		}else{
			map.put("msg", "失敗");
		}
		return map;
	}
	
	@RequestMapping("/commodityManager")
	public ModelAndView commodityManager(Page page,String param, HttpServletRequest request, HttpServletResponse res) throws ParseException{
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/yjya/commodityManager");
		String params[] = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date activityLaunchTime = null;
		Date activityStopTime = null;
		Integer type1 = null;
		Integer state = null;
		String key = null;
		if(param!=null && !param.equals(""))
		{
			params = param.split("\\+");
			if(!params[0].equals("")){
				activityLaunchTime = dateFormat.parse(params[0]);
			}
			if(!params[1].equals("")){
				activityStopTime = dateFormat.parse(params[1]);
			}
			type1 = Integer.valueOf(params[2]);
			state = Integer.valueOf(params[3]);
			if(params.length>4){
				key = params[4]; 
			}
		}
		List<Object> list = new ArrayList<Object>();
		if(activityLaunchTime!=null || activityStopTime!=null || type1 != null || state !=  null || key != null){
			list = yjyaService.getClothInfoList(page,activityLaunchTime,activityStopTime,type1,state,key);
		}else{
			list = yjyaService.getClothInfoList(page,null,null,0,5,null);
		}
		model.addObject("clothInfoList", list);
		//获取类别信息
		String ctInfo = "";
		List<ClothType> ctList = yjyaService.getAllClothType();
		if(ctList!=null){
			ctInfo = ctList.size()  + "|";
			for(ClothType clothType : ctList){
				ctInfo += clothType.getClothTypeId() + "├" + clothType.getClothTypeName() + "|";
			}
		}
		model.addObject("ctInfo", ctInfo);
		model.addObject("pages", page);
		if(activityLaunchTime!=null || activityStopTime!=null){
			model.addObject("activityLaunchTime", dateFormat.format(activityLaunchTime));
			model.addObject("activityStopTime", dateFormat.format(activityStopTime));
		}else{
			model.addObject("activityLaunchTime", activityLaunchTime);
			model.addObject("activityStopTime", activityStopTime);
		}
		model.addObject("type1", type1);
		model.addObject("state", state);
		model.addObject("keyWord", key);
		model.addObject("myparam", param);
		return model;
	}
	/**
	 * 衣物置顶
	 * @param clothId
	 * @param res
	 */
	@RequestMapping("/stick_do")
	public void stickDo(String clothId,HttpServletResponse res){
		String result="";
		if(yjyaService.stickCloth(Integer.valueOf(clothId))){
			result="true";
		}else{
			result="false";
		}
		write(result,res);
	}
	@RequestMapping("/deleteCloths")
	public void deleteCloths(String clothIds,HttpServletResponse res){
		if(yjyaService.deleteClothById(clothIds)){
			write("true",res);
		}else{
			write("fail",res);
		}
	}
	/**
	 * 评价详情页
	 * @param clothId
	 * @param request
	 * @param res
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/comment")
	public ModelAndView commentManager(String clothId,HttpServletRequest request, HttpServletResponse res){
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/yjya/comment");
		Map<String, Object> map = yjyaService.getCommentInfoByClothId(Integer.valueOf(clothId));
		List<Object> commentInfoList = (List<Object>)map.get("commentInfoList");
		if(commentInfoList!=null){
			model.addObject("clothInfo", commentInfoList.get(0));
		}else{
			model.addObject("clothInfo", commentInfoList);
		}
		return model;
	}
	
	@RequestMapping("/clothCategory")
	public ModelAndView clothCategory(Page page, HttpServletRequest request, HttpServletResponse res){
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/yjya/clothCategory");
		List<ClothType> list = yjyaService.getClothTypeList("",page);
		model.addObject("clothTypeList", list);
		model.addObject("pages", page);
		return model;
	}
	
	
	@RequestMapping("/commentManager")
	public ModelAndView commentManager(Page page, HttpServletRequest request, HttpServletResponse res){
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/yjya/commentManager");
		List<Object> list = yjyaService.getCommentInfoList(page);
		model.addObject("commentInfoList", list);
		int getNum;
		if(list!=null){
			getNum = list.size();
		}else{
			getNum = 0;
		}
		model.addObject("getNum", getNum);
		model.addObject("pages", page);
		return model;
	}
	@RequestMapping("/setRule")
	public ModelAndView setRule(){
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/yjya/setRule");
		Map<String,Object> map = yjyaService.getRule();
		model.addObject("limitNum", map.get("1"));
		model.addObject("shareCredict", map.get("2"));
		model.addObject("receptedCredict", map.get("3"));
		return model;
	}
	@RequestMapping("/updateRule_do")
	public void updateRule_do(String id,String value,HttpServletResponse response){
		String result="";
		if(yjyaService.updateRule(Integer.valueOf(id), Integer.valueOf(value))){
			result="true";
		}else{
			result="fail";
		}
		write(result,response);
	
	}
	
}

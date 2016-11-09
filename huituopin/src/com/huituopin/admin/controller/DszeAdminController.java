package com.huituopin.admin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.dsze.service.IDszeService;
import com.huituopin.activity.entity.Activity;
import com.huituopin.common.utils.Page;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
@RequestMapping("/admin/dsze")
@Controller
public class DszeAdminController {
	@Autowired
	private IDszeService dszeService;
	
	@Autowired
	private IUserService userService;
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	
	
	
	
	@RequestMapping("/dszeAllList")
	public ModelAndView getAllActivities(){
		
		List<Dsze> list= dszeService.getAllDszeList();
		
		return new ModelAndView("admin/dsze/dszeList","dszeList",list);
		//return "admin/dsze/dszeList";
	}
	
	@RequestMapping("/editDsze")
	public ModelAndView editDsze(int id){
		Dsze dsze=dszeService.getDszeById(id);
		return new ModelAndView("admin/dsze/editDsze","dsze",dsze);
		//return "admin/dsze/dszeList";
	}
	@RequestMapping("/addDsze")
	public ModelAndView addDsze(){
		return new ModelAndView("admin/dsze/editDsze");
		//return "admin/dsze/dszeList";
	}
	
	@RequestMapping("/saveDsze")
	public String saveDsze(Dsze dsze){
		
		if(dsze.getDszeId()==null){
			dsze.setDszeRaised(0);
			dsze.setDszeSuportNum(0);
			dsze.setDszeIntime(new Date());
			dszeService.addDsze(dsze);
		}else{
			dszeService.updateDsze(dsze);
		}
		
		
		return "redirect:/admin/dsze/dszeList";
	}
	
	@RequestMapping("/delDsze")
	public String delDsze(int id){
		Dsze dsze=dszeService.getDszeById(id);
		dszeService.delDsze(dsze);
		return "redirect:/admin/dsze/dszeList";
	}
	
	
	
	@RequestMapping("/zhidingDsze")
	public String zhiding(int id){
		
		Dsze dsze=dszeService.getDszeById(id);
		if(dsze.getDszeIsOrder()){
			dsze.setDszeIsOrder(false);
			dsze.setDszeOrderTime(null);
		}else{
			dsze.setDszeIsOrder(true);
			Date date=new Date();
			dsze.setDszeOrderTime(date);
		}
		
		
		dszeService.updateDsze(dsze);
		return "redirect:/admin/dsze/dszeList";
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/dszeList")
	public ModelAndView getAllDsze(Page page,Date dszeLaunchTime,Date dszeStopTime,Integer state,String key, HttpServletRequest request, HttpServletResponse res){
		List<Dsze> list=null;
		if(dszeLaunchTime!=null||dszeStopTime!=null||state!=null||key!=null){
			list = dszeService.getDszeByOptions(page,dszeLaunchTime,dszeStopTime,state,key);
			
		}else{
			list = dszeService.getDsze(page);
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/dsze/dszeList");
		model.addObject("dszeList", list);
		model.addObject("pages", page);
		model.addObject("dszeLaunchTime", dszeLaunchTime);
		model.addObject("dszeStopTime", dszeStopTime);
		model.addObject("state", state);
		model.addObject("key", key);
		
		return model;
	}
	
	@RequestMapping("/dszeSupportList")
	public String getSupportList( int id,Page page,Model model){
		List<DszeSupport> list=  dszeService.getDszeSupportByDszeId(id,page);
		if(list!=null){
		Map<String,Object> map =null; 
		User user=null;
		List<Map> list2=new ArrayList<Map>();
		for(DszeSupport dszeSupport :list){
			user=userService.getUserByUserId(dszeSupport.getUserId());
			map=new HashMap<String,Object>();
			map.put("dszeSupport", dszeSupport);
			map.put("user", user);
			list2.add(map);
		}
		model.addAttribute("list",list2);
		
		}
		model.addAttribute("pages", page);
		return "admin/dsze/dszeSupport";
	}
	/**
	 *  按条件筛选   
	 *
	 */
	
	
}

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.activity.service.IActivityService;
import com.huituopin.common.utils.Page;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.yjya.entity.ClothType;
@RequestMapping("/admin/zdjz")
@Controller
public class ActivityAdminController {
	@Autowired
	private IActivityService activityService;
	
	@Autowired
	private IUserService userService;
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	
	
	
	
	@RequestMapping("/activityAllList")
	public ModelAndView getAllActivities(){
		
		List<Activity> list= activityService.getAllActivityList();
		
		return new ModelAndView("admin/activity/activityList","activityList",list);
		//return "admin/activity/activityList";
	}
	
	@RequestMapping("/editActivity")
	public ModelAndView editActivity(int id){
		Activity activity=activityService.getActivityById(id);
		return new ModelAndView("admin/activity/editActivity","activity",activity);
		//return "admin/activity/activityList";
	}
	@RequestMapping("/addActivity")
	public ModelAndView addActivity(){
		return new ModelAndView("admin/activity/editActivity");
		//return "admin/activity/activityList";
	}
	
	@RequestMapping("/saveActivity")
	public String saveActivity(Activity activity){
		
		if(activity.getActivityId()==null){
			activity.setActivityRaised(0);
			activity.setActivitySuportNum(0);
			activity.setActivityIntime(new Date());
			activityService.addActivity(activity);
		}else{
			activityService.updateActivity(activity);
		}
		
		
		return "redirect:/admin/zdjz/activityList";
	}
	
	@RequestMapping("/delActivity")
	public String delActivity(int id){
		Activity activity=activityService.getActivityById(id);
		activityService.delActivity(activity);
		return "redirect:/admin/zdjz/activityList";
	}
	
	
	@RequestMapping("/activityList")
	public ModelAndView getAllActivity(Page page,Date activityLaunchTime,Date activityStopTime,Integer state,String key, HttpServletRequest request, HttpServletResponse res){
		List<Activity> list=null;
		if(activityLaunchTime!=null||activityStopTime!=null||state!=null||key!=null){
			list = activityService.getActivityByOptions(page,activityLaunchTime,activityStopTime,state,key);
			
		}else{
			list = activityService.getActivity(page);
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/activity/activityList");
		model.addObject("activityList", list);
		model.addObject("pages", page);
		model.addObject("activityLaunchTime", activityLaunchTime);
		model.addObject("activityStopTime", activityStopTime);
		model.addObject("state", state);
		model.addObject("key", key);
		
		return model;
	}
	
	
	@RequestMapping("/zhidingActivity")
	public String zhiding(int id){
		
		Activity activity=activityService.getActivityById(id);
		if(activity.getActivityIsOrder()){
			activity.setActivityIsOrder(false);
			activity.setActivityOrderTime(null);
		}else{
			activity.setActivityIsOrder(true);
			Date date=new Date();
			activity.setActivityOrderTime(date);
		}
		
		
		activityService.updateActivity(activity);
		return "redirect:/admin/zdjz/activityList";
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/activitySupportList")
	public String getSupportList( int id,Page page,Model model){
		List<ActivitySupport> list=  activityService.getActivitySupportByActivityId(id,page);
		if(list!=null){
		Map<String,Object> map =null; 
		User user=null;
		List<Map> list2=new ArrayList<Map>();
		for(ActivitySupport activitySupport :list){
			user=userService.getUserByUserId(activitySupport.getUserId());
			map=new HashMap<String,Object>();
			map.put("activitySupport", activitySupport);
			map.put("user", user);
			list2.add(map);
		}
		model.addAttribute("list",list2);
		
		}
		model.addAttribute("pages", page);
		return "admin/activity/activitySupport";
	}
	/**
	 *  按条件筛选   
	 *
	 */
	
	
}

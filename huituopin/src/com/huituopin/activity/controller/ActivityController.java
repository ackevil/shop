package com.huituopin.activity.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.activity.entity.ActivityComment;
import com.huituopin.activity.service.IActivityCommentService;
import com.huituopin.activity.service.IActivityService;
import com.huituopin.activity.service.IActivitySupportService;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.wechat.core.service.IWechatService;

@RequestMapping("/zdjz")
@Controller
public class ActivityController {
	
	@Autowired
	private IActivityService activityService;
	
	@Autowired
	private IActivityCommentService activityCommentService;
	
	@Autowired
	private IActivitySupportService activitySupportService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IWechatService wechatService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping("/signficant")
	public ModelAndView signficant(){
		
		List<Activity> list =activityService.getAllActivityList();
		ModelAndView model=new ModelAndView();
		model.setViewName("wechat/jsp/zhongdajiuzhu/signficant");
		model.addObject("activityList", list);
		//model.addObject("pages", page);
		return model;
	}
	@RequestMapping("/signficant_sub")
	public ModelAndView signficant_sub(int id){
Activity activity=activityService.getActivityById(id);
		
		List<ActivitySupport> list =activitySupportService.getActivitySupportByActivityId(id);
		
		
		Date stop=activity.getActivityStopTime();
		Date now=new Date();
		int day= (int)((stop.getTime()-now.getTime())/(24*60*60*1000)); 
		if(day<=0){
			day=0;
		}
		
		
		ModelAndView model=new ModelAndView();
		if(list!=null){
			Map<String,Object> map =null; 
			User user=null;
			List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
			for(ActivitySupport activitySupport :list){
				user=userService.getUserByUserId(activitySupport.getUserId());
				map=new HashMap<String,Object>();
				map.put("activitySupport", activitySupport);
				map.put("user", user);
				list2.add(map);
			}
			model.addObject("list",list2);
		}
		model.addObject("activity", activity);
		model.addObject("day", day);
		model.setViewName("wechat/jsp/zhongdajiuzhu/signficant_sub");
		return model;
	}
	@RequestMapping("/detail")
	public ModelAndView detail(int id){
		
		Activity activity=activityService.getActivityById(id);
		
		Date stop=activity.getActivityStopTime();
		Date now=new Date();
		int day= (int)((stop.getTime()-now.getTime())/(24*60*60*1000)); 
		if(day<=0){
			day=0;
		}
		
		ModelAndView model=new ModelAndView();
		model.addObject("day", day);
		model.addObject("activity", activity);
		model.setViewName("wechat/jsp/zhongdajiuzhu/detail");
		return model;
	}
   @RequestMapping("/rating")
    public ModelAndView rating(int id,HttpServletRequest request,HttpServletResponse response){
	   
	   /**
	    * 判断是否注册
	    */
	   if(request.getSession().getAttribute("user")==null){
		    ModelAndView model1=new ModelAndView();
	        model1.setViewName("wechat/jsp/user/register");
	        return model1;
	   }
	   
	   
	   
	   
	   	Activity activity=activityService.getActivityById(id);
        List<ActivityComment> comments = activityService.getActivityComments(id);
        String commentsStr = "";
        try {
        	objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            commentsStr =  objectMapper.writeValueAsString(comments);
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
        ModelAndView model=new ModelAndView();
        model.addObject("comments", commentsStr);
        model.addObject("activity", activity);
        model.setViewName("wechat/jsp/zhongdajiuzhu/rating");
        
        return model;
    }
	@RequestMapping("/support")
	public ModelAndView support(int id,HttpServletRequest request){
		
		/**
		    * 判断是否注册
		    */
		   if(request.getSession().getAttribute("user")==null){
			    ModelAndView model1=new ModelAndView();
		        model1.setViewName("wechat/jsp/user/register");
		        return model1;
		   }
		
		
		
		String url = request.getRequestURL().toString();
		if (request.getQueryString() != null){
			url += "?" + request.getQueryString();
		}
		Map<String,String> map = wechatService.getJsConfig(url);
		Activity activity=activityService.getActivityById(id);
		
		ModelAndView model=new ModelAndView();
		model.setViewName("wechat/jsp/zhongdajiuzhu/support");
		model.addObject("activity", activity);
		model.addObject("map", map);
		return model;
	}
	
	@RequestMapping("/saveComment")
	public ModelAndView saveComment(ActivityComment activityComment){
		activityComment.setActCommentIntime(new Date());
		activityCommentService.addActivityComment(activityComment);
		int id =activityComment.getActivityId();
		Activity activity=activityService.getActivityById(id);
        List<ActivityComment> comments = activityService.getActivityComments(id);
        String commentsStr = "";
        try {
        	objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            commentsStr =  objectMapper.writeValueAsString(comments);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView model=new ModelAndView();
        model.addObject("comments", commentsStr);
        model.addObject("activity", activity);
        model.setViewName("wechat/jsp/zhongdajiuzhu/rating");
        return model;
		
	}
	
	
	
}

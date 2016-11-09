package com.huituopin.dsze.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeComment;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.dsze.service.IDszeCommentService;
import com.huituopin.dsze.service.IDszeService;
import com.huituopin.dsze.service.IDszeSupportService;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;
import com.huituopin.wechat.core.service.IWechatService;

@RequestMapping("/dsze")
@Controller
public class DszeController {
	
	@Autowired
	private IDszeService dszeService;
	
	@Autowired
	private IDszeCommentService dszeCommentService;
	
	@Autowired
	private IDszeSupportService dszeSupportService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IWechatService wechatService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping("/dsze")
	public ModelAndView dsze(){
		
		List<Dsze> list =dszeService.getAllDszeList();
		ModelAndView model=new ModelAndView();
		model.setViewName("wechat/jsp/dishuizhien/dsze");
		model.addObject("dszeList", list);
		//model.addObject("pages", page);
		return model;
	}
	@RequestMapping("/dsze_sub")
	public ModelAndView dsze_sub(int id){
Dsze dsze=dszeService.getDszeById(id);
		
		List<DszeSupport> list =dszeSupportService.getDszeSupportByDszeId(id);
		
		Date stop=dsze.getDszeStopTime();
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
			for(DszeSupport dszeSupport :list){
				user=userService.getUserByUserId(dszeSupport.getUserId());
				map=new HashMap<String,Object>();
				map.put("dszeSupport", dszeSupport);
				map.put("user", user);
				list2.add(map);
			}
			model.addObject("list",list2);
		}
		model.addObject("dsze", dsze);
		model.addObject("day", day);
		model.setViewName("wechat/jsp/dishuizhien/dsze_sub");
		return model;
	}
	@RequestMapping("/detail")
	public ModelAndView detail(int id){
		Dsze dsze=dszeService.getDszeById(id);
		
		Date stop=dsze.getDszeStopTime();
		Date now=new Date();
		int day= (int)((stop.getTime()-now.getTime())/(24*60*60*1000)); 
		if(day<=0){
			day=0;
		}
		ModelAndView model=new ModelAndView();
		model.addObject("day", day);
		model.addObject("dsze", dsze);
		model.setViewName("wechat/jsp/dishuizhien/detail");
		return model;
	}
   @RequestMapping("/rating")
    public ModelAndView rating(int id,HttpServletRequest request){
	   
	   /**
	    * 判断是否注册
	    */
	   if(request.getSession().getAttribute("user")==null){
		    ModelAndView model1=new ModelAndView();
	        model1.setViewName("wechat/jsp/user/register");
	        return model1;
	   }
	   
	   	Dsze dsze=dszeService.getDszeById(id);
        List<DszeComment> comments = dszeService.getDszeComments(id);
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
        model.addObject("dsze", dsze);
        model.setViewName("wechat/jsp/dishuizhien/rating");
        
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
		Dsze dsze=dszeService.getDszeById(id);
		
		ModelAndView model=new ModelAndView();
		model.setViewName("wechat/jsp/dishuizhien/support");
		model.addObject("dsze", dsze);
		model.addObject("map", map);
		return model;
	}
	
	@RequestMapping("/saveComment")
	public ModelAndView saveComment(DszeComment dszeComment){
		dszeComment.setDszeCommentIntime(new Date());
		dszeCommentService.addDszeComment(dszeComment);
		int id =dszeComment.getDszeId();
		Dsze dsze=dszeService.getDszeById(id);
        List<DszeComment> comments = dszeService.getDszeComments(id);
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
        model.addObject("dsze", dsze);
        
        model.setViewName("wechat/jsp/dishuizhien/rating");
        return model;
		
	}
	
	
	
}

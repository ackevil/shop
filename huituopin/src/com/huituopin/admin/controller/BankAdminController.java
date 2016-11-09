package com.huituopin.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huituopin.bank.entity.Credicts;
import com.huituopin.bank.service.ICredictsService;
import com.huituopin.common.utils.Page;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IAdminuserService;
import com.huituopin.user.service.IUserService;
import com.huituopin.yjya.entity.Rule;
import com.huituopin.yjya.service.IYjyaService;
@RequestMapping("/admin/bank")
@Controller
public class BankAdminController {
	@Autowired
	public  IAdminuserService adminuserService;
	@Autowired
	public ICredictsService credictsService;
	@Autowired
	public IYjyaService iYjyaService;
	@Autowired
	public IUserService userService;
	
	/**
	 * 查看所有爱心人士
	 * @param request
	 * @return
	 */
	@RequestMapping("/lovingBankList")
	public ModelAndView lovingBankList(String param,Page page) throws ParseException{
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/bank/lovingBankList");
		String params[] = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date activityLaunchTime = null;
		Date activityStopTime = null;
		Integer gender = null;
		Integer state = null;
		String keyWord = null;
		if(param!=null && !param.equals(""))
		{
			params = param.split("\\+");
			if(!params[0].equals("")){
				activityLaunchTime = dateFormat.parse(params[0]);
			}
			if(!params[1].equals("")){
				activityStopTime = dateFormat.parse(params[1]);
			}
			gender = Integer.valueOf(params[2]);
			state = Integer.valueOf(params[3]);
			if(params.length>4){
				keyWord = params[4]; 
			}
		}
		List<Object> userList = new ArrayList<Object>();
		if(activityLaunchTime!=null || activityStopTime!=null || gender != null || state !=  null || keyWord != null){
			userList = adminuserService.searchAllLovingPeopleInfo(page,activityLaunchTime,activityStopTime,gender,state,keyWord);
		}else{
			userList = adminuserService.searchAllLovingPeopleInfo(page,null,null,3,0,null);
		}
		model.addObject("userList", userList);
		model.addObject("count", page.maxRows);
		model.addObject("pages", page);
		if(activityLaunchTime==null || activityLaunchTime==null){
			model.addObject("activityLaunchTime", activityLaunchTime);
			model.addObject("activityStopTime", activityStopTime);
		}else{
			model.addObject("activityLaunchTime", dateFormat.format(activityLaunchTime));
			model.addObject("activityStopTime", dateFormat.format(activityStopTime));
		}
		model.addObject("gender", gender);
		model.addObject("state", state);
		model.addObject("keyWord", keyWord);
		model.addObject("myparam", param);
		return model;
	}
	
	/**
	 *查询一个爱心人士积分记录 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/huiYanJiFenList")
	public ModelAndView huiYanJiFenList(String userId){
		ModelAndView model = new ModelAndView();
		List<Credicts> Credictlist = credictsService.searchInfoByUserId(Integer.parseInt(userId));
		List<Credicts> CredictlistIn = credictsService.searchInfoByType(Integer.parseInt(userId), 0);
		List<Credicts> CredictlistOut = credictsService.searchInfoByType(Integer.parseInt(userId), 1);
		User user = userService.getUserByUserId(Integer.parseInt(userId));
		LovingPeople lovingPeople = userService.searchLpInfoByUserID(Integer.parseInt(userId));
		model.addObject("user", user);
		model.addObject("lovingPeople", lovingPeople);
		model.addObject("Credictlist", Credictlist);
		model.addObject("CredictlistOut", CredictlistOut);
		model.addObject("CredictlistIn", CredictlistIn);
		model.setViewName("admin/bank/huiYanJiFenList");
		return model;
	}
	
	@RequestMapping("/setRule")
	public ModelAndView setRule(){
		ModelAndView model = new ModelAndView();
		List<Rule> rules =  iYjyaService.getCridictsRule();
		for(Rule rule :rules){
			if(rule.getRuleId() != 1)
				model.addObject("rule"+rule.getRuleId(), rule);
		}
		model.setViewName("admin/bank/setRule");
		return model;
	}
	
	@RequestMapping("/changeRule")
	public @ResponseBody 
	boolean changeRule(String ruleId,String goodNum){
		return iYjyaService.updateRule(Integer.parseInt(ruleId),Integer.parseInt(goodNum));
	}
}

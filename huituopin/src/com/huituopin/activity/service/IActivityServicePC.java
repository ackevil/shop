package com.huituopin.activity.service;

import java.util.List;

import com.huituopin.activity.entity.Activity;

public interface IActivityServicePC {
	
	/**
	 * 获取重大救助项目总页数，每页pageSize个项目
	 * @param pageSize
	 * @return
	 */
	public int getActivityPage(int pageSize);
	
	/**
	 * 获取指定页数的项目列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Object> getActivityList(int pageNo,int pageSize);

}

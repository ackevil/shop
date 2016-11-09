package com.huituopin.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.activity.dao.IActivityDao;
import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.service.IActivityServicePC;
import com.huituopin.common.utils.Page;

@Transactional(readOnly=false)
@Service
public class ActivityServicePCImpl implements IActivityServicePC {

	@Autowired
	private IActivityDao activityDao;
	
	@Override
	public int getActivityPage(int pageSize) {
		int num = 0;
		List<Activity> list = activityDao.getAllActivityList();
		if(list != null){
			num = (int) Math.ceil(list.size()*1.0/pageSize);
		}
		return num;
	}

	@Override
	public List<Object> getActivityList(int pageNo, int pageSize) {
		Page page = new Page();
		page.pageSizes = pageSize;
		page.page = pageNo;
		return activityDao.getActivityListPC(page);

	}

}

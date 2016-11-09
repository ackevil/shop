package com.huituopin.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.activity.dao.IActivitySupportDao;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.activity.service.IActivitySupportService;

@Service
@Transactional(readOnly=false)
public class ActivitySupportServiceImpl implements IActivitySupportService {

	@Autowired
	private IActivitySupportDao activitySupportDao;
	
	@Override
	public boolean addActivitySupport(ActivitySupport activitySupport) {
		
		return activitySupportDao.addActivitySupport(activitySupport);
	}

	@Override
	public boolean checkOrder(String actSupOrder) {
		return activitySupportDao.checkOrder(actSupOrder);
	}

	@Override
	public List<ActivitySupport> getActivitySupportByUserId(int userId) {
		return activitySupportDao.getActivitySupportByUserId(userId);
	}

	@Override
	public List<ActivitySupport> getActivitySupportByActivityId(int id) {
		// TODO Auto-generated method stub
		return activitySupportDao.getActivitySupportByActivityId(id);
	}

}

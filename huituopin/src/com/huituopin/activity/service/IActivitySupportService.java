package com.huituopin.activity.service;

import java.util.List;

import com.huituopin.activity.entity.ActivitySupport;

public interface IActivitySupportService {

	public boolean addActivitySupport(ActivitySupport activitySupport);

	public boolean checkOrder(String actSupOrder);
	
	public List<ActivitySupport> getActivitySupportByUserId(int userId);

	public List<ActivitySupport> getActivitySupportByActivityId(
			int id);

	
}

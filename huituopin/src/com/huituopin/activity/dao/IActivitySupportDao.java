package com.huituopin.activity.dao;

import java.util.List;

import com.huituopin.activity.entity.ActivitySupport;

public interface IActivitySupportDao {
	public boolean addActivitySupport(ActivitySupport activitySupport);

	public boolean checkOrder(String actSupOrder);

	public List<ActivitySupport> getActivitySupportByUserId(int userId);

	public List<ActivitySupport> getActivitySupportByActivityId(int id);
}

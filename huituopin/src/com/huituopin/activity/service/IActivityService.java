package com.huituopin.activity.service;

import java.util.Date;
import java.util.List;

import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivityComment;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.common.utils.Page;


public interface IActivityService {
	public List<Activity> getAllActivityList();
	public Activity getActivityById(int id);
	public void addActivity(Activity activity);
	public void updateActivity(Activity activity);
	void delActivity(Activity activity);
	public void delActivityById(int id);
	public List<Activity> getActivity(Page page);
	public List<ActivitySupport> getActivitySupportByActivityId(int id,Page page);
	public List<ActivitySupport> getActivitySupport(Page page);
    public List<ActivityComment> getActivityComments(int activityServiceId);
	public List<ActivityComment> getActivityCommentsByActivityId(int id);
	public List<Activity> getActivityByOptions(Page page,
			Date activityLaunchTime, Date activityStopTime, Integer state,
			String key);
}

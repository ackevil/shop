package com.huituopin.activity.dao;

import java.util.Date;
import java.util.List;

import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivityComment;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.common.dao.BaseDao;
import com.huituopin.common.utils.Page;
import com.huituopin.user.entity.User;

public interface IActivityDao extends BaseDao<Activity, String>{


	public Activity getActivityById(int id);

	public void add(Activity activity);

	public void deleteActivityById(int id);

	public List<Activity> getActivityList(Page page);

	public List<Activity> getAllActivityList();

	public List<ActivitySupport> getActivitySupportByActivityId(int id,
			Page page);

	public List<ActivitySupport> getActivitySupport(Page page);

	public List<ActivityComment> getActivityComment(int activityId);

	public ActivityComment getActivityCommentById(int id);

    public List<ActivityComment> getActivityCommentByActCommentId(int id);

	public List<ActivityComment> getActivityCommentsByActivityId(int id);

	public List<Activity> getActivityByOptions(Page page,
			Date activityLaunchTime, Date activityStopTime, Integer state,
			String key);
	
	public List<Object> getActivityListPC(Page page);

}

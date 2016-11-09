package com.huituopin.activity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.activity.dao.IActivityDao;
import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivityComment;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.activity.service.IActivityService;
import com.huituopin.common.utils.Page;


@Transactional(readOnly=false)
@Service
public class ActivityServiceImpl implements IActivityService {

	@Autowired
	private IActivityDao activityDao;
	
	@Override
	public List<Activity> getAllActivityList() {
		
		return activityDao.getAllActivityList();
	}

	@Override
	public Activity getActivityById(int id) {
		return activityDao.getActivityById(id);
	}

	@Override
	public void addActivity(Activity activity) {
		 activityDao.add(activity);
	}
	
	@Override
	public void updateActivity(Activity activity) {
		activityDao.update(activity);
	}

	@Override
	public void delActivity(Activity activity) {
		activityDao.delete(activity);
	}

	@Override
	public void delActivityById(int id) {
		activityDao.deleteActivityById(id);
	}

	@Override
	public List<Activity> getActivity(Page page) {
		return activityDao.getActivityList(page);
	}

	@Override
	public List<ActivitySupport> getActivitySupportByActivityId(int id,
			Page page) {
		
		return activityDao.getActivitySupportByActivityId(id,page);
	}

	@Override
	public List<ActivitySupport> getActivitySupport(Page page) {
		return activityDao.getActivitySupport(page);
	}
	
	@Override
	public List<ActivityComment> getActivityComments(int activityId) {
	    return activityDao.getActivityComment(activityId);
	}

    @Override
    public List<ActivityComment> getActivityCommentsByActivityId(int id) {
        return activityDao.getActivityCommentsByActivityId(id);
    }

	@Override
	public List<Activity> getActivityByOptions(Page page,
			Date activityLaunchTime, Date activityStopTime, Integer state,
			String key) {
		 return activityDao.getActivityByOptions(page,
					activityLaunchTime,activityStopTime,state,
					key);
	}

}

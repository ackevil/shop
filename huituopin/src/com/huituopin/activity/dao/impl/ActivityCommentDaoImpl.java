package com.huituopin.activity.dao.impl;

import org.springframework.stereotype.Repository;

import com.huituopin.activity.dao.IActivityCommentDao;
import com.huituopin.activity.entity.ActivityComment;
import com.huituopin.common.dao.impl.BaseDaoImpl;

@Repository
public class ActivityCommentDaoImpl extends BaseDaoImpl<ActivityComment, String> implements IActivityCommentDao {

	@Override
	public void addActivityComment(ActivityComment activityComment) {
		save(activityComment);
	}

}

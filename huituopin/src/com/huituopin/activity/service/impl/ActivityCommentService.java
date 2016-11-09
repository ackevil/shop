package com.huituopin.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.activity.dao.IActivityCommentDao;
import com.huituopin.activity.dao.IActivityDao;
import com.huituopin.activity.entity.ActivityComment;
import com.huituopin.activity.service.IActivityCommentService;

@Transactional(readOnly=false)
@Service
public class ActivityCommentService implements IActivityCommentService {

	@Autowired
	private IActivityCommentDao activityCommentDao;
	
	@Override
	public void addActivityComment(ActivityComment activityComment) {
		activityCommentDao.addActivityComment(activityComment);
	}

}

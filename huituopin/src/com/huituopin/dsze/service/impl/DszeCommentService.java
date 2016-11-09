package com.huituopin.dsze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.dsze.dao.IDszeCommentDao;
import com.huituopin.dsze.entity.DszeComment;
import com.huituopin.dsze.service.IDszeCommentService;

@Transactional(readOnly=false)
@Service
public class DszeCommentService implements IDszeCommentService {

	@Autowired
	private IDszeCommentDao dszeCommentDao;
	
	@Override
	public void addDszeComment(DszeComment dszeComment) {
		dszeCommentDao.addDszeComment(dszeComment);
	}

}

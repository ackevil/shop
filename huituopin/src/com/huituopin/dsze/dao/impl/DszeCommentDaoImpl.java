package com.huituopin.dsze.dao.impl;

import org.springframework.stereotype.Repository;

import com.huituopin.dsze.dao.IDszeCommentDao;
import com.huituopin.dsze.entity.DszeComment;
import com.huituopin.common.dao.impl.BaseDaoImpl;

@Repository
public class DszeCommentDaoImpl extends BaseDaoImpl<DszeComment, String> implements IDszeCommentDao {

	@Override
	public void addDszeComment(DszeComment dszeComment) {
		save(dszeComment);
	}

}

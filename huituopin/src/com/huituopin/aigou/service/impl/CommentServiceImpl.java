package com.huituopin.aigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.aigou.dao.IProductCommentDao;
import com.huituopin.aigou.service.ICommentService;
import com.huituopin.common.utils.Page;

@Transactional(readOnly=false)
@Service
public class CommentServiceImpl implements ICommentService{
	@Autowired
	private IProductCommentDao pcDao;
	
	@Override
	public List<Object> getAllComment(Page page) {
		return pcDao.getAllComment(page);
	}

}

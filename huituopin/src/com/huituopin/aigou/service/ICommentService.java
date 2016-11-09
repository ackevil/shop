package com.huituopin.aigou.service;

import java.util.List;

import com.huituopin.common.utils.Page;

public interface ICommentService {
	/**
	 * 获取所有的评论信息
	 * @return
	 */
	public List<Object> getAllComment(Page page);

}

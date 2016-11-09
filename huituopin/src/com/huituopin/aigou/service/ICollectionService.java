package com.huituopin.aigou.service;

import java.util.List;

import com.huituopin.aigou.entity.Collection;

public interface ICollectionService {
	/**
	 * 根据用户ID查询一个用户想所有收藏的商品
	 * @param userId
	 * @return
	 */
	public List<Object> searchInfoByUserId(int userId);
	/**
	 * 插入一条收藏记录
	 * @param userId
	 * @param productId
	 * @return
	 */
	public boolean insertCollection(Collection collection);
	/**
	 * 根据ID删除一条记录
	 * @param collectionId
	 * @return
	 */
	public boolean delCollection(int collectionId);
	
	/**
	 * 根据用户ID和产品ID查询一条记录
	 */
	public Collection searchInfoByUserIdAndProductId(int userId,int productId);
}

package com.huituopin.yjya.dao;

import java.util.List;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.entity.ClothComment;

public interface IClothCommentDao extends BaseDao<ClothComment, String>{
	
	/**
	 * 插入一条ClothComment评论
	 * @param clothComment
	 * @return
	 */
	public boolean insert(ClothComment clothComment);
	
	/**
	 * 查找userId用户评论ClothId的信息
	 * @param clothId
	 * @param userId
	 * @return
	 */
	public List<Object> getCommentInfo(int clothId);
	
	/**
	 * 根据userId获取该user评论的所有衣物
	 * @param userId
	 * @return
	 */
	public List<ClothComment> getCommentInfoByUserId(int userId);
	
	/**
	 * 获取所有评论信息
	 * @param page
	 * @return
	 */
	public List<Object> getAllCommentInfo(Page page);

	/**
	 * 获取贫苦人士评价信息（后台）
	 * @param userId
	 * @return
	 */
	public List<Object> getCommentInfoByUserIdAdmin(int userId);
	
	/**
	 * 根据评论Id获取该评论
	 * @param ccId
	 * @return
	 */
	public ClothComment getClothCommentByCCId(int ccId);
	/**
	 * 更新ClothComment信息
	 * @param clothComment
	 * @return
	 */
	public boolean updateClothComment(ClothComment clothComment);
}

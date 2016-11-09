package com.huituopin.aigou.dao;

import java.util.List;

import com.huituopin.aigou.entity.ProductComment;
import com.huituopin.common.utils.Page;

public interface IProductCommentDao {
	
	/**
	 * 插入一张产品评论
	 * @param pc
	 * @return
	 */
	public boolean insertPC(ProductComment pc);
	
	/**
	 * 根据产品图片ID获取该ProductComment
	 * @param pcId
	 * @return
	 */
	public ProductComment getPCByPCId(int pcId);
	
	/**
	 * 根据该产品图片的ID删除该评论
	 * @param pcId
	 * @return
	 */
	public boolean deletePCByPCId(int pcId);
	
	/**
	 * 修改pc
	 * @param pc
	 * @return
	 */
	public boolean updatePC(ProductComment pc);
	
	/**
	 * 获取某一产品的所有评价
	 * @param pId
	 * @return
	 */
	public List<Object[]> getPCByPId(int pId);
	
	/**
	 * 获取某一用户的所有评价信息
	 * @param userId
	 * @return
	 */
	public List<Object[]> getPCInfoByUserId(int userId);
	
	/**
	 * 获取所有的评价信息
	 * @return
	 */
	public List<Object> getAllComment(Page page);
	
	

}

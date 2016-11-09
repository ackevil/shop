package com.huituopin.aigou.dao;

import java.util.List;

import com.huituopin.aigou.entity.ProductOrderDetail;

public interface IProductOrderDetailDao {
	
	/**
	 * 插入一张产品订单详情
	 * @param pod
	 * @return
	 */
	public boolean insertPOD(ProductOrderDetail pod);
	
	/**
	 * 根据产品订单详情ID获取该ProductOrderDetail
	 * @param podId
	 * @return
	 */
	public ProductOrderDetail getPODByPODId(int podId);
	
	/**
	 * 根据该产品订单详情的ID删除该订单详情
	 * @param podId
	 * @return
	 */
	public boolean deletePODByPODId(int podId);
	
	/**
	 * 修改pod
	 * @param pod
	 * @return
	 */
	public boolean updatePOD(ProductOrderDetail pod);
	
	/**
	 * 获取某一订单的所有商品信息
	 * @param poNum
	 * @return
	 */
	public List<Object> getByPONum(String poNum);
	
	/**
	 * 获取某一用户未评价的订单详情
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Object> getByUserIdAndStatus(int userId ,short status);
	
	/**
	 * 根据订单详情ID获取该订单详情
	 * @param podId
	 * @return
	 */
	public List<Object> getPODInfoByPODId(int podId);
	
	/**
	 * 获取某一订单的所有产品
	 * @param poNum
	 * @return
	 */
	public List<ProductOrderDetail> getAllByPONum(String poNum);

}

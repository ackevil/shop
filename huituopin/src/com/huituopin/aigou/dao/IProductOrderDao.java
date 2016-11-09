package com.huituopin.aigou.dao;

import java.util.List;

import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductOrder;

public interface IProductOrderDao {
	/**
	 * 插入一张产品订单
	 * @param po
	 * @return
	 */
	public boolean insertPO(ProductOrder po);
	
	/**
	 * 根据产品订单ID获取该ProductOrder
	 * @param poId
	 * @return
	 */
	public ProductOrder getPOByPOId(int poId);
	
	/**
	 * 根据该产品订单的ID删除该订单
	 * @param poId
	 * @return
	 */
	public boolean deletePOByPOId(int poId);
	
	/**
	 * 修改po
	 * @param po
	 * @return
	 */
	public boolean updatePO(ProductOrder po);
	
	/**
	 * 获取某一用户的全部订单信息
	 * @param userId
	 * @return
	 */
	public List<ProductOrder> getByUserId(int userId);
	
	/**
	 * 获取某一用户某一状态的所有订单
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<ProductOrder> getByUserIdAndStatus(int userId,short status);
	
	/**
	 * 获取 一个用户的消费记录
	 * @param userId
	 * @return
	 */
	public List<ProductOrder> getPayedByUserId(int userId);
	
	/**
	 * 获取一个订单里的产品信息
	 * @param poId
	 * @return
	 */
	public List<Product> getProductsByPOId(int poId);
	
	
}

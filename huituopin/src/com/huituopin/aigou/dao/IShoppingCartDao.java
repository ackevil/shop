package com.huituopin.aigou.dao;

import java.util.List;

import com.huituopin.aigou.entity.ShoppingCart;

public interface IShoppingCartDao {
	/**
	 * 插入一条购物车记录
	 * @param sc
	 * @return
	 */
	public boolean insert(ShoppingCart sc);
	
	/**
	 * 根据购物车记录ID删除该记录
	 * @param scId
	 * @return
	 */
	public boolean deleteSCById(int scId);
	
	/**
	 * 根据用户ID获取该用户所有的购物车记录
	 * @param userId
	 * @return
	 */
	public List<Object> getAllByUserId(int userId);

	/**
	 * 根据购物车记录Id获取该记录
	 * @param scId
	 * @return
	 */
	public ShoppingCart getSCBySCId(int scId);

	/**
	 * 更新一条购物车记录
	 * @param sc
	 * @return
	 */
	public boolean updateSC(ShoppingCart sc);
	
	/**
	 * 根据用户Id和产品ID获取一条记录 
	 * @param userId
	 * @param pId
	 * @return
	 */
	public ShoppingCart getSCByUserIdAndPId(int userId,int pId);
	
	/**
	 * 根据用户Id和产品Id删除一条记录
	 * @param userId
	 * @param pId
	 * @return
	 */
	public boolean deleteSCByUserIdAndPId(int userId,int pId);
}

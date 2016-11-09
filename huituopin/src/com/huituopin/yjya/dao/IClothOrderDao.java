package com.huituopin.yjya.dao;

import java.util.List;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.yjya.entity.ClothOrder;

public interface IClothOrderDao extends BaseDao<ClothOrder, String>{
	/**
	 * 插入一条ClothOrder记录
	 * @param clothOrder
	 * @return
	 */
	public boolean insertClothOrder(ClothOrder clothOrder);
	
	/**
	 * 更新一条ClothOrder
	 * @param clothOrder
	 * @return
	 */
	public boolean updateClothOrder(ClothOrder clothOrder);
	
	/**
	 * 获取某用户某种状态的订单信息，收货地址ID,cloth主图路径，订单产生时间，cloth名称,clothId,clothOrderId
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Object> getCOsByUserIDAndStatus(int userId, short status);
	/**
	 * 获取某贫困人士所有已领取的订单信息，收货地址ID,cloth主图路径，订单产生时间，cloth名称,clothId,clothOrderId
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Object> getReceivedCOsByPoorUserIDAndStatus(int userId);
	/**
	 * 获取某贫困人士所有已收货的订单信息，收货地址ID,cloth主图路径，订单产生时间，cloth名称,clothId,clothOrderId
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Object> getReceptionCOsByPoorUserIDAndStatus(int userId);
	
	/**
	 * 根据订单Id获取该订单
	 * @param coId
	 * @return
	 */
	public ClothOrder getClothOrderByCOId(int coId);
	
	/**
	 * 根据clothId获取该订单
	 * @param clothId
	 * @return
	 */
	public ClothOrder getClothOrderByClothId(int clothId);
	
	/**
	 * 根据订单Id删除该订单，实则把该记录的is_delete状态置为true
	 * @param coId
	 * @return
	 */
	public boolean deleteClothOrderByCOId(int coId);
	

}

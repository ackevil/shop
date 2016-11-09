package com.huituopin.user.dao;

import java.util.List;

import com.huituopin.user.entity.ShippingAddress;

public interface IShippingAddressDao {
	/**
	 * 插入一条收货地址数据
	 * @param shippingAddress
	 * @return
	 */
	public boolean insert(ShippingAddress shippingAddress);
	
	/**
	 * 根据Id删除一条收货地址记录
	 * @param id
	 * @return
	 */
	public boolean deleteShippingAddressById(int id);
	
	/**
	 * 修改收货地址
	 * @param shippingAddress
	 * @return
	 */
	public boolean updateShippingAddress(ShippingAddress shippingAddress);
	
	/**
	 * 根据id获取一条收货地址记录
	 * @param id
	 * @return
	 */
	public ShippingAddress getShippingAddressById(int id);
	
	/**
	 * g根据userId获取该用户所有的收货地址
	 * @param userId
	 * @return
	 */
	public List<ShippingAddress> getShippingAddressByUserId(int userId);
	/**
	 * 把默认地址改为普通地址
	 * 直接根据shipId来更新下即可
	 * @param shipId
	 * @return
	 */
	public boolean deleteDefaultShipAddress(int shipId);
	/**
	 * 把普通地址改为默认地址
	 * 先根据userId找到当前user的默认地址 ，并把改地址改为普通地址
	 * 再根据shipId把该地址设置为默认
	 * @param userId
	 * @param shipId
	 * @return
	 */
	public boolean changeShipAddressToDefault(int userId,int shipId);
	
	/**
	 * 根据userId获取他的默认收货地址
	 * @param userId
	 * @return
	 */
	public ShippingAddress getDefaultShippingAddressByUserId(int userId);
}

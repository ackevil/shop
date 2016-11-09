package com.huituopin.user.dao;

import com.huituopin.user.entity.LogisticsCompany;

public interface ILogisticsCompanyDao {
	/**
	 * 插入一条收货地址数据
	 * @param LogisticsCompany
	 * @return
	 */
	public boolean insert(LogisticsCompany LogisticsCompany);
	
	/**
	 * 根据Id删除一条收货地址记录
	 * @param id
	 * @return
	 */
	public boolean deleteLogisticsCompanyById(int id);
	
	/**
	 * 修改收货地址
	 * @param LogisticsCompany
	 * @return
	 */
	public boolean updateLogisticsCompany(LogisticsCompany LogisticsCompany);
	
	/**
	 * 根据id获取一条收货地址记录
	 * @param id
	 * @return
	 */
	public LogisticsCompany getLogisticsCompanyById(int id);

}

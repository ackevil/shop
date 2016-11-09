package com.huituopin.aigou.service;

import java.util.List;

import com.huituopin.aigou.entity.Logistics;

public interface ILogisticsService {
	/**
	 * 查询所有的信息
	 * @return
	 */
	public List<Logistics> searchAllData();
	/**
	 * 更新一条记录
	 * @return
	 */
	public boolean updataOneData(Logistics logistics);
	/**
	 * 插入一条记录
	 * @return
	 */
	public boolean insertOneData(Logistics logistics);
	/**
	 * 删除一条记录
	 * @param Lid
	 * @return
	 */
	public boolean deleteOneData(int Lid);
	/**
	 * 查询一条记录
	 * @param Lid
	 * @return
	 */
	public Logistics searchDataById(int Lid);
}

package com.huituopin.bank.dao;

import java.util.List;

import com.huituopin.bank.entity.Credicts;

public interface ICredictsDao {
	/**
	 * 根据积分类型来查
	 */
	public List<Credicts> searchInfoByType(int userId,int type);
	/**
	 * 查所有
	 * @return
	 */
	public List<Credicts> searchAllInfo();
	/**
	 * 根据用户ID查询一条记录
	 * @param userId
	 * @return
	 */
	public List<Credicts> searchInfoByUserId(int userId);
	/**
	 * 根据ID查询一条记录
	 * @param userId
	 * @return
	 */
	public Credicts searchInfoById(int credictsId);
	/**
	 * 插入一条记录
	 * @param credicts
	 * @return
	 */
	public boolean insertOneData(Credicts credicts);
	/**
	 * 删除一条记录
	 * @param credictsId
	 * @return
	 */
	public boolean delOneData(int credictsId);
}

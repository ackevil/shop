package com.huituopin.user.dao;

import com.huituopin.user.entity.LovingPeople;

public interface ILovingPeopleDao {
	
	/**
	 * 插入一条lovingpeople对象 成功返回true 失败返回false
	 * @param lovingPeople
	 * @return
	 */
	
	
	public Boolean insert(LovingPeople lovingPeople);
	/**
	 * 根据lovingpeople表中的id查找返回一条lovingpeople，无数据则返回空
	 * @param poorId
	 * @return
	 */
	
	
	public LovingPeople searchById(int loveId);
	/**
     * 更新爱心人士详细信息
     * @param lovingPeople
     * @return
     */
    public Boolean updataLovingPeopleDetailInfo(LovingPeople lovingPeople);
    
    /**
     * 根据UserID查找用户详细信息
     * @param userId
     * @return
     */
    public LovingPeople searchInfoByUserId(int userId);

}

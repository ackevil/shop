package com.huituopin.user.dao;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;

public interface IPoorPeopleDao extends BaseDao<PoorPeople, String>{
	
	
	/**
	 * 插入一条poorpeople对象 成功返回true 失败返回false
	 * @param lovingPeople
	 * @return
	 */
	
	
	public Boolean insert(PoorPeople poorPeople);
	/**
	 * 根据poorpeople表中的id查找返回一条poorpeople，无数据则返回空
	 * @param poorId
	 * @return
	 */
	
	
	public PoorPeople searchById(int poorId);
	 /**
     * 更新贫困人士详细信息
     * @param poorPeople
     * @return
     */
    public Boolean updataPoorPeopleDetailInfo(PoorPeople poorPeople);
    
    /**
     * 根据用户ID查找贫困人士详细信息
     * @param userId
     * @return
     */
    public PoorPeople searchPpInfoByUserID(int userId);
}

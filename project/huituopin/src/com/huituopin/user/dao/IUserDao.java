package com.huituopin.user.dao;

import java.util.List;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;
import com.huituopin.user.entity.User;

public interface IUserDao extends BaseDao<User, String> {
    /**
     * 插入一条user对象 成功返回true 失败返回false
     * @param user
     * @return
     */
    public Boolean insert(User user);
    /**
     * 添加爱心人士详细信息
     * @param userId
     * @param lovingPeople
     * @return
     */
    public Boolean insertLovingPeopleDetailInfo(int userId,LovingPeople lovingPeople);
    /**
     * 添加贫困人士详细信息
     * @param userId
     * @param poorPeople
     * @return
     */
    public Boolean insertPoorPeopelDetailInfo(int userId,PoorPeople poorPeople);
    
    
    
    
    /**
     * 根据user表中的id查找返回一条user，无数据则返回空
     * @param userId
     * @return
     */
    public User searchById(int userId);
    
    /**
     * 根据user表中的userPhone查找返回一条user，无数据则返回空
     * @param userPhone
     * @return 
     */
    public User searchByUserPhone(String userPhone);
    
    /**
     * 根据user表中的Wid查找返回一条记录，无数据则返回空
     * @param userWcId
     * @return
     */
    public User searchByWid(char userWcId);
    
    /**
     * 查询所有的爱心人士基本信息
     * @return
     */
    public List<User> searchAllLovingPeople();
    /**
     * 查询所有的贫困人士
     * @return
     */
    public List<User> searchAllPoorPeopel();
    
    
    
    
    
    /**
     * 更新用户基本信息
     * @param user
     * @return
     */
    public Boolean updataUserInfo(User user);
    /**
     * 更新爱心人士详细信息
     * @param lovingPeople
     * @return
     */
    public Boolean updataLovingPeopleDetailInfo(LovingPeople lovingPeople);
    /**
     * 更新贫困人士详细信息
     * @param poorPeople
     * @return
     */
    public Boolean updataPoorPeopleDetailInfo(PoorPeople poorPeople);
    
    
}

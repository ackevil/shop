package com.huituopin.user.dao;

import java.util.Date;
import java.util.List;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.common.utils.Page;
import com.huituopin.user.entity.User;

public interface IUserDao extends BaseDao<User, String> {
	 /**
     * 根据电话号码查询用户
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone);
	
    /**
     * 插入一条user对象 成功返回true 失败返回false
     * @param user
     * @return
     */
    public Boolean insert(User user);
   
    
    /**
     * 根据user表中的id查找返回一条user，无数据则返回空
     * @param userId
     * @return
     */
    public User searchById(int userId);
    
    
    /**
     * 根据user表中的Wid查找返回一条记录，无数据则返回空
     * @param userWcId
     * @return
     */
    public User searchByWid(String userWcId);
    
    
    /**
     * 查询所有的爱心人士基本信息
     * @return
     */
    public List<Object>  searchAllLovingPeople(Page page,Date datetimeStart,Date datetimeEnd,Integer gender,Integer state,String keyWord);
    
    
    /**
     * 查询所有的贫困人士
     * @return
     */
    public List<User> searchAllPoorPeopel(Page page,Date datetimeStart,Date datetimeEnd,Integer gender,Integer state,String keyWord);
    
    
    /**
     * 根据电话号码查询用户 ，不存在返回false
     * @return
     */
    public boolean searchUserbyPhoneNumber(String phoneNumber);
  
    
    /**
     * 更新用户基本信息
     * @param user
     * @return
     */
    public Boolean updataUserInfo(User user);
    
    /**
     * 根据userId查找贫困人士所有信息    两个表一起查
     * @param userId
     * @return
     */
    public List<Object> searchPpAllInfoByUserId(int userId);
    /**
     * 根据userId查找爱心人士所有信息    两个表一起查
     * @param userId
     * @return
     */
    public List<Object> searchLpAllInfoByUserId(int userId);
    
    /**
     * 获取所有爱心人士的积分信息
     * @param page
     * @param datetimeStart
     * @param datetimeEnd
     * @param gender
     * @param state
     * @param keyWord
     * @return
     */
    public List<Object> searchAllLovingPeopleInfo(Page page,Date datetimeStart,Date datetimeEnd,Integer gender,Integer state,String keyWord);
}

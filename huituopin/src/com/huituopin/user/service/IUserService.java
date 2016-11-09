package com.huituopin.user.service;

import java.util.List;

import com.huituopin.common.exception.BusinessException;
import com.huituopin.common.exception.ParameterException;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;
import com.huituopin.user.entity.ShippingAddress;
import com.huituopin.user.entity.User;

public interface IUserService {

    public void login(String username, String password) throws ParameterException, BusinessException;
    /**
     * 检查手机号是否已经被注册
     * 已经注册 返回true
     * 还没注册 返回false
     * @param phone
     * @return
     */
    public boolean phoneExists(String phone);   //gqs  返回手机号是否存在
    /**
     * 根据电话号码查询用户
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone);
    
    
    
    /**
     * 根据userID返回user对象
     * @param uid
     * @return
     */
    public User getUserByUserId(int uid);
    
    /**
     * 更新user信息
     * @param user
     * @return
     */
    public boolean updataUserInfo(User user);
    /**
     * 添加一条用户信息  注册用户 成功返回true
     * @param user
     * @return
     */
    public boolean insertUser(User user); 
    /**
     * 添加爱心人士详细信息
     * @param userId
     * @param lovingPeople
     * @return
     */
    public Boolean insertLovingPeopleDetailInfo(LovingPeople lovingPeople);
    /**
     * 添加贫困人士详细信息
     * @param userId
     * @param poorPeople
     * @return
     */
    public Boolean insertPoorPeopelDetailInfo(PoorPeople poorPeople);
    
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
    /**
     * 保存用户信息
     * @param user
     */
    public void saveUser(User user);
    /**
     * 根据微信ID查找用户记录
     * @param userWcId
     * @return
     */
    public User searchByWid(String userWcId);
    
    /**
     * 根据用户ID查找爱心人士详细信息
     * @param userId
     * @return
     */
    public LovingPeople searchLpInfoByUserID(int userId);
    
    /**
     * 根据用户ID查找贫困人士详细信息
     * @param userId
     * @return
     */
    public PoorPeople searchPpInfoByUserID(int userId);
    
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
    
    /*----------------------------------地址管理-----------------------------------------*/
    /**
     * 添加收货地址
     * @param shippingAddress
     * @return
     */
    public Boolean addShipingAddress(ShippingAddress shippingAddress);
    /**
     * 删除收货地址
     * @param shippingAddress
     * @return
     */
    public Boolean deleteShipingAddress(int shipId);
    /**
     * 更新收货地址
     * @param shippingAddress
     * @return
     */
    public Boolean updataShipingAddress(ShippingAddress shippingAddress);
    /**
     * 查找收货地址 根据用户ID
     * @param shippingAddress
     * @return
     */
    public List<ShippingAddress>  searchShipingAddressByUserId(int userId);
    /**
     * 查找收货地址 根据主键ID
     * @param shippingAddress
     * @return
     */
    public ShippingAddress  searchShipingAddressById(int id);
    /**
     * 删除默认地址
     * @param shipId
     * @return
     */
    public boolean deleteDefaultShipAddress(int shipId);
    /**
     * 增加默认地址
     * @param userId
     * @param shipId
     * @return
     */
	public boolean changeShipAddressToDefault(int userId,int shipId);
	/**
	 *根据userId获取其默认收货地址 
	 * @param userId
	 * @return
	 */
	public ShippingAddress searchDefaultShippingAddressByUserId(int userId);
    /*----------------------------------地址管理-----------------------------------------*/
	
	/**
	 * 根据用户ID获取该用户的积分
	 * 
	 * @return
	 */
	public int getCredictByUserId(int userId);
}

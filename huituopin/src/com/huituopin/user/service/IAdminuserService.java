package com.huituopin.user.service;

import java.util.Date;
import java.util.List;

import com.huituopin.common.utils.Page;
import com.huituopin.user.entity.Adminuser;
import com.huituopin.user.entity.User;

public interface IAdminuserService {
	/**
	 * 插入一条adminuser记录
	 * @param adminuser
	 * @return
	 */
	public Boolean insert(Adminuser adminuser);
	
	/**
	 * 更新adminuser  修改密码
	 */
	public Boolean updateAdminuser(Adminuser adminuser);
	/**
	 * 根据用户名查询记录
	 * @param adminusername
	 * @return
	 */
	public Adminuser searchAdminuserByName(String adminusername);
	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	public Adminuser searchAdminuserById(int id);
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

package com.huituopin.user.dao;

import com.huituopin.common.dao.BaseDao;
import com.huituopin.user.entity.Adminuser;

public interface IAdminuserDao extends BaseDao<Adminuser,String>{
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
	
}

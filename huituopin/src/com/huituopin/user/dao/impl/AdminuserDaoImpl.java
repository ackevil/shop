package com.huituopin.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.user.dao.IAdminuserDao;
import com.huituopin.user.entity.Adminuser;

@Repository
public class AdminuserDaoImpl extends BaseDaoImpl<Adminuser,String> implements IAdminuserDao {

	@Override
	public Boolean insert(Adminuser adminuser) {
				try {
					save(adminuser);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
	}

	@Override
	public Boolean updateAdminuser(Adminuser adminuser) {
				try {
					update(adminuser);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
	}

	@Override
	public Adminuser searchAdminuserByName(String adminusername) {
				List<Adminuser> ls = findBy("adminuserName",adminusername);
				if(ls.size()!=0)
					return ls.get(0);
				else
					return null;
	}

	@Override
	public Adminuser searchAdminuserById(int id) {
				List<Adminuser> ls = findBy("adminuserId",id);
				if(ls.size()!=0)
					return ls.get(0);
				else
					return null;
	}

	
}

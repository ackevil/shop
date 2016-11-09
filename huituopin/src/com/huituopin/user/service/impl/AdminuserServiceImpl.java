package com.huituopin.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.common.utils.Page;
import com.huituopin.user.dao.IAdminuserDao;
import com.huituopin.user.dao.IUserDao;
import com.huituopin.user.entity.Adminuser;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IAdminuserService;

@Transactional(readOnly=false)
@Service
public class AdminuserServiceImpl implements IAdminuserService{
	@Autowired
	public IAdminuserDao adminuserDao;
	
	@Autowired
	public IUserDao userDao;
	@Override
	public Boolean insert(Adminuser adminuser) {
		return adminuserDao.insert(adminuser);
	}

	@Override
	public Boolean updateAdminuser(Adminuser adminuser) {
		return adminuserDao.updateAdminuser(adminuser);
	}

	@Override
	public Adminuser searchAdminuserByName(String adminusername) {
		return adminuserDao.searchAdminuserByName(adminusername);
	}

	@Override
	public Adminuser searchAdminuserById(int id) {
		return adminuserDao.searchAdminuserById(id);
	}

	@Override
	public List<Object>  searchAllLovingPeople(Page page,Date datetimeStart,Date datetimeEnd,Integer gender,Integer state,String keyWord) {
		return userDao.searchAllLovingPeople(page,datetimeStart,datetimeEnd,gender,state,keyWord);
	}

	@Override
	public List<User> searchAllPoorPeopel(Page page,Date datetimeStart,Date datetimeEnd,Integer gender,Integer state,String keyWord) {
		// TODO Auto-generated method stub
		
		return userDao.searchAllPoorPeopel(page,datetimeStart,datetimeEnd,gender,state,keyWord);
	}

	@Override
	public List<Object> searchAllLovingPeopleInfo(Page page,
			Date datetimeStart, Date datetimeEnd, Integer gender,
			Integer state, String keyWord) {
		return userDao.searchAllLovingPeopleInfo(page, datetimeStart, datetimeEnd, gender, state, keyWord);
	}

}

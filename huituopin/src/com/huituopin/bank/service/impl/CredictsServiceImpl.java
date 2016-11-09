package com.huituopin.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.bank.dao.ICredictsDao;
import com.huituopin.bank.entity.Credicts;
import com.huituopin.bank.service.ICredictsService;
@Transactional(readOnly=false)
@Service
public class CredictsServiceImpl implements ICredictsService {
	@Autowired
	private ICredictsDao credictsDao;
	
	@Override
	public List<Credicts> searchAllInfo() {
		return credictsDao.searchAllInfo();
	}

	@Override
	public List<Credicts> searchInfoByUserId(int userId) {
		return credictsDao.searchInfoByUserId(userId);
	}

	@Override
	public boolean insertOneData(Credicts credicts) {
		return credictsDao.insertOneData(credicts);
	}

	@Override
	public boolean delOneData(int credictsId) {
		return credictsDao.delOneData(credictsId);
	}

	@Override
	public List<Credicts> searchInfoByType(int userId, int type) {
		return credictsDao.searchInfoByType(userId,type);
	}

}

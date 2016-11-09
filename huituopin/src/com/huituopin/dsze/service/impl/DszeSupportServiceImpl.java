package com.huituopin.dsze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.dsze.dao.IDszeSupportDao;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.dsze.service.IDszeSupportService;

@Service
@Transactional(readOnly=false)
public class DszeSupportServiceImpl implements IDszeSupportService {

	@Autowired
	private IDszeSupportDao dszeSupportDao;
	
	@Override
	public boolean addDszeSupport(DszeSupport dszeSupport) {
		
		return dszeSupportDao.addDszeSupport(dszeSupport);
	}

	@Override
	public boolean checkOrder(String dszeSupOrder) {
		return dszeSupportDao.checkOrder(dszeSupOrder);
	}

	@Override
	public List<DszeSupport> getDszeSupportByUserId(int userId) {
		return dszeSupportDao.getDszeSupportByUserId(userId);
	}

	@Override
	public List<DszeSupport> getDszeSupportByDszeId(int id) {
		// TODO Auto-generated method stub
		return dszeSupportDao.getDszeSupportByDszeId(id);
	}

}

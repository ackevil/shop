package com.huituopin.aigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.aigou.dao.ILogisticsDao;
import com.huituopin.aigou.entity.Logistics;
import com.huituopin.aigou.service.ILogisticsService;
@Transactional(readOnly=false)
@Service
public class LogisticsServiceImpl implements ILogisticsService {
	@Autowired
	private  ILogisticsDao logisticsDao;
		
	@Override
	public List<Logistics> searchAllData() {
		return logisticsDao.searchAllData();
	}

	@Override
	public boolean updataOneData(Logistics logistics) {
		return logisticsDao.updataOneData(logistics);
	}

	@Override
	public boolean insertOneData(Logistics logistics) {
		return logisticsDao.insertOneData(logistics);
	}

	@Override
	public boolean deleteOneData(int Lid) {
		return logisticsDao.deleteOneData(Lid);
	}

	@Override
	public Logistics searchDataById(int Lid) {
		return logisticsDao.searchDataById(Lid);
	}

}

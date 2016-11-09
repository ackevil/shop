package com.huituopin.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.user.dao.ILogisticsCompanyDao;
import com.huituopin.user.entity.LogisticsCompany;

@Repository
public class LogisticsCompanyDaoImpl extends BaseDaoImpl<LogisticsCompany, String> implements ILogisticsCompanyDao{

	@Override
	public boolean insert(LogisticsCompany LogisticsCompany) {
		try {
			save(LogisticsCompany);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateLogisticsCompany(LogisticsCompany LogisticsCompany) {
		try {
			update(LogisticsCompany);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean deleteLogisticsCompanyById(int id) {
		try {
			delete(getLogisticsCompanyById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public LogisticsCompany getLogisticsCompanyById(int id) {
		List<LogisticsCompany> ls = findBy("logisticsCompanyId",id);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

}


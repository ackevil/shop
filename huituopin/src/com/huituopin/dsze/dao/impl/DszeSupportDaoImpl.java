package com.huituopin.dsze.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.dsze.dao.IDszeSupportDao;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.common.dao.impl.BaseDaoImpl;

@Repository
public class DszeSupportDaoImpl extends BaseDaoImpl<DszeSupport, String> implements IDszeSupportDao{

	@Override
	public boolean addDszeSupport(DszeSupport dszeSupport) {
		try{
			save(dszeSupport);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean checkOrder(String dszeSupOrder) {
		if(createQuery("from DszeSupport where dszeSupOrder = ?", dszeSupOrder).list().size()!=0)
			return true;
		else
			return false;
	}

	@Override
	public List<DszeSupport> getDszeSupportByUserId(int userId) {

		List<DszeSupport> ls = findBy("userId",userId);
		if(ls.size()!=0)
			return ls;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DszeSupport> getDszeSupportByDszeId(int id) {
		String sql="from DszeSupport where dszeId = "+id+" order by dszeSupTime desc";
		Query query=createQuery(sql);
		List<DszeSupport> list= (List<DszeSupport>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}

	

}

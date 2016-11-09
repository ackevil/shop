package com.huituopin.bank.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.entity.Collection;
import com.huituopin.bank.dao.ICredictsDao;
import com.huituopin.bank.entity.Credicts;
import com.huituopin.common.dao.impl.BaseDaoImpl;
@Repository
public class CredictsDaoImpl  extends BaseDaoImpl<Credicts, String>  implements ICredictsDao {

	@Override
	public List<Credicts> searchAllInfo() {
		return null;
	}

	@Override
	public List<Credicts> searchInfoByUserId(int userId) {
		List<Credicts> ls = findBy("userId",userId);
		if(ls != null)
			return ls;
		else
			return null;
	}

	@Override
	public boolean insertOneData(Credicts credicts) {
		try {
			save(credicts);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delOneData(int credictsId) {
		try {
			delete(searchInfoById(credictsId));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Credicts searchInfoById(int credictsId) {
		List<Credicts> ls = findBy("credictsId",credictsId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Credicts> searchInfoByType(int userId, int type) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Credicts c where c.userId = " + userId + " and c.credictsType = " + type );
		Query query = this.createQuery(sql.toString()); 
		List<Credicts> list = query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

}

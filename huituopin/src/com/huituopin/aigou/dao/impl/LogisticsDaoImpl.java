package com.huituopin.aigou.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.ILogisticsDao;
import com.huituopin.aigou.entity.Logistics;
import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductPicture;
import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.user.entity.User;
@Repository
public class LogisticsDaoImpl extends BaseDaoImpl<Logistics, String> implements ILogisticsDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Logistics> searchAllData() {
		String sql = "from Logistics log where log.logisticsIsDelete = 0";
		Query query = createQuery(sql);
		List<Logistics> list = query.list();
		if(list != null){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public boolean updataOneData(Logistics logistics) {
		try {
			update(logistics);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertOneData(Logistics logistics) {
		try {
			save(logistics);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteOneData(int Lid) {
		try {
			delete(searchDataById(Lid));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Logistics searchDataById(int Lid) {
		List<Logistics> ls = findBy("logisticsId",Lid);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}
	
}

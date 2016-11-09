package com.huituopin.yjya.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.yjya.dao.IClothOrderDao;
import com.huituopin.yjya.entity.ClothOrder;

@Repository
public class ClothOrderDaoImpl extends BaseDaoImpl<ClothOrder, String> implements IClothOrderDao{

	@Override
	public boolean insertClothOrder(ClothOrder clothOrder) {
		try {
			save(clothOrder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateClothOrder(ClothOrder clothOrder) {
		try {
			update(clothOrder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	//这里的userId为爱心人士ID，所以需要联合查询
	public List<Object> getCOsByUserIDAndStatus(int userId, short status) {
		StringBuilder sql = new StringBuilder();
		sql.append("select co.clothOrderId,co.clothId,co.shippingAddId,co.clothOrderIntime,c.clothName,c.clothMainpicPath from Cloth as c,ClothOrder as co where c.clothId = co.clothId and c.userId = ");
		sql.append(userId);
		sql.append(" and c.clothState = ");
		sql.append(status);
	    Query query = createQuery(sql.toString());
	    List<Object> list =  query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@Override
	public ClothOrder getClothOrderByCOId(int coId) {
		List<ClothOrder> ls = findBy("clothOrderId",coId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getReceivedCOsByPoorUserIDAndStatus(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select co.clothOrderId,co.clothId,co.shippingAddId,co.clothOrderIntime,c.clothName,c.clothMainpicPath,c.clothState from Cloth as c,ClothOrder as co where c.clothId = co.clothId and co.userId = ");
		sql.append(userId);
		sql.append(" and c.clothState > 0 order by c.clothState asc ");
	    Query query = createQuery(sql.toString());
	    List<Object> list =  query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getReceptionCOsByPoorUserIDAndStatus(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select co.clothOrderId,co.clothId,co.shippingAddId,co.clothOrderIntime,c.clothName,c.clothMainpicPath,c.clothState from Cloth as c,ClothOrder as co where c.clothId = co.clothId and co.clothOrderIsDelete = 0 and co.userId = ");
		sql.append(userId);
		sql.append(" and c.clothState > 2 order by c.clothState asc ");
	    Query query = createQuery(sql.toString());
	    List<Object> list =  query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@Override
	public ClothOrder getClothOrderByClothId(int clothId) {
		List<ClothOrder> ls = findBy("clothId",clothId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public boolean deleteClothOrderByCOId(int coId) {
		ClothOrder co = getClothOrderByCOId(coId);
		co.setClothOrderIsDelete(true);
		return updateClothOrder(co);
	}
}

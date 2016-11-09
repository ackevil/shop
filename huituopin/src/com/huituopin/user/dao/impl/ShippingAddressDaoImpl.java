package com.huituopin.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.user.dao.IShippingAddressDao;
import com.huituopin.user.entity.ShippingAddress;

@Repository
public class ShippingAddressDaoImpl extends BaseDaoImpl<ShippingAddress, String> implements IShippingAddressDao{

	@Override
	public boolean insert(ShippingAddress shippingAddress) {
		try {
			save(shippingAddress);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateShippingAddress(ShippingAddress shippingAddress) {
		try {
			update(shippingAddress);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean deleteShippingAddressById(int id) {
		try {
			delete(getShippingAddressById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ShippingAddress getShippingAddressById(int id) {
		List<ShippingAddress> ls = findBy("shippingAddId",id);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingAddress> getShippingAddressByUserId(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("from ShippingAddress sa where sa.userId = ");
		sql.append(userId);
		sql.append("order by sa.shippingAddIsMain desc");
		Query query = createQuery(sql.toString());
		List<ShippingAddress> ls = (List<ShippingAddress>)query.list();
		if(ls.size()!=0)
			return ls;
		else
			return null;
	}

	@Override
	public boolean deleteDefaultShipAddress(int shipId) {
		ShippingAddress shippingAddress = getShippingAddressById(shipId);
		shippingAddress.setShippingAddIsMain(false);
		return updateShippingAddress(shippingAddress);
	}

	@Override
	public boolean changeShipAddressToDefault(int userId, int shipId) {
		String origiMainId = getMainShippingAddressIdByUserId(userId);
		if(origiMainId==null||"".equals(origiMainId)){
			ShippingAddress shippingAddress = getShippingAddressById(shipId);
			shippingAddress.setShippingAddIsMain(true);
			return updateShippingAddress(shippingAddress);
		}else{
			ShippingAddress shippingAddress = getShippingAddressById(Integer.parseInt(origiMainId));
			shippingAddress.setShippingAddIsMain(false);
			updateShippingAddress(shippingAddress);
			
			shippingAddress = getShippingAddressById(shipId);
			shippingAddress.setShippingAddIsMain(true);
			return updateShippingAddress(shippingAddress);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String getMainShippingAddressIdByUserId(int userId){
		StringBuilder sql = new StringBuilder();
	    sql.append("from ShippingAddress sa where sa.shippingAddIsMain=true and sa.userId = ");
	    sql.append(userId);
	    Query query = createQuery(sql.toString());
		List<ShippingAddress> ls = (List<ShippingAddress>)query.list();
		if(ls.size()!=0)
			return ls.get(0).getShippingAddId().toString();
		else
			return "";
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShippingAddress getDefaultShippingAddressByUserId(int userId) {
		StringBuilder sql = new StringBuilder();
	    sql.append("from ShippingAddress sa where sa.shippingAddIsMain=true and sa.userId = ");
	    sql.append(userId);
	    Query query = createQuery(sql.toString());
		List<ShippingAddress> ls = (List<ShippingAddress>)query.list();
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

}

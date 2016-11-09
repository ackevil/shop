package com.huituopin.aigou.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.IProductOrderDao;
import com.huituopin.aigou.entity.Product;
import com.huituopin.aigou.entity.ProductOrder;
import com.huituopin.common.dao.impl.BaseDaoImpl;

@Repository
public class ProductOrderDaoImpl extends BaseDaoImpl<ProductOrder, String> implements IProductOrderDao{

	@Override
	public boolean insertPO(ProductOrder po) {
		try {
			save(po);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProductOrder getPOByPOId(int poId) {
		String sql = "from ProductOrder po where po.productOrderIsDelete = 0 and po.productOrderId = " + poId;
		Query query = createQuery(sql);
		List<ProductOrder> list = query.list();
		if(list != null){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean deletePOByPOId(int poId) {
		ProductOrder po = getPOByPOId(poId);
		po.setProductOrderIsDelete(true);
		if(updatePO(po)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updatePO(ProductOrder po) {
		try {
			update(po);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductOrder> getByUserId(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("from ProductOrder po where po.productOrderIsDelete = false and po.userId = ");
		sql.append(userId);
		sql.append(" order by po.productOrderState asc, po.productOrderIntime desc");
		Query query = createQuery(sql.toString());
		List<ProductOrder> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductOrder> getByUserIdAndStatus(int userId, short status) {
		StringBuilder sql = new StringBuilder();
		sql.append("from ProductOrder po where po.productOrderIsDelete = false and po.userId = ");
		sql.append(userId);
		if(status!=-1){
			sql.append(" and po.productOrderState = ");
			sql.append(status);
		}
		sql.append(" order by po.productOrderState asc, po.productOrderIntime desc");
		Query query = createQuery(sql.toString());
		List<ProductOrder> list = query.list();
		if(list!=null){
			return list;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductOrder> getPayedByUserId(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("from ProductOrder po where  po.userId = ");
		sql.append(userId);
		sql.append(" and po.productOrderState > 0  ");
		sql.append(" order by po.productOrderState asc, po.productOrderIntime desc");
		Query query = createQuery(sql.toString());
		List<ProductOrder> list = query.list();
		if(list!=null){
			return list;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByPOId(int poId) {
		String sql = "from Product where productId in (select pod.productId from ProductOrderDetail as pod where pod.poNum =(select productOrderNum from ProductOrder where productOrderId = "+poId+"))";
		Query query = createQuery(sql.toString());
		List<Product> list = query.list();
		if(list!=null){
			return list;
		}else{
			return null;
		}

	}



}

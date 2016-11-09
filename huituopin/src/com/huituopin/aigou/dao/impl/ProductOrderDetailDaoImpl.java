package com.huituopin.aigou.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.IProductOrderDetailDao;
import com.huituopin.aigou.entity.ProductOrderDetail;
import com.huituopin.common.dao.impl.BaseDaoImpl;

@Repository
public class ProductOrderDetailDaoImpl extends BaseDaoImpl<ProductOrderDetail,String> implements IProductOrderDetailDao{
	@Override
	public boolean insertPOD(ProductOrderDetail pod) {
		try {
			save(pod);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProductOrderDetail getPODByPODId(int podId) {
		String sql = "from ProductOrderDetail pod where pod.poDetailIsDelete = 0 and pod.poDetailId = " + podId;
		Query query = createQuery(sql);
		List<ProductOrderDetail> list = query.list();
		if(list != null){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean deletePODByPODId(int podId) {
		ProductOrderDetail pod = getPODByPODId(podId);
		pod.setPoDetailIsDelete(true);
		if(updatePOD(pod)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updatePOD(ProductOrderDetail pod) {
		try {
			update(pod);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getByPONum(String poNum) {
		StringBuilder sql = new StringBuilder("select p.product_name,p.product_mainpic_path,p.product_price,pod.product_num,pod.po_detail_id,p.product_specification_value,pod.product_is_comment,p.product_id from ProductOrderDetail pod, Product p where p.product_id = pod.product_id and pod.po_num = ");
		sql.append(poNum);
		Query query = createSQLQuery(sql.toString());
		List<Object> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getByUserIdAndStatus(int userId, short status) {
		StringBuilder sql = new StringBuilder("select p.product_name,p.product_mainpic_path,p.product_specification_value,pod.po_detail_id from ProductOrderDetail as pod left join (select po.productOrder_num from ProductOrder as po where po.user_id = ");
		sql.append(userId);
		sql.append("  and po.product_order_state = 3) as o on o.productOrder_num=pod.po_num left join Product p on p.product_Id = pod.product_id where pod.product_is_comment = 0 ");
		Query query = createSQLQuery(sql.toString());
		List<Object> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getPODInfoByPODId(int podId) {
		String sql = "select p.product_Id,pod.po_detail_id,p.product_mainpic_path from ProductOrderDetail pod left join Product p on p.product_Id = pod.product_id where pod.po_detail_id = "+podId;
		Query query = createSQLQuery(sql.toString());
		List<Object> list = query.list();
		return list;
	}

	@Override
	public List<ProductOrderDetail> getAllByPONum(String poNum) {
		return findBy("poNum",poNum);
	}
}

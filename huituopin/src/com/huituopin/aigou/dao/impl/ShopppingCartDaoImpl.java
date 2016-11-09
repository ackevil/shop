package com.huituopin.aigou.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.IShoppingCartDao;
import com.huituopin.aigou.entity.ShoppingCart;
import com.huituopin.common.dao.impl.BaseDaoImpl;
@Repository
public class ShopppingCartDaoImpl extends BaseDaoImpl<ShoppingCart,String> implements IShoppingCartDao{

	@Override
	public boolean insert(ShoppingCart sc) {
		try {
			save(sc);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteSCById(int scId) {
		ShoppingCart sc = getSCBySCId(scId);
		sc.setScIsDelete(true);
		if(updateSC(sc)){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllByUserId(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select sc.sc_id,sc.product_id,sc.product_num,p.product_mainpic_path,p.product_name,p.product_price,p.product_stock,p.product_sale_num,p.product_stopbuy_num from ShoppingCart sc,Product p where p.product_Id = sc.product_id and sc.sc_is_delete = 0 and sc.user_id = ");
		sql.append(userId);
		sql.append(" order by sc.sc_intime desc");
		Query query = createSQLQuery(sql.toString());
		List<Object> list = (List<Object>)query.list();
		if(list!=null){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public ShoppingCart getSCBySCId(int scId) {
		List<ShoppingCart> list = findBy("scId",scId);
		if(list!=null){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean updateSC(ShoppingCart sc) {
		try {
			update(sc);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShoppingCart getSCByUserIdAndPId(int userId, int pId) {
		String sql = "from ShoppingCart sc where sc.scIsDelete=0 and sc.userId = "+userId+" and sc.productId = "+pId;
		Query query = createQuery(sql);
		List<ShoppingCart> list = query.list();
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean deleteSCByUserIdAndPId(int userId, int pId) {
		ShoppingCart sc = getSCByUserIdAndPId(userId, pId);
		sc.setScIsDelete(true);
		if(updateSC(sc)){
			return true;
		}else{
			return false;
		}
	}

}

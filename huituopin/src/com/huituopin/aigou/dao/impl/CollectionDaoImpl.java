package com.huituopin.aigou.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.ICollectionDao;
import com.huituopin.aigou.entity.Collection;
import com.huituopin.aigou.entity.Logistics;
import com.huituopin.common.dao.impl.BaseDaoImpl;
@Repository
public class CollectionDaoImpl extends BaseDaoImpl<Collection, String> implements ICollectionDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchInfoByUserId(int userId) {
		String sql = "select c.collection_id,p.product_id,p.product_mainpic_path,p.product_name,p.product_price,c.collection_date " +
				" from Collection c,Product p where c.product_id = p.product_id and c.user_id = "+userId+" and c.collection_is_delete " +
						" = 0 order by c.collection_date desc";
		Query query = createSQLQuery(sql.toString());
		List<Object> ls = query.list();
		if(ls != null)
			return ls;
		else
			return null;

	}

	@Override
	public boolean insertCollection(Collection collection) {
		try {
			save(collection);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delCollection(int collectionId) {
		try {
			delete(searchDataById(collectionId));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Collection searchDataById(int collectionId) {
		List<Collection> ls = findBy("collectionId",collectionId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public Collection searchInfoByUserIdAndProductId(int userId, int productId) {
		String sql = "from Collection col where col.collectionIsDelete = 0 and col.userId = " + userId + "and col.productId = " + productId;
		Query query = createQuery(sql);
		List<Collection> ls = query.list();
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

}

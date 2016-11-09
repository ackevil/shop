package com.huituopin.aigou.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.IProductTypeDao;
import com.huituopin.aigou.entity.ProductType;
import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.entity.ClothType;

@Repository
public class ProductTypeDaoImpl extends BaseDaoImpl<ProductType,String> implements IProductTypeDao{
	@Override
	public boolean insertPT(ProductType pt) {
		try {
			save(pt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProductType getPTByPTId(short ptId) {
		String sql = "from ProductType pt where pt.productTypeIsDelete = 0 and pt.productTypeId = " + ptId;
		Query query = createQuery(sql);
		List<ProductType> list = query.list();
		if(list != null){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean deletePTByPTId(short ptId) {
		ProductType pt = getPTByPTId(ptId);
		pt.setProductTypeIsDelete(true);
		if(updatePT(pt)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updatePT(ProductType pt) {
		try {
			update(pt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductType> getAllProductType(String keyword, Page page) {
		StringBuilder sql = new StringBuilder("from ProductType pt where  pt.productTypeIsDelete = false ");
	    StringBuilder sqlCount = new StringBuilder("select count(*) from ProductType where product_type_is_delete = false");
	    if(keyword != null){
	    	sql.append(" and ct.productTypeName like '%"+keyword+"%'");
	    	sqlCount.append(" and product_type_name like '%"+keyword+"%'");
	    }
	    Query query = createQuery(sql.toString());
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        Query queryCount = this.createSQLQuery(sqlCount.toString()); 
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

        List<ProductType> list= (List<ProductType>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductType> getAllProductType() {
		StringBuilder sql = new StringBuilder("from ProductType pt where  pt.productTypeIsDelete = false ");
		Query query = createQuery(sql.toString());
		List<ProductType> list= (List<ProductType>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}
}

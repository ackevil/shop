package com.huituopin.yjya.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.dao.IClothTypeDao;
import com.huituopin.yjya.entity.ClothType;

@Repository
public class ClothTypeDaoImpl extends BaseDaoImpl<ClothType,String> implements IClothTypeDao{

	@Override
	public boolean insert(ClothType clothType) {
		try {
			save(clothType);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateClothType(ClothType clothType) {
		try {
			update(clothType);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteClothTypeById(short clothTypeId) {
		ClothType clothType = getClothTypeById(clothTypeId);
		clothType.setClothTypeIsDelete(true);
		if(updateClothType(clothType)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public ClothType getClothTypeById(short clothTypeId) {
		List<ClothType> ls = findBy("clothTypeId",clothTypeId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClothType> getAllClothType(String keyword,Page page) {
	    StringBuilder sql = new StringBuilder("from ClothType ct where  ct.clothTypeIsDelete = false ");
	    StringBuilder sqlCount = new StringBuilder("select count(*) from ClothType where cloth_type_is_delete = false");
	    if(keyword != null){
	    	sql.append(" and ct.clothTypeName like '%"+keyword+"%'");
	    	sqlCount.append(" and cloth_type_name like '%"+keyword+"%'");
	    }
	    Query query = createQuery(sql.toString());
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        Query queryCount = this.createSQLQuery(sqlCount.toString()); 
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

        List<ClothType> list= (List<ClothType>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClothType> getAllClothType() {
		StringBuilder sql = new StringBuilder("from ClothType ct where 0=0 and ct.clothTypeIsDelete = false");
		 Query query = createQuery(sql.toString());
		 List<ClothType> list = (List<ClothType>)query.list();
		 if(list.size() != 0 )
			 return list;
		 else
			 return null;
	}

}

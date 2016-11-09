package com.huituopin.aigou.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.aigou.dao.IProductPictureDao;
import com.huituopin.aigou.entity.ProductPicture;
import com.huituopin.common.dao.impl.BaseDaoImpl;

@Repository
public class ProductPictureDaoImpl extends BaseDaoImpl<ProductPicture,String> implements IProductPictureDao{

	@Override
	public boolean insertPP(ProductPicture pp) {
		try {
			save(pp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProductPicture getPPByPPId(int ppId) {
		String sql = "from ProductPicture pp where pp.productPicIsDelete = 0 and pp.productPicId = " + ppId;
		Query query = createQuery(sql);
		List<ProductPicture> list = query.list();
		if(list != null){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean deletePPByPPId(int ppId) {
		ProductPicture pp = getPPByPPId(ppId);
		pp.setProductPicIsDelete(true);
		if(updatePP(pp)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updatePP(ProductPicture pp) {
		try {
			update(pp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ProductPicture> getByPODId(int pId) {
		return findBy("productId",pId);
	}

}

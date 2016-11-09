package com.huituopin.yjya.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.common.constants.PictureType;
import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.yjya.dao.IClothPictureDao;
import com.huituopin.yjya.entity.ClothPicture;

@Repository
public class ClothPictureDaoImpl extends BaseDaoImpl<ClothPicture, String> implements IClothPictureDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ClothPicture> getDetailPicsByClothId(int clothId) {
		StringBuilder sql = new StringBuilder();
		sql.append("from ClothPicture cp where cp.clothId = ");
		sql.append(clothId);
		sql.append("and cp.clothPicType = ");
		sql.append(PictureType.DETAILPICTURE);
		Query query = createQuery(sql.toString());
		List<ClothPicture> list= (List<ClothPicture>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClothPicture> getCommentPicsByClothId(int clothId) {
		StringBuilder sql = new StringBuilder();
		sql.append("from ClothPicture cp where cp.clothId = ");
		sql.append(clothId);
		sql.append("and cp.clothPicType = ");
		sql.append(PictureType.COMMENTPICTURE);
		Query query = createQuery(sql.toString());
		List<ClothPicture> list= (List<ClothPicture>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	
	@Override
	public boolean insertClothPicture(ClothPicture clothPicture) {
		try {
			save(clothPicture);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}}

package com.huituopin.yjya.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.dao.IClothCommentDao;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.yjya.entity.ClothComment;

@Repository
public class ClothCommentDaoImpl  extends BaseDaoImpl<ClothComment, String> implements IClothCommentDao{

	@Override
	public boolean insert(ClothComment clothComment) {
		try {
			save(clothComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getCommentInfo(int clothId) {
		StringBuilder sql = new StringBuilder("select user.userId,user.userWcNickname,user.userWcAvatar,cc.clothComContent,cc.clothComIntime,cloth.clothName from ClothComment as cc,User as user,Cloth as cloth where cc.clothComState = 1 and user.userId = cc.poorId and cc.clothId = cloth.clothId and cc.clothId = ");
		sql.append(clothId);
		sql.append(" order by cc.clothComIntime desc");
		Query query = createQuery(sql.toString());
	    List<Object> list =  query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClothComment> getCommentInfoByUserId(int userId) {
		StringBuilder sql = new StringBuilder("from ClothComment cc where cc.poorId = "+userId);
		sql.append(" order by cc.clothComIntime desc");
		Query query = createQuery(sql.toString());
		List<ClothComment> ls = query.list();
		if(ls.size()!=0)
			return ls;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllCommentInfo(Page page) {
		StringBuilder sql = new StringBuilder("select c.cloth_com_intime,c.cloth_com_content,user.user_wc_nickname,cloth.cloth_name,cloth.cloth_id,c.cloth_com_id,c.cloth_com_state from ClothComment as c left join User as user on c.poor_id = user.user_id left join Cloth as cloth on c.cloth_id = cloth.cloth_id");
		sql.append(" order by c.cloth_com_intime desc");
	    Query query = createSQLQuery(sql.toString());
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        Query queryCount = this.createSQLQuery(sql.toString()); 
        List<Object> list= (List<Object>) query.list();
        if(list.size()!=0){
			page.setMaxRows(queryCount.list().size());
			return list;
		}else{
			page.setMaxRows(0);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCommentInfoByUserIdAdmin(int userId){
		StringBuilder sql = new StringBuilder("select comment.clothComIntime,comment.clothComContent,user.userWcNickname,cloth.clothName,comment.clothComId,comment.clothComState from ClothComment comment,User user,Cloth cloth where comment.clothId=cloth.clothId and comment.poorId=user.userId and user.userId =  "+userId);
		sql.append(" order by comment.clothComIntime desc");
		Query query = createQuery(sql.toString());
		List<Object> list= (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ClothComment getClothCommentByCCId(int ccId) {
		StringBuilder sql = new StringBuilder("from ClothComment cc where cc.clothComId = "+ccId);
		sql.append(" order by cc.clothComIntime desc");
		Query query = createQuery(sql.toString());
		List<ClothComment> clothComments = (List<ClothComment>)query.list();
		if(clothComments!=null){
			return clothComments.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean updateClothComment(ClothComment clothComment) {
		try {
			update(clothComment);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	

}

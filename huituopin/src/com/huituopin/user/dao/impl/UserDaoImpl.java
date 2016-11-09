package com.huituopin.user.dao.impl;

import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;
import com.huituopin.user.dao.IUserDao;

import com.huituopin.user.entity.User;
import com.huituopin.yjya.entity.Cloth;
import com.huituopin.common.constants.UserType;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements IUserDao {

    // 三种不同的写法，前两种是hibernate生成的sql，第三种是原生sql，分情况使用

    // @Override
    // public User getUserByUserName(String username) {
    // return findBy("username", username).get(0);
    // }

//    @Override
//    public User getUserByUserName(String username) {
//       return (User) createQuery("from User as u where u.username = ?", username).list().get(0);
//    }
    

	@Override
	public Boolean insert(User user) {
		try {
			save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User searchById(int userId) {
		List<User> ls = findBy("userId",userId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public User searchByWid(String userWcId) {
		List<User> ls = findBy("userWcId",userWcId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object>  searchAllLovingPeople(Page page,Date datetimeStart,Date datetimeEnd,Integer gender,Integer state,String keyWord) {
		StringBuilder sql = new StringBuilder("select u.user_wc_avatar,u.user_wc_nickname,u.user_signtime,lp.love_credicts,u.user_id from User as u left join LovingPeople as lp on u.user_id = lp.user_id where u.user_type = ");
		StringBuilder sqlCount = new StringBuilder("select count(*) from User as u where u.user_type = ");
		sql.append(UserType.LOVINGPEOPLE);
		sqlCount.append(UserType.LOVINGPEOPLE);
		if(datetimeStart!=null && datetimeEnd!=null){
			sql.append(" and u.userSigntime between ? and ? ");
			sqlCount.append(" and u.user_signtime between ? and ? ");
		}
		if(gender!=3){
			sql.append(" and u.userWcGender = " + gender);
			sqlCount.append(" and u.user_wc_gender = " + gender);
		}
		if(state!=0){
			sql.append(" and u.userStates = "+ state);
			sqlCount.append(" and u.user_states = "+ state);
		}
		if(keyWord!=null){
			sql.append(" and u.userWcNickname like '%" + keyWord + "%'");
			sqlCount.append(" and u.user_wc_nickname like '%" + keyWord + "%'");
		}
		sql.append(" order by u.userSigntime desc ");
		sqlCount.append(" order by u.user_signtime desc ");
		Query query = this.createQuery(sql.toString());
	    if(datetimeStart!=null && datetimeEnd!=null){
			query.setDate(0, datetimeStart);
			query.setDate(1, datetimeEnd);
			
		}
	    
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        Query queryCount = this.createSQLQuery(sqlCount.toString()); 
        if(datetimeStart!=null && datetimeEnd!=null){
        	queryCount.setDate(0, datetimeStart);
        	queryCount.setDate(1, datetimeEnd);
			
		}
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
        
        
        List<Object>  list= (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;    
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchAllPoorPeopel(Page page,Date datetimeStart,Date datetimeEnd,Integer gender,Integer state,String keyWord) {
		StringBuilder sql = new StringBuilder("from User as u where u.userType = ");
		StringBuilder sqlCount = new StringBuilder("select count(*) from User as u where u.user_type = ");
		sql.append(UserType.POORPEOPLE);
		sqlCount.append(UserType.POORPEOPLE);
		if(datetimeStart!=null && datetimeEnd!=null){
			sql.append(" and u.userSigntime between ? and ? ");
			sqlCount.append(" and u.user_signtime between ? and ? ");
		}
		if(gender!=3){
			sql.append(" and u.userWcGender = " + gender);
			sqlCount.append(" and u.user_wc_gender = " + gender);
		}
		if(state!=0){
			sql.append(" and u.userStates = "+ state);
			sqlCount.append(" and u.user_states = "+ state);
		}
		if(keyWord!=null){
			sql.append(" and u.userWcNickname like '%" + keyWord + "%'");
			sqlCount.append(" and u.user_wc_nickname like '%" + keyWord + "%'");
		}
		sql.append(" order by u.userSigntime desc ");
		sqlCount.append(" order by u.user_signtime desc ");
		Query query = this.createQuery(sql.toString());
	    if(datetimeStart!=null && datetimeEnd!=null){
			query.setDate(0, datetimeStart);
			query.setDate(1, datetimeEnd);
			
		}
	    
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        Query queryCount = this.createSQLQuery(sqlCount.toString()); 
        if(datetimeStart!=null && datetimeEnd!=null){
        	queryCount.setDate(0, datetimeStart);
        	queryCount.setDate(1, datetimeEnd);
			
		}
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
        
        
        List<User> list= (List<User>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;    
	}
	
	@Override
	public boolean searchUserbyPhoneNumber(String phoneNumber) {
		if(createQuery("from User as u where u.userPhone = ? order by u.userSigntime desc", phoneNumber).list().size()!=0)
			return true;
		else
			return false;
	}

	@Override
	public Boolean updataUserInfo(User user) {
		try {
			update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUserByPhone(String phone) {
		List<User> ls = findBy("userPhone",phone);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchPpAllInfoByUserId(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select user.user_id from User as user,PoorPeople as pp where user.user_id = pp.user_id");
		sql.append(" and user.user_id = " + userId);
		Query query = this.createQuery(sql.toString()); 
		List<Object> list = (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchLpAllInfoByUserId(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select user.user_id from User as user,LovingPeople as lp where user.user_id = lp.user_id");
		sql.append(" and user.user_id = " + userId);
		Query query = this.createQuery(sql.toString()); 
		List<Object> list = (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchAllLovingPeopleInfo(Page page,
			Date datetimeStart, Date datetimeEnd, Integer gender,
			Integer state, String keyWord) {
		StringBuilder sql = new StringBuilder("select u.user_wc_avatar,u.user_wc_nickname,u.user_signtime,lp.love_credicts,u.user_id from User as u left join LovingPeople as lp on u.user_id = lp.user_id where u.user_type = ");
		StringBuilder sqlCount = new StringBuilder("select count(*) from User as u where u.user_type = ");
		sql.append(UserType.LOVINGPEOPLE);
		sqlCount.append(UserType.LOVINGPEOPLE);
		if(datetimeStart!=null && datetimeEnd!=null){
			sql.append(" and u.user_signtime between ? and ? ");
			sqlCount.append(" and u.user_signtime between ? and ? ");
		}
		if(gender!=3){
			sql.append(" and u.user_wc_gender = " + gender);
			sqlCount.append(" and u.user_wc_gender = " + gender);
		}
		if(state!=0){
			sql.append(" and u.user_states = "+ state);
			sqlCount.append(" and u.user_states = "+ state);
		}
		if(keyWord!=null){
			sql.append(" and u.user_wc_nickname like '%" + keyWord + "%'");
			sqlCount.append(" and u.user_wc_nickname like '%" + keyWord + "%'");
		}
		sql.append(" order by u.user_signtime desc ");
		sqlCount.append(" order by u.user_signtime desc ");
		Query query = this.createSQLQuery(sql.toString());
	    if(datetimeStart!=null && datetimeEnd!=null){
			query.setDate(0, datetimeStart);
			query.setDate(1, datetimeEnd);
			
		}
	    
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        Query queryCount = this.createSQLQuery(sqlCount.toString()); 
        if(datetimeStart!=null && datetimeEnd!=null){
        	queryCount.setDate(0, datetimeStart);
        	queryCount.setDate(1, datetimeEnd);
			
		}
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
        
        List<Object> list= (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;    
	}


    // 常用于没有实体情况下
    // @Override
    // public User getUserByUserName(String username) {
    // StringBuffer str = new
    // StringBuffer("select * from UserInfo as u where u.username=?");
    // Query query = createSQLQuery(str.toString(),
    // username).addEntity(User.class);
    //
    // return (User) query.list().get(0);
    // }
}

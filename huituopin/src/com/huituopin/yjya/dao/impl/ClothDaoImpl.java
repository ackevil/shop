package com.huituopin.yjya.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;
import com.huituopin.yjya.dao.IClothDao;
import com.huituopin.yjya.entity.Cloth;

@Repository
public class ClothDaoImpl extends BaseDaoImpl<Cloth, String> implements IClothDao{

	
	@Override
	public int insert(Cloth cloth) {
		try{
			save(cloth);
			return cloth.getClothId();
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean updateCloth(Cloth cloth) {
		try {
			update(cloth);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteClothById(int clothId) {
			Cloth cloth = getClothByID(clothId);
			cloth.setClothIsDelete(true);
			if(updateCloth(cloth)){
				return true;
			}else{
				return false;
			}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cloth> getAllCloth(Page page) {
		String sql = "from Cloth cloth where cloth.clothIsDelete = 0 ";
	    Query query = createQuery(sql);
        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
        query.setMaxResults(page.getPageSizes());
        
        String sqlCount = "select count(*) from Cloth";
        Query queryCount = this.createSQLQuery(sqlCount); 
        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

        List<Cloth> list= (List<Cloth>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@Override
	public Cloth getClothByID(int clothId) {
		List<Cloth> ls = findBy("clothId",clothId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllClothInfo(Page page,Date datetimeStart,Date datetimeEnd,Integer type1,Integer state,String key) {
		StringBuilder sql = new StringBuilder("SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,ct.cloth_type_name,c.cloth_state,u.user_wc_nickname AS setname,o.getname FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname FROM ClothOrder AS c, User AS u  WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0");
		StringBuilder sqlCount = new StringBuilder("SELECT count(*) FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname FROM ClothOrder AS c, User AS u  WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0");
		if(datetimeStart!=null && datetimeEnd!=null){
			sql.append(" and c.cloth_intime between ? and ? ");
			sqlCount.append(" and c.cloth_intime between ? and ? ");
		}
		if(type1!=0){
			sql.append(" and c.cloth_type1 = " + type1);
			sqlCount.append(" and c.cloth_type1 = " + type1);
		}
		if(state!=5){
			sql.append(" and c.cloth_state = "+ state);
			sqlCount.append(" and c.cloth_state = "+ state);
		}
		if(key!=null){
			sql.append(" and c.cloth_name like '%" + key + "%'");
			sqlCount.append(" and c.cloth_name like '%" + key + "%'");
		}
		sql.append(" order by c.cloth_state asc,c.cloth_order desc,c.cloth_order_time desc,c.cloth_intime desc ");
		//String sql = "select c.clothId,c.clothName,c.clothType1,c.clothState,c.clothMainpicPath,c.clothIntime,user..loveReadname,p.poorRealname from Cloth c left join LovingPeople l on c.lovingId = l.loveId left join ClothOrder co on c.clothId = co.clothId left join PoorPeople p on co.poorId = p.poorId";
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getLpDonateInfo(Page page,Date datetimeStart,Date datetimeEnd,Integer type1,Integer state,String key,String userId) {
		StringBuilder sql = new StringBuilder("SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,ct.cloth_type_name,c.cloth_state,u.user_wc_nickname AS setname,o.getname FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname FROM ClothOrder AS c, User AS u  WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0 and c.user_id = "+userId);
		if(datetimeStart!=null && datetimeEnd!=null){
			sql.append(" and c.cloth_intime between ? and ? ");
		}
		if(type1!=0){
			sql.append(" and c.cloth_type1 = " + type1);
		}
		if(state!=5){
			sql.append(" and c.cloth_state = "+ state);
		}
		if(key!=""){
			sql.append(" and c.cloth_name like '%" + key + "%'");
		}
		sql.append(" order by cloth_intime desc ");
		//String sql = "select c.clothId,c.clothName,c.clothType1,c.clothState,c.clothMainpicPath,c.clothIntime,user..loveReadname,p.poorRealname from Cloth c left join LovingPeople l on c.lovingId = l.loveId left join ClothOrder co on c.clothId = co.clothId left join PoorPeople p on co.poorId = p.poorId";
	    Query query = this.createSQLQuery(sql.toString());
	    if(datetimeStart!=null && datetimeEnd!=null){
			query.setDate(0, datetimeStart);
			query.setDate(1, datetimeEnd);
		}
//        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
//        query.setMaxResults(page.getPageSizes());
//        int maxRows=0;
//        if(query.list()!=null){
//        	maxRows = query.list().size();
//        }
//        page.setMaxRows(maxRows);
        List<Object> list= (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cloth> getTargetClothList(int begin, int end) {
		StringBuilder sql = new StringBuilder("select top 10 * from Cloth where clothState = '1' and clothIsDelete = 1 and clothId not in (select clothId from(select * from Cloth limit ");
		sql.append(begin);
		sql.append(") as t)limit ");
		sql.append(end);
		Query query = this.createQuery(sql.toString()); 
		List<Cloth> list = (List<Cloth>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cloth> getAllCloth() {
		String sql = "from Cloth cloth where cloth.clothIsDelete = 0 order by cloth.clothState asc,cloth.clothOrder desc,cloth.clothOrderTime desc,cloth.clothIntime desc";
	    Query query = createQuery(sql);
	    List<Cloth> list = (List<Cloth>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cloth> getClothsByUserId(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Cloth cloth where cloth.clothIsDelete = 0 and cloth.userId = ");
		sql.append(userId);
		sql.append(" order by cloth.clothState asc");
	    Query query = createQuery(sql.toString());
	    List<Cloth> list = (List<Cloth>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cloth> getClothsByTypes(String clothType1, String clothType2,
			String clothType3) {
		StringBuilder sql = new StringBuilder("from Cloth cloth where cloth.clothIsDelete = 0 ");
		if(!clothType1.equalsIgnoreCase("0")){
			sql.append(" and cloth.clothType1 = " + clothType1);
		}
		if(!clothType2.equalsIgnoreCase("0")){
			sql.append(" and cloth.clothType2 = " + clothType2);
		}
		if(!clothType3.equalsIgnoreCase("0")){
			sql.append(" and cloth.clothType3 = " + clothType3);
		}
		sql.append(" order by cloth.clothState asc,cloth.clothIntime desc");
		Query query = createQuery(sql.toString());
		List<Cloth> ls = (List<Cloth>) query.list();
		if(ls.size()!=0)
			return ls;
		else 
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cloth> getClothsByUserIDAndStatus(int userId, short status) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Cloth cloth where cloth.clothIsDelete = 0 and cloth.userId = ");
		sql.append(userId);
		sql.append(" and cloth.clothState = ");
		sql.append(status);
	    Query query = createQuery(sql.toString());
	    List<Cloth> list = (List<Cloth>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getClothByPoorId(int userId) {
		StringBuilder sql = new StringBuilder("SELECT c.cloth_id,c.cloth_intime,c.cloth_mainpic_path,c.cloth_name,ct.cloth_type_name,c.cloth_state,u.user_wc_nickname AS setname,o.getname,o.gettime,o.getuserId FROM Cloth AS c LEFT JOIN User u ON c.user_id=u.user_id LEFT JOIN (SELECT c.cloth_id,u.user_wc_nickname AS getname,c.cloth_order_intime As gettime,c.user_id As getuserId FROM ClothOrder AS c, User AS u WHERE c.user_id=u.user_id) AS o ON o.cloth_id = c.cloth_id LEFT JOIN ClothType As ct on ct.cloth_type_id = c.cloth_type1 where c.cloth_is_delete = 0");
		sql.append(" and getuserId = "+userId);
		sql.append(" order by o.gettime desc");
		Query query = createSQLQuery(sql.toString());
		List<Object> ls = (List<Object>) query.list();
		if(ls.size()!=0)
			return ls;
		else 
			return null;
	}
	//******************pc 端****************************
	@SuppressWarnings("unchecked")
	@Override
	public List<Cloth> getClothsByPC(int pageNo, int pageSize, int ct1,
			int ct2, int ct3) {
		StringBuilder sql = new StringBuilder("from Cloth cloth where cloth.clothIsDelete = 0 ");
		if(ct1 != 0){
			sql.append(" and cloth.clothType1 = " + ct1);
		}
		if(ct2 != 0){
			sql.append(" and cloth.clothType2 = " + ct2);
		}
		if(ct3 != 0){
			sql.append(" and cloth.clothType3 = " + ct3);
		}
		sql.append(" order by cloth.clothState asc,cloth.clothIntime desc");
		Query query = createQuery(sql.toString());
		query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
		List<Cloth> ls = (List<Cloth>) query.list();
		if(ls.size()!=0)
			return ls;
		else 
			return null;
	}

}

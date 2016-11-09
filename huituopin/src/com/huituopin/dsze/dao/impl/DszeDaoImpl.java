package com.huituopin.dsze.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.dsze.dao.IDszeDao;
import com.huituopin.dsze.entity.Dsze;
import com.huituopin.dsze.entity.DszeComment;
import com.huituopin.dsze.entity.DszeSupport;
import com.huituopin.activity.entity.Activity;
import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;

@Repository
public class DszeDaoImpl  extends BaseDaoImpl<Dsze, String> implements IDszeDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dsze> getAllDszeList() {
		String sql = "from Dsze where dszeState=1 order by dszeIsOrder desc,dszeOrderTime desc,dszeIntime desc ";
	    Query query = createQuery(sql);
	    List<Dsze> list= (List<Dsze>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}

	@Override
	public Dsze getDszeById(int id) {
		
		List<Dsze> ls = findBy("dszeId",id);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public void add(Dsze dsze) {
		save(dsze);
	}

	@Override
	public void deleteDszeById(int id) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dsze> getDszeList(Page page) {
		String sql = "from Dsze order by dszeIsOrder desc,dszeOrderTime desc,dszeIntime desc ";
		    Query query = createQuery(sql);
	        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
	        query.setMaxResults(page.getPageSizes());
	        
	        String sqlCount = "select count(*) from Dsze";
	        Query queryCount = this.createSQLQuery(sqlCount); 
	        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

	        List<Dsze> list= (List<Dsze>) query.list();
			if(list.size()!=0)
				return list;
			else
				return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getDszeListPC(Page page) {
		StringBuilder sql = new StringBuilder("select d.dsze_id,d.dsze_name,d.dsze_main_pic from Dsze d where d.dsze_is_delete = false order by d.dsze_is_order desc, d.dsze_order_time desc,d.dsze_intime desc");
	    Query query = createSQLQuery(sql.toString());
        query.setFirstResult((page.page - 1) * page.pageSizes);
        query.setMaxResults(page.pageSizes);
        
        List<Object> list= (List<Object>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DszeSupport> getDszeSupportByDszeId(int id,
			Page page) {
		String sql="from DszeSupport where dsze_id = "+id;
		Query query=createQuery(sql);
		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		String sqlCount="select count(*) from DszeSupport where dsze_id="+id;
		Query queryCount=createSQLQuery(sqlCount);
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<DszeSupport> list= (List<DszeSupport>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DszeSupport> getDszeSupport(Page page) {
		String sql="from DszeSupport";
		Query query=createQuery(sql);
		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		String sqlCount="select count(*) from DszeSupport";
		Query queryCount=createSQLQuery(sqlCount);
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<DszeSupport> list= (List<DszeSupport>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
    public List<DszeComment> getDszeComment(int dszeId) {
	    String sql = "from DszeComment where dszeCommentIsDelete = false and dszeId = ? order by dszeCommentIntime";
	    Query query = createQuery(sql, new Object[] {dszeId});
	    return (List<DszeComment>)query.list();
	}
	
	@SuppressWarnings("unchecked")
    @Override
	public DszeComment getDszeCommentById(int id) {
	    String sql = "from DszeComment where id = ?";
	    Query query = this.createQuery(sql, new Object[]{id});
	    List<DszeComment> commets = (List<DszeComment>)query.list();
	    return commets.get(0);
	}
	
	@SuppressWarnings("unchecked")
    @Override
	public List<DszeComment> getDszeCommentByDszeCommentId(int id) {
	    String sql = "from DszeComment where dszeCommentIsDelete = false and dsze_comment_id = ? order by dszeCommentIntime";
        return (List<DszeComment>)createQuery(sql, new Object[] {id}).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DszeComment> getDszeCommentsByDszeId(int id) {
		String sql = "from DszeComment where dszeId = ? and dszeCommentIsDelete = false order by dszeCommentIntime";
		Query query = this.createQuery(sql, new Object[]{id});
		return (List<DszeComment>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dsze> getDszeByOptions(Page page,
			Date dszeLaunchTime, Date dszeStopTime, Integer state,
			String key) {
		  
		String sql="from Dsze where  1=1 ";
		String sqlCount="select count(*) from Dsze where  1=1 ";
		if(dszeLaunchTime!=null&&dszeStopTime!=null){
				sql+=" and dszeLaunchTime between ? and ? ";
				sqlCount+=" and dsze_intime between ? and ? ";
		}
		
		if(state!=null){
			sql+=" and dszeState="+state;
			sqlCount+=" and dsze_state="+state;
		}
		if(key!=null){
			sql+=" and dszeName like '%"+key+"%'";
			sqlCount+=" and dsze_name like '%"+key+"%'";
		}
		sql+="order by dszeIsOrder desc,dszeOrderTime desc,dszeIntime desc ";
		System.out.println(sql);
		Query query=createQuery(sql);
		if(dszeLaunchTime!=null&&dszeStopTime!=null)
		{
			query.setDate(0, dszeLaunchTime);
			query.setDate(1, dszeStopTime);
		}

		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		
		Query queryCount=createSQLQuery(sqlCount);
		if(dszeLaunchTime!=null&&dszeStopTime!=null)
		{
			queryCount.setDate(0, dszeLaunchTime);
			queryCount.setDate(1, dszeStopTime);
		}
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<Dsze> list= (List<Dsze>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
		
	}


}

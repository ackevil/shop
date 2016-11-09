package com.huituopin.activity.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.activity.dao.IActivityDao;
import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivityComment;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.common.utils.Page;
import com.huituopin.user.entity.User;
import com.huituopin.yjya.entity.ClothType;

@Repository
public class ActivityDaoImpl  extends BaseDaoImpl<Activity, String> implements IActivityDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getAllActivityList() {
		String sql = "from Activity where activityState=1 order by activityIsOrder desc,activityOrderTime desc,activityIntime desc ";
	    Query query = createQuery(sql);
	    List<Activity> list= (List<Activity>) query.list();
		if(list.size()!=0)
			return list;
		else
			return null;
		
	}

	@Override
	public Activity getActivityById(int id) {
		
		List<Activity> ls = findBy("activityId",id);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public void add(Activity activity) {
		save(activity);
	}

	@Override
	public void deleteActivityById(int id) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getActivityList(Page page) {
		String sql = "from Activity  order by activityIsOrder desc,activityOrderTime desc,activityIntime desc ";
		    Query query = createQuery(sql);
	        query.setFirstResult((page.getPage() - 1) * page.getPageSizes());
	        query.setMaxResults(page.getPageSizes());
	        
	        String sqlCount = "select count(*) from Activity";
	        Query queryCount = this.createSQLQuery(sqlCount); 
	        page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));

	        List<Activity> list= (List<Activity>) query.list();
			if(list.size()!=0)
				return list;
			else
				return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getActivityListPC(Page page) {
		StringBuilder sql = new StringBuilder("select a.activity_id,a.activity_name,a.activity_main_pic from Activity a where a.activity_is_delete = false order by a.activity_is_order desc, a.activity_order_time desc,a.activity_intime desc");
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
	public List<ActivitySupport> getActivitySupportByActivityId(int id,
			Page page) {
		String sql="from ActivitySupport where activity_id = "+id;
		Query query=createQuery(sql);
		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		String sqlCount="select count(*) from ActivitySupport where activity_id="+id;
		Query queryCount=createSQLQuery(sqlCount);
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<ActivitySupport> list= (List<ActivitySupport>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivitySupport> getActivitySupport(Page page) {
		String sql="from ActivitySupport";
		Query query=createQuery(sql);
		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		String sqlCount="select count(*) from ActivitySupport";
		Query queryCount=createSQLQuery(sqlCount);
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<ActivitySupport> list= (List<ActivitySupport>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
    public List<ActivityComment> getActivityComment(int activityId) {
	    String sql = "from ActivityComment where actCommentIsDelete = false and activityId = ? order by actCommentIntime";
	    Query query = createQuery(sql, new Object[] {activityId});
	    return (List<ActivityComment>)query.list();
	}
	
	@SuppressWarnings("unchecked")
    @Override
	public ActivityComment getActivityCommentById(int id) {
	    String sql = "from ActivityComment where id = ?";
	    Query query = this.createQuery(sql, new Object[]{id});
	    List<ActivityComment> commets = (List<ActivityComment>)query.list();
	    return commets.get(0);
	}
	
	@SuppressWarnings("unchecked")
    @Override
	public List<ActivityComment> getActivityCommentByActCommentId(int id) {
	    String sql = "from ActivityComment where actCommentIsDelete = false and act_comment_id = ? order by actCommentIntime";
        return (List<ActivityComment>)createQuery(sql, new Object[] {id}).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityComment> getActivityCommentsByActivityId(int id) {
		String sql = "from ActivityComment where activityId = ? and actCommentIsDelete = false order by actCommentIntime";
		Query query = this.createQuery(sql, new Object[]{id});
		return (List<ActivityComment>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getActivityByOptions(Page page,
			Date activityLaunchTime, Date activityStopTime, Integer state,
			String key) {
		  
		String sql="from Activity where  1=1 ";
		String sqlCount="select count(*) from Activity where  1=1 ";
		if(activityLaunchTime!=null&&activityStopTime!=null){
				sql+=" and activityLaunchTime between ? and ? ";
				sqlCount+=" and activity_intime between ? and ? ";
		}
		
		if(state!=null){
			sql+=" and activityState="+state;
			sqlCount+=" and activity_state="+state;
		}
		if(key!=null){
			sql+=" and activityName like '%"+key+"%'";
			sqlCount+=" and activity_name like '%"+key+"%'";
		}
		sql+="order by activityIsOrder desc,activityOrderTime desc,activityIntime desc";
		System.out.println(sql);
		Query query=createQuery(sql);
		if(activityLaunchTime!=null&&activityStopTime!=null)
		{
			query.setDate(0, activityLaunchTime);
			query.setDate(1, activityStopTime);
		}

		query.setFirstResult((page.getPage()-1)*page.getPageSizes());
		query.setMaxResults(page.getPageSizes());
		
		
		Query queryCount=createSQLQuery(sqlCount);
		if(activityLaunchTime!=null&&activityStopTime!=null)
		{
			queryCount.setDate(0, activityLaunchTime);
			queryCount.setDate(1, activityStopTime);
		}
		page.setMaxRows(Integer.parseInt(queryCount.uniqueResult().toString()));
		List<Activity> list= (List<Activity>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
		
	}


}

package com.huituopin.activity.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huituopin.activity.dao.IActivitySupportDao;
import com.huituopin.activity.entity.Activity;
import com.huituopin.activity.entity.ActivitySupport;
import com.huituopin.common.dao.impl.BaseDaoImpl;

@Repository
public class ActivitySupportDaoImpl extends BaseDaoImpl<ActivitySupport, String> implements IActivitySupportDao{

	@Override
	public boolean addActivitySupport(ActivitySupport activitySupport) {
		try{
			save(activitySupport);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean checkOrder(String actSupOrder) {
		if(createQuery("from ActivitySupport where actSupOrder = ?", actSupOrder).list().size()!=0)
			return true;
		else
			return false;
	}

	@Override
	public List<ActivitySupport> getActivitySupportByUserId(int userId) {

		List<ActivitySupport> ls = findBy("userId",userId);
		if(ls.size()!=0)
			return ls;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivitySupport> getActivitySupportByActivityId(int id) {
		String sql="from ActivitySupport where activityId = "+id+" order by actSupTime desc";
		Query query=createQuery(sql);
		List<ActivitySupport> list= (List<ActivitySupport>)query.list();
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}

	

}

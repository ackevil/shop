package com.huituopin.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.user.dao.ILovingPeopleDao;
import com.huituopin.user.entity.LovingPeople;

@Repository
public class LovingPeopleImpl extends BaseDaoImpl<LovingPeople, String> implements ILovingPeopleDao {

	@Override
	public Boolean insert(LovingPeople lovingPeople) {
		// TODO Auto-generated method stub
		try {
			save(lovingPeople);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public LovingPeople searchById(int loveId) {
		// TODO Auto-generated method stub
		List<LovingPeople> ls = findBy("loveId",loveId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public Boolean updataLovingPeopleDetailInfo(LovingPeople lovingPeople) {
		// TODO Auto-generated method stub
		try {
			update(lovingPeople);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public LovingPeople searchInfoByUserId(int userId) {
		List<LovingPeople> ls = findBy("userId",userId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

}

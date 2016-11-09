package com.huituopin.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.user.dao.IPoorPeopleDao;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;

@Repository
public class PoorPeopleImpl extends BaseDaoImpl<PoorPeople, String> implements IPoorPeopleDao{

	@Override
	public Boolean insert(PoorPeople poorPeople) {
		// TODO Auto-generated method stub
		try {
			save(poorPeople);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PoorPeople searchById(int poorId) {
		// TODO Auto-generated method stub
		List<PoorPeople> ls = findBy("poorId",poorId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	@Override
	public Boolean updataPoorPeopleDetailInfo(PoorPeople poorPeople) {
		// TODO Auto-generated method stub
		try {
			update(poorPeople);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PoorPeople searchPpInfoByUserID(int userId) {
		List<PoorPeople> ls = findBy("userId",userId);
		if(ls.size()!=0)
			return ls.get(0);
		else
			return null;
	}

	
	

}

package com.huituopin.user.dao.impl;

import java.util.List;

import javax.persistence.Entity;
import org.springframework.stereotype.Repository;

import com.huituopin.common.dao.impl.BaseDaoImpl;
import com.huituopin.user.dao.IUserDao;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;
import com.huituopin.user.entity.User;

@Entity
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements IUserDao {

    // 三种不同的写法，前两种是hibernate生成的sql，第三种是原生sql，分情况使用

    // @Override
    // public User getUserByUserName(String username) {
    // return findBy("username", username).get(0);
    // }

//    @Override
//    public User getUserByUserName(String username) {
//        return (User) createQuery("from User as u where u.username = ?", username).list().get(0);
//    }

	@Override
	public Boolean insert(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertLovingPeopleDetailInfo(int userId,
			LovingPeople lovingPeople) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertPoorPeopelDetailInfo(int userId, PoorPeople poorPeople) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User searchById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User searchByWid(char userWcId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchAllLovingPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchAllPoorPeopel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updataUserInfo(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updataLovingPeopleDetailInfo(LovingPeople lovingPeople) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updataPoorPeopleDetailInfo(PoorPeople poorPeople) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User searchByUserPhone(String userPhone) {
		// TODO Auto-generated method stub
		return (User) createQuery("from User as u where u.user_phone = ?", userPhone).list().get(0);
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

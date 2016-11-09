package com.huituopin.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huituopin.common.constants.IntegerConstants;
import com.huituopin.common.exception.BusinessException;
import com.huituopin.common.exception.ParameterException;
import com.huituopin.common.service.BaseService;
import com.huituopin.common.utils.MD5;
import com.huituopin.user.dao.ILovingPeopleDao;
import com.huituopin.user.dao.IPoorPeopleDao;
import com.huituopin.user.dao.IShippingAddressDao;
import com.huituopin.user.dao.IUserDao;
import com.huituopin.user.dao.impl.ShippingAddressDaoImpl;
import com.huituopin.user.dao.impl.UserDaoImpl;
import com.huituopin.user.entity.LovingPeople;
import com.huituopin.user.entity.PoorPeople;
import com.huituopin.user.entity.ShippingAddress;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;

@Transactional(readOnly=false)
@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    public IUserDao userDao;
    @Autowired
    public IPoorPeopleDao poorPeopleDao;
    @Autowired
    public ILovingPeopleDao lovingPeopleDao;
    @Autowired
    public IShippingAddressDao shippingAddressDao;

    @Override
    public void login(String username, String password) throws ParameterException, BusinessException {
        User loginUser = null;
        ParameterException parameterException = new ParameterException();

        int userNameLength = username.length();
        int passwordLength = password.length();

        if (userNameLength > IntegerConstants.MAX_USER_NAME_LENGTH
                || userNameLength < IntegerConstants.MIN_USER_NAME_LENGTH) {
            parameterException.addErrorParameter("userName", "User name is too long or too short");
        }

        if (passwordLength > IntegerConstants.MAX_PASSWORD_LENGTH
                || passwordLength < IntegerConstants.MIN_PASSWORD_LENGTH) {
            parameterException.addErrorParameter("password", "Password is too long or too short");
        }

        if (parameterException.hasErrorParameter()) {
            throw parameterException;
        }
    }

	@Override
	public boolean phoneExists(String phone) {
		//对电话号码进行正则表达式验证
		
		return userDao.searchUserbyPhoneNumber(phone);
		
		
	}

	@Override
	public User getUserByUserId(int uid) {
		return userDao.searchById(uid);
	}

	@Override
	public boolean updataUserInfo(User user) {
		return userDao.updataUserInfo(user);
	}

	@Override
	@Transactional(readOnly=false)
	public boolean insertUser(User user) {
		return userDao.insert(user);
	}

	@Override
	public Boolean insertLovingPeopleDetailInfo(LovingPeople lovingPeople) {
		return lovingPeopleDao.insert(lovingPeople);
	}

	@Override
	public Boolean insertPoorPeopelDetailInfo(PoorPeople poorPeople) {
		return poorPeopleDao.insert(poorPeople);
	}

	@Override
	public Boolean updataLovingPeopleDetailInfo(LovingPeople lovingPeople) {
		return lovingPeopleDao.updataLovingPeopleDetailInfo(lovingPeople);
	}

	@Override
	public Boolean updataPoorPeopleDetailInfo(PoorPeople poorPeople) {
		return poorPeopleDao.updataPoorPeopleDetailInfo(poorPeople);
	}
	
	//需要开启事务的需要加上注解，可以直接加在类名上面，标示整个类所有方法都开启事务
	@Transactional(readOnly=false)
	public void saveUser(User user) {
	    System.out.println("123123");
	    userDao.save(user);
	}
	@Override
	public User searchByWid(String userWcId) {
		return userDao.searchByWid(userWcId);
	}

	public Boolean addShipingAddress(ShippingAddress shippingAddress) {
		return shippingAddressDao.insert(shippingAddress);
	}

	@Override
	public Boolean deleteShipingAddress(int shipId) {
		return shippingAddressDao.deleteShippingAddressById(shipId);
	}

	@Override
	public Boolean updataShipingAddress(ShippingAddress shippingAddress) {
		return shippingAddressDao.updateShippingAddress(shippingAddress);
	}
	@Override
	public ShippingAddress searchShipingAddressById(int id) {
		return shippingAddressDao.getShippingAddressById(id);
	}

	@Override
	public List<ShippingAddress> searchShipingAddressByUserId(int userId) {
		return shippingAddressDao.getShippingAddressByUserId(userId);
	}

	@Override
	public boolean deleteDefaultShipAddress(int shipId) {
		return shippingAddressDao.deleteDefaultShipAddress(shipId);
	}

	@Override
	public boolean changeShipAddressToDefault(int userId, int shipId) {
		return shippingAddressDao.changeShipAddressToDefault(userId, shipId);
	}

	@Override
	public LovingPeople searchLpInfoByUserID(int userId) {
		return lovingPeopleDao.searchInfoByUserId(userId);
	}

	@Override
	public ShippingAddress searchDefaultShippingAddressByUserId(int userId) {
		return shippingAddressDao.getDefaultShippingAddressByUserId(userId);
	}

	@Override
	public User getUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return userDao.getUserByPhone(phone);
	}

	@Override
	public PoorPeople searchPpInfoByUserID(int userId) {
		return poorPeopleDao.searchPpInfoByUserID(userId);
	}

	@Override
	public List<Object> searchPpAllInfoByUserId(int userId) {
		// TODO Auto-generated method stub
		return userDao.searchPpAllInfoByUserId(userId);
	}

	@Override
	public List<Object> searchLpAllInfoByUserId(int userId) {
		// TODO Auto-generated method stub
		return userDao.searchLpAllInfoByUserId(userId);
	}

	@Override
	public int getCredictByUserId(int userId) {
		LovingPeople lp = searchLpInfoByUserID(userId);
		return lp.getLoveCredicts();
	}
}

package com.huituopin.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huituopin.common.constants.IntegerConstants;
import com.huituopin.common.exception.BusinessException;
import com.huituopin.common.exception.ParameterException;
import com.huituopin.common.service.BaseService;
import com.huituopin.common.utils.MD5;
import com.huituopin.user.dao.IUserDao;
import com.huituopin.user.entity.User;
import com.huituopin.user.service.IUserService;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    public IUserDao userDao = null;

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

//        loginUser = userDao.getUserByUserName(username);
//
//        if (loginUser.getPassword().equals(MD5.md5(password))) {
//            try {
//                // userDao.update(loginUser);
//                // TODO: 更新用户登陆状态信息，因为这里只是作为一个演示，数据里没有设计，故此处不再给出代码
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            throw new BusinessException("用户名或密码错误");
//        }
    }

	@Override
	public boolean phoneExists(String phone) {
		// TODO Auto-generated method stub
		return false;
	}
}

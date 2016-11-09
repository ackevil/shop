package com.huituopin.user.service;

import com.huituopin.common.exception.BusinessException;
import com.huituopin.common.exception.ParameterException;

public interface IUserService {

    public void login(String username, String password) throws ParameterException, BusinessException;
    
    public boolean phoneExists(String phone);   //gqs  返回手机号是否存在
}

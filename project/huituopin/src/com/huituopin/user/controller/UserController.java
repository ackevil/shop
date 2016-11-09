package com.huituopin.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huituopin.common.exception.BusinessException;
import com.huituopin.common.exception.ParameterException;
import com.huituopin.common.utils.LogUtil;
import com.huituopin.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService = null;

    @RequestMapping(value = "login")
    public void login(HttpServletResponse response, HttpServletRequest request, String username, String password) {

        try {
            userService.login(username, password);
            LogUtil.logInfo(username + "登陆了系统");
        } catch (ParameterException e) {
            Map<String, String> errorParameters = e.getErrorParameters();
            // TODO: 此处需要将验证信息返回前台
            System.out.println(errorParameters.size());

        } catch (BusinessException e) {
            System.out.println(e.getMessage());
            LogUtil.logInfo(e.getMessage());
            // TODO: 此处需要将验证信息返回前台
        }
    }
}

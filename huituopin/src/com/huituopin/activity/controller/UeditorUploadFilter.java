package com.huituopin.activity.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.baidu.ueditor.ActionEnter;

@WebFilter(filterName="uploadImage",urlPatterns="*.ImageAction")
public class UeditorUploadFilter implements Filter {

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse respone,
			FilterChain arg2) throws IOException, ServletException {
		String rootPath = request.getServletContext().getRealPath("/");
		//rootPath+="WEB-INF/admin/upload";
		String action = request.getParameter("action");
		String result=new ActionEnter( (HttpServletRequest)request, rootPath ).exec();
		if( action!=null && 
				   (action.equals("listfile") || action.equals("listimage") ) ){
				    rootPath = rootPath.replace("\\", "/");
				    result = result.replaceAll(rootPath, "/");//把返回路径中的物理路径替换为 '/'
				}
		respone.getWriter().print(result)  ;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

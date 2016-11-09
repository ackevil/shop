package com.huituopin.admin.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class AdminSessionFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//
		String[] notFilter = new String[]
				{"login","checkUserName","/skin"};
		
		// 请求的uri
				String uri = request.getRequestURI();

				// uri中包含background时才进行过滤
				if (uri.indexOf("admin") != -1) {
					// 是否过滤
					boolean doFilter = true;
					for (String s : notFilter) {
						if (uri.indexOf(s) != -1) {
							// 如果uri中包含不过滤的uri，则不进行过滤
							doFilter = false;
							break;
						}
					}
					if (doFilter) {
						// 执行过滤
						// 从session中获取登录者实体
						Object obj = request.getSession().getAttribute("adminuser");
						if (null == obj) {
							String path = request.getContextPath();
							request.getSession().setAttribute("type",2);
							response.sendRedirect(path+"/admin/login");
						} else {
							// 如果session中存在登录者实体，则继续
							filterChain.doFilter(request, response);
						}
					} else {
						// 如果不执行过滤，则继续
						filterChain.doFilter(request, response);
					}
				} else {
					// 如果uri中不包含background，则继续
					filterChain.doFilter(request, response);
				}
		
	}
	
}

package com.sony.mts.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器
 * 
 * @author 黄龙
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger();

	/**
	 * 目标方法执行前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestURI = request.getRequestURI();
		logger.info("preHandle拦截的请求路径是{}", requestURI);

		// 登录检查逻辑
		HttpSession session = request.getSession();
		Object adminUser = session.getAttribute("aEmployee");
		Object commonUser = session.getAttribute("bEmployee");
		if (adminUser != null) {
			return true;
		}
		if (commonUser != null) {
			return true;
		}
		request.setAttribute("illegalreq", "请登录");
		request.getRequestDispatcher("/").forward(request, response);
		return false;

	}

}

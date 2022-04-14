package com.sony.mts.base;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.sony.mts.exception.NoLoginException;

/**
 * AdminBaseController
 * 
 * @author 黄龙
 */
public class AdminBaseController extends BaseController {

	/**
	 * 权限校验，处理方法执行前执行该方法
	 */
	@ModelAttribute
	public void isLogin(HttpSession session) throws NoLoginException {
		if (session.getAttribute("aEmployee") == null) {
			throw new NoLoginException("illegal request:not authorized");
		}
	}

}

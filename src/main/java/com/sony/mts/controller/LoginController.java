package com.sony.mts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sony.mts.base.BaseController;
import com.sony.mts.entity.Employee;
import com.sony.mts.service.EmployeeService;

/**
 * 登录Controller
 * 
 * @author 黄龙
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	/** 员工信息Service */
	@Autowired
	EmployeeService employeeService;

	/**
	 * 登录页面初始化
	 */
	@RequestMapping(value = "/toLogin")

	public String toLogin() {
		logger.info(properties.getProperty("log_initUserLogin"));
		return "index";
	}

	/**
	 * 登录信息验证
	 */
	@PostMapping(value = "/detail")
	public String detail(Model model, HttpServletRequest request) {

		try {
			String empId = request.getParameter("empId");
			String passWd = request.getParameter("passWd");
			passWd = DigestUtils.md5DigestAsHex(passWd.getBytes());
			Employee employee = employeeService.checkEmployee(empId, passWd);

			if (checkUsers(employee, model)) {
				String posNum = employee.getPosNum();
				return checkPosNum(posNum, request, employee, model);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_login_fail"), e);
			throw e;
		}
		return "index";

	}

	/**
	 * 用户登出，杀死session
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logger.info(properties.getProperty("log_userLogout"));
		session.invalidate();
		return "index";
	}

	/**
	 * 用户校验
	 */
	private boolean checkUsers(Employee employee, Model model) {

		if (employee == null) {
			model.addAttribute("verify_fail", properties.getProperty("verify_fail"));
			logger.info(properties.getProperty("log_verify_fail"));
			return false;
		}
		return true;

	}

	/**
	 * 用户职位校验
	 */
	private String checkPosNum(String posNum, HttpServletRequest request, Employee employee, Model model) {

		if ("A1".equals(posNum)) {
			logger.info(properties.getProperty("log_verify_managerLogin"));
			request.getSession().setAttribute("aEmployee", employee);
			model.addAttribute("employee", employee);
			return "homePage/detail_admin";
		}
		if ("B1".equals(posNum)) {
			logger.info(properties.getProperty("log_verify_managerLogin"));
			request.getSession().setAttribute("aEmployee", employee);
			model.addAttribute("employee", employee);
			return "homePage/detail_admin";
		}
		if ("X1".equals(posNum)) {
			logger.info(properties.getProperty("log_verify_empLogin"));
			request.getSession().setAttribute("bEmployee", employee);
			model.addAttribute("employee", employee);
			return "homePage/detail_emp";
		}
		return "index";

	}

}

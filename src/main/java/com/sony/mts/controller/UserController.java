package com.sony.mts.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sony.mts.base.UserBaseController;
import com.sony.mts.entity.Department;
import com.sony.mts.entity.EmpProjectRela;
import com.sony.mts.entity.Employee;
import com.sony.mts.exception.NoLoginException;
import com.sony.mts.service.DepartmentService;
import com.sony.mts.service.EmpProjectRelaService;
import com.sony.mts.service.EmployeeService;

/**
 * 员工身份Controller
 * 
 * @author 黄龙
 */
@Controller
@RequestMapping("/user")
public class UserController extends UserBaseController {

	/** 部门信息Service */
	@Autowired
	DepartmentService departmentService;
	/** 员工信息Service */
	@Autowired
	EmployeeService employeeService;
	/** 任务信息Service */
	@Autowired
	EmpProjectRelaService eprService;

	/**
	 * 员工：所属部门信息取得
	 */
	@RequestMapping("/selectDept/{depNum}")
	public String selectDeptByPrimaryKey(Model model, @PathVariable(value = "depNum") String depNum,
			HttpServletRequest request) throws NoLoginException {

		logger.info(properties.getProperty("log_initSelectDept"));
		Employee employee = (Employee) request.getSession().getAttribute("bEmployee");
		if (!employee.getDepNum().equals(depNum)) {
			throw new NoLoginException("illegal access");
		}
		try {
			String userDeptInfo = employee.getDepNum();
			model.addAttribute("userDeptInfo", userDeptInfo);
			Department department = departmentService.selectByPrimaryKey(depNum);
			model.addAttribute("department", department);
			logger.info(properties.getProperty("log_selectDept_success"));
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectDept_fail"), e);
			throw e;
		}
		return "dept/admin_depList";

	}

	/**
	 * 员工个人信息更新页面跳转
	 */
	@RequestMapping("/toUpdatePersonalInfo/{empId}")
	public String toUpdateInfo(@PathVariable(value = "empId") String empId, Model model, HttpServletRequest request)
			throws NoLoginException {

		logger.info(properties.getProperty("log_initUpdatePersonalInfo"));
		Employee emp = (Employee) request.getSession().getAttribute("bEmployee");
		if (!emp.getEmpId().equals(empId)) {
			throw new NoLoginException("illegal access");
		}
		Employee employee = employeeService.selectByPrimaryKey(empId);
		model.addAttribute("employee", employee);

		return "emp/emp_updateInfo";

	}

	/**
	 * 员工个人信息更新（主键）
	 */
	@PostMapping("/updatePersonalInfo")
	public String updatePersonalInfo(Employee employee, Model model, HttpServletRequest request) throws Exception {

		try {
			employeeService.updateByPrimaryKeySelective(employee);
			model.addAttribute("updateEmp_success", properties.getProperty("updateEmp_success"));
			logger.info(properties.getProperty("log_updatePersonalInfo_success"));
			return selectEmpByPrimaryKey(model, employee.getEmpId(), request);
		} catch (Exception e) {
			logger.error(properties.getProperty("log_updatePersonalInfo_fail"), e);
			throw e;
		}

	}

	/**
	 * 员工：个人信息取得
	 */
	@RequestMapping(value = "/selectEmp/{empId}")
	public String selectEmpByPrimaryKey(Model model, @PathVariable(value = "empId") String empId,
			HttpServletRequest request) throws NoLoginException {

		logger.info(properties.getProperty("log_initSelectEmp"));
		Employee emp = (Employee) request.getSession().getAttribute("bEmployee");
		// 用户权限校验
		if (!emp.getEmpId().equals(empId)) {
			throw new NoLoginException("illegal access");
		}
		try {
			Employee employee = employeeService.selectByPrimaryKey(empId);
			model.addAttribute("empInfoModel", empId);
			model.addAttribute("employee", employee);
			logger.info(properties.getProperty("log_selectEmp_success"));
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectEmp_fail"), e);
			throw e;
		}
		return "emp/admin_empList";

	}

	/**
	 * 员工：个人任务信息取得
	 */
	@RequestMapping("/selectTask/{empId}")
	public String selectTaskByEmpId(Model model, @PathVariable(value = "empId") String empId,
			HttpServletRequest request) throws Exception {

		logger.info(properties.getProperty("log_initSelectTaskByEmpId"));

		try {
			if (checkPersonnalTaskInfo(empId, model, request)) {
				model.addAttribute("taskInfoModel", empId);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectTaskByEmpId_fail"), e);
			throw e;
		}
		return "task/admin_taskList";

	}

	/**
	 * 个人任务信息取得校验
	 */
	private boolean checkPersonnalTaskInfo(String empId, Model model, HttpServletRequest request)
			throws NoLoginException {

		Employee emp = (Employee) request.getSession().getAttribute("bEmployee");
		if (!emp.getEmpId().equals(empId)) {
			throw new NoLoginException("illegal access");
		}
		List<EmpProjectRela> empProjectRelaList = eprService.selectByEmpId(empId);
		logger.info(properties.getProperty("log_selectTaskByEmpId_success"));
		if (empProjectRelaList.size() == 0) {
			model.addAttribute("hasNoTask", properties.getProperty("hasNoTask"));
			model.addAttribute("taskInfoModel", empId);
			logger.info(properties.getProperty("log_hasNoTask"));
			return false;
		}
		model.addAttribute("empProjectRelaList", empProjectRelaList);
		return true;

	}

	/**
	 * 员工：跳转到主页面
	 */
	@RequestMapping(value = "/homePage")
	public String homePage(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("bEmployee");
		model.addAttribute("employee", employee);
		return "homePage/detail_emp";
	}

}

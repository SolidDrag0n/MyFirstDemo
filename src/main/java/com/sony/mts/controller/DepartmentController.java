package com.sony.mts.controller;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sony.mts.base.AdminBaseController;
import com.sony.mts.entity.Department;
import com.sony.mts.entity.Employee;
import com.sony.mts.service.DepartmentService;
import com.sony.mts.service.EmployeeService;

/**
 * 部门信息Controller
 * 
 * @author 黄龙
 */

@Controller
@RequestMapping("/dept")
public class DepartmentController extends AdminBaseController {

	/** 部门信息Service */
	@Autowired
	private DepartmentService departmentService;
	/** 员工信息Service */
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 部门信息追加页面跳转
	 */
	@RequestMapping("/toInsertDept")
	public String toInsertDept() {
		logger.info(properties.getProperty("log_initInsertDept"));
		return "dept/admin_editDept";
	}

	/**
	 * 部门信息追加
	 */
	@PostMapping("/insertDept")
	public String insertDept(Department department, Model model) throws Exception {

		try {
			if (checkInsertDept(department, model)) {
				departmentService.insert(department);
				model.addAttribute("insertDept_success", properties.getProperty("insertDept_success"));
				logger.info(properties.getProperty("log_insertDept_success"));
				return selectAllDept(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_insertDept_fail"), e);
			throw e;
		}
		return "dept/admin_editDept";

	}

	/**
	 * 部门信息删除
	 *
	 * @param depNum（主键）
	 */
	@RequestMapping("/deleteDept/{depNum}")
	public String deleteDeptByPrimaryKey(@PathVariable(value = "depNum") String depNum, Model model) {

		logger.info(properties.getProperty("log_initDeleteDept"));
		try {
			if (checkDeleteDept(depNum, model)) {
				departmentService.deleteByPrimaryKey(depNum);
				model.addAttribute("deleteDept_success", properties.getProperty("deleteDept_success"));
				logger.info(properties.getProperty("log_deleteDept_success"));
				return selectAllDept(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_deleteDept_fail"), e);
			throw e;
		}
		return selectAllDept(model, 1, 5);

	}

	/**
	 * 部门信息更新页面跳转
	 */
	@RequestMapping("/toUpdateDept/{depNum}")
	public String toUpdateDept(@PathVariable(value = "depNum") String depNum, Model model) {

		Department department = departmentService.selectByPrimaryKey(depNum);
		model.addAttribute("department", department);
		logger.info(properties.getProperty("log_initUpdateDept"));
		return "dept/admin_editDept";

	}

	/**
	 * 部门信息更新（主键）
	 */
	@PostMapping("/updateDept")
	public String updateDeptByPrimaryKey(Department department, Model model) {

		try {
			if (checkUpdateDept(department, model)) {
				departmentService.updateByPrimaryKey(department);
				model.addAttribute("updateDept_success", properties.getProperty("updateDept_success"));
				logger.info(properties.getProperty("log_updateDept_success"));
				return selectAllDept(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_updateDept_fail"), e);
			throw e;
		}
		return toUpdateDept(department.getDepNum(), model);

	}

	/**
	 * 管理员：所有部门信息取得
	 */
	@RequestMapping("/selectAllDept")
	public String selectAllDept(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

		logger.info(properties.getProperty("log_initSelectAllDept"));
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<Department> departmentList = departmentService.selectAllDept();
			PageInfo<Department> pageInfo = new PageInfo<>(departmentList);
			model.addAttribute("pageInfo", pageInfo);
			logger.info(properties.getProperty("log_selectAllDept_success"));
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectAllDept_fail"), e);
			throw e;
		}
		return "dept/admin_depList";

	}

	/**
	 * 部门信息模糊查询
	 */
	@RequestMapping("/selectByInput")
	public String selectDeptByInput(Model model, HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

		logger.info(properties.getProperty("log_initSelectDeptByInput"));
		String input = request.getParameter("input");
		model.addAttribute("input", input);
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<Department> departmentList = departmentService.selectDeptByInput(input);
			if (departmentList.size() == 0) {
				model.addAttribute("selectDeptByInput_noResult", properties.getProperty("selectDeptByInput_noResult"));
				logger.info(properties.getProperty("log_selectDeptByInput_noResult"));
			}
			PageInfo<Department> pageInfo = new PageInfo<>(departmentList);
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectDeptByInput_fail"), e);
			throw e;
		}
		return "dept/admin_depList";

	}

	/**
	 * 部门追加校验
	 */
	private boolean checkInsertDept(Department department, Model model)
			throws IllegalArgumentException, IllegalAccessException {

		Field[] fields = department.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String dept = fields[i].get(department).toString();

			if (StringUtils.isEmptyOrWhitespace(dept)) {
				model.addAttribute("editDept_infoLack", properties.getProperty("editDept_infoLack"));
				logger.info(properties.getProperty("log_editDept_infoLack"));
				return false;
			}
		}
		Department existDeptName = departmentService.selectByDepName(department.getDepName());
		Department existDept = departmentService.selectByPrimaryKey(department.getDepNum());
		if (existDeptName != null) {
			model.addAttribute("existDeptName", properties.getProperty("existDeptName"));
			logger.info(properties.getProperty("log_insertDept_existDepName"));
			return false;
		}
		if (existDept != null) {
			model.addAttribute("deptExist", properties.getProperty("deptExist"));
			logger.info(properties.getProperty("log_insertDept_existDept"));
			return false;
		}
		return true;

	}

	/**
	 * 部门删除校验
	 */
	private boolean checkDeleteDept(String depNum, Model model) {

		List<Employee> list = employeeService.checkEmpExistDep(depNum);
		if (list.size() != 0) {
			model.addAttribute("empExistDept", properties.getProperty("empExistDept"));
			logger.info(properties.getProperty("log_deleteDept_empExistDept"));
			return false;
		}
		return true;

	}

	/**
	 * 部门更新校验
	 */
	private boolean checkUpdateDept(Department department, Model model) {

		String depNum = department.getDepNum();
		Department dept = departmentService.selectByPrimaryKey(depNum);
		String depName = department.getDepName();
		String chairmanNum = department.getChairmanNum();
		Department existDeptName = departmentService.selectByDepName(depName);
		model.addAttribute("depNum", department.getDepNum());

		Boolean isOwnDeptName = department.getDepName().equals(dept.getDepName());

		if (StringUtils.isEmptyOrWhitespace(depName) || StringUtils.isEmptyOrWhitespace(chairmanNum)) {
			model.addAttribute("editDept_infoLack", properties.getProperty("editDept_infoLack"));
			logger.info(properties.getProperty("log_editDept_infoLack"));
			return false;
		}
		if (!isOwnDeptName && existDeptName != null) {
			model.addAttribute("existDeptName", properties.getProperty("existDeptName"));
			logger.info(properties.getProperty("log_updateDept_existDeptName"));
			return false;
		}
		return true;

	}

}

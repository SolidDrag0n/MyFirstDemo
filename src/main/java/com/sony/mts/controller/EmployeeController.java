package com.sony.mts.controller;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sony.mts.base.AdminBaseController;
import com.sony.mts.entity.Department;
import com.sony.mts.entity.EmpProjectRela;
import com.sony.mts.entity.Employee;
import com.sony.mts.entity.Position;
import com.sony.mts.service.DepartmentService;
import com.sony.mts.service.EmpProjectRelaService;
import com.sony.mts.service.EmployeeService;
import com.sony.mts.service.PositionService;

/**
 * 员工信息Controller
 * 
 * @author 黄龙
 */
@Controller
@RequestMapping("/emp")
public class EmployeeController extends AdminBaseController {

	/** 员工信息Service */
	@Autowired
	private EmployeeService employeeService;
	/** 任务信息Service */
	@Autowired
	private EmpProjectRelaService eprservice;
	/** 部门信息Service */
	@Autowired
	private DepartmentService departmentService;
	/** 职位信息Service */
	@Autowired
	private PositionService positionService;

	/**
	 * 员工信息追加页面跳转
	 */
	@RequestMapping("/toInsertEmp")

	public String toInsertEmp(Model model) {

		logger.info(properties.getProperty("log_initInsertEmp"));

		List<Department> departmentList = departmentService.selectAllDept();
		model.addAttribute("departmentList", departmentList);
		List<Position> positionList = positionService.selectAllPos();
		model.addAttribute("positionList", positionList);

		return "emp/admin_editEmp";

	}

	/**
	 * 员工信息追加
	 */
	@PostMapping("/insertEmp")
	public String insertEmp(Employee employee, Model model) throws Exception {

		try {
			if (checkInsertEmp(employee, model)) {
				employee.setPassWd(getMD5(employee.getPassWd()));
				employeeService.insert(employee);
				model.addAttribute("insertEmp_success", properties.getProperty("insertEmp_success"));
				logger.info(properties.getProperty("log_insertEmp_success"));
				return selectAllEmp(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_insertEmp_fail"), e);
			throw e;
		}
		return toInsertEmp(model);

	}

	/**
	 * 员工信息删除（主键）
	 *
	 * @param empId
	 */
	@RequestMapping("/deleteEmp/{empId}")
	public String deleteEmpByPrimaryKey(@PathVariable(value = "empId") String empId, Model model) {

		logger.info(properties.getProperty("log_initDeleteEmp"));
		try {
			if (checkDeleteEmp(empId, model)) {
				employeeService.deleteByPrimaryKey(empId);
				model.addAttribute("deleteEmp_success", properties.getProperty("deleteEmp_success"));
				logger.info(properties.getProperty("log_deleteEmp_success"));
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_deleteEmp_fail"), e);
			throw e;
		}
		return selectAllEmp(model, 1, 5);

	}

	/**
	 * 员工信息批量删除(主键）
	 *
	 * @param idList
	 */
	@RequestMapping("/batchDeleteEmp")
	public String batchDeleteEmp(@RequestParam(value = "idList") List<String> idList, Model model) throws Exception {

		logger.info(properties.getProperty("log_initbatchDeleteEmp"));
		try {
			if (checkBatchDeleteEmp(model, idList)) {
				employeeService.batchDeleteEmp(idList);
				model.addAttribute("deleteEmp_success", properties.getProperty("deleteEmp_success"));
				logger.info(properties.getProperty("log_initbatchDeleteEmp_success"));
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_initbatchDeleteEmp_fail"), e);
			throw e;
		}
		return selectAllEmp(model, 1, 5);

	}

	/**
	 * 员工信息更新页面跳转
	 */
	@RequestMapping("/toUpdateEmp/{empId}")
	public String toUpdateEmp(@PathVariable(value = "empId") String empId, Model model) {

		logger.info(properties.getProperty("log_initUpdateEmp"));

		List<Department> departmentList = departmentService.selectAllDept();
		model.addAttribute("departmentList", departmentList);
		List<Position> positionList = positionService.selectAllPos();
		model.addAttribute("positionList", positionList);

		Employee emp = employeeService.selectByPrimaryKey(empId);
		model.addAttribute("emp", emp);

		return "emp/admin_editEmp";

	}

	/**
	 * 员工信息更新（主键）
	 */
	@PostMapping("/updateEmp")
	public String updateEmpByPrimaryKey(Employee employee, Model model) throws Exception {

		try {
			if (checkUpdateEmp(employee, model)) {
				employee.setPassWd(getMD5(employee.getPassWd()));
				employeeService.updateByPrimaryKey(employee);
				model.addAttribute("updateEmp_success", properties.getProperty("updateEmp_success"));
				logger.info(properties.getProperty("log_updateEmp_success"));
				return selectAllEmp(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_updateEmp_fail"), e);
			throw e;
		}
		return toUpdateEmp(employee.getEmpId(), model);

	}

	/**
	 * 管理员：员工、部门以及职位关联查询
	 */
	@RequestMapping("/selectAllEmp")
	public String selectAllEmp(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

		logger.info(properties.getProperty("log_initSelectAllEmp"));
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<Employee> employeeList = employeeService.selectEmpDeptPos();
			PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
			model.addAttribute("pageInfo", pageInfo);
			logger.info(properties.getProperty("log_selectAllEmp_success"));
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectAllEmp_fail"), e);
			throw e;
		}
		return "emp/admin_empList";

	}

	/**
	 * 员工信息模糊查询
	 */
	@RequestMapping("/selectByInput")
	public String selectEmpByInput(Model model, HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

		logger.info(properties.getProperty("log_selectEmpByInput"));
		String input = request.getParameter("input");
		model.addAttribute("input", input);
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<Employee> employeeList = employeeService.selectByInput(input);

			if (employeeList.size() == 0) {
				model.addAttribute("selectEmpByInput_noResult", properties.getProperty("selectEmpByInput_noResult"));
				logger.info(properties.getProperty("log_selectEmpByInput_noResult"));
			}
			PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectEmpByInput_fail"), e);
			throw e;
		}
		return "emp/admin_empList";

	}

	/**
	 * 管理员：跳转到主页面
	 */
	@RequestMapping(value = "admin/homePage")
	public String adminHomePage(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("aEmployee");
		model.addAttribute("employee", employee);
		return "homePage/detail_admin";
	}

	/**
	 * 员工追加校验
	 */
	private boolean checkInsertEmp(Employee employee, Model model)
			throws IllegalArgumentException, IllegalAccessException {

		Field[] fields = employee.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length - 2; i++) {

			fields[i].setAccessible(true);
			String emp = fields[i].get(employee).toString();

			if (StringUtils.isEmptyOrWhitespace(emp)) {
				model.addAttribute("editEmp_infoLack", properties.getProperty("editEmp_infoLack"));
				logger.info(properties.getProperty("log_editEmp_infoLack"));
				return false;
			}

		}
		Employee exist = employeeService.selectByPrimaryKey(employee.getEmpId());
		if (exist != null) {
			model.addAttribute("empExist", properties.getProperty("empExist"));
			logger.info(properties.getProperty("log_empExist"));
			return false;
		}
		return true;

	}

	/**
	 * 员工删除校验
	 */
	private boolean checkDeleteEmp(String empId, Model model) {

		List<EmpProjectRela> taskList = eprservice.selectByEmpId(empId);

		if (taskList.size() > 0) {
			model.addAttribute("empHasTask", properties.getProperty("empHasTask"));
			logger.info(properties.getProperty("log_empHasTask"));
			return false;
		}
		return true;

	}

	/**
	 * 员工更新校验
	 */
	private boolean checkUpdateEmp(Employee employee, Model model)
			throws IllegalArgumentException, IllegalAccessException {

		Field[] fields = employee.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length - 2; i++) {

			fields[i].setAccessible(true);

			String emp = fields[i].get(employee).toString();

			if (StringUtils.isEmptyOrWhitespace(emp)) {
				model.addAttribute("editEmp_infoLack", properties.getProperty("editEmp_infoLack"));
				model.addAttribute("empId", employee.getEmpId());
				logger.info(properties.getProperty("log_editEmp_infoLack"));
				return false;
			}

		}
		return true;

	}

	/**
	 * 员工批量删除校验
	 */
	private boolean checkBatchDeleteEmp(Model model, List<String> idList) {

		for (int i = 0; i < idList.size(); i++) {
			List<EmpProjectRela> taskList = eprservice.selectByEmpId(idList.get(i));
			if (taskList.size() > 0) {
				model.addAttribute("empHasTask", properties.getProperty("empHasTask"));
				logger.info(properties.getProperty("log_empHasTask"));
				return false;
			}
		}
		return true;

	}

	/**
	 * Md5加密
	 */
	private static String getMD5(String str) {
		String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
		return md5;
	}

}

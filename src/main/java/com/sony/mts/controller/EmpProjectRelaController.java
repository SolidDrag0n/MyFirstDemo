package com.sony.mts.controller;

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
import com.sony.mts.entity.EmpProjectRela;
import com.sony.mts.entity.Employee;
import com.sony.mts.entity.Project;
import com.sony.mts.service.EmpProjectRelaService;
import com.sony.mts.service.EmployeeService;
import com.sony.mts.service.ProjectService;

/**
 * 任务信息Controller
 * 
 * @author 黄龙
 */
@Controller
@RequestMapping("/epr")
public class EmpProjectRelaController extends AdminBaseController {

	/** 任务信息Service */
	@Autowired
	EmpProjectRelaService eprService;
	/** 员工信息Service */
	@Autowired
	EmployeeService employeeService;
	/** 项目信息Service */
	@Autowired
	ProjectService projectService;

	/**
	 * 任务分配页面跳转
	 */
	@RequestMapping("/toAssignTask")
	public String toAssignTask(Model model) {

		logger.info(properties.getProperty("log_initAssignTask"));

		List<Employee> employeeList = employeeService.selectAllEmp();
		model.addAttribute("employeeList", employeeList);
		List<Project> projectList = projectService.selectAllPro();
		model.addAttribute("projectList", projectList);

		return "task/admin_editTask";

	}

	/**
	 * 任务分配
	 */
	@PostMapping("/assignTask")
	public String assignTask(Model model, EmpProjectRela empProjectRela) {

		try {
			if (checkAssignTask(empProjectRela, model)) {
				eprService.insert(empProjectRela);
				model.addAttribute("assignTask_success", properties.getProperty("assignTask_success"));
				logger.info(properties.getProperty("log_assignTask_success"));
				return selectAllTask(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_assignTask_fail"), e);
			throw e;
		}
		return toAssignTask(model);

	}

	/**
	 * 任务信息删除（主键）
	 * 
	 * @param taskId
	 */
	@RequestMapping("/deleteTask/{taskId}")
	public String deleteTaskByPrimaryKey(@PathVariable(value = "taskId") String taskId, Model model) {

		logger.info(properties.getProperty("log_initDeleteTask"));
		try {
			eprService.deleteByPrimaryKey(taskId);
			model.addAttribute("deleteTask_success", properties.getProperty("deleteTask_success"));
			logger.info(properties.getProperty("log_deleteTask_success"));
			return selectAllTask(model, 1, 5);
		} catch (Exception e) {
			logger.error(properties.getProperty("log_deleteTask_fail"), e);
			throw e;
		}

	}

	/**
	 * 任务更新页面跳转
	 */
	@RequestMapping("/toUpdateTask/{taskId}")
	public String toUpdateTask(@PathVariable(value = "taskId") String taskId, Model model) {

		logger.info(properties.getProperty("log_initUpdateTask"));

		EmpProjectRela eProjectRela = eprService.selectByPrimaryKey(taskId);
		model.addAttribute("eProjectRela", eProjectRela);
		List<Employee> employeeList = employeeService.selectAllEmp();
		model.addAttribute("employeeList", employeeList);

		return "task/admin_editTask";

	}

	/**
	 * 任务更新（主键）
	 */
	@PostMapping("/updateTask")
	public String updateTaskByPrimaryKey(EmpProjectRela empProjectRela, Model model) {

		try {
			if (checkUpdateTask(empProjectRela, model)) {
				eprService.updateByPrimaryKey(empProjectRela);
				logger.info(properties.getProperty("log_updateTask_success"));
				model.addAttribute("updateTask_success", properties.getProperty("updateTask_success"));
				return selectAllTask(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_updateTask_fail"), e);
			throw e;
		}
		return toUpdateTask(empProjectRela.getTaskId(), model);

	}

	/**
	 * 管理员：全部任务信息取得
	 */
	@RequestMapping("/selectAllTask")
	public String selectAllTask(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

		try {
			PageHelper.startPage(pageNum, pageSize);
			logger.info(properties.getProperty("log_initSelectAllTask"));
			List<EmpProjectRela> empProjectRelaList = eprService.selectAllTask();
			PageInfo<EmpProjectRela> pageInfo = new PageInfo<>(empProjectRelaList);
			model.addAttribute("pageInfo", pageInfo);
			logger.info(properties.getProperty("log_selectAllTask_success"));
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectAllTask_fail"), e);
			throw e;
		}
		return "task/admin_taskList";

	}

	/**
	 * 任务信息模糊查询
	 */
	@RequestMapping("/selectByInput")
	public String selectTaskByInput(Model model, HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

		logger.info(properties.getProperty("log_initSelectTaskByInput"));
		String input = request.getParameter("input");
		model.addAttribute("input", input);
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<EmpProjectRela> empProjectRelaList = eprService.selectTaskByInput(input);
			if (empProjectRelaList.size() == 0) {
				model.addAttribute("selectTaskByInput_noResult", properties.getProperty("selectTaskByInput_noResult"));
				logger.info(properties.getProperty("log_selectTaskByInput_noResult"));
			}
			PageInfo<EmpProjectRela> pageInfo = new PageInfo<>(empProjectRelaList);
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectTaskByInput_fail"), e);
			throw e;
		}
		return "task/admin_taskList";

	}

	/**
	 * 任务分配校验
	 */
	private boolean checkAssignTask(EmpProjectRela empProjectRela, Model model) {

		EmpProjectRela taskExist = eprService.selectByPrimaryKey(empProjectRela.getTaskId());
		if (taskExist != null) {
			model.addAttribute("taskExist", properties.getProperty("taskExist"));
			logger.info(properties.getProperty("log_taskExist"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(empProjectRela.getEmpId())) {
			model.addAttribute("proEmpIsNull", properties.getProperty("proEmpIsNull"));
			logger.info(properties.getProperty("log_proEmpIsNull"));
			return false;
		}
		return true;

	}

	/**
	 * 任务更新校验
	 */
	private boolean checkUpdateTask(EmpProjectRela empProjectRela, Model model) {

		if (StringUtils.isEmptyOrWhitespace(empProjectRela.getEmpId())) {
			model.addAttribute("taskId", empProjectRela.getTaskId());
			model.addAttribute("taskEmpIsNull", properties.getProperty("taskEmpIsNull"));
			model.addAttribute("log_taskEmpIsNull", properties.getProperty("log_taskEmpIsNull"));
			return false;
		}
		return true;

	}

}
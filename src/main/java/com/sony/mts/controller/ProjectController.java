package com.sony.mts.controller;

import java.util.List;

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
import com.sony.mts.entity.Project;
import com.sony.mts.service.EmpProjectRelaService;
import com.sony.mts.service.ProjectService;

/**
 * 项目信息Controller
 * 
 * @author 黄龙
 */
@Controller
@RequestMapping("/pro")
public class ProjectController extends AdminBaseController {

	/** 项目信息Service */
	@Autowired
	private ProjectService projectService;
	/** 任务信息Service */
	@Autowired
	private EmpProjectRelaService empProjectRelaService;

	/**
	 * 项目追加页面跳转
	 */
	@RequestMapping("/toInsertPro")

	public String toInsertPro() {
		logger.info(properties.getProperty("log_initInsertPro"));
		return "pro/admin_editPro";
	}

	/**
	 * 项目追加
	 */
	@PostMapping("/insertPro")
	public String insertProject(Project project, Model model) {

		try {
			if (checkInsertPro(project, model)) {
				projectService.insert(project);
				logger.info(properties.getProperty("log_insertPro_success"));
				model.addAttribute("insertPro_success", properties.getProperty("insertPro_success"));
				return selectAllPro(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_insertPro_fail"), e);
			throw e;
		}
		return "pro/admin_editPro";

	}

	/**
	 * 项目信息删除（主键）
	 * 
	 * @param proNum
	 */
	@RequestMapping("/deletePro/{proNum}")
	public String deleteProByPrimaryKey(@PathVariable(value = "proNum") String proNum, Model model) {

		try {
			if (checkDeletePro(proNum, model)) {
				projectService.deleteByPrimaryKey(proNum);
				model.addAttribute("deletePro_success", properties.getProperty("deletePro_success"));
				logger.info(properties.getProperty("log_deletePro_success"));
				return selectAllPro(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_deletePro_fail"), e);
			throw e;
		}
		return selectAllPro(model, 1, 5);

	}

	/**
	 * 项目更新页面跳转
	 */
	@RequestMapping("/toUpdatePro/{proNum}")
	public String toUpdatePro(@PathVariable(value = "proNum") String proNum, Model model) {

		Project pro = projectService.selectByPrimaryKey(proNum);
		model.addAttribute("pro", pro);
		logger.info(properties.getProperty("log_initUpdatePro"));
		return "pro/admin_editPro";

	}

	/**
	 * 项目信息更新(主键）
	 */
	@PostMapping("/updatePro")
	public String updateProByPrimaryKey(Project project, Model model) {

		try {
			if (checkUpdatePro(project, model)) {
				projectService.updateByPrimaryKey(project);
				logger.info(properties.getProperty("log_updatePro_success"));
				model.addAttribute("updatePro_success", properties.getProperty("updatePro_success"));
				return selectAllPro(model, 1, 5);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_updatePro_fail"), e);
			throw e;
		}
		return toUpdatePro(project.getProNum(), model);

	}

	/**
	 * 管理员：全部项目信息取得
	 */
	@RequestMapping("/selectAllPro")
	public String selectAllPro(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

		logger.info(properties.getProperty("log_initSelectAllPro"));
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<Project> projectList = projectService.selectAllPro();
			logger.info(properties.getProperty("log_selectAllPro_success"));
			PageInfo<Project> pageInfo = new PageInfo<>(projectList);
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectAllPro_fail"), e);
			throw e;
		}
		return "pro/admin_proList";

	}

	/**
	 * 校验项目追加
	 */
	private boolean checkInsertPro(Project project, Model model) {

		String proNum = project.getProNum();
		String proName = project.getProName();

		Project existProName = projectService.selectByProName(proName);
		Project existPro = projectService.selectByPrimaryKey(proNum);

		if (StringUtils.isEmptyOrWhitespace(proNum) || StringUtils.isEmptyOrWhitespace(proName)) {
			model.addAttribute("editPro_infoLack", properties.getProperty("editPro_infoLack"));
			logger.info(properties.getProperty("log_editPro_infoLack"));
			return false;
		}
		if (existProName != null) {
			model.addAttribute("existProName", properties.getProperty("existProName"));
			logger.info(properties.getProperty("log_existProName"));
			return false;
		}
		if (existPro != null) {
			model.addAttribute("existPro", properties.getProperty("existPro"));
			logger.info(properties.getProperty("log_existPro"));
			return false;
		}
		return true;

	}

	/**
	 * 校验项目删除
	 */
	private boolean checkDeletePro(String proNum, Model model) {

		List<EmpProjectRela> list = empProjectRelaService.selectByProNum(proNum);
		if (list.size() != 0) {
			model.addAttribute("projecting", properties.getProperty("projecting"));
			logger.info(properties.getProperty("log_deletePro_projecting"));
			return false;
		}
		return true;

	}

	/**
	 * 校验项目更新
	 */
	private boolean checkUpdatePro(Project project, Model model) {

		String proName = project.getProName();

		if (StringUtils.isEmptyOrWhitespace(proName)) {
			model.addAttribute("editPro_infoLack", properties.getProperty("editPro_infoLack"));
			model.addAttribute("proNum", project.getProNum());
			logger.info(properties.getProperty("log_editPro_infoLack"));
			return false;
		}
		if (projectService.selectByProName(proName) != null) {
			model.addAttribute("existProName", properties.getProperty("existProName"));
			model.addAttribute("proNum", project.getProNum());
			logger.info(properties.getProperty("log_existProName"));
			return false;
		}
		return true;

	}

}
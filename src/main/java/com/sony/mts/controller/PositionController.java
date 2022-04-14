package com.sony.mts.controller;

import java.lang.reflect.Field;
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
import com.sony.mts.entity.Department;
import com.sony.mts.entity.Employee;
import com.sony.mts.entity.Position;
import com.sony.mts.service.DepartmentService;
import com.sony.mts.service.EmployeeService;
import com.sony.mts.service.PositionService;

/**
 * 职位信息Controller
 * 
 * @author 黄龙
 */
@Controller
@RequestMapping("/pos")
public class PositionController extends AdminBaseController {

	/** 职位信息Service */
	@Autowired
	private PositionService positionService;
	/** 部门信息Service */
	@Autowired
	private DepartmentService departmentService;
	/** 员工信息Service */
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 职位信息追加页面跳转
	 */
	@RequestMapping("/toInsertPos")
	public String toInsertPos(Model model) {
		logger.info(properties.getProperty("log_initInsertPos"));
		List<Department> departmentList = departmentService.selectAllDept();
		model.addAttribute("departmentList", departmentList);
		return "pos/admin_insertPos";
	}

	/**
	 * 职位信息追加
	 */
	@PostMapping("/insertPos")
	public String insertPosition(Position position, Model model) throws Exception {

		try {
			if (checkInsertPos(position, model)) {
				positionService.insert(position);
				model.addAttribute("insertPos_success", properties.getProperty("insertPos_success"));
				logger.info(properties.getProperty("log_insertPos_success"));
				return selectAllPos(model, 1, 10);
			}
		} catch (Exception e) {
			logger.error(properties.getProperty("log_insertPos_fail"), e);
			throw e;
		}
		return toInsertPos(model);

	}

	/**
	 * 职位信息删除
	 */
	@RequestMapping("/deletePos/{posNum}")
	public String deletePosition(@PathVariable(value = "posNum") String posNum, Model model) {

		logger.info(properties.getProperty("log_initdeletePos"));
		try {
			if (checkDeletePostion(posNum, model)) {
				positionService.deleteByPrimaryKey(posNum);
				model.addAttribute("deletePos_success", properties.getProperty("deletePos_success"));
			}

		} catch (Exception e) {
			logger.error(properties.getProperty("log_deletePos_fail"), e);
			throw e;
		}
		return selectAllPos(model, 1, 10);

	}

	/**
	 * 管理员：全部职位信息取得
	 */
	@RequestMapping("/selectAllPos")
	public String selectAllPos(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		try {
			PageHelper.startPage(pageNum, pageSize);
			logger.info(properties.getProperty("log_initSelectAllPos"));
			List<Position> positionList = positionService.selectAllPos();
			PageInfo<Position> pageInfo = new PageInfo<>(positionList);
			model.addAttribute("pageInfo", pageInfo);
			logger.info(properties.getProperty("log_selectAllPos_success"));
		} catch (Exception e) {
			logger.error(properties.getProperty("log_selectAllPos_fail"), e);
			throw e;
		}
		return "pos/admin_posList";

	}

	/**
	 * 职位追加校验
	 */
	private boolean checkInsertPos(Position position, Model model)
			throws IllegalArgumentException, IllegalAccessException {

		Field[] fields = position.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length - 1; i++) {

			fields[i].setAccessible(true);
			String pos = fields[i].get(position).toString();

			if (StringUtils.isEmptyOrWhitespace(pos)) {
				model.addAttribute("editPos_infoLack", properties.getProperty("editPos_infoLack"));
				logger.info(properties.getProperty("log_editPos_infoLack"));
				return false;
			}

		}
		Position existPosName = positionService.selectByPosName(position.getPosName());
		Position existPos = positionService.selectByPrimaryKey(position.getPosNum());
		if (existPosName != null || existPos != null) {
			model.addAttribute("existPos", properties.getProperty("existPos"));
			logger.info(properties.getProperty("log_existPos"));
			return false;
		}
		return true;

	}

	/**
	 * 职位删除校验
	 */
	private boolean checkDeletePostion(String posNum, Model model) {

		List<Employee> empHasPos = employeeService.selectEmpByPosNum(posNum);
		if (empHasPos.size() > 0) {
			model.addAttribute("empHasPos", properties.getProperty("empHasPos"));
			logger.info(properties.getProperty("log_deletePos_empHasPos"));
			return false;
		}
		return true;

	}

}
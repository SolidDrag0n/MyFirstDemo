package com.sony.mts.base;

import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sony.mts.controller.DepartmentController;
import com.sony.mts.controller.EmpProjectRelaController;
import com.sony.mts.controller.EmployeeController;
import com.sony.mts.controller.PositionController;
import com.sony.mts.controller.ProjectController;

/**
 * BaseController
 * 
 * @author 黄龙
 */
public class BaseController {

	/** 日志 */
	protected static final Logger logger = LogManager.getLogger();

	protected static Properties properties = new Properties();

	/** 加载错误信息 */
	/*
	 * 加载*.prompt_info.properties配置文件
	 * 字节流不支持直接写入或读取Unicode，因此乱码，所以使用InputStreamReader将字节转为字符
	 */
	static {

		try {
			properties.load(new InputStreamReader(DepartmentController.class.getClassLoader()
					.getResourceAsStream("prompt_info/dept_prompt_info.properties"), "UTF-8"));
		} catch (Exception e) {
			logger.error("读取dept_prompt_info.properties配置文件失败", e);
		}

	}

	static {

		try {
			properties.load(new InputStreamReader(EmployeeController.class.getClassLoader()
					.getResourceAsStream("prompt_info/emp_prompt_info.properties"), "UTF-8"));
		} catch (Exception e) {
			logger.error("读取emp_PromptInfo.properties配置文件失败", e);
		}

	}

	static {

		try {
			properties.load(new InputStreamReader(EmpProjectRelaController.class.getClassLoader()
					.getResourceAsStream("prompt_info/task_prompt_info.properties"), "UTF-8"));
		} catch (Exception e) {
			logger.error("读取task_prompt_info.properties配置文件失败", e);
		}

	}

	static {

		try {
			properties.load(new InputStreamReader(PositionController.class.getClassLoader()
					.getResourceAsStream("prompt_info/pos_prompt_info.properties"), "UTF-8"));
		} catch (Exception e) {
			logger.error("读取pos_prompt_info.properties配置文件失败", e);
		}

	}

	static {

		try {
			properties.load(new InputStreamReader(ProjectController.class.getClassLoader()
					.getResourceAsStream("prompt_info/pro_prompt_info.properties"), "UTF-8"));
		} catch (Exception e) {
			logger.error("读取pro_prompt_info.properties配置文件失败", e);
		}

	}

}

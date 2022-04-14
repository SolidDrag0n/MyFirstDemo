package com.sony.mts.entity;

/**
 * 任务实体类
 * 
 * @author 黄龙
 */
public class EmpProjectRela {

	/** 任务编号 */
	private String taskId;

	/** 员工编号 */
	private String empId;

	/** 项目编号 */
	private String proNum;

	/** Employee实体 */
	private Employee employee;

	/** Project实体 */
	private Project project;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getProNum() {
		return proNum;
	}

	public void setProNum(String proNum) {
		this.proNum = proNum;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
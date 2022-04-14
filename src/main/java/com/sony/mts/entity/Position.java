package com.sony.mts.entity;

/**
 * 职位实体类
 * 
 * @author 黄龙
 */
public class Position {

	/** 职位编号 */
	private String posNum;

	/** 部门编号 */
	private String depNum;

	/** 职位名称 */
	private String posName;

	/** Employee实体 */
	private Employee employee;

	public String getPosNum() {
		return posNum;
	}

	public void setPosNum(String posNum) {
		this.posNum = posNum;
	}

	public String getDepNum() {
		return depNum;
	}

	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
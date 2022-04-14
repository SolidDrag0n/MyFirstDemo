package com.sony.mts.entity;

/**
 * 部门实体类
 * 
 * @author 黄龙
 */
public class Department {

	/** 部门编号 */
	private String depNum;

	/** 部门名称 */
	private String depName;

	/** 负责人编号 */
	private String chairmanNum;

	public String getDepNum() {
		return depNum;
	}

	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getChairmanNum() {
		return chairmanNum;
	}

	public void setChairmanNum(String chairmanNum) {
		this.chairmanNum = chairmanNum;
	}

}
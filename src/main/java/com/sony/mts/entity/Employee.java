package com.sony.mts.entity;

/**
 * 员工实体类
 * 
 * @author 黄龙
 */
public class Employee {

	/** 员工编号 */
	private String empId;

	/** 部门编号 */
	private String depNum;

	/** 职位编号 */
	private String posNum;

	/** 员工姓名 */
	private String empName;

	/** 身份证号 */
	private String cardId;

	/** 性别 */
	private String sex;

	/** 手机号码 */
	private String mobileNum;

	/** 邮箱地址 **/
	private String emailAdr;

	/** 密码 */
	private String passWd;

	/** Department实体 */
	private Department department;

	/** Position实体 */
	private Position position;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDepNum() {
		return depNum;
	}

	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}

	public String getPosNum() {
		return posNum;
	}

	public void setPosNum(String posNum) {
		this.posNum = posNum;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEmailAdr() {
		return emailAdr;
	}

	public void setEmailAdr(String emailAdr) {
		this.emailAdr = emailAdr;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
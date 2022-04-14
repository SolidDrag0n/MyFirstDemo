package com.sony.mts.service;

import java.util.List;

import com.sony.mts.entity.Employee;

/**
 * 员工数据操作Service
 * 
 * @author 黄龙
 */
public interface EmployeeService {

	/**
	 * 员工信息追加
	 */
	int insert(Employee employee);

	/**
	 * 员工信息删除（主键）
	 * 
	 * @empId
	 */
	int deleteByPrimaryKey(String empId);

	/**
	 * 员工信息批量删除（主键）
	 * 
	 * @idList
	 */
	int batchDeleteEmp(List<String> idList);

	/**
	 * 员工信息更新（主键）
	 */
	int updateByPrimaryKey(Employee employee);

	/**
	 * 员工信息部分更新（主键）
	 */
	int updateByPrimaryKeySelective(Employee record);

	/**
	 * 员工信息取得
	 * 
	 * @param empId
	 */
	Employee selectByPrimaryKey(String empId);

	/**
	 * 员工信息取得
	 * 
	 * @param posNum
	 */
	List<Employee> selectEmpByPosNum(String posNum);

	/**
	 * 全部员工信息取得
	 */
	List<Employee> selectAllEmp();

	/**
	 * 员工、部门及职位信息取得
	 */
	List<Employee> selectEmpDeptPos();

	/**
	 * 员工登录验证
	 */
	Employee checkEmployee(String empId, String passWd);

	/**
	 * 员工隶属部门验证
	 * 
	 * @param depNum
	 */
	List<Employee> checkEmpExistDep(String depNum);

	/**
	 * 员工信息模糊查询
	 * 
	 * @param input
	 */
	List<Employee> selectByInput(String input);

}

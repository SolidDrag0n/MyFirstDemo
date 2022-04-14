package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sony.mts.dao.EmployeeMapper;
import com.sony.mts.entity.Employee;
import com.sony.mts.service.EmployeeService;

/**
 * 员工信息操作Service实现类
 * 
 * @author 黄龙
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;

	/**
	 * 员工信息追加
	 */
	@Override
	public int insert(Employee employee) {
		return employeeMapper.insert(employee);
	}

	/**
	 * 员工信息删除（主键）
	 * 
	 * @param empId
	 */
	@Override
	public int deleteByPrimaryKey(String empId) {
		return employeeMapper.deleteByPrimaryKey(empId);
	}

	/**
	 * 员工信息批量删除（主键）
	 * 
	 * @idList
	 */
	@Override
	public int batchDeleteEmp(List<String> idList) {
		return employeeMapper.batchDeleteEmp(idList);
	}

	/**
	 * 员工信息更新（主键）
	 */
	@Override
	public int updateByPrimaryKey(Employee employee) {
		return employeeMapper.updateByPrimaryKey(employee);
	}

	/**
	 * 员工信息部分更新（主键）
	 */
	@Override
	public int updateByPrimaryKeySelective(Employee record) {
		return employeeMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 全部员工信息取得
	 */
	@Override
	public List<Employee> selectAllEmp() {
		List<Employee> employeeList = employeeMapper.selectAllEmp();
		return employeeList;
	}

	/**
	 * 员工信息取得（主键）
	 * 
	 * @param empId
	 */
	@Override
	public Employee selectByPrimaryKey(String empId) {
		return employeeMapper.selectByPrimaryKey(empId);
	}

	/**
	 * 员工信息取得
	 * 
	 * @param posNum
	 */
	@Override
	public List<Employee> selectEmpByPosNum(String posNum) {
		List<Employee> employeeList = employeeMapper.selectEmpByPosNum(posNum);
		return employeeList;
	}

	/**
	 * 员工登录验证
	 */
	@Override
	public Employee checkEmployee(String empId, String passWd) {
		return employeeMapper.checkEmployee(empId, passWd);
	}

	/**
	 * 员工隶属部门验证
	 */
	@Override
	public List<Employee> checkEmpExistDep(String depNum) {
		List<Employee> employeeList = employeeMapper.checkEmpExistDep(depNum);
		return employeeList;
	}

	/**
	 * 员工信息模糊查询
	 * 
	 * @param input
	 */
	@Override
	public List<Employee> selectByInput(String input) {
		List<Employee> employeeList = employeeMapper.selectByInput(input);
		return employeeList;
	}

	/**
	 * 员工、部门及职位信息查询
	 */
	@Override
	public List<Employee> selectEmpDeptPos() {
		List<Employee> empDeptPosList = employeeMapper.selectEmpDeptPos();
		return empDeptPosList;
	}

}
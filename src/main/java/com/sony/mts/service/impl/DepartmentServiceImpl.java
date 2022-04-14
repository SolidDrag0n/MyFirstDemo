package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sony.mts.dao.DepartmentMapper;
import com.sony.mts.entity.Department;
import com.sony.mts.service.DepartmentService;

/**
 * 部门数据操作Service实现类
 * 
 * @author 黄龙
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentMapper departmentMapper;

	/**
	 * 部门信息追加
	 */
	@Override
	public int insert(Department record) {
		return departmentMapper.insert(record);
	}

	/**
	 * 部门信息删除（主键）
	 * 
	 * @depNum
	 */
	@Override
	public int deleteByPrimaryKey(String depNum) {
		return departmentMapper.deleteByPrimaryKey(depNum);
	}

	/**
	 * 部门信息更新（主键）
	 */
	@Override
	public int updateByPrimaryKey(Department record) {
		return departmentMapper.updateByPrimaryKey(record);
	}

	/**
	 * 部门信息部分更新（主键）
	 */
	@Override
	public int updateByPrimaryKeySelective(Department record) {
		int i = departmentMapper.updateByPrimaryKeySelective(record);
		return i;
	}

	/**
	 * 所有部门信息取得
	 */
	@Override
	public List<Department> selectAllDept() {
		List<Department> departmentList = departmentMapper.selectAllDept();
		return departmentList;
	}

	/**
	 * 部门信息取得（主键）
	 * 
	 * @param depNum
	 */
	@Override
	public Department selectByPrimaryKey(String depNum) {
		return departmentMapper.selectByPrimaryKey(depNum);
	}

	/**
	 * 部门信息取得
	 * 
	 * @param depName
	 */
	@Override
	public Department selectByDepName(String depName) {
		return departmentMapper.selectByDepName(depName);
	}

	/**
	 * 部门信息模糊查询
	 * 
	 * @param input
	 */
	@Override
	public List<Department> selectDeptByInput(String input) {
		List<Department> departmentList = departmentMapper.selectDeptByInput(input);
		return departmentList;
	}

}

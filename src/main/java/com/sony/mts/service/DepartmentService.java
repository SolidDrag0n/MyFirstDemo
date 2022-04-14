
package com.sony.mts.service;

import java.util.List;

import com.sony.mts.entity.Department;

/**
 * 部门数据操作Service
 * 
 * @author 黄龙
 */
public interface DepartmentService {

	/**
	 * 部门信息追加
	 */
	int insert(Department record);

	/**
	 * 部门信息删除（主键）
	 */
	int deleteByPrimaryKey(String depNum);

	/**
	 * 部门信息更新（主键）
	 */
	int updateByPrimaryKey(Department record);

	/**
	 * 部门信息部分更新（主键）
	 */
	int updateByPrimaryKeySelective(Department record);

	/**
	 * 所有部门信息取得
	 */
	List<Department> selectAllDept();

	/**
	 * 部门信息取得
	 * 
	 * @param depNum
	 */
	Department selectByPrimaryKey(String depNum);

	/**
	 * 部门信息取得
	 * 
	 * @param depName
	 */
	Department selectByDepName(String depName);

	/**
	 * 部门信息模糊查询
	 */
	List<Department> selectDeptByInput(String input);

}

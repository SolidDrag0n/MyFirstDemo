package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.Department;

/**
 * 部门信息Mapper
 * 
 * @author 黄龙
 */
@Mapper
public interface DepartmentMapper {

	/**
	 * 部门信息追加
	 */
	int insert(Department record);

	/**
	 * 部门信息删除
	 */
	int deleteByPrimaryKey(String depNum);

	/**
	 * 部门信息更新
	 */
	int updateByPrimaryKey(Department record);

	/**
	 * 部门信息部分更新
	 */
	int updateByPrimaryKeySelective(Department record);

	/**
	 * 部门信息取得（主键）
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
	 * 所有部门信息取得
	 */
	List<Department> selectAllDept();

	/**
	 * 部门信息模糊查询
	 */
	List<Department> selectDeptByInput(String input);

}
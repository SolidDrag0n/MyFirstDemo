package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.EmpProjectRela;

/**
 * 任务信息Mapper
 * 
 * @author 黄龙
 */
@Mapper
public interface EmpProjectRelaMapper {

	/**
	 * 任务信息追加
	 */
	int insert(EmpProjectRela record);

	/**
	 * 任务信息删除（主键）
	 */
	int deleteByPrimaryKey(String taskId);

	/**
	 * 任务信息更新（主键）
	 */
	int updateByPrimaryKey(EmpProjectRela record);

	/**
	 * 任务信息部分更新（主键）
	 */
	int updateByPrimaryKeySelective(EmpProjectRela record);

	/**
	 * 任务信息取得
	 * 
	 * @param taskId
	 */
	EmpProjectRela selectByPrimaryKey(String taskId);

	/**
	 * 所有任务信息取得
	 */
	List<EmpProjectRela> selectAllTask();

	/**
	 * 任务信息取得
	 * 
	 * @param empId（主键）
	 */
	List<EmpProjectRela> selectByEmpId(String empId);

	/**
	 * 任务信息取得
	 * 
	 * @param proNum
	 */
	List<EmpProjectRela> selectByProNum(String proNum);

	/**
	 * 任务模糊查询
	 */
	List<EmpProjectRela> selectTaskByInput(String input);

}
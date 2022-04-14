package com.sony.mts.service;

import java.util.List;

import com.sony.mts.entity.EmpProjectRela;

/**
 * 任务信息操作Service
 * 
 * @author 黄龙
 */
public interface EmpProjectRelaService {

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
	 * @param empId
	 */
	List<EmpProjectRela> selectByEmpId(String empId);

	/**
	 * 任务信息取得
	 * 
	 * @param proNum
	 */
	List<EmpProjectRela> selectByProNum(String proNum);

	/**
	 * 任务信息模糊查询
	 */
	List<EmpProjectRela> selectTaskByInput(String input);

}

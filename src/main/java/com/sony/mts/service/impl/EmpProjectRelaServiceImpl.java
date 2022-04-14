package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sony.mts.dao.EmpProjectRelaMapper;
import com.sony.mts.entity.EmpProjectRela;
import com.sony.mts.service.EmpProjectRelaService;

/**
 * 任务信息操作Service实现类
 * 
 * @author 黄龙
 */
@Service
@Transactional
public class EmpProjectRelaServiceImpl implements EmpProjectRelaService {

	@Autowired
	EmpProjectRelaMapper empProjectRelaMapper;

	/**
	 * 任务信息追加
	 */
	@Override
	public int insert(EmpProjectRela record) {
		return empProjectRelaMapper.insert(record);
	}

	/**
	 * 任务信息删除（主键）
	 */
	@Override
	public int deleteByPrimaryKey(String taskId) {
		return empProjectRelaMapper.deleteByPrimaryKey(taskId);
	}

	/**
	 * 任务信息更新（主键）
	 */
	@Override
	public int updateByPrimaryKey(EmpProjectRela record) {
		return empProjectRelaMapper.updateByPrimaryKey(record);
	}

	/**
	 * 任务信息部分更新
	 */
	@Override
	public int updateByPrimaryKeySelective(EmpProjectRela record) {
		return empProjectRelaMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 所有任务信息取得
	 */
	@Override
	public List<EmpProjectRela> selectAllTask() {
		List<EmpProjectRela> empProjectRelaList = empProjectRelaMapper.selectAllTask();
		return empProjectRelaList;
	}

	/**
	 * 任务信息取得(主键）
	 * 
	 * @param taskId
	 */
	@Override
	public EmpProjectRela selectByPrimaryKey(String taskId) {
		return empProjectRelaMapper.selectByPrimaryKey(taskId);
	}

	/**
	 * 任务信息取得（主键）
	 * 
	 * @param empId
	 */
	@Override
	public List<EmpProjectRela> selectByEmpId(String empId) {
		List<EmpProjectRela> empProjectRelaList = empProjectRelaMapper.selectByEmpId(empId);
		return empProjectRelaList;
	}

	/**
	 * 任务信息取得
	 * 
	 * @param proNum
	 */
	@Override
	public List<EmpProjectRela> selectByProNum(String proNum) {
		List<EmpProjectRela> empProjectRelaList = empProjectRelaMapper.selectByProNum(proNum);
		return empProjectRelaList;
	}

	/**
	 * 任务信息模糊查询
	 * 
	 * @param input
	 */
	@Override
	public List<EmpProjectRela> selectTaskByInput(String input) {
		List<EmpProjectRela> empProjectRelaList = empProjectRelaMapper.selectTaskByInput(input);
		return empProjectRelaList;
	}

}

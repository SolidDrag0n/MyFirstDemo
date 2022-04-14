package com.sony.mts.service;

import java.util.List;

import com.sony.mts.entity.Project;

/**
 * 项目信息操作Service
 * 
 * @author 黄龙
 */
public interface ProjectService {

	/**
	 * 项目信息追加
	 */
	int insert(Project project);

	/**
	 * 项目信息删除（主键）
	 */
	int deleteByPrimaryKey(String proNum);

	/**
	 * 项目信息更新（主键）
	 */
	int updateByPrimaryKey(Project project);

	/**
	 * 项目信息部分更新（主键）
	 */
	int updateByPrimaryKeySelective(Project record);

	/**
	 * 项目信息取得
	 * 
	 * @param proNum
	 */
	Project selectByPrimaryKey(String proNum);

	/**
	 * 全部项目信息取得
	 */
	List<Project> selectAllPro();

	/**
	 * 项目信息取得
	 * 
	 * @param proName
	 */
	Project selectByProName(String proName);

}

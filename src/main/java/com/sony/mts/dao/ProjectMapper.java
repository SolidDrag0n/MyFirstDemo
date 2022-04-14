package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.Project;

/**
 * 项目信息Mapper
 * 
 * @author 黄龙
 */
@Mapper
public interface ProjectMapper {

	/**
	 * 项目信息追加
	 */
	int insert(Project record);

	/**
	 * 项目信息删除（主键）
	 */
	int deleteByPrimaryKey(String proNum);

	/**
	 * 项目信息更新（主键）
	 */
	int updateByPrimaryKey(Project record);

	/**
	 * 项目信息部分更新（主键）
	 */
	int updateByPrimaryKeySelective(Project record);

	/**
	 * 全部项目信息取得
	 */
	List<Project> selectAllPro();

	/**
	 * 项目信息取得
	 * 
	 * @param proNum
	 */
	Project selectByPrimaryKey(String proNum);

	/**
	 * 项目信息取得
	 * 
	 * @param proName
	 */
	Project selectByProName(String proName);

}
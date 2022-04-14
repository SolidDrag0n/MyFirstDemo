package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sony.mts.dao.ProjectMapper;
import com.sony.mts.entity.Project;
import com.sony.mts.service.ProjectService;

/**
 * 项目信息操作Service实现类
 * 
 * @author 黄龙
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectMapper projectMapper;

	/**
	 * 项目信息追加
	 */
	@Override
	public int insert(Project record) {
		return projectMapper.insert(record);
	}

	/**
	 * 项目信息删除（主键）
	 */
	@Override
	public int deleteByPrimaryKey(String proNum) {
		return projectMapper.deleteByPrimaryKey(proNum);
	}

	/**
	 * 项目信息更新（主键）
	 */
	@Override
	public int updateByPrimaryKey(Project project) {
		return projectMapper.updateByPrimaryKey(project);
	}

	/**
	 * 项目信息部分更新（主键）
	 */
	@Override
	public int updateByPrimaryKeySelective(Project record) {
		return projectMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 全部项目信息取得
	 */
	@Override
	public List<Project> selectAllPro() {
		List<Project> projectList = projectMapper.selectAllPro();
		return projectList;
	}

	/**
	 * 项目信息取得
	 * 
	 * @param proNum
	 */
	@Override
	public Project selectByPrimaryKey(String proNum) {
		return projectMapper.selectByPrimaryKey(proNum);
	}

	/**
	 * 项目信息取得
	 * 
	 * @param proName
	 */
	@Override
	public Project selectByProName(String proName) {
		return projectMapper.selectByProName(proName);
	}

}

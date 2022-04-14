package com.sony.mts.service;

import java.util.List;

import com.sony.mts.entity.Position;

/**
 * 职位信息操作Service
 * 
 * @author 黄龙
 */
public interface PositionService {

	/**
	 * 职位信息追加
	 */
	int insert(Position record);

	/**
	 * 职位信息删除（主键）
	 */
	int deleteByPrimaryKey(String posNum);

	/**
	 * 职位信息更新（主键）
	 */
	int updateByPrimaryKey(Position position);

	/**
	 * 职位信息部分更新（主键）
	 */
	int updateByPrimaryKeySelective(Position record);

	/**
	 * 职位信息取得
	 * 
	 * @param posNum
	 */
	Position selectByPrimaryKey(String posNum);

	/**
	 * 职位信息取得
	 * 
	 * @param posName
	 */
	Position selectByPosName(String posName);

	/**
	 * 全部职位信息取得
	 */
	List<Position> selectAllPos();

	/**
	 * 员工与职位信息取得
	 */
	List<Position> selectEmpPos();

}
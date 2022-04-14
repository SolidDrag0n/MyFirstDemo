package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.Position;

/**
 * 职位信息Mapper
 * 
 * @author 黄龙
 */
@Mapper
public interface PositionMapper {

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
	int updateByPrimaryKey(Position record);

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
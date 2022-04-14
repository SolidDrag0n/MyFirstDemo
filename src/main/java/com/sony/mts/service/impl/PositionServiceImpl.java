package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sony.mts.dao.PositionMapper;
import com.sony.mts.entity.Position;
import com.sony.mts.service.PositionService;

/**
 * 职位信息操作Service实现类
 * 
 * @author 黄龙
 */
@Service
@Transactional
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionMapper positionMapper;

	/**
	 * 职位信息追加
	 */
	@Override
	public int insert(Position record) {
		return positionMapper.insert(record);
	}

	/**
	 * 职位信息删除（主键）
	 */
	@Override
	public int deleteByPrimaryKey(String posNum) {
		return positionMapper.deleteByPrimaryKey(posNum);
	}

	/**
	 * 职位信息更新（主键）
	 */
	@Override
	public int updateByPrimaryKey(Position record) {
		return positionMapper.updateByPrimaryKey(record);
	}

	/**
	 * 职位信息部分更新（主键）
	 */
	@Override
	public int updateByPrimaryKeySelective(Position record) {
		return positionMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 职位信息取得（主键）
	 * 
	 * @posNum
	 */
	@Override
	public Position selectByPrimaryKey(String posNum) {
		return positionMapper.selectByPrimaryKey(posNum);
	}

	/**
	 * 职位信息取得
	 * 
	 * @param posName
	 */
	@Override
	public Position selectByPosName(String posName) {
		return positionMapper.selectByPosName(posName);
	}

	/**
	 * 全部职位信息取得
	 */
	@Override
	public List<Position> selectAllPos() {
		List<Position> positionList = positionMapper.selectAllPos();
		return positionList;
	}

	/**
	 * 员工与职位信息取得
	 */
	@Override
	public List<Position> selectEmpPos() {
		return positionMapper.selectEmpPos();
	}

}

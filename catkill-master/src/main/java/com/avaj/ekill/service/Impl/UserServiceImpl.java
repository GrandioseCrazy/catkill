package com.avaj.ekill.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.avaj.ekill.mapper.UserMapper;
import com.avaj.ekill.model.User;
import com.avaj.ekill.service.UserService;


public class UserServiceImpl implements UserService {

	//导入依赖
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		//通过UserMapper查询数据库 
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(record);
	}

}

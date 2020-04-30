package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean findUserCheck(String param, Integer type) {
		//1.定义查询字段，type为1代表查询username，2查询phone，3查询email
		String column;
		column=(type==1)?"username":((type==2)?"phone":"email");
		//2.查询数据库中是否有数据？
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq(column, param);
		int rows=userMapper.selectCount(queryWrapper);
		
		return rows==0?false:true;
	}
	
	
	
}

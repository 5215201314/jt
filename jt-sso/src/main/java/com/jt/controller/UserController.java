package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/check/{param}/{type}")  //注册时校验用户名是否存在和手机是否正确
	public JSONPObject findCheckUser(String callback, @PathVariable String param,@PathVariable Integer type) {
		JSONPObject jsonpObject=null;
		try {
			boolean flag=userService.findUserCheck(param,type);
			jsonpObject=new JSONPObject(callback,flag);
		}catch(Exception e) {
			e.printStackTrace();
			jsonpObject=new JSONPObject(callback,e);
		}
		return jsonpObject;
	}
	
	@RequestMapping("/query/{token}")
	public JSONPObject findUserByToken(String callback,@PathVariable String token) {
		//1.根据秘钥查询用户信息
				String userJSON = jedisCluster.get(token);
				JSONPObject jsonpObject = null;
				if(StringUtils.isEmpty(userJSON)) {
					jsonpObject = new JSONPObject(callback,SysResult.fail());
				}else {
					//2.表示用户数据获取成功
					jsonpObject = new JSONPObject(callback,SysResult.success(userJSON));
				}
				return jsonpObject; 
	}
	
	
}

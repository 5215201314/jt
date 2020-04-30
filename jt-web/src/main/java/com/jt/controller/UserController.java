package com.jt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
	/*
	 * 利用dubbo注解 @Reference注入对象
	 */
	@Reference(timeout=3000,check=false)
	private DubboUserService dubboUserService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	//通用页面跳转
	@RequestMapping("{moduleName}")
	public String module(@PathVariable String moduleName) {
		return moduleName;
	}
	
	//用户注册
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
		System.out.println(user);
		dubboUserService.saveUser(user);
		return SysResult.success();
	}
	
	/**
	 * 1.关于Cookie声明周期问题
	 * cookie.setMaxAge(>0); cookie数据存活100秒
	 * cookie.setMaxAge(0);	 表示删除cookie
	 * cookie.setMaxAge(-1); 会话关闭cookie删除
	 * 
	 * 2.表示Cookie使用的权限问题
	 * www.baidu.com
	 *此Cookie设置权限为： cookie.setPath("/"); 则www.baidu.com/aa/1.html 页面 可以访问
	 * cookie.setpath("/bb")
	 * www.baidu.com/aa/1.html	不可以访问
	 * 
	 * 3.设定cookie共享	
	 * 	规定:每一个网址都有自己固定的Cookie信息.默认不能共享
	 * 	需求:
	 * 		www.jd.com	一级域名
	 * 		item.jd.com	二级域名
	 * 	要求在一级域名与二级域名实现cookie数据共享.
	 *  实现步骤:
	 *  	可以设定domain标签实现cookie共享.
	 * @param user
	 * @param response
	 * @return
	 */
	//用户单点登录
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletResponse response) {
		String token=dubboUserService.doLogin(user);//token 为存储到redis的用户信息对应的key
		//校验远程服务器返回数据是否为null
		if(StringUtils.isEmpty(token)) {
			return SysResult.fail();
		}
		//将token 数据吸入Cookie
		Cookie cookie=new Cookie("JT_TICKET",token);
		cookie.setMaxAge(7*24*3600);//cookie的生存时间设置
		cookie.setPath("/");
		cookie.setDomain("jt.com"); //设置Cookie共享
		response.addCookie(cookie);
		
		return SysResult.success();		
	}
	
	@RequestMapping("/logout")   //退出登录
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		//删除redis   key---jt_ticket---Cookie
		Cookie[] cookies=request.getCookies();
		String token=null;
		if(cookies.length>0){
			for(Cookie cookie:cookies) {
				if("JT_TICKET".equals(cookie.getName())) {
					token=cookie.getValue();
					break;
				}
			}
		}
		if(!StringUtils.isEmpty(token)) {
			jedisCluster.del(token);
			Cookie cookie=new Cookie("JT_TICKET","");
			cookie.setMaxAge(0);  //删除cookie
			cookie.setPath("/");
			cookie.setDomain("jt.com");
			response.addCookie(cookie);
			
		}
		System.out.println("退出咯---------");
		return "redirect:/"; //重定向到系统首页
	}
}
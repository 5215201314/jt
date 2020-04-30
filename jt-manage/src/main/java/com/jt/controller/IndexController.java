package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.Item;

@Controller
public class IndexController {
	/*
	 * RestFul风格
	 * 1.URL中的参数必须使用"/"分割
	 * 2.服务端获取数据时，必须使用“{}”包裹参数
	 * 3.使用@PathVariable注解实现数据转化
	 * 
	 * 参数可以不同名，但必须为注解的value赋值，如下,用参数abc接收请求路径的参数
	 * @PathVariable(value="moduleName") String abc
	 */
	//返回路径中请求的jsp文件
	  @RequestMapping("/page/{moduleName}") 
	  public String module(@PathVariable String moduleName) {
	  
	  return moduleName; }
	 
	  //localhost:8091/saveItem/手机/永动机/1000000
	  @RequestMapping("/saveItem/{title}/{sellPoint}/{price}")
	  @ResponseBody
	  public Item saveItem(@PathVariable String title,
		@PathVariable String sellPoint,
		@PathVariable Long price) {
		  Item item=new Item();
		  item.setTitle(title).setSellPoint(sellPoint).setPrice(price);
		  return item;
	  }
	  
	  @RequestMapping("/getMsg")
		@ResponseBody
		public String getMsg(){
			
			return "这是8093";
		}
	
}

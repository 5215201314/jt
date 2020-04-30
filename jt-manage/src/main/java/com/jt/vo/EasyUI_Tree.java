package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//vo:服务器数据与页面交互的对象 一般需要转化为json格式
@Data   //安装lombok插件
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUI_Tree {
	private Long id;  //分类ID号
	private String text; //分类名称
	private String state;  //open 打开， closed关闭
}

package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
@TableName("tb_item")
@JsonIgnoreProperties(ignoreUnknown=true)  //不同时存在get和set方法时使用此注解
public class Item extends BasePojo {
	@TableId(type=IdType.AUTO)
	 private Long  id ;       // 主键'商品ID，也是商品编号',
	 private String title;    // 商品标题,
	 private String sellPoint;   //卖点信息 
	 private Long price;       // 价格 '单位为：分',
	 private Integer num;    // 商品数量
	 private String barcode ; // 二维码
	 private String image;    // 商品图片
	 private Long cid;       // 商品分类信息      
	 private Integer status; //商品状态 1正常
	 
	 public String[] getImages() {  //为了满足item.jsp中了表达式${item.images[0]}获取image的值，el表达式取值调用get方法
		 return image.split(",");
	 }
}

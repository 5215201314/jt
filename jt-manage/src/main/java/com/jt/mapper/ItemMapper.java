package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;

public interface ItemMapper extends BaseMapper<Item>{
	List<Item> findItemByPage(
		@Param("startIndex")Integer startIndex,Integer rows);

	/**
	 * 关于Mybatis传参问题
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * 
	 * 规则:mybaits中默认是不允许多值传参,
	 * 需要用户将多值转化为单值
	 * 1.本身数据就是一个参数
	 * 2.将数据使用对象进行封装
	 * 3.使用集合进行封装  list array
	 * 4.无论参数是什么都可以封装为Map集合 使用 @Param
	 * @param ids
	 */
	void deleteItems(Long[] ids);

	void updateStatus(@Param("ids")Long[] ids,@Param("status")int status);
	
	
}

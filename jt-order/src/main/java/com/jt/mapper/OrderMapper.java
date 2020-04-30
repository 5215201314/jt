package com.jt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Order;


@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}

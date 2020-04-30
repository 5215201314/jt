package com.jt.pojo;


import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_order")
@Data
@Accessors(chain=true)
public class Order extends BasePojo{
	@TableField(exist=false)	//入库操作忽略该字段
	private OrderShipping orderShipping;
								//封装订单商品信息  一对多
	@TableField(exist=false)	//入库操作忽略该字段
	private List<OrderItem> orderItems;
	
	@TableId
    private String orderId;
    private String payment;  //实付金额
    private Integer paymentType; //支付类型
    private String postFee; //邮费
    private Integer status; //'状态：1、未付款 2、已付款3、未发货4、已发货5、交易成功6、交易关闭'
    private Date paymentTime; //支付时间
    private Date consignTime; //发货时间
    private Date endTime;//交易完成时间
    private Date closeTime;//交易关闭时间
    private String shippingName;//物流名称
    private String shippingCode;//物流单号
    private Long userId;//用户id
    private String buyerMessage;//买家留言
    private String buyerNick; //买家昵称
    private Integer buyerRate;//买家是否已评价

}
package com.jt.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Reference(timeout=3000,check=false)
	private DubboCartService dubboCartService;
	
	@Autowired
	private JedisCluster jedisCluster;
	/**
	 * 当用户点击购物车按钮时.应该展现用户的购物记录
	 * 业务实现:
	 * 	查询用户购物行为  userId 暂时写死 
	 * @return
	 */
	@RequestMapping("/show")
	public String show(Model model,HttpServletRequest request) {
		//User user = (User) request.getAttribute("JT_USER");
		Long userId = UserThreadLocal.get().getId();
		
		/* 通过Cookie获取redis中的key值，从而查找到保存在redis的用户信息，从而获取到用户id
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
			String userJson = jedisCluster.get(token);
			User user=ObjectMapperUtil.toObject(userJson, User.class);
			Long userId0=user.getId();
			System.out.println(userId0);
		}  
		*/
		
		List<Cart> cartList = 
		dubboCartService.findCartListByUserId(userId);
		
		model.addAttribute("cartList", cartList);
		return "cart"; //跳转购物车页面
	}
	/**
	 * 如果rest风格接收参数时与对象的属性名称一致
	 * 则可以使用对象接收
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(Cart cart) {
		Long userId = UserThreadLocal.get().getId();
		cart.setUserId(userId);
		dubboCartService.deleteCart(cart);
		return "redirect:/cart/show.html";//应该采用重定向方式获取数据
	}
	
	
	/**
	 * 该方法利用页面的表单提交获取参数.
	 * 之后应该跳转购物车展现页面
	 * @param cart
	 * @return
	 */
	@RequestMapping("/add/{itemId}")
	public String saveCart(Cart cart) {
		Long userId = UserThreadLocal.get().getId();
		cart.setUserId(userId);
		dubboCartService.saveCart(cart);
		return "redirect:/cart/show.html";	
	}
	
	//更新购物车
	//http://www.jt.com/cart/update/num/562379/10
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(Cart cart) {
		Long userId =UserThreadLocal.get().getId();
		cart.setUserId(userId);
		dubboCartService.updateCartNum(cart);
		return SysResult.success();
	}
}

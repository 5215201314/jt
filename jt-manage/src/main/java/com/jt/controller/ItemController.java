package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUI_Table;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/*
	 * 1.根据页面查询当页记录
	 * http://localhost:8091/page/query?page=1&rows=50
	 * springMvc取赋值过程
	 * httpServlet request
	 * p=request.getparamter("page");
	 * page=Integer.parseInt(p);
	 * 
	 */
	@RequestMapping("/query")
	public EasyUI_Table findItemByPage(Integer page,Integer rows) {
		return itemService.findItemByPage(page,rows);
	}
	
	/*
	 * 实现商品的新增
	 */
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		
		itemService.saveItem(item,itemDesc);
		return SysResult.success();
	}
	/*
	 * 实现商品的修改
	 */
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
		itemService.updateItem(item,itemDesc);
		return SysResult.success();
	}
	
	/*
	 * 请求传过来的参数var params={“ids”:1,2,3,4,5,}
	 *  如果参数是通过,号分割.
	 * 则接收时可以使用数组
	 */
	@RequestMapping("/delete")
	public SysResult deleteItem(Long... ids) {
		itemService.delteItem(ids );
		return SysResult.success();
	}
	
	//商品下架
	@RequestMapping("/instock")
	public SysResult itemInStock(Long[] ids) {
		int status=2;  //2表示下架
		itemService.updateStatus(ids,status);
		return SysResult.success();
	}
	//商品上架
		@RequestMapping("/reshelf")
		public SysResult itemReshelf(Long[] ids) {
			int status=1;  //1表示上架
			itemService.updateStatus(ids,status);
			return SysResult.success();
		}
		/**
		 * 根据商品id号,查询商品详情信息
		 */
		@RequestMapping("/query/item/desc/{itemId}")
		public SysResult findItemDescById
		(@PathVariable Long itemId) {
			//需要将返回值传给页面进行数据展现
			ItemDesc itemDesc = itemService.findItemDescById(itemId);
			return SysResult.success(itemDesc);
		}
	
	
	
	
	
}

package com.jt.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.anno.Cache_Find;
import com.jt.enu.KEY_ENUM;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;

@RestController
@RequestMapping("/web/item")
public class WebItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/findItemById/{itemId}")
	@Cache_Find(keyType=KEY_ENUM.AUTO)
	public Item findItemById(@PathVariable Long itemId) {
		return itemService.findItemById(itemId);
	}
	@RequestMapping("/findItemDescById/{itemId}")
	@Cache_Find(keyType=KEY_ENUM.AUTO)
	public ItemDesc findItemDescById(@PathVariable Long itemId) {
		return itemService.findItemDescById(itemId);
	}
	
}

package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUI_Table;

public interface ItemService {

	EasyUI_Table findItemByPage(Integer page, Integer rows);

	Item findItemById(Long itemId);

	ItemDesc findItemDescById(Long itemId);

	void saveItem(Item item,ItemDesc itemDesc);

	void updateItem(Item item,ItemDesc itemDesc);

	void delteItem(Long[] ids);

	void updateStatus(Long[] ids, int status);

	
	
}

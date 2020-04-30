package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.Item;
import com.jt.util.HttpClientService;
import com.jt.util.ObjectMapperUtil;

@Service
public class ItemServiceImpl implements ItemService {
@Autowired
HttpClientService client;
	@Override
	public Item findItemById(Long itemId) {
		String url="http://manage.jt.com/web/item/findItemById/"+itemId;
		 String response = client.doGet(url);
		return ObjectMapperUtil.toObject(response, Item.class);
	}

}

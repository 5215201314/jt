package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUI_Table;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUI_Table findItemByPage(Integer page, Integer rows) {
		int total=itemMapper.selectCount(null);
		List<Item> list = itemMapper.findItemByPage((page-1)*rows, rows);
		return new EasyUI_Table(total,list);
	}

	@Override
	public Item findItemById(Long itemId) {
		
		return itemMapper.selectById(itemId);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectById(itemId);
	}
	
	@Transactional  //添加事务控制
	@Override
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		//因为商品详情与商品是id一致.但是item数据是主键
		//自增只有入库之后才能获取主键信息.
		//答案:向数据库插入item时会返回Id值赋给item，
		//	所以执行itemMapper.insert(item);前后item.id 的值不一样
		itemDesc.setItemId(item.getId()) .setCreated(item.getCreated()).setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);;
		
	}
	
	//更新商品
	@Transactional
	@Override   
	public void updateItem(Item item,ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId()).setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
	}
	
	@Transactional
	@Override
	public void delteItem(Long[] ids) {
		//1.手写sql 复习mybatis用法
				//itemMapper.deleteItems(ids);
		
		//2使用mybatisplus
		List<Long> idList=Arrays.asList(ids); //将数组转化成集合
		itemMapper.deleteBatchIds(idList);
		itemDescMapper.deleteBatchIds(idList);
	}
	
	//修改状态信息
	@Override
	public void updateStatus(Long[] ids, int status) {
		/*
		 * Item item = new Item(); item.setStatus(status) .setUpdated(new Date());
		 * List<Long> longList = Arrays.asList(ids); UpdateWrapper<Item>
		 * upteUpdateWrapper = new UpdateWrapper<Item>(); upteUpdateWrapper.in("id",
		 * longList); itemMapper.update(item, upteUpdateWrapper);
		 */
		itemMapper.updateStatus(ids, status);
		
	}

	
	
	
	
	
	
	
	
}

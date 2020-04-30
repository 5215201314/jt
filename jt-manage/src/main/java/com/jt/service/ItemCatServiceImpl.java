package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUI_Tree;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	ItemCatMapper itemCatMapper;
	
	@Override
	public ItemCat findItemName(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.findItemName(itemCatId);
		return itemCat;
	}
	
	//查询商品分类信息
	@Override
	public List<EasyUI_Tree> findItemCatByParentId(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper=new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
		List<EasyUI_Tree> treeList=new ArrayList<EasyUI_Tree>();
		for(ItemCat itemCat:itemCatList) {
			Long id=itemCat.getId();
			String text=itemCat.getName();
			String state=itemCat.getIsParent()?"closed":"open";
			EasyUI_Tree tree=new EasyUI_Tree(id,text,state);
			treeList.add(tree);
		}
		return treeList;
	}
}

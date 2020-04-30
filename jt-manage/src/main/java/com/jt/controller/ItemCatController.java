package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.anno.Cache_Find;
import com.jt.enu.KEY_ENUM;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUI_Tree;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping("/queryItemName")
	@ResponseBody
	public String finditem(Long itemCatId) {
		 ItemCat itemCat = itemCatService.findItemName(itemCatId);
		 
		 return itemCat.getName();
	}
	
	//实现商品分类的树形结构查询
	@RequestMapping("/list")
	@ResponseBody
	@Cache_Find(keyType = KEY_ENUM.AUTO)
	public List<EasyUI_Tree> findItemCatByParentId(@RequestParam(name="id",defaultValue="0")Long parentId){
		return itemCatService.findItemCatByParentId(parentId); 
	}
}

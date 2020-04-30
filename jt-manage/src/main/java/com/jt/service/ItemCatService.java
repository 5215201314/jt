package com.jt.service;

import java.util.List;

import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUI_Tree;

public interface ItemCatService {
	ItemCat findItemName(Long itemCatId);

	List<EasyUI_Tree> findItemCatByParentId(Long parentId);

	
}

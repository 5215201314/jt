package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUI_Tree;

public interface ItemCatMapper extends BaseMapper<ItemCat> {
	@Select("select * from tb_item_cat where id=#{itemCatId}")
	ItemCat findItemName(Long itemCatId);

	List<EasyUI_Tree> findItemCatByParentId();
}

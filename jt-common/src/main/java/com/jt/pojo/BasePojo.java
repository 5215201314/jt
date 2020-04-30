package com.jt.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class BasePojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date created;  //创建时间
	private Date updated;//更新时间
}

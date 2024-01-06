package cn.novots.test.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data 
public class StaffPicksFeed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ItemsVo> items;
	
	private PagingInfo pagingInfo;
}

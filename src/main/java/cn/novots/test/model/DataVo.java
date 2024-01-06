package cn.novots.test.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StaffPicksFeed  staffPicksFeed;
}

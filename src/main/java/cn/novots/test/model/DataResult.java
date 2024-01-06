package cn.novots.test.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataVo  data;
}

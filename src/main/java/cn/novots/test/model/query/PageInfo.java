package cn.novots.test.model.query;

import java.io.Serializable;

import lombok.Data;

@Data
public class PageInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String from;
	
	private int limit;
	
	private String to;
	
}

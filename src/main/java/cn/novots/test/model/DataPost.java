package cn.novots.test.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataPost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private long clapCount;
}

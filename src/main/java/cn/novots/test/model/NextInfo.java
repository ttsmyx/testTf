package cn.novots.test.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class NextInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private int limit;

	private String from;

}

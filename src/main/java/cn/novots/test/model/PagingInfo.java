package cn.novots.test.model;

import java.io.Serializable;

import lombok.Data;

@Data 
public class PagingInfo implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NextInfo next;
	
}

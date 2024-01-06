package cn.novots.test.model.query;

import java.io.Serializable;

import lombok.Data;

@Data
public class Variable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PageInfo paging;
	
	private String tagSlug;
}

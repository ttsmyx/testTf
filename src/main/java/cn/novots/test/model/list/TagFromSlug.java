package cn.novots.test.model.list;

import java.io.Serializable;

import lombok.Data;

@Data
public class TagFromSlug implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private ViewerEdge  viewerEdge;
}

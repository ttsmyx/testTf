package cn.novots.test.model.list;

import java.io.Serializable;

import lombok.Data;

@Data
public class ViewerEdge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private RecommendedPostsFeed  recommendedPostsFeed;
}

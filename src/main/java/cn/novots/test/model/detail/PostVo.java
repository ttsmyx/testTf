package cn.novots.test.model.detail;

import java.io.Serializable;

import lombok.Data;

@Data
public class PostVo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String titleName;

	private ViewerEdge viewerEdge;
}

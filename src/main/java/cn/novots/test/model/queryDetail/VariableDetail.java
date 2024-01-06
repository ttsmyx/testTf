package cn.novots.test.model.queryDetail;

import java.io.Serializable;

import lombok.Data;

@Data
public class VariableDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String postId;
	
	private PostMeteringOptions  postMeteringOptions;
}

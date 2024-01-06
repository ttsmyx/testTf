package cn.novots.test.model.detail;

import java.io.Serializable;

import lombok.Data;

@Data
public class UploadResult implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String path;
	
	private String name;
	
}

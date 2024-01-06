package cn.novots.test.model.query;

import java.io.Serializable;

import lombok.Data;

/**
 * 列表查询对象
 * 
 * @author ttscjr
 *
 */
@Data
public class QueryInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String operationName;

	private String query;

	private Variable variables;

}

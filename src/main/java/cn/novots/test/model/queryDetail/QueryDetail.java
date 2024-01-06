package cn.novots.test.model.queryDetail;

import java.io.Serializable;

import lombok.Data;

/**
 * 列表查询对象
 * 
 * @author ttscjr
 *
 */
@Data
public class QueryDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String operationName;

	private String query;

	private VariableDetail variables;

}

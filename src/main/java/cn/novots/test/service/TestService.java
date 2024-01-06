package cn.novots.test.service;

import cn.novots.test.model.detail.PostVo;

/**
 * 
 * @author ttscjr
 *
 */
public interface TestService {

	public void createPdfList();
	
	public PostVo getDetail(String postId,String titleName);
}
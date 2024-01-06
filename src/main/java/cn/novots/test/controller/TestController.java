package cn.novots.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.novots.test.service.TestService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ttscjr
 *
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/888")
public class TestController {
	
	@Autowired
	private TestService testService;

	@GetMapping(value = "/createPdfList")
	public void createPdfList() {
		log.info("createPdfList.start");
		testService.createPdfList();
		log.info("createPdfList.end");
	}
	
	@GetMapping(value = "/downFile")
	public void downFile() {
		log.info("downFile.start");
		// TODO 
		log.info("downFile.end");
	}

	
}

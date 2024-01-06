package cn.novots.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.novots.test.model.detail.PostVo;
import cn.novots.test.model.detail.TextVo;
import cn.novots.test.model.detail.UploadResult;
import cn.novots.test.service.TestService;
import cn.novots.test.service.impl.TestServiceImpl;
import cn.novots.test.util.PdfCommon;
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
	public int createPdfList() {
		log.info("createPdfList.start");
		testService.createPdfList();
		log.info("createPdfList.end");
		return 200;
	}

	@GetMapping(value = "/download")
	public void download(HttpServletResponse response, HttpServletRequest request) {
		log.info("download.start");
		List<PostVo> pdfList = TestServiceImpl.pdfList;
		log.info("download.start.list={}", pdfList.size());
		if (CollectionUtils.isEmpty(pdfList)) {
			log.error("download.list.null");
			return;
		}
		List<UploadResult> files = new ArrayList<UploadResult>();
		for (PostVo vo : pdfList) {
			List<TextVo> list = vo.getViewerEdge().getFullContent().getBodyModel().getParagraphs();
			UploadResult file = PdfCommon.generatePDF(list, vo.getId(), vo.getTitleName());
			files.add(file);
		}
		try {
			writePdfZip(files, response);
		} catch (Exception e) {
			log.info("download.error", e);
		}
		log.info("download.end");
	}

	public static void writePdfZip(List<UploadResult> files, HttpServletResponse response) throws Exception {
		OutputStream os = response.getOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(os);
		// 被压缩文件
		for (UploadResult filePath : files) {
			File file = new File(filePath.getPath());
			// 读取file文件
			FileInputStream inputStream = new FileInputStream(file);
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zipOutputStream.putNextEntry(zipEntry);
			zipOutputStream.write(bytes);
			zipOutputStream.flush();
			inputStream.close();
			zipOutputStream.closeEntry();

		}
		// 删除文件
		for (UploadResult filePath : files) {
			File file = new File(filePath.getPath());
			file.delete();
		}
		// 关闭各种流
		zipOutputStream.closeEntry();
		zipOutputStream.close();
	}

}

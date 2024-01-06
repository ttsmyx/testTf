package cn.novots.test.util;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import cn.novots.test.model.detail.TextVo;
import cn.novots.test.model.detail.UploadResult;

public class PdfCommon {

	public static UploadResult generatePDF(List<TextVo> list, String fileName, String titleName) {
		Document document = null;
		try {
			document = PdfUtil.createDocument();

			UploadResult fileUploadResult = new UploadResult();
			fileUploadResult.setPath("D:\\" + fileName + ".pdf");
			fileUploadResult.setName(fileName);
			FileOutputStream fos = new FileOutputStream(fileUploadResult.getPath());
			PdfWriter.getInstance(document, fos);
			document.open();

			// 字体定义
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font titlefont = new Font(bfChinese, 16, Font.BOLD);
			Font textfont = new Font(bfChinese, 10, Font.NORMAL);

			// 生成居中标题
			Paragraph title = PdfUtil.createParagraph(titleName, titlefont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(new Paragraph("   "));

			for (TextVo textVo : list) {
				String concent = textVo.getText();
				Paragraph paragraph2 = new Paragraph(concent, textfont);
				paragraph2.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph2);
			}

			return fileUploadResult;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document != null)
				document.close();
		}
		return null;
	}
}

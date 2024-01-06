package cn.novots.test.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.draw.LineSeparator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PdfUtil333 {

	public static void setResponseContentType(HttpServletResponse response, String fileName)
			throws UnsupportedEncodingException {
		response.setContentType("application/pdf");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".pdf");
		response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
	}

	public static void downloadPdf(String title,Map<String, String> dataMap, HttpServletResponse response) {
		// 定义全局的字体静态变量
		Font titlefont;
		Font headfont;
		Font keyfont = null;
		Font textfont = null;
		Font content = null;
		Font space = null;
		Font space1 = null;
		Font space2 = null;
		Font space3 = null;
		// 最大宽度
		try {
			// 不同字体（这里定义为同一种字体：包含不同字号、不同style）
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			titlefont = new Font(bfChinese, 16, Font.BOLD);
			headfont = new Font(bfChinese, 14, Font.BOLD);
			keyfont = new Font(bfChinese, 22, Font.BOLD);
			textfont = new Font(bfChinese, 15, Font.NORMAL);
			content = new Font(bfChinese, 16, Font.NORMAL);
			space = new Font(bfChinese, 5, Font.NORMAL);
			space1 = new Font(bfChinese, 20, Font.NORMAL);
			space2 = new Font(bfChinese, 20, Font.NORMAL);
			space3 = new Font(bfChinese, 3, Font.NORMAL);

		} catch (Exception e) {
			e.printStackTrace();
		}
		BaseFont bf;
		Font font = null;
		try {
			// 创建字体
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			// 使用字体并给出颜色
			font = new Font(bf, 36, Font.BOLD, BaseColor.RED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Document document = new Document(new Rectangle(PageSize.A4));

		try {
			com.itextpdf.text.pdf.PdfWriter.getInstance(document, response.getOutputStream());
			// 打开PDF文件
			document.open();
			// 设置内容
			Paragraph paragraph = new Paragraph(title, font);
			// 居中设置
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			// 页眉横线
//			document.add(new Paragraph("\n", space2));
//			LineSeparator line = new LineSeparator(3f, 100, BaseColor.RED, Element.ALIGN_CENTER, 0f);
//			document.add(line);
//			document.add(new Paragraph("\n", space3));
//			LineSeparator lineStart = new LineSeparator(1f, 100, BaseColor.RED, Element.ALIGN_CENTER, 0f);
//			document.add(lineStart);
//
//			document.add(new Paragraph("\n", space));
//			String text = "深储整改〔 " + dataMap.get("${year}") + "〕" + dataMap.get("${sort}") + "号";
//			Paragraph paragraph0 = new Paragraph(text, content);
//			paragraph0.setAlignment(Element.ALIGN_RIGHT);
//			document.add(paragraph0);

			document.add(new Paragraph("\n"));
			Paragraph paragraph1 = new Paragraph(dataMap.get("${inspectionCompany}") + "关于", keyfont);
			paragraph1.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph1);

			document.add(new Paragraph("\n", space));
			String concent = dataMap.get("${planName}") + "发现问题整改的通知";
			Paragraph paragraph2 = new Paragraph(concent, keyfont);
			paragraph2.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph2);

			document.add(new Paragraph("\n"));
			Paragraph paragraph3 = new Paragraph(dataMap.get("${qymc}") + ":", content);
			paragraph3.setAlignment(Element.ALIGN_LEFT);
			document.add(paragraph3);

			document.add(new Paragraph("\n", space));
			String concent1 = "             现将" + dataMap.get("${kdmc}") + dataMap.get("${planName}")
					+ "检查发现问题及整改要求转给你司，请严格按期限要求进行整改，并将整改落实情况(含佐证材料及附件)通过深圳市粮食和物资储备信息管理平台反馈我中心。";
			Paragraph paragraph4 = new Paragraph(concent1, content);
			// 设置首行缩进
			paragraph4.setIndentationRight(2);
			document.add(paragraph4);

			document.add(new Paragraph("\n", space));
			Paragraph paragraph5 = new Paragraph("              特此通知。", content);
			paragraph5.setIndentationRight(2);// 右缩进2格
			document.add(paragraph5);

			document.add(new Paragraph("\n", space1));
			document.add(new Paragraph("\n", space1));
			// 附件
			Paragraph paragraph6 = new Paragraph("              附件:", content);
			paragraph6.setIndentationRight(2);// 右缩进2格
			document.add(paragraph6);

			document.add(new Paragraph("\n", space));
			Paragraph paragraph7 = new Paragraph(dataMap.get("${attachmentNameStr}"), content);
			document.add(paragraph7);

			document.add(new Paragraph("\n", space1));
			document.add(new Paragraph("\n", space1));
			document.add(new Paragraph("\n", space1));

			// 日期
			Paragraph paragraph8 = new Paragraph(dataMap.get("${date}"), content);
			// 向右
			paragraph8.setAlignment(Element.ALIGN_RIGHT);
			document.add(paragraph8);

			document.add(new Paragraph("\n", space1));
			// 落款
			Paragraph paragraph9 = new Paragraph(dataMap.get("${inspectionTeamLeaderInfo}"), content);
			paragraph9.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph9);

			// 页尾横线
//			document.add(new Paragraph("\n", space2));
//			document.add(new Paragraph("\n", space2));
//			LineSeparator lineEnd = new LineSeparator(3f, 100, BaseColor.RED, Element.ALIGN_CENTER, 0f);
//			document.add(lineEnd);
//			document.add(new Paragraph("\n", space3));
//			LineSeparator lineEnd1 = new LineSeparator(1f, 100, BaseColor.RED, Element.ALIGN_CENTER, 0f);
//			document.add(lineEnd1);

			// 关闭文档
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("导出pdf失败：{}", e);
		}

	}

}

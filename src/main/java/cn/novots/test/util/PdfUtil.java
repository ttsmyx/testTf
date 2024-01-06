package cn.novots.test.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfUtil {

	/**
	 * 创建document对象
	 */
	public static Document createDocument() {
		// 生成pdf
		Document document = new Document();
		// 页面大小
		Rectangle rectangle = new Rectangle(PageSize.A4);
		// 页面背景颜色
		rectangle.setBackgroundColor(BaseColor.WHITE);
		document.setPageSize(rectangle);
		// 页边距 左，右，上，下
		document.setMargins(20, 20, 20, 20);
		return document;
	}

	/**
	 * @param text 段落内容
	 * @return
	 */
	public static Paragraph createParagraph(String text, Font font) {
		Paragraph elements = new Paragraph(text, font);
		elements.setSpacingBefore(5);
		elements.setSpacingAfter(5);
		return elements;
	}

	/**
	 * 创建默认列宽，指定列数、水平(居中、右、左)的表格
	 *
	 * @param colNumber
	 * @param align
	 * @return
	 */
	public static PdfPTable createTable(int colNumber, int align) {
		PdfPTable table = new PdfPTable(colNumber);
		try {
			table.setLockedWidth(true);
			table.setHorizontalAlignment(align);
			table.getDefaultCell().setBorder(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	/**
	 * 创建单元格（指定字体、水平居..、单元格跨x列合并）
	 *
	 * @param value
	 * @param font
	 * @param align
	 * @param colspan
	 * @return
	 */
	public static PdfPCell createCell(String value, Font font, int align, int colspan) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		cell.setMinimumHeight(20f);
		return cell;
	}

}

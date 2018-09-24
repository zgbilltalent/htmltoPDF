package wqb.pdf.test;

import java.io.*;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * html转pdf
 * 
 * @author wqb
 *
 */
public class PdfDemoTest {

	public static void main(String[] args) throws Exception {
		//读取html的流
		InputStream inputStream = PdfDemoTest.class.getResourceAsStream("11.html");

//		//流转换成字符串
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = inputStream.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}

        String html = out.toString();

		String pdffile = "/Users/zhanggai/Desktop/ww.pdf";

		OutputStream os = new FileOutputStream(pdffile);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		// writer.setPageEvent(header);
		ITextFontResolver fontResolver = renderer.getFontResolver();

		fontResolver.addFont("file:/Users/zhanggai/Downloads/htmlToPdfDemo/src/SIMSUN.TTC",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();

	}


	/**
	 * 通过网站域名URL获取该网站的源码
	 *
	 * @param url
	 * @return String
	 * @throws Exception
	 */
	public static String getURLSource(InputStream inStream) throws Exception
	{

		// 通过输入流获取html二进制数据
		byte [] data = readInputStream(inStream); // 把二进制数据转化为byte字节数据
		String htmlSource = new String(data);

		inStream.close();
		return htmlSource;
	}


	/**
	 * 把二进制流转化为byte字节数组
	 *
	 * @param instream
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte [] readInputStream(InputStream instream) throws Exception
	{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte [] buffer = new byte[1204];
		int len = 0;
		while ((len = instream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, len);
		}
		instream.close();
		return outStream.toByteArray();
	}


	// 支持中文
	public static void htmlToPdf(String htmlstr,String cssSource) throws Exception
	{
		String outputFile = "/Users/zhanggai/Desktop/test.pdf";
		Document document = new Document();
		PdfWriter writer = null;
		writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
		document.open();

		InputStream bis = new ByteArrayInputStream(htmlstr.toString().getBytes());
		InputStream cssis = new ByteArrayInputStream(cssSource.toString().getBytes());

//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, bis,cssis,new myFontProvider());
//
//
//		document.close();
	}



}

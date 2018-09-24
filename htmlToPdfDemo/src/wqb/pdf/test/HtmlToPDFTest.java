package wqb.pdf.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HtmlToPDFTest {


    public static void main(String[] args) throws Exception {

        parseHtmlToPdf();

    }

    /**
     * 使用 iText XML Worker实现HTML转PDF
     * itextpdf-5.5.6.ja
     * @throws Exception
     */
    public static void parseHtmlToPdf() throws Exception {
        String htmlFile ="/Users/zhanggai/Desktop/11.html";
        String pdfFile = "/Users/zhanggai/Desktop/22.pdf";
        InputStream htmlFileStream = new FileInputStream(htmlFile);

        // 创建一个document对象实例
        Document document = new Document();
        // 为该Document创建一个Writer实例
        PdfWriter pdfwriter = PdfWriter.getInstance(document,
                new FileOutputStream(pdfFile));
        pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
        // 打开当前的document
        document.open();

        InputStreamReader isr = new InputStreamReader(htmlFileStream, "UTF-8");
        XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, isr);
        document.close();
    }

}

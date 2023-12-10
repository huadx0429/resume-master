package master.resume.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

@Component
@Slf4j
public class PdfUtil {

    //wkhtmltopdf在系统中的路径
    private static final String toPdfTool = "C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";

    /**
     * html转pdf
     *
     * @param destPath pdf保存路径
     * @return 转换成功返回true
     */
    public boolean convert(Map<String, Object> data, String destPath) {
        boolean result = true;
        try {
            // 获取模板文件
            Configuration configuration = new Configuration(Configuration.getVersion());
            configuration.setClassForTemplateLoading(this.getClass(), "/pdfTemplate");
            Template template = null;
            try {
                template = configuration.getTemplate("test.html");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringWriter out = new StringWriter();
            // 合并模板跟数据
            template.process(data, out);
            // htmlData 模板字符流
            String htmlData = out.toString();
            byte[] htmlDataBytes = htmlData.getBytes();
            File fileTem = File.createTempFile("tmp", ".html");
            OutputStream os = new FileOutputStream(fileTem);
            os.write(htmlDataBytes);
            os.close();
            //调用wkhtmltopdf命令
            System.out.println("创建临时文件");
            File file = new File(destPath);
            File parent = file.getParentFile();
            //如果pdf保存路径不存在，则创建路径
            if (!parent.exists()){
                log.info("文件夹不存在");
                parent.mkdirs();
            }else {
                log.info("文件夹已存在");
            }
            StringBuilder cmd = new StringBuilder();
            if (OsInfoUtil.isLinux()){
                cmd.append("/usr/local/bin/wkhtmltopdf ");
            }else {
                cmd.append(toPdfTool);
            }
            cmd.append(" ");
            cmd.append("--enable-local-file-access ");
            cmd.append("--disable-smart-shrinking ");
            cmd.append("--page-size A4 ");
            cmd.append("--margin-bottom 0 ");
            cmd.append("--margin-left 0 ");
            cmd.append("--margin-right 0 ");
            cmd.append("--margin-top 0 ");
            cmd.append(fileTem.getPath());
            cmd.append(" ");
            cmd.append(destPath);
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
            log.info("开始转换: "+cmd);
            error.start();
            output.start();
            proc.waitFor();
            log.info("转换完毕");
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        //将文件打包并写入oss
        return result;
    }

    static class PdfPageHelper extends PdfPageEventHelper {
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            super.onOpenDocument(writer, document);
            writer.getDirectContent().createTemplate(30, 16);
        }

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            super.onStartPage(writer, document);
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            BaseFont bf = null;
            try {
                bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            extracted(writer, document, bf);
            // 添加水印
            addWaterMark(writer, bf);
        }

        /**
         * 添加水印
         */
        private void addWaterMark(PdfWriter writer, BaseFont bf) {
            PdfContentByte cb = writer.getDirectContent();
            cb.saveState();
            cb.beginText();
            cb.setColorFill(BaseColor.GRAY);
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            cb.setGState(gs);
            cb.setFontAndSize(bf, 100);
            // rotation指倾斜度 可以根据自己的需求做调整 不需要倾斜的话可以调整为0
            cb.showTextAligned(Element.ALIGN_MIDDLE, "总装一线", 100, 450, 0);
            cb.endText();
            cb.restoreState();
        }
    }

    static class MyFontsProvider extends XMLWorkerFontProvider {

        private int fontSize;

        public MyFontsProvider(int fontSize) {
            this.fontSize = fontSize;
        }

        @Override
        public Font getFont(final String fontName, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color) {
            BaseFont bf = null;
            try {
                bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
            Font font = null;
            if (fontSize != 0) {
                font = new Font(bf, fontSize, style, color);
            } else {
                font = new Font(bf, size, style, color);
            }
            font.setColor(color);
            return font;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

    }

    static class PdfReportM1HeaderFooter extends PdfPageEventHelper {

        /**
         * 页眉
         */
        public String header = "页眉";

        /**
         * 文档字体大小，页脚页眉最好和文本大小一致
         */
        public int presentFontSize = 12;

        // 模板
        public PdfTemplate total;

        private BaseFont bf = null;

        public PdfReportM1HeaderFooter() {

        }

        /**
         * 文档打开时创建模板
         */
        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
        }

        /**
         * 关闭每页的时候，写入页眉，写入'第几页共'这几个字。
         *
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onEndPage(PdfWriter writer, Document document) {
            Font fontDetail = null;
            try {
                bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
                fontDetail = new Font(bf, presentFontSize, Font.NORMAL);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 1.写入页眉
//            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(header, fontDetail), document.left(), document.top() + 20, 0);
            // 2.写入前半部分的 第 X页/共
            int pageS = writer.getPageNumber();
            String foot1 = "第 " + pageS + " 页 /共";
            Phrase footer = new Phrase(foot1, fontDetail);
            // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len
            float len = bf.getWidthPoint(foot1, presentFontSize);
            // 4.拿到当前的PdfContentByte
            PdfContentByte cb = writer.getDirectContent();
            // 5.写入页脚
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, (document.rightMargin() + document.right() + document.leftMargin() - document.left() - len) / 2.0F + 20F, document.bottom() - 20, 0);

            cb.addTemplate(total, (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 20F, document.bottom() - 20);

        }

        /**
         * 关闭文档时，替换模板，完成整个页眉页脚组件
         */
        public void onCloseDocument(PdfWriter writer, Document document) {
            // 关闭文档的时候，将模板替换成实际的 Y 值,至此，page x of y 制作完毕，完美兼容各种文档size。
            total.beginText();
            total.setFontAndSize(bf, presentFontSize);// 生成的模版的字体、颜色
            String foot2 = " " + (writer.getPageNumber()) + " 页";
            total.showText(foot2);// 模版显示的内容
            total.endText();
            total.closePath();
        }
    }


}


package com.tav.common.util.pdf;

import java.awt.Color;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 *
 * @author truongtx5
 *
 */
public class PdfUtil {

    private static Logger LOGGER = Logger.getLogger(PdfUtil.class);

    /**
     * Check file is PDF
     *
     * @param fileName
     * @return
     */
    /**
     * Create water-mark
     *
     * @param inputFilePath
     * @param outputFilePath
     * @param watermark
     * @return
     * @throws Exception
     */
    public static String createWatermark(String inputFilePath,
            String outputFilePath, String watermark) throws Exception {
        PdfReader pdfReader = null;
        PdfStamper pdfStamper = null;
        boolean success = false;
        try {
            pdfReader = new PdfReader(inputFilePath);
            pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(
                    outputFilePath));
            int numPages = pdfReader.getNumberOfPages();
            PdfContentByte underContent;
            PdfGState pdfGState;
            BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            int[] height = {-700, -300, 0, 300, 700};
            for (int i = 1; i <= numPages; i++) {
                underContent = pdfStamper.getUnderContent(i);
                pdfGState = new PdfGState();
                pdfGState.setFillOpacity(0.8f);
                underContent.setGState(pdfGState);
                underContent.beginText();
                underContent.setFontAndSize(baseFont, 12);
                underContent.setColorFill(Color.LIGHT_GRAY);
                for (int j : height) {
                    underContent.showTextAligned(Element.ALIGN_CENTER,
                            watermark, 0, j, 45);
                }
                underContent.endText();
            }
            success = true;
        } catch (Exception e) {
            LOGGER.error("Error on create watermark. InputFilePath = "
                    + inputFilePath, e);
        } finally {
            try {
                if (pdfStamper != null) {
                    pdfStamper.close();
                }
                if (pdfReader != null) {
                    pdfReader.close();
                }
            } catch (Exception e2) {
                LOGGER.error("Error on closing PdfStamper and PdfReader", e2);
            }
        }
        if (success) {
            return outputFilePath;
        }
        return null;
    }

    /**
     * Create water-mark
     *
     * @param inputFilePath
     * @param outputFilePath
     * @param watermark
     * @return
     * @throws Exception
     */
    public static String addAnnotation(String inputFilePath,
            String outputFilePath, String watermark) throws Exception {
//        PdfReader pdfReader = null;
//        PdfStamper pdfStamper = null;
        boolean success = false;
        try {
//            pdfReader = new PdfReader(inputFilePath);
//            pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(
//                    outputFilePath));
//            int numPages = pdfReader.getNumberOfPages();
//            PdfContentByte underContent;
//            PdfGState pdfGState;
//            BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN,
//                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//            int[] height = { -700, -300, 0, 300, 700 };
//            for (int i = 1; i <= numPages; i++) {
//                underContent = pdfStamper.getUnderContent(i);
//                pdfGState = new PdfGState();
//                pdfGState.setFillOpacity(0.8f);
//                underContent.setGState(pdfGState);
//                underContent.beginText();
//                underContent.setFontAndSize(baseFont, 12);
//                underContent.setColorFill(Color.LIGHT_GRAY);
//                for (int j : height) {
//                    underContent.showTextAligned(Element.ALIGN_CENTER,
//                            watermark, 0, j, 45);
//                }
//                underContent.endText();
//            }
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("D:/generic_annotations.pdf"));
            writer.setPageEvent(new GenericAnnotations());
            // step 3
            document.open();
            // step 4
            Paragraph p = new Paragraph();
            Chunk chunk;
//            Chunk tab = new Chunk(new VerticalPositionMark());
            for (int i = 0; i < GenericAnnotations.getICONS().length; i++) {
                chunk = new Chunk(GenericAnnotations.getICONS()[i]);
                chunk.setGenericTag(GenericAnnotations.getICONS()[i]);
                p.add(chunk);
//                p.add(tab);
            }
            document.add(p);
            // step 5
            document.close();
            success = true;
        } catch (Exception e) {
            LOGGER.error("Error on create watermark. InputFilePath = "
                    + inputFilePath, e);
        } finally {
//            try {
//                if (pdfStamper != null) {
//                    pdfStamper.close();
//                }
//                if (pdfReader != null) {
//                    pdfReader.close();
//                }
//            } catch (Exception e2) {
//                LOGGER.error("Error on closing PdfStamper and PdfReader", e2);
//            }
        }
        if (success) {
            return outputFilePath;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        PdfUtil.addAnnotation(null, null, null);
    }

}

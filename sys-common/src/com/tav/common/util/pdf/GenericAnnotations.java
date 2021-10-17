/**
 *
 */
package com.tav.common.util.pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfBoolean;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

/**
 * @author TruongTX5
 *
 */
public class GenericAnnotations extends PdfPageEventHelper {

    /**
     * The resulting PDF.
     */
    public static final String RESULT = "D:/generic_annotations.pdf";
    /**
     * Possible icons.
     */
    private static final String[] ICONS = {"Comment", "Key", "Note", "Help",
        "NewParagraph", "Paragraph", "Insert"};

    /**
     * Main method.
     *
     * @param args no arguments needed
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(String[] args) throws IOException,
            DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(RESULT));
        writer.setPageEvent(new GenericAnnotations());
        // step 3
        document.open();
        // step 4
        Paragraph p = new Paragraph();
        Chunk chunk;
        Chunk tab = new Chunk(new VerticalPositionMark());
        for (int i = 0; i < ICONS.length; i++) {
            chunk = new Chunk(ICONS[i]);
            chunk.setGenericTag(ICONS[i]);
            p.add(chunk);
            p.add(tab);
        }
        document.add(p);
        // step 5
        document.close();
    }

    /**
     * @see
     * com.itextpdf.text.pdf.PdfPageEventHelper#onGenericTag(com.itextpdf.text.pdf.PdfWriter,
     * com.itextpdf.text.Document, com.itextpdf.text.Rectangle,
     * java.lang.String)
     */
    @Override
    public void onGenericTag(PdfWriter writer, Document document,
            Rectangle rect, String text) {
        PdfAnnotation annotation = new PdfAnnotation(writer, new Rectangle(
                rect.getRight() + 10, rect.getBottom(), rect.getRight() + 30,
                rect.getTop()));
        annotation.setTitle("Text annotation");
        annotation.put(PdfName.SUBTYPE, PdfName.TEXT);
        annotation.put(PdfName.OPEN, PdfBoolean.PDFFALSE);
        annotation.put(PdfName.CONTENTS,
                new PdfString(String.format("Icon: %s", text)));
        annotation.put(PdfName.NAME, new PdfName(text));
        writer.addAnnotation(annotation);
    }

    public static String[] getICONS() {
        return ICONS;
    }

}

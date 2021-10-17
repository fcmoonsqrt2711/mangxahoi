package com.tav.common.util.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.itextpdf.text.pdf.parser.Vector;
import com.tav.common.util.AppConstants;


public class PdfUtils {
    private static final Logger logger = Logger.getLogger(PdfUtils.class);    

    /**
	 * 
	 * @param pdfFile
	 * @param searchText
	 * @return
	 * @throws IOException 
	 */
	private static List<TextLocationInfo> findPositions(String pdfFile, String searchText) throws IOException {
		List<TextLocationInfo> result = new ArrayList<TextLocationInfo>();
        PdfReader reader = new PdfReader(pdfFile);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        SearchTextExtractionStrategy strategy;
        for (int idx = 1; idx <= reader.getNumberOfPages(); idx++) {
            strategy = parser.processContent(idx, new SearchTextExtractionStrategy(idx, searchText));
			if (strategy != null) {
				List<TextLocationInfo> locations = strategy.getLocations();
				if ((locations != null) && (locations.size() > 0)) {
					result.addAll(locations);
				}
			}
        }
        reader.close();
		return result;
    }
    
    /**
     * 
     * @param pdfFile
     * @param searchText
     */
    public static void addSignPlaceHolder(String pdfFile, String searchText) {
    	try {
    		List<TextLocationInfo> locations = findPositions(pdfFile, searchText);
    		if ((locations != null) && (locations.size() > 0)) {
    			TextLocationInfo location = locations.get(0);
    			Rectangle position = location.getPosition();
    	        String outputFile = pdfFile + ".tmp";
    	    	PdfReader reader = new PdfReader(pdfFile);
    	    	PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
    	    	PdfAnnotation annotation;
    	    	float pageHeight = reader.getPageSize(reader.getNumberOfPages()).getHeight();
    	    	int shiftY = -10;
    	    	int page = location.getPage();
    	    	float x = position.getLeft() + 40;
    	    	float y = position.getTop() + shiftY;
    	    	
    	    	if ((y < 1) && (page < reader.getNumberOfPages())) {
    	    		page += 1;
    	    		y = pageHeight + (2 * shiftY);
    	    	}
    	    	Rectangle rectangle = new Rectangle(x, y, x, y);    	    	
    	    	annotation = PdfAnnotation.createText(stamper.getWriter(), rectangle, "Signature", "1", true, "Comment");
    	        stamper.addAnnotation(annotation, page);
    	        stamper.close();
    	        reader.close();
    	        
    	        // Thay the file cu
    	        (new File(pdfFile)).delete();
    	        File oldFile = new File(pdfFile);
    	        new File(outputFile).renameTo(oldFile);
    		}
    	} catch (Exception ex) {
    		logger.error("Error when adding signature place holder: ", ex);
    	}
    }
}


/**
*
* @author tond
*/
class SearchTextExtractionStrategy implements TextExtractionStrategy {
	private Integer page;
	private String searchText;
	private StringBuilder builder = new StringBuilder();
	private List<TextLocationInfo> locations = new ArrayList<TextLocationInfo>();

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	/**
	 * 
	 * @param builder 
	 */
	public void setBuilder(StringBuilder builder) {
		this.builder = builder;
	}

	/**
	 * 
	 * @return 
	 */
	public List<TextLocationInfo> getLocations() {
		return locations;
	}

	/**
	 * 
	 * @param locations 
	 */
	public void setLocations(List<TextLocationInfo> locations) {
		this.locations = locations;
	}
	
	/**
	 * 
	 * @param page
	 * @param searchText 
	 */
	public SearchTextExtractionStrategy(Integer page, String searchText) {
		this.page = page;
		this.searchText = searchText;
	}
	
	@Override
	public String getResultantText() {
		String result = null;
		
		if (locations.size() > 0) {
			result = searchText;
		}
		return result;
	}

	@Override
	public void beginTextBlock() {
	}

	@Override
	public void renderText(TextRenderInfo renderInfo) {
		builder.append(renderInfo.getText());
		if (builder.toString().endsWith(searchText)) {
			builder.setLength(0);
			Vector startPoint = renderInfo.getBaseline().getStartPoint();
			Vector endPoint = renderInfo.getAscentLine().getEndPoint();
			Rectangle rectangle = new Rectangle(startPoint.get(Vector.I1), startPoint.get(Vector.I2), endPoint.get(Vector.I1), endPoint.get(Vector.I2));
			locations.add(new TextLocationInfo(page, searchText, rectangle));
		}
	}

	@Override
	public void endTextBlock() {
	}

	@Override
	public void renderImage(ImageRenderInfo iri) {
	}	
}

/**
 * 
 * @author tond
 *
 */
class TextLocationInfo {
	private int page;
	private String search;
	private Rectangle position;

	/**
	 * 
	 * @param page
	 * @param search
	 * @param position 
	 */
	public TextLocationInfo(Integer page, String search, Rectangle position) {
		this.page = page;
		this.search = search;
		this.position = position;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public Rectangle getPosition() {
		return position;
	}
	
	public void setPosition(Rectangle position) {
		this.position = position;
	}
}


package com.tav.common.util.excel;

public class ImportError {
	/**
	 * Lỗi không đúng kiểu dữ liệu
	 */
	public static final int TYPE_ERROR = 0;
	/**
	 * Lỗi sai định dạng dữ liệu
	 */
	public static final int FORMAT_ERROR = 1;
	/**
	 * Lỗi dữ liệu file excel
	 */
	public static final int EXCEL_ERROR = 2;

	/**
	 * Lỗi không có dữ liệu với trường hợp bắt buộc
	 */
	public static final int EMPTY_ERROR = 3;

	/**
	 * Lỗi trùng dữ liệu
	 */
	public static final int DUPLICATE_ERROR = 4;
	/**
	 * Vượt quá độ dài quy định
	 */
	public static final int MAXLENGTH_ERROR = 5;

	/**
	 * Không đủ độ dài quy định
	 */
	public static final int MINLENGTH_ERROR = 7;

	/**
	 * Dữ liệu không tồn tại
	 */
	public static final int NON_EXIST_ERROR = 6;

	/**
	 * Lỗi phân quyền
	 */
	public static final int PERMISSION_ERROR = 8;

	private int row;
	private int column;
	private String columnName = "";
	private int errorType;
	private String errorInfo;
	private String errorDetail = "";

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public String getErrorTypeName() {
		switch (errorType) {
		case DUPLICATE_ERROR:
			return "Trùng dữ liệu";
		case EMPTY_ERROR:
			return "Chưa nhập dữ liệu";
		case FORMAT_ERROR:
			return "Định dạng dữ liệu sai quy định";
		case MAXLENGTH_ERROR:
			return "Độ dài vượt quá quy định (" + errorDetail + ")";
		case MINLENGTH_ERROR:
			return "Độ dài nhỏ hơn quy định tối thiểu (" + errorDetail + ")";
		case TYPE_ERROR:
			return "Kiểu dữ liệu không đúng quy định";
		case NON_EXIST_ERROR:
			return "Dữ liệu không tồn tại";
		case PERMISSION_ERROR:
			return "Lỗi phân quyền";
		default:
			return "Unknown";
		}
	}
}

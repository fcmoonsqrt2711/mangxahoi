package com.tav.common.util.excel;

import java.util.List;

public class ImportExcelException extends Exception {
	public static final int ERROR_WHEN_READ = 0;
	public static final int ERROR_AFTER_READ = 1;
	private static final long serialVersionUID = 1L;
	private List<ImportError> errorList;
	private ImportError error;
	private List<?> dataList;
	private int type = ERROR_WHEN_READ;

	public ImportExcelException() {
		super();
	}

	public ImportExcelException(ImportError error) {
		super();
		this.error = error;
	}

	public List<ImportError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ImportError> errorList) {
		this.errorList = errorList;
	}

	public ImportError getError() {
		return error;
	}

	public void setError(ImportError error) {
		this.error = error;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}

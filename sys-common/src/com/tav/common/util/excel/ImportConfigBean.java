package com.tav.common.util.excel;

public class ImportConfigBean {
	private String name = "";
	private boolean require = false;
	private int maxlength = -1;
	private int minlength = 0;
	private String constrain = "";
	private int validateType = ImportConfig.TYPE_NOTHING;
	private String[] validateValues;
	private boolean duplicate = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRequire() {
		return require;
	}

	public void setRequire(boolean require) {
		this.require = require;
	}

	public int getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	public int getMinlength() {
		return minlength;
	}

	public void setMinlength(int minlength) {
		this.minlength = minlength;
	}

	public String getConstrain() {
		return constrain;
	}

	public void setConstrain(String constrain) {
		this.constrain = constrain;
	}

	public int getValidateType() {
		return validateType;
	}

	public void setValidateType(int validateType) {
		this.validateType = validateType;
	}

	public String[] getValidateValues() {
		return validateValues;
	}

	public void setValidateValues(String[] validateValues) {
		this.validateValues = validateValues;
	}

	public boolean isDuplicate() {
		return duplicate;
	}

	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}
}

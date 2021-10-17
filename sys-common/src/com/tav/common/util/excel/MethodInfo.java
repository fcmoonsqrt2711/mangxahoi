package com.tav.common.util.excel;

import java.lang.reflect.Method;

/**
 * Lớp lưu dữ liệu trung gian
 * 
 * @author pmdn_tutm3
 * @Time: Mar 20, 2013
 */
public class MethodInfo {
	private Method method = null;
	private ImportConfigBean configBean;
	private Class<?> paramClass;

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public ImportConfigBean getConfigBean() {
		return configBean;
	}

	public void setConfigBean(ImportConfigBean configBean) {
		this.configBean = configBean;
	}

	public Class<?> getParamClass() {
		return paramClass;
	}

	public void setParamClass(Class<?> paramClass) {
		this.paramClass = paramClass;
	}
}

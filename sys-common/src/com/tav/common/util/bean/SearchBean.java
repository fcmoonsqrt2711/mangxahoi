package com.tav.common.util.bean;

/**
 * @Author gpdn_ducln
 *
 * @param <T>
 */
public class SearchBean<T> {

	private T bean;
	private boolean checked;
	
	public SearchBean(T bean, boolean checked){
		this.bean = bean;
		this.checked = checked;
	}

	public T getBean() {
		return bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}

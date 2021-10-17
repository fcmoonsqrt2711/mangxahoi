/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.base.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kdvt_binhnt22@viettel.com.vn
 * @version 1.0
 * @since since_text
 */
public class ConditionBean {

    private String field;
    private String stringValue;
    private Date dateValue;
    private Long longValue;
    private Integer intValue;
    private Double doubleValue;
    private String operator;
    private String type;
    private List listValue = new ArrayList();

    public ConditionBean() {

    }

    public ConditionBean(String field, String operator, String value) {
        this.field = field;
        this.stringValue = value;
        this.operator = operator;
    }

    public ConditionBean(String field, String operator, String value, String type) {
        this.field = field;
        this.stringValue = value;
        this.operator = operator;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public List getListValue() {
        return listValue;
    }

    public void setListValue(List listValue) {
        this.listValue = listValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public static final String TYPE_STRING = "STRING";
    public static final String TYPE_LIST = "LIST";
    public static final String TYPE_DOUBLE = "DOUBLE";
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String TYPE_LONG = "LONG";
    public static final String TYPE_DATE = "DATE";
}

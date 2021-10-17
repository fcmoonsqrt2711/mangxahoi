/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.pojo;

/**
 *
 * @author thuannht
 */
public class OrderBean {

    public final static String DESC = " desc ";
    public final static String ASC = " asc ";
    public final static String DELIMITER = " , ";
    private String field;
    private String operator;

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

}

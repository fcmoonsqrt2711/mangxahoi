/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.ignite.base;

/**
 *
 * @author HungLQ9
 */
public class BusinessBean {

    private String className;
    private String methodName;
    private Class[] methodParam;

    public BusinessBean(String className, String methodName, Class[] methodParam) {
        this.className = className;
        this.methodName = methodName;
        this.methodParam = methodParam;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getMethodParam() {
        return methodParam;
    }

    public void setMethodParam(Class[] methodParam) {
        this.methodParam = methodParam;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.webservice.base.client;

/**
 *
 * @author thuannht
 */
public class WsEndpointConfig {

    private String fullClassName;
    private String address;
    private String targetNameSpace;
    private String userName;
    private String password;
    private int connectTimeout = 30000;
    private int receiverTimeout = 30000;
    private String passwordCallbackClass;
    private int maxPoolSize = 1000;
    private int maxIdleTime = 30000;
    private int maxWaitMillis = 30000;

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getMaxIdleTime() {
        return maxIdleTime;
    }

    public void setMaxIdleTime(int maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getReceiverTimeout() {
        return receiverTimeout;
    }

    public void setReceiverTimeout(int receiverTimeout) {
        this.receiverTimeout = receiverTimeout;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTargetNameSpace() {
        return targetNameSpace;
    }

    public void setTargetNameSpace(String targetNameSpace) {
        this.targetNameSpace = targetNameSpace;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getPasswordCallbackClass() {
        return passwordCallbackClass;
    }

    public void setPasswordCallbackClass(String passwordCallbackClass) {
        this.passwordCallbackClass = passwordCallbackClass;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.bean;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Apple family
 */
public class MessageObject {
    
    private Long messageId;
    private String userSend;
    private List<String> listUserReceive=new ArrayList<String>();
    private Long deptSend;
    private List<Long> listDeptReceive=new ArrayList<Long>();
    private String content;
    private String timeSendText;
    private String timeReceive;
    private Hashtable<String,String> keyValue=new Hashtable<String,String>() ;
    private Long addTime=0L;
    private int type;

    public String getUserSend() {
        return userSend;
    }

    public void setUserSend(String userSend) {
        this.userSend = userSend;
    }

    public List<String> getListUserReceive() {
        return listUserReceive;
    }

    public void setListUserReceive(List<String> listUserReceive) {
        this.listUserReceive = listUserReceive;
    }

    public Long getDeptSend() {
        return deptSend;
    }

    public void setDeptSend(Long deptSend) {
        this.deptSend = deptSend;
    }

    public List<Long> getListDeptReceive() {
        return listDeptReceive;
    }

    public void setListDeptReceive(List<Long> listDeptReceive) {
        this.listDeptReceive = listDeptReceive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeSendText() {
        return timeSendText;
    }

    public void setTimeSendText(String timeSendText) {
        this.timeSendText = timeSendText;
    }

    public String getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(String timeReceive) {
        this.timeReceive = timeReceive;
    }

    public Hashtable getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(Hashtable keyValue) {
        this.keyValue = keyValue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
        
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.bean;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Apple family
 */
@Scope("singleton")
@Component
public class MessageList {
    private List<MessageObject> listMessageObject=new ArrayList<MessageObject>();

    public List<MessageObject> getListMessageObject() {
        return listMessageObject;
    }

    public void setListMessageObject(List<MessageObject> listMessageObject) {
        this.listMessageObject = listMessageObject;
    }
    
    
}

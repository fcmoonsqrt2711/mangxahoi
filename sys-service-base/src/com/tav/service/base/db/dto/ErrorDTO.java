/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.db.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HungLQ9
 */
@XmlRootElement(name = "error")
public class ErrorDTO {

    private String statusCode;
    private String errorMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}

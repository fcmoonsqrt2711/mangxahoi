/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.common;

/**
 *
 * @author KhietDT
 */
public class Config {
    
    private String restURL;
    
    private String cert_restURL;
    
    private String cecm_restURL;
    
    private String cer_restURL;
    
    private String cet_restURL;
    
    private String ced_restURL;
    
    private String cee_restURL;
    
    private String cem2_restURL;
    
    private String cebm_restURL;
    
    private String cem1_restURL;
    
    private String encryptionKey;
    
    private String nationalPhoneCode;
    
    private String sourceRequest; // for sending sms
    
    private Long requestTemplateId; // for sending sms
    
    private String mgcert_serviceServer;    //mongodb service
    private String mgcert_serviceLocal;

    public String getCert_restURL() {
        return cert_restURL;
    }

    public void setCert_restURL(String cert_restURL) {
        this.cert_restURL = cert_restURL;
    }

    public String getCet_restURL() {
        return cet_restURL;
    }

    public void setCet_restURL(String cet_restURL) {
        this.cet_restURL = cet_restURL;
    }

    public String getCed_restURL() {
        return ced_restURL;
    }

    public void setCed_restURL(String ced_restURL) {
        this.ced_restURL = ced_restURL;
    }

    public String getCee_restURL() {
        return cee_restURL;
    }

    public void setCee_restURL(String cee_restURL) {
        this.cee_restURL = cee_restURL;
    }

    public String getCem2_restURL() {
        return cem2_restURL;
    }

    public void setCem2_restURL(String cem2_restURL) {
        this.cem2_restURL = cem2_restURL;
    }

    public String getCebm_restURL() {
        return cebm_restURL;
    }

    public void setCebm_restURL(String cebm_restURL) {
        this.cebm_restURL = cebm_restURL;
    }

    public String getCem1_restURL() {
        return cem1_restURL;
    }

    public void setCem1_restURL(String cem1_restURL) {
        this.cem1_restURL = cem1_restURL;
    }
    
    
    public String getCecm_restURL() {
        return cecm_restURL;
    }

    public void setCecm_restURL(String cecm_restURL) {
        this.cecm_restURL = cecm_restURL;
    }

    public String getCer_restURL() {
        return cer_restURL;
    }

    public void setCer_restURL(String cer_restURL) {
        this.cer_restURL = cer_restURL;
    }
    public String getSourceRequest() {
        return sourceRequest;
    }

    public void setSourceRequest(String sourceRequest) {
        this.sourceRequest = sourceRequest;
    }

    public Long getRequestTemplateId() {
        return requestTemplateId;
    }

    public void setRequestTemplateId(Long requestTemplateId) {
        this.requestTemplateId = requestTemplateId;
    }
    
    public String getRestURL() {
        return restURL;
    }

    public void setRestURL(String restURL) {
        this.restURL = restURL;
    }

    public String getMgcert_serviceServer() {
        return mgcert_serviceServer;
    }

    public void setMgcert_serviceServer(String mgcert_serviceServer) {
        this.mgcert_serviceServer = mgcert_serviceServer;
    }

    public String getMgcert_serviceLocal() {
        return mgcert_serviceLocal;
    }

    public void setMgcert_serviceLocal(String mgcert_serviceLocal) {
        this.mgcert_serviceLocal = mgcert_serviceLocal;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public String getNationalPhoneCode() {
        return nationalPhoneCode;
    }

    public void setNationalPhoneCode(String nationalPhoneCode) {
        this.nationalPhoneCode = nationalPhoneCode;
    }
    
}

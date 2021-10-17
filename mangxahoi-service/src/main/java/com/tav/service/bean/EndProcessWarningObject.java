/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.bean;

/**
 *
 * @author Apple family
 */
public class EndProcessWarningObject {

    private Long warningId;
    private String codeDevice;
    private Long deptId;
    private String userName;
    private Long warningIdTmp;

    public Long getWarningId() {
        return warningId;
    }

    public void setWarningId(Long warningId) {
        this.warningId = warningId;
    }

    public String getCodeDevice() {
        return codeDevice;
    }

    public void setCodeDevice(String codeDevice) {
        this.codeDevice = codeDevice;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getWarningIdTmp() {
        return warningIdTmp;
    }

    public void setWarningIdTmp(Long warningIdTmp) {
        this.warningIdTmp = warningIdTmp;
    }
    
    

}

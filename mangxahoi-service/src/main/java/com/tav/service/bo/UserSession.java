/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.bo;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;

/**
 *
 * @author KhietDT
 */
public class UserSession {

    protected static final Logger logger = Logger.getLogger(UserSession.class);

    private final static int UPDATE_DURATION = 60000;

    private Long gid;

    private String userName;
    private String fullName;
    private Date lastUpdateTime;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private Set<String> setPermittedUrls = new HashSet<>();

    private Map<String, String> mapPermittedUrls = new HashMap<>();

    private long userStatusUpdateTime;

    private long permissionUpdateTime;

    private final AtomicInteger failedAuthNum = new AtomicInteger();

    private AtomicInteger failedResetCodeCount = null;

    private AtomicInteger failedSubmitCount = null;

    private boolean expiredPassword = false;

    private boolean isSysAdmin = false;

    private boolean isLocked = false;

    private String ipAddress; // permitted IP
    

   

    public Set<String> getSetPermittedUrls() {
        return setPermittedUrls;
    }

    public void setSetPermittedUrls(Set<String> setPermittedUrls) {
        this.setPermittedUrls = setPermittedUrls;
    }

    public Map<String, String> getMapPermittedUrls() {
        return mapPermittedUrls;
    }


    public int getFailedAuthNum() {
        return failedAuthNum.get();
    }

    public int incFailedAuthNum() {
        return failedAuthNum.incrementAndGet();
    }

    public void resetFailedAuthNum() {
        failedAuthNum.set(0);
    }

    public int getFailedSubmitCount() {
        if (this.failedSubmitCount == null) {
            this.failedSubmitCount = new AtomicInteger();
        }

        return failedSubmitCount.get();
    }

    public int incFailedSubmitCount() {
        if (this.failedSubmitCount == null) {
            this.failedSubmitCount = new AtomicInteger();
        }

        return failedSubmitCount.incrementAndGet();
    }

    public void resetFailedSubmitCount() {
        this.failedSubmitCount = null;
    }

    public int getFailedResetCodeCount() {
        return failedResetCodeCount.get();
    }

    public int incFailedResetCodeCount() {
        if (this.failedResetCodeCount == null) {
            this.failedResetCodeCount = new AtomicInteger();
        }

        return failedResetCodeCount.incrementAndGet();
    }

    public void resetFailedResetCodeCount() {
        this.failedResetCodeCount = null;
    }

    public boolean isExpiredPassword() {
        return expiredPassword;
    }

    public void setExpiredPassword(boolean expiredPassword) {
        this.expiredPassword = expiredPassword;
    }

    public boolean isSysAdmin() {
        return isSysAdmin;
    }

    public void setIsSysAdmin(boolean isSysAdmin) {
        this.isSysAdmin = isSysAdmin;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    
}

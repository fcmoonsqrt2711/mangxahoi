/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tav.service.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author: AnTV
 * @version: 1.0
 * @since: 19-05-2017
 */
public class WarningObject {

    private Long warningId;
    private int state;
    /**
     * 1- Cảnh báo 2- Mất kết nối 3 bình thường
     */

    //Thời điểm gửi tin về
    private Date timeWarning;
    private int warningType;

    private Long tblDeviceGid;
    private String tblDeviceName;
    private String tblDeviceMobile;
    private String tblDeviceCodeDevice;
    private String tblDeviceIMEI;
    private String tblDeviceAddress;
    private Long tblDeviceIdSetlement;

    private String tblSettlementsName;
    private String tblSettlementsManagerBuilding;
    private String tblSettlementsMobileBuilding;
    private String tblSettlementsNameManager;
    private String tblSettlementsMobileManager;
    private String tblSettlementsAddress;
    private Long tblSettlementsDepid;
    private String tblSettlementsLat;
    private String tblSettlementsLong;

    private String deptName;
    private String deptTelephone;

    private String userName;
    private String usersFullName;
    private String usersCellphone;

    private String tblTmpDeviceStatusPortStatus;
    private String timeWarningText;

    private List<WarningUser> listWarningUser;

    //Tổng Số tổ PCCC phụ trách tòa nhà - truy vấn từ bảng tbl_rescue_team
    private int rescueTeamNumber;

    //Tổng Số gười trong tất cả các tổ phụ trách tòa nhà - truy vấn từ tbl_rescue_team và tbl_rescue_team_member
    private int rescueTeamMemberNumber;

    //Tổng Số loại công cụ dụng cụ có trong tòa nhà  - truy vấn từ bảng tbl_tool, theo trường id_settlements
    private int toolNumber;

    //Tổng Số loại công cụ dụng cụ có trong tòa nhà  - truy vấn từ bảng tbl_tool, theo trường tool_type và id_settlements
    private int toolTypeNumber;

    //loại hình kinh doanh - truy vấn từ bảng tbl_settlements (field_work) và mst_division (lấy dvs_name thông qua dvs_value) với dvs_group_cd=017
    private String fieldWork;

    private List<String> updateTime = new ArrayList<String>();
    
    private List<Long> listDeptRelate=new ArrayList<Long>();
    /*
    Nguồn bắn notify
    Có thể có nhiều nguồn notify/1thiết bị - thì chỉ lấy 1 nguồn đầu tiên (anh Hòa đề xuất sau)
    - sourceNotify - có thể là 1 chuỗi gồm IP, cổng,mã gateway,...
    - trong file environment.properties chứa 1 tham số là list các sourceNotify cho phép tiếp nhận WarningObject
    */
    //private String sourceNotify;
    
    private List<Long> listSupportDeptId = new ArrayList<Long>();
    

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getTimeWarning() {
        return timeWarning;
    }

    public void setTimeWarning(Date timeWarning) {
        this.timeWarning = timeWarning;
    }

    public Long getTblDeviceGid() {
        return tblDeviceGid;
    }

    public void setTblDeviceGid(Long tblDeviceGid) {
        this.tblDeviceGid = tblDeviceGid;
    }

    public String getTblDeviceName() {
        return tblDeviceName;
    }

    public void setTblDeviceName(String tblDeviceName) {
        this.tblDeviceName = tblDeviceName;
    }

    public String getTblDeviceMobile() {
        return tblDeviceMobile;
    }

    public void setTblDeviceMobile(String tblDeviceMobile) {
        this.tblDeviceMobile = tblDeviceMobile;
    }

    public String getTblDeviceCodeDevice() {
        return tblDeviceCodeDevice;
    }

    public void setTblDeviceCodeDevice(String tblDeviceCodeDevice) {
        this.tblDeviceCodeDevice = tblDeviceCodeDevice;
    }

    public String getTblDeviceIMEI() {
        return tblDeviceIMEI;
    }

    public void setTblDeviceIMEI(String tblDeviceIMEI) {
        this.tblDeviceIMEI = tblDeviceIMEI;
    }

    public Long getTblDeviceIdSetlement() {
        return tblDeviceIdSetlement;
    }

    public void setTblDeviceIdSetlement(Long tblDeviceIdSetlement) {
        this.tblDeviceIdSetlement = tblDeviceIdSetlement;
    }

    public String getTblSettlementsName() {
        return tblSettlementsName;
    }

    public void setTblSettlementsName(String tblSettlementsName) {
        this.tblSettlementsName = tblSettlementsName;
    }

    public String getTblSettlementsMobileBuilding() {
        return tblSettlementsMobileBuilding;
    }

    public void setTblSettlementsMobileBuilding(String tblSettlementsMobileBuilding) {
        this.tblSettlementsMobileBuilding = tblSettlementsMobileBuilding;
    }

    public String getTblSettlementsNameManager() {
        return tblSettlementsNameManager;
    }

    public void setTblSettlementsNameManager(String tblSettlementsNameManager) {
        this.tblSettlementsNameManager = tblSettlementsNameManager;
    }

    public String getTblSettlementsMobileManager() {
        return tblSettlementsMobileManager;
    }

    public void setTblSettlementsMobileManager(String tblSettlementsMobileManager) {
        this.tblSettlementsMobileManager = tblSettlementsMobileManager;
    }

    public String getTblSettlementsAddress() {
        return tblSettlementsAddress;
    }

    public void setTblSettlementsAddress(String tblSettlementsAddress) {
        this.tblSettlementsAddress = tblSettlementsAddress;
    }

    public Long getTblSettlementsDepid() {
        return tblSettlementsDepid;
    }

    public void setTblSettlementsDepid(Long tblSettlementsDepid) {
        this.tblSettlementsDepid = tblSettlementsDepid;
    }

    public String getTblSettlementsLat() {
        return tblSettlementsLat;
    }

    public void setTblSettlementsLat(String tblSettlementsLat) {
        this.tblSettlementsLat = tblSettlementsLat;
    }

    public String getTblSettlementsLong() {
        return tblSettlementsLong;
    }

    public void setTblSettlementsLong(String tblSettlementsLong) {
        this.tblSettlementsLong = tblSettlementsLong;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsersFullName() {
        return usersFullName;
    }

    public void setUsersFullName(String usersFullName) {
        this.usersFullName = usersFullName;
    }

    public String getUsersCellphone() {
        return usersCellphone;
    }

    public void setUsersCellphone(String usersCellphone) {
        this.usersCellphone = usersCellphone;
    }

    public String getTblTmpDeviceStatusPortStatus() {
        return tblTmpDeviceStatusPortStatus;
    }

    public void setTblTmpDeviceStatusPortStatus(String tblTmpDeviceStatusPortStatus) {
        this.tblTmpDeviceStatusPortStatus = tblTmpDeviceStatusPortStatus;
    }

    public String getTblSettlementsManagerBuilding() {
        return tblSettlementsManagerBuilding;
    }

    public void setTblSettlementsManagerBuilding(String tblSettlementsManagerBuilding) {
        this.tblSettlementsManagerBuilding = tblSettlementsManagerBuilding;
    }

    public String getTblDeviceAddress() {
        return tblDeviceAddress;
    }

    public void setTblDeviceAddress(String tblDeviceAddress) {
        this.tblDeviceAddress = tblDeviceAddress;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptTelephone() {
        return deptTelephone;
    }

    public void setDeptTelephone(String deptTelephone) {
        this.deptTelephone = deptTelephone;
    }

    public int getWarningType() {
        return warningType;
    }

    public void setWarningType(int warningType) {
        this.warningType = warningType;
    }

    public Long getWarningId() {
        return warningId;
    }

    public void setWarningId(Long warningId) {
        this.warningId = warningId;
    }

    public List<WarningUser> getListWarningUser() {
        return listWarningUser;
    }

    public void setListWarningUser(List<WarningUser> listWarningUser) {
        this.listWarningUser = listWarningUser;
    }

    public String getTimeWarningText() {
        return convertTimeDisplay(this.timeWarning);
    }

    public void setTimeWarningText(String timeWarningText) {
        this.timeWarningText = timeWarningText;
    }

    private String convertTimeDisplay(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(date);
    }

    public List<String> getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(List<String> updateTime) {
        this.updateTime = updateTime;
    }

    public String getFieldWork() {
        return fieldWork;
    }

    public void setFieldWork(String fieldWork) {
        this.fieldWork = fieldWork;
    }

    public int getRescueTeamNumber() {
        return rescueTeamNumber;
    }

    public void setRescueTeamNumber(int rescueTeamNumber) {
        this.rescueTeamNumber = rescueTeamNumber;
    }

    public int getRescueTeamMemberNumber() {
        return rescueTeamMemberNumber;
    }

    public void setRescueTeamMemberNumber(int rescueTeamMemberNumber) {
        this.rescueTeamMemberNumber = rescueTeamMemberNumber;
    }

    public int getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(int toolNumber) {
        this.toolNumber = toolNumber;
    }

    public int getToolTypeNumber() {
        return toolTypeNumber;
    }

    public void setToolTypeNumber(int toolTypeNumber) {
        this.toolTypeNumber = toolTypeNumber;
    }

    public List<Long> getListSupportDeptId() {
        return listSupportDeptId;
    }

    public void setListSupportDeptId(List<Long> listSupportDeptId) {
        this.listSupportDeptId = listSupportDeptId;
    }

    public List<Long> getListDeptRelate() {
        return listDeptRelate;
    }

    public void setListDeptRelate(List<Long> listDeptRelate) {
        this.listDeptRelate = listDeptRelate;
    }

    
    
    

}

/*
* Copyright 2017 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.common;

/**
 * @author: lequytuan
 * @version 1.0
 * @since 27-Mar-17 20:00 PM
 */
public class DivisionConstant {

    // user type
    public static final String GROUP_USER_TYPE = "001";

    public static final String USER_TYPE_ADMIN = "1";
    public static final String USER_TYPE_DEPARTMENT = "2"; // tổng cục
    public static final String USER_TYPE_DIVISION = "3"; // phòng
    public static final String USER_TYPE_STATION = "4"; // trạm

    // division type
    public static final String GROUP_DIVISION_TYPE = "002";
    
    public static final String GROUP_DIVISION_TYPE_DEPT_LOCAL = "003";
   

    // Loại người dùng ~ phòng ban
    public static final String DIVISION_TYPE_LEVEL_0 = "0"; // cục hoặc quản trị
    public static final String DIVISION_TYPE_LEVEL_1 = "1"; // sở
    public static final String DIVISION_TYPE_LEVEL_2 = "2"; // phòng
    public static final String DIVISION_TYPE_LEVEL_3 = "3"; // tổ
    public static final String DIVISION_TYPE_LEVEL_4 = "4"; // khác


    // device fixfire status
    public static final String GROUP_DEVICE_FIXFIRE_STATUS = "006";

    //water points type
    public static final String GROUP_WATER_POINTS_TYPE = "007";
    public static final String GROUP_USER_TOPIC_POSITION = "098";
    //status of document
    public static final String STATUS_OF_DOCUMENT = "034";
    public static final String STATUS_OF_NOTIFY = "035";
    //type topic
    public static final String GROUP_TOPIC_TYPE = "091";
    public static final String DREEGREE = "102";
    public static final Integer CHU_NHIEM = 1;
    public static final Integer THU_KI = 2;
    public static final String PROGRESS_TYPE = "105";
    public static final Integer UY_VIEN = 3;
    public static final Integer MANAGER = 1;
    public static final Long USER_TYPE_ID_MANAGER = 1L;
    public static final Long USER_TYPE_ID_STAFF = 2L;

    // object type
    public static final String GROUP_OBJECT_TYPE = "008";
    
    //
    public static final String GROUP_RANK = "029";
    public static final String GROUP_EDU = "049";
    public static final String GROUP_SEX = "010";
    
    //Agencies type
    public static final String GROUP_AGENCIES_TYPE = "004";
    public static final String GROUP_EXPER_TYPE = "090";
    public static final String GROUP_COUNCIL_REVIEW = "095";
    public static final String GROUP_COUNCIL_POSITION = "094";
    public static final String GROUP_TYPE_PRODUCT = "104";
    public static final String GROUP_DEVICE_STATUS = "014";
    public static final String GROUP_EXPERT_FIELDS = "092";
    public static final String TYPE_CHILD = "111";
    public static final String GROUP_EXPERT_DEGREE = "102";
    public static final String GROUP_EXPERT_RANK = "101";
    public static final String GROUP_ROLE = "110";
    public static final String GROUP_TOPIC_STATUS = "093";
    public static final String GROUP_DEVICE_STATUS_DRASHBOARD = "015";
    public static final String GROUP_SETTLEMENT_TYPE = "017";
    public static final String GROUP_ADMIN = "018";
    public static final String GROUP_GIAOLO_TYPE = "023";
    public static final String GROUP_EXPERT_POSITION = "103";
    
    
    //Event Action
    public static final String EVENT_ACTION = "019";
    //Event Object
    public static final String EVENT_OBJECT = "020";
    public static final String EVENT_ACTION_CREATE = "1";
    public static final String EVENT_ACTION_UPDATE = "2";
    public static final String EVENT_ACTION_DEL = "3";
    
    public static final String EVENT_OBJECT_SETTLEMENTS = "1";
    public static final String EVENT_OBJECT_DEPARTMENTS = "2";
    public static final String EVENT_OBJECT_DEVICE = "3";
    public static final String EVENT_OBJECT_ROLE = "4";
    public static final String EVENT_OBJECT_OBJECT = "5";
    public static final String EVENT_OBJECT_AREA = "6";
    public static final String EVENT_OBJECT_TBLAGENCIES = "7";
    public static final String EVENT_OBJECT_TBLWATERPOINT = "8";
    public static final String EVENT_OBJECT_GUIDEPROPERTY = "9";
    
    public static final String FUNCTION_UNIT = "024";
    
    public static final Integer DEVICE_FIXFIRE_STATUS_DEFAULT = 1;
    public static final String OBJ_RELATION_VICTIM_SUPPORT_PARTNER = "CED_VICTIM_SUPPORT_PARTNER";
    public static final String OBJ_RELATION_VICTIM_SUPPORT_DEMAND = "CED_VICTIM_SUPPORT_DEMAND";
}

/*
* Copyright 2017 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.common;

import com.tav.web.util.PropertiesUtil;

/**
 * @author: lequytuan
 * @version 1.0
 * @since 27-Mar-17 20:00 PM
 */
public class CommonConstant {

    public static final String TBL_FIRE_TRUCK_URL = "/tblFireTruckRsService";
    public static final String TBL_ADDRESS_URL = "/tblAddressRsServiceRest";
    public static final String RESCUE_TEAM_MEMBER_URL = "/tblRescueTeamMemberRsServiceRest";
    public static final String RESCUE_TEAM_URL = "/tblRescueTeamRsServiceRest";
    public static final String TOOL_URL = "/tblToolRsServiceRest";
    public static final String SETTLEMENT_URL = "/settlementsServiceRest";
    public static final String DEVICE_SUBSCRIBER_URL = "/tblDeviceSubscriberRsServiceRest";
    public static final String DEVICE_URL = "/tblDeviceServiceRest";
    public static final String TBL_AGENCY_URL = "/tblAgenciesServiceRest";
    public static final String TBL_MAP_AGENCY_URL = "/tblMapSettlementAgencyRsServiceRest";
    public static final String TBL_CHARACTERISTIC_URL = "/TblSettlementCharacteristicRsServiceRest";
    public static final String TBL_DIAGRAM_URL = "/TblDiagramFireRsServiceRest";
    public static final String AREA_MANAGER_URL = "/areaManagerServiceRest";
    public static final String FUNCTION_UNIT_URL = "/tblFunctionUnitRsServiceRest";
    public static final String HST_DEVICE_FIXFIRE_APPOINT_URL = "/hstDeviceFixfireAppointRsServiceRest";
    public static final String TBL_INTERSECTIONS_URL = "/tblIntersectionsRsServiceRest";
    public static final String MST_DIVISION_URL = "/mstDivisionServiceRest";
    public static final String OBJECT_URL = "/objectsServiceRest";
    public static final String SUB_DEVICE_FIX_FIRE_TYPE_URL = "/tblDeviceFixfireTypeServiceRest";

    public static final String INTRO_IMAGE_SETTLEMENT = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("INTRO_IMAGE_SETTLEMENT"));
    public static final String TYPE_IMAGE = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("TYPE_IMAGE"));
    public static final String PATH_FOLDER_IMAGE_SETTLEMENT = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_IMAGE_SETTLEMENT"));
    public static final String PATH_FOLDER_TOPIC_FILE = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_TOPIC_FILE"));
    public static final String PATH_FOLDER_IMAGE_GALLERY = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_IMAGE_GALLERY"));
    public static final String PATH_FOLDER_TBL_DOCUMENT_FILE = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_TBL_DOCUMENT_FILE"));
    public static final String PATH_FOLDER_INFO_NOTIFY = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_INFO_NOTIFY"));
    public static final String HOST = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("HOST"));
    public static final String INTERVAL = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("INTERVAL"));
    public static final String SMS_CALL_HOST = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("SMS_CALL_HOST"));
    public static final String APP_ID = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("APP_ID"));

    public static final String SLASH = "/";
    public static final Integer MAX_LENGTH_100 = 100;
    public static final Integer MAX_LENGTH_200 = 200;
    public static final Integer MAX_LENGTH_400 = 400;
    public static final Integer MAX_LENGTH_500 = 500;
    public static final Integer MAX_LENGTH_255 = 255;

    public static final String RESULT_UPDATED_OBJECT = "100";
    public static final String RESULT_ADDED_OBJECT = "200";
    public static final String RESULT_ERROR_ID_OBJECT = "300";
    public static final String RESULT_ERROR_NULL_OBJECT = "400";
    public static final String RESULT_ERROR_EXCEPTION = "500";
    public static final String RESULT_SUCCESS_OBJECT = "600";
    public static final String RESULT_NO_OBJECT = "700";
    public static final String NO_PROCESS_OBJECT = "800";
    public static final Integer DEFAULT_LIMIT = 4;
    public static final String OBJ_RELATION_CECM_PLAN_MACHINE = "CECM_PLAN_PROGRAM_MACHINE";
    public static final String OBJ_RELATION_CECM_PLAN_DEVICE = "CECM_PLAN_PROGRAM_DEVICE";
}

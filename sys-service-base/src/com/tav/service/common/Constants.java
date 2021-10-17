/*
* Copyright 2017 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.common;



/**
 *
 * @author KhietDT
 */
public class Constants {

    public static class Message {

        public static final String DUPLICATED_CODE = "message.duplicatedCode";
        public static final String DUPLICATED_NAME = "message.duplicatedName";
        public static final String CANNOT_ADD_DATA = "message.cannotAddData";
        public static final String CANNOT_UPDATE_DATA = "message.cannotUpdateData";
        public static final String CANNOT_DELETE_DATA = "message.cannotDeleteData";
        public static final String CANNOT_DELETE_CONSTRAINT_VIOLATION = "message.cannotDeleteConstraintViolation";
        public static final String CANNOT_DELETE_EXISTS_SUB_DEPARMENT = "message.cannotDeleteExistsSubDepartment";
        public static final String CANNOT_DELETE_EXISTS_USER = "message.cannotDeleteExistsUser";
        public static final String CANNOT_DELETE_EXISTS_SUBSTATION = "message.cannotDeleteExistsSubstation";
        public static final String CANNOT_DELETE_EXISTS_SUB_OBJECT = "message.cannotDeleteExistsSubObject";
        public static final String INSUFFICIENT_PRIORITY = "message.insufficientPriority";
        public static final String ERROR_ATTT = "message.error_attt";
        public static final String SUCCESS = "SUCCESS";
        public static final String FAIL = "FAIL";
        public static final String HAS_BELONG_WITH_FIRE_STATION = "message.hasBelongWithFireStation";
        /* MESSAGE ERROR VALIDATE */
        public static final String VALID_RELATION_TBL_DEVICE = "message.relationTblDevice";
        public static final String VALID_RELATION_TBL_SETTLEMENT = "message.relationTblSettlement";
        public static final String FAIL_ADD_AREA = "message.notFormatCorrect";

        //#FIRE TRUCK
        public static final String FAIL_INSERT_FIRETRUCK = "message.failInsertFireTruck";
        public static final String FAIL_UPDATE_FIRETRUCK = "message.failUpdateFireTruck";
        public static final String FAIL_DELETE_FIRETRUCK = "message.failDeleteFireTruck";

        //#FUNCTION UNIT
        public static final String FAIL_DELETE_FUNCTION_UNIT = "message.failDeleteFunctionUnit";

        //<editor-fold defaultstate="collapsed" desc="TBL_INTERSECTIONS">
        public static final String FAIL_DELETE_INTERSECTION = "message.failDeleteIntersection";
        public static final String FAIL_INSERT_INTERSECTION = "message.failInsertIntersection";
        public static final String FAIL_UPDATE_INTERSECTION = "message.failUpdateIntersection";
        public static final String FAIL_GET_INFO_INTERSECTION = "message.failGetInfoIntersection";
        //</editor-fold>

        //#RESCUE TEAM
        public static final String VALID_RELATION_TBL_RESCUE_TEAM = "message.relationTblRescueTeam";
    }

    public static class CommonConstant {

        /* encoding for web services */
        public static final String ENCODING_UTF8 = "text/html;charset=utf-8";
        public static final String PATH_FOLDER_IMAGE_SETTLEMENT = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_IMAGE_SETTLEMENT"));
        public static final String PATH_FOLDER_TOPIC_FILE = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_TOPIC_FILE"));
        public static final String PATH_FOLDER_IMAGE_FULL_SETTLEMENT = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_IMAGE_FULL_SETTLEMENT"));
        public static final String PATH_FOLDER_ICON_TREE = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("PATH_FOLDER_ICON_TREE"));
        public static final String ICON_TREE_ROOT = "house.png";
        public static final String ICON_TREE_ROOT_FIRE = "so_phong_pccc_warning.png";
        public static final String ICON_TREE_SO = "house.png";
        public static final String ICON_TREE_SO_FIRE = "so_phong_pccc_warning.png";
        public static final String ICON_TREE_PHONG = "file.png";
        public static final String ICON_TREE_PHONG_FIRE = "so_phong_pccc_warning.png";
        public static final String ICON_TREE_TO = "file.png";
        public static final String ICON_TREE_TO_FIRE = "so_phong_pccc_warning.png";
        public static final String ICON_TREE_OTHER = "house.png";
        public static final String ICON_TREE_OTHER_FIRE = "so_phong_pccc_warning.png";
        public static final String ICON_TREE_SETTLEMENT = "toa_nha.png";
        public static final String ICON_TREE_SETTLEMENT_FIRE = "toa_nha_warning.png";
        public static final String ICON_TREE_DEVICE = "device_ok.png";
        public static final String ICON_TREE_DEVICE_FIRE = "device_warning.png";
        public static final String ICON_TREE_DEVICE_ERROR = "device_not_connect.png";
        public static final String SLASH = "/";
        public static final String SYSTEM_PASSWORD = "123456a@";

        public static final String OBJ_RELATION_DEPARTMENT_ACTION_TYPE = "CER_DEPT_ACTION";
        public static final String OBJ_RELATION_DEPARTMENT_SOURCE_MONEY = "CER_DEPT_SOURCE_MONEY";

        /* delete flag */
        public static final Boolean DELETE_FLAG_YES = true;
        public static final Boolean DELETE_FLAG_NO = false;

        /* boolean value */
        public static final Boolean BOOLEAN_TRUE = true;
        public static final Boolean BOOLEAN_FALSE = false;

        /* paging */
        public static final int LIMIT_PER_PAGE = 20;

        public static final String DECODE_URL_FAIL = "decodeFail";

        public static final int DEVICE_STATUS_GOOD = 1;
        public static final int DEVICE_STATUS_ERROR = 2;
        public static final int DEVICE_STATUS_WARNING = 4;

        public static final int DEPT_TYPE_1 = 1;
        public static final int DEPT_TYPE_2 = 2;

        public static final int ACTION_TYPE_ADD = 1;
        public static final int ACTION_TYPE_UPDATE = 2;
        public static final int ACTION_TYPE_DELETE = 3;

        public static final String RESULT_UPDATED_OBJECT = "100";
        public static final String RESULT_ADDED_OBJECT = "200";
        public static final String RESULT_ERROR_ID_OBJECT = "300";
        public static final String RESULT_ERROR_NULL_OBJECT = "400";
        public static final String RESULT_ERROR_EXCEPTION = "500";
        public static final String RESULT_SUCCESS_OBJECT = "600";
        public static final String RESULT_NO_OBJECT = "700";
        public static final String NO_PROCESS_OBJECT = "800";

        public static final int MESSAGE_TYPE_MONITOR = 1;
        public static final int MESSAGE_ON_PROCESS = 2;
        public static final int MESSAGE_END_PROCESS = 3;
        public static final int MESSAGE_TYPE_MONITOR_IGNORER = 4;
        public static final int MESSAGE_ON_PROCESS_SUPPORT = 5;
        public static final int MESSAGE_ON_IGNORE_SUPPORT = 6;

        public static final int MESSAGE_TYPE_CALL_URGENT_IGNORER = 7;
        public static final int MESSAGE_END_CALL_URGENT_PROCESS = 8;
        public static final int MESSAGE_ON_PROCESS_URGENT = 9;

        public static final int MESSAGE_TYPE_CALL_URGENT_IGNORER_SUPPORT = 10;
        public static final int MESSAGE_ON_PROCESS_CALL_URGENT_SUPPORT = 11;

        public static final String RESULT_CAN_END_WARNING = "10";
        public static final String RESULT_NOT_END_WARNING = "20";
        public static final String RESULT_END_WARNING_IGNORE = "30";

        public static final String RESULT_CAN_END_WARNING_PROCESS = "40";
        public static final String RESULT_NOT_END_WARNING_PROCESS = "50";

        public static final Long HST_PROCESS_SOURCE_TYPE_WARNING = 0L;
        public static final Long HST_PROCESS_SOURCE_TYPE_CALL_URGENT = 1L;

        public static final String OBJ_RELATION_CBMA_BOMB = "CBMA_BOMB";
        public static final String OBJ_RELATION_CBMA_DEEP = "CBMA_DEEP";
        
        public static final String OBJ_RELATION_CHA_PLANT_TYPE = "CHA_PLANT_TYPE";
        public static final String OBJ_RELATION_CHA_PLANT_LEVEL = "CHA_PLANT_LEVEL";
        public static final String OBJ_RELATION_CHA_PLANT_DEVICE = "CHA_PLANT_DEVICE";
        public static final String OBJ_RELATION_CHA_SOIL_TYPE = "CHA_SOIL_TYPE";
        public static final String OBJ_RELATION_CHA_GEOLOGICAL_TYPE = "CHA_GEOLOGICAL_TYPE";
        public static final String OBJ_RELATION_CHA_AREA_TYPE = "CHA_AREA_TYPE";
        public static final String OBJ_RELATION_CHA_AREA_DEVICE = "CHA_AREA_DEVICE";
        public static final String OBJ_RELATION_CHA_TOPOGRAPHIC = "CHA_TOPOGRAPHIC";

        public static final String OBJ_RELATION_CECM_PROGRAM_ACTION = "CECM_PROGRAM_ACTION";
        public static final String OBJ_RELATION_CECM_PROGRAM_SOURCE_MONEY = "CECM_PROGRAM_SOURCE_MONEY";
        public static final String OBJ_RELATION_CECM_PROGRAM_PERSON_ASIGN = "CECM_PROGRAM_PERSON_ASIGN";
        
        
                public static final String OBJ_RELATION_CECM_PROGRAM_NEW_ACTION = "CECM_PROGRAM_NEW_ACTION";
        public static final String OBJ_RELATION_CECM_PROGRAM_NEW_SOURCE_MONEY = "CECM_PROGRAM_NEW_SOURCE_MONEY";
        public static final String OBJ_RELATION_CECM_PROGRAM_NEW_PERSON_ASIGN = "CECM_PROGRAM_NEW_PERSON_ASIGN";
        
        
        public static final String OBJ_RELATION_CECM_PROGRAM_PACKAGE_ACTION = "CECM_PROGRAM_PACKAGE_ACTION";
        public static final String OBJ_RELATION_CECM_PROGRAM_PACKAGE_SOURCE_MONEY = "CECM_PROGRAM_PACKAGE_SOURCE_MONEY";
        public static final String OBJ_RELATION_CECM_PROGRAM_PACKAGE_PERSON_ASIGN = "CECM_PROGRAM_PACKAGE_PERSON_ASIGN";
        

        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_ACTION = "CECM_PLAN_PROGRAM_ACTION";
        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_SOURCE_MONEY = "CECM_PLAN_PROGRAM_SOURCE_MONEY";
        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_PERSON_ASIGN = "CECM_PLAN_PROGRAM_PERSON_ASIGN";
        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_PROCESS_TECH = "CECM_PLAN_PROGRAM_PROCESS_TECH";
        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_METHOD_BM = "CECM_PLAN_PROGRAM_PERSON_ASIGN_METHOD_BM";

        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_EDU_PERSON = "CECM_PLAN_PROGRAM_EDU_PERSON";
        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_EDU_BOMB = "CECM_PLAN_PROGRAM_EDU_BOMB";
        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_EDU_ACTION = "CECM_PLAN_PROGRAM_EDU_ACTION";
        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_VICTIM_SUPPORT_TYPE = "CECM_PLAN_PROGRAM_VICTIM_SUPPORT_TYPE";
        public static final String OBJ_RELATION_CECM_PLAN_PROGRAM_VICTIM_SUPPORT_DEPT_OTHER = "CECM_PLAN_PROGRAM_VICTIM_SUPPORT_DEPT_OTHER";

        public static final String OBJ_RELATION_CECM_PLAN_MACHINE = "CECM_PLAN_PROGRAM_MACHINE";
        public static final String OBJ_RELATION_CER_PLAN_RESULT_ACTION = "CER_PLAN_RESULT_ACTION";
        public static final String OBJ_RELATION_CER_PLAN_RESULT_SOURCE_MONEY = "CER_PLAN_RESULT_SOURCE_MONEY";
        public static final String OBJ_RELATION_CER_PLAN_RESULT_PERSON_ASIGN = "CER_PLAN_RESULT_PERSON_ASIGN";
        public static final String OBJ_RELATION_CER_PLAN_RESULT_PROCESS_TECH = "CER_PLAN_RESULT_PROCESS_TECH";
        public static final String OBJ_RELATION_CER_PLAN_RESULT_METHOD_BM = "CER_PLAN_RESULT_PERSON_ASIGN_METHOD_BM";
        public static final String OBJ_RELATION_CER_PLAN_RESULT_DEPT_CHECK = "CER_PLAN_RESULT_DEPT_CHECK";

        public static final String OBJ_RELATION_CER_PLAN_RESULT_EDU_PERSON = "CER_PLAN_RESULT_EDU_PERSON";
        public static final String OBJ_RELATION_CER_PLAN_RESULT_EDU_BOMB = "CER_PLAN_RESULT_EDU_BOMB";
        public static final String OBJ_RELATION_CER_PLAN_RESULT_EDU_ACTION = "CER_PLAN_RESULT_EDU_ACTION";

        public static final String OBJ_RELATION_CED_ACCIDENT_AREA_TYPE = "CED_ACCIDENT_AREA_TYPE";
        public static final String OBJ_RELATION_CED_ACCIDENT_ACTION_TYPE = "CED_ACCIDENT_ACTION_TYPE";
        public static final String OBJ_RELATION_CED_ACCIDENT_BOMB_TYPE = "CED_ACCIDENT_BOMB_TYPE";
        public static final String OBJ_RELATION_CED_ACCIDENT_MARKER_TYPE = "CED_ACCIDENT_MARKER_TYPE";
        public static final String OBJ_RELATION_CED_ACCIDENT_AFFECT_TYPE = "CED_ACCIDENT_AFFECT_TYPE";

        public static final String OBJ_RELATION_CED_VICTIM_SUPPORT_DEPT = "CED_VICTIM_SUPPORT_DEPT";
        public static final String OBJ_RELATION_CED_VICTIM_DEMAND = "CED_VICTIM_DEMAND";
        public static final String OBJ_RELATION_CED_BODY_VICTIM_RIGHT = "CED_BODY_VICTIM_RIGHT";
        public static final String OBJ_RELATION_CED_BODY_VICTIM_LEFT = "CED_BODY_VICTIM_LEFT";
        public static final String OBJ_RELATION_CED_BODY_VICTIM_OTHER = "CED_BODY_VICTIM_OTHER";
    }
}

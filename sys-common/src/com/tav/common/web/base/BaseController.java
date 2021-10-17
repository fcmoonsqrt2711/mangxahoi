/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.web.base;

import com.tav.common.webservice.pool.WsClientPool;
import com.tav.common.webservice.base.client.CxfWsClientFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author thuannht
 */
public class BaseController {

    protected final Logger logger = Logger.getLogger(BaseController.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Resource(name = "messageSource")
    private MessageSource messageSource;
    @Resource(name = "configSource")
    private MessageSource configSource;
    @Resource(name = "casSource")
    private MessageSource casSource;
    public ModelAndView errorModel = new ModelAndView("error");
    protected static final String ERROR_PAGE = "error";

    public Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public MessageSource getConfigSource() {
        return configSource;
    }

    public MessageSource getCasSource() {
        return casSource;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpSession getHttpSession() {
        return request.getSession();
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getLanguageByKey(String key) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(key, null, locale);
        } catch (Exception ex) {
            logger.error("FW: Key not found:" + ex);
            return key;
        }
    }

    public String getConfigByKey(String key) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return configSource.getMessage(key, null, locale);
        } catch (Exception ex) {
            logger.error("FW: Key not found:" + ex);
            return key;
        }
    }

    public String getCasByKey(String key) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return casSource.getMessage(key, null, locale);
        } catch (Exception ex) {
            logger.error("FW: Key not found:" + ex);
            return key;
        }
    }

    public <T> T createWsClient(Class<T> interfaceClass) throws Exception {
        return WsClientPool.getWsClient(interfaceClass);
    }

    public <T> void closeWsClient(T wsClient) {
        try {
            if (wsClient != null) {
                WsClientPool.closeWsClient(wsClient);
            }
        } catch (Exception ex) {
            logger.error(ex);
        }

    }

    public static <T> List<T> createListForTest(Class<T> clazz, int total) throws Exception {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < total; i++) {
            T obj = clazz.newInstance();
            Field fields[] = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (f.getType().toString().toLowerCase().contains("date")) {
                    f.setAccessible(true);
                    f.set(obj, new Date());
                } else if (f.getType().toString().toLowerCase().contains("long")) {
                    f.setAccessible(true);
                    f.set(obj, new Long(new Random().nextLong()));
                } else if (f.getType().toString().toLowerCase().contains("bool")) {
                    f.setAccessible(true);
                    f.set(obj, new Boolean(false));
                } else if (f.getType().toString().toLowerCase().contains("string")) {
                    f.setAccessible(true);
                    f.set(obj, "Mo ta thu " + (new Random().nextLong()));
                }
            }
            list.add(obj);
        }
        return list;
    }

    protected int getCurrentPage() {
        try {
            String curPage = getRequest().getParameter(PG_CURPAGE);
            if (curPage != null && !curPage.isEmpty()) {
                return Integer.parseInt(curPage);
            } else {
                return 1;
            }
        } catch (Exception ex) {
            logger.error(ex);
            return 0;
        }
    }

    protected String getParamAutoComplete() {
        try {
            String q = getRequest().getParameter(AUTO_COMPLETE_PRNAME);
            return q != null ? q : "";
        } catch (Exception ex) {
            logger.error(ex);
            return "";
        }
    }

    protected String getParam(String paramName) {
        try {
            String rs = getRequest().getParameter(paramName);
            return rs != null ? rs : "";
        } catch (Exception ex) {
            logger.error(ex);
            return "";
        }
    }

    protected int getTotalRecordPerPage() {
        try {
            String totalRecordPerPage = getRequest().getParameter(PG_TOTAL_RECORD_PER_PAGE);
            if (totalRecordPerPage != null && !totalRecordPerPage.isEmpty()) {
                return Integer.parseInt(totalRecordPerPage);
            } else {
                return 10;
            }
        } catch (Exception ex) {
            logger.error(ex);
            return 10;
        }
    }
    //Khai bao cac constant
    //so thu tu trang hien tai
    public static final String PG_CURPAGE = "pagenum";
    //Tong so ban ghi tren mot trang
    public static final String PG_TOTAL_RECORD_PER_PAGE = "pagesize";
    //so thu tu ban ghi cuoi trong trang
    public static final String RECORD_END_INDEX = "recordendindex";
    //index ban ghi dau tien trong trang
    public static final String RECORD_START_INDEX = "recordstartindex";
    public static final String AUTO_COMPLETE_PRNAME = "keysearch";
    public static final String TREE_PARAM = "parentValue";

}

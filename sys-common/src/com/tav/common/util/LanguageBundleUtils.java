/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.common.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;
import org.apache.log4j.Logger;

/**
 *
 * @author binhnt22@viettel.com.vn
 * @since Apr 12, 2010
 * @version 1.0
 */
public class LanguageBundleUtils {

    private static final Logger logger = Logger.getLogger(LanguageBundleUtils.class);

    private LanguageBundleUtils() {
    }
    /**
     * RESOURCE.
     */
    //private static final String RESOURCE = "com/viettel/config/ ";
    private static final String RESOURCE = "com/viettel/config/message/Language";
    private static final String RESOURCEMESSAGE = "com/viettel/config/message/Message";
    /**
     * local.
     */
    private static Locale local = null;

    /**
     * languageRb.
     */
//    private static ResourceBundle languageRb = null;
//    private static ResourceBundle messageRb = null;
    /**
     * .
     */
    /**
     * getString
     *
     * @param key String
     * @return value
     */
    public static String getString(String key) {
        ResourceBundle languageRb;
        if (local == null) {
            languageRb = ResourceBundle.getBundle(RESOURCE, local);
        } else {
            languageRb = ResourceBundle.getBundle(RESOURCE);
        }
        try {
            return languageRb.getString(key);
        } catch (Exception ex) {
            logger.error(ex);
            return key;
        }
    }

    public static String getString(Locale locale, String key) {
        ResourceBundle languageRb;
        if (locale != null) {
            languageRb = ResourceBundle.getBundle(RESOURCE, locale);
        } else {
            languageRb = ResourceBundle.getBundle(RESOURCE);
        }
        return languageRb.getString(key);
    }

    public static String getStringMessage(Locale locale, String key) {
        ResourceBundle messageRb;
        if (locale != null) {
            messageRb = ResourceBundle.getBundle(RESOURCEMESSAGE, locale);
        } else {
            messageRb = ResourceBundle.getBundle(RESOURCEMESSAGE);
        }
        return messageRb.getString(key);
    }

    public static String getString(String key, String... args) {
        ResourceBundle languageRb;
        if (local != null) {
            languageRb = ResourceBundle.getBundle(RESOURCE, local);
        } else {
            languageRb = ResourceBundle.getBundle(RESOURCE);
        }
        MessageFormat msgFormat = new MessageFormat(languageRb.getString(key));
        Object[] arguments = new Object[args.length];
        int i = 0;
        for (Object obj : args) {
            String arg = (String) obj;
            try {
                arg = languageRb.getString((String) obj);
            } catch (Exception e) {
                logger.error(e);
            } finally {
                arguments[i++] = arg;
            }

        }
        String output = msgFormat.format(arguments);
        return output;
    }

    public static String getString(Locale locale, String key, String... args) {
        ResourceBundle languageRb;
        if (locale != null) {
            languageRb = ResourceBundle.getBundle(RESOURCE, locale);
        } else {
            languageRb = ResourceBundle.getBundle(RESOURCE);
        }
        MessageFormat msgFormat = new MessageFormat(languageRb.getString(key));
        Object[] arguments = new Object[args.length];
        int i = 0;
        for (Object obj : args) {
            String arg = (String) obj;
            try {
                arg = languageRb.getString((String) obj);
            } catch (Exception e) {
                logger.error(e);
            } finally {
                arguments[i++] = arg;
            }

        }
        String output = msgFormat.format(arguments);
        return output;
    }

    public static String createMessage(Locale locale, String divName, String key, String... args) {
        String output = getString(locale, key, args);
        output = divName + ParamUtils.SEPARATOR + output;
        return output;
    }

    public static String createMessage(String divName, String content) {
        return divName + ParamUtils.SEPARATOR + content;
    }

    public static String getMessage(Locale locale, String key, String... args) {
        ResourceBundle messageRb;
        if (locale != null) {
            messageRb = ResourceBundle.getBundle(RESOURCEMESSAGE, locale);
        } else {
            messageRb = ResourceBundle.getBundle(RESOURCEMESSAGE);
        }
        MessageFormat msgFormat = new MessageFormat(messageRb.getString(key));
        Object[] arguments = new Object[args.length];
        int i = 0;
        for (Object obj : args) {
            String arg = (String) obj;
            try {
                arg = messageRb.getString((String) obj);
            } catch (Exception e) {
                logger.error(e);
            } finally {
                arguments[i++] = arg;
            }

        }
        String output = msgFormat.format(arguments);
        return output;
    }
}

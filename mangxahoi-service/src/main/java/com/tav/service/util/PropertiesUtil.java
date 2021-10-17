
/* 
* Copyright 2017 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tav.service.util;

import com.tav.service.common.Constants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author BacDV
 * @version 1.0
 * @since 25-May-17 5:07 PM
 */
public class PropertiesUtil {
    protected static final Logger LOGGER = Logger.getLogger(PropertiesUtil.class);

    public static Properties getProperties() {
        String filename = "environment.properties";
        Properties prop = new Properties();
        try {
            InputStream inStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
            prop.load(inStream);
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return prop;
    }
    
    public static String decrypt(String st)
    {
        StandardPBEStringEncryptor  textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword(Constants.CommonConstant.SYSTEM_PASSWORD);
        return textEncryptor.decrypt(st);
    }
}

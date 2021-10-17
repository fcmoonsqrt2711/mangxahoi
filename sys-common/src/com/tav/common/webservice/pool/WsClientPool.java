/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.webservice.pool;

import com.tav.common.webservice.base.client.CxfWsClientFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author thuannht
 */
public class WsClientPool {

    public static final Logger logger = Logger.getLogger(WsClientPool.class);

    public static <T> T getWsClient(Class<T> interfaceClass) throws Exception {
        try {
            String serviceName = interfaceClass.getTypeName();
            return (T) CxfWsClientFactory.getMapPool().get(serviceName).borrowObject();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public static <T> void closeWsClient(T wsClient) throws Exception {
        try {
            String serviceName = wsClient.getClass().getTypeName();
            CxfWsClientFactory.getMapPool().get(serviceName).returnObject(wsClient);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }
}

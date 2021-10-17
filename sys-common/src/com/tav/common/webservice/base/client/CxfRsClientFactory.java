/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.webservice.base.client;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;

import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

/**
 *
 * @author thuannht
 */
public class CxfRsClientFactory {

    private static final Logger logger = Logger.getLogger(CxfRsClientFactory.class);
    private static final Long DEFAULT_RECEIVE_TIMEOUT = 100000l;
    private static final Long DEFAULT_CONNECTION_TIMEOUT = 100000l;

    public static WebClient createRsClient(String urlBase) throws Exception {
        return createRsClient(urlBase, null);
    }

    public static WebClient createRsClient(String urlBase, Credential cre) throws Exception {
        return createRsClient(urlBase, cre, DEFAULT_RECEIVE_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT);
    }

    public static WebClient createRsClient(String urlBase, Long receiveTimeout, Long connectionTimeout) throws Exception {
        return createRsClient(urlBase, null, receiveTimeout, connectionTimeout);
    }

    public static WebClient createRsClient(String urlBase, Credential cre, Long receiveTimeout, Long connectionTimeout) throws Exception {
        // using jackson json provider
        List<Object> providers = new ArrayList<>();
        providers.add(new JacksonJaxbJsonProvider());
        // create web client
        WebClient client = WebClient.create(urlBase, providers);

        // Authen with basic authentication
        if (cre != null && cre.isNotNull()) {
            String authorizationHeader = "Basic " + Base64Utility.encode((cre.getUsername() + ":" + cre.getPassword()).getBytes());
            client.header("Authorization", authorizationHeader);
        }
        // 
        HTTPConduit conduit = WebClient.getConfig(client).getHttpConduit();
        conduit.getClient().setReceiveTimeout(receiveTimeout != null ? receiveTimeout : DEFAULT_RECEIVE_TIMEOUT);
        conduit.getClient().setConnectionTimeout(connectionTimeout != null ? connectionTimeout : DEFAULT_CONNECTION_TIMEOUT);
        // 
        client.type(MediaType.APPLICATION_JSON);
        client.accept(MediaType.APPLICATION_JSON);

        return client;
    }

}

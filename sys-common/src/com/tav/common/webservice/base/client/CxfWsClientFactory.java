/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.webservice.base.client;

import com.tav.common.webservice.pool.WsClientPoolFactory;
import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.cxf.configuration.security.AuthorizationPolicy;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

import org.apache.log4j.Logger;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 *
 * @author thuannht
 */
public class CxfWsClientFactory {

    private static final Logger logger = Logger.getLogger(CxfWsClientFactory.class);
    private static Map<String, WsEndpointConfig> mapWsConfig = new HashMap<>();
    private static Map<String, GenericObjectPool> mapPool = new HashMap<String, GenericObjectPool>();
    public static final String USERNAME="21232f297a57a5a743894a0e4a801fc3";
    public static final String PASSWORD="21232f297a57a5a743894a0e4a801fc3";

    public static <T> T createWsClient(Class<T> interfaceClass) throws Exception {
        WsEndpointConfig wsEndpoint = getWsEndpointConfig(interfaceClass);
        if (wsEndpoint == null) {
            logger.error("FW: Not found webservice with name: " + interfaceClass
                    + " .Please specify full path with package name.");
            throw new Exception("FW: Not found webservice with name: " + interfaceClass
                    + " .Please specify full path with package name.");
        }
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(interfaceClass);
        factory.setAddress(wsEndpoint.getAddress());
        T serviceClient = (T) factory.create();
        Client client = ClientProxy.getClient(serviceClient);
        if (client != null) {
            HTTPConduit conduit = (HTTPConduit) client.getConduit();
            HTTPClientPolicy policy = new HTTPClientPolicy();
            policy.setConnectionTimeout(wsEndpoint.getConnectTimeout());//miliseconds
            policy.setReceiveTimeout(wsEndpoint.getReceiverTimeout());//miliseconds
            conduit.setClient(policy);
            // Bo sung them phan Basic Authen
            AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
            authorizationPolicy.setUserName(CxfWsClientFactory.USERNAME);//username
            authorizationPolicy.setPassword(CxfWsClientFactory.PASSWORD);//Pass truyen tu file config vao.Ma hoa MD5
            authorizationPolicy.setAuthorizationType("Basic");
            conduit.setAuthorization(authorizationPolicy);
            //End
            if (!wsEndpoint.getPasswordCallbackClass().isEmpty()) {
                Map<String, Object> outProps = new HashMap<String, Object>();
                outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
                outProps.put(WSHandlerConstants.USER, wsEndpoint.getUserName());
                outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordDigest");
                outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, wsEndpoint.getPasswordCallbackClass());
                client.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
            }
        }
        return serviceClient;
    }

    public static <T> T createWsClient(String serviceName) throws Exception {
        Class<?> interfaceClass = Class.forName(serviceName);
        WsEndpointConfig wsEndpoint = getWsEndpointConfig(interfaceClass);
        if (wsEndpoint == null) {
            logger.error("FW: Not found webservice with name: " + interfaceClass
                    + " .Please specify full path with package name.");
            throw new Exception("FW: Not found webservice with name: " + interfaceClass
                    + " .Please specify full path with package name.");
        }
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(interfaceClass);
        factory.setAddress(wsEndpoint.getAddress());
        T serviceClient = (T) factory.create();
        Client client = ClientProxy.getClient(serviceClient);
        if (client != null) {
            HTTPConduit conduit = (HTTPConduit) client.getConduit();
            HTTPClientPolicy policy = new HTTPClientPolicy();
            policy.setConnectionTimeout(wsEndpoint.getConnectTimeout());//miliseconds
            policy.setReceiveTimeout(wsEndpoint.getReceiverTimeout());//miliseconds
            conduit.setClient(policy);

            //
            if (!wsEndpoint.getPasswordCallbackClass().isEmpty()) {
                Map<String, Object> outProps = new HashMap<String, Object>();
                outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
                outProps.put(WSHandlerConstants.USER, wsEndpoint.getUserName());
                outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordDigest");
                outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, wsEndpoint.getPasswordCallbackClass());
                client.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
            }
        }
        return serviceClient;
    }

    static {
        loadConfig();
    }
    private static final String WS_ENDPOINT_CFG = "/com/viettel/erp/config/WSEndpointConfig.xml";

    public static void loadConfig() {
        try {

            InputStream in = CxfWsClientFactory.class.getResourceAsStream(WS_ENDPOINT_CFG);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();

            // SAFE: disable xxe attack
// This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all XML entity attacks are prevented
// Xerces 2 only - http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
            String FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
            dbFactory.setFeature(FEATURE, true);

// If you can't completely disable DTDs, then at least do the following:
// Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
// Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
            FEATURE = "http://xml.org/sax/features/external-general-entities";
            dbFactory.setFeature(FEATURE, false);

// Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
// Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
            FEATURE = "http://xml.org/sax/features/external-parameter-entities";
            dbFactory.setFeature(FEATURE, false);

// and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks" (see reference below)
            dbFactory.setXIncludeAware(false);
            dbFactory.setExpandEntityReferences(false);

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);
            doc.getDocumentElement().normalize();
            logger.info("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Service");
            logger.info("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                logger.info("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    WsEndpointConfig endpointConfig = new WsEndpointConfig();
                    String fullClassName = eElement
                            .getElementsByTagName("fullClassName")
                            .item(0)
                            .getTextContent();
                    endpointConfig.setFullClassName(fullClassName);

                    endpointConfig.setAddress(eElement
                            .getElementsByTagName("address")
                            .item(0)
                            .getTextContent());

                    endpointConfig.setTargetNameSpace(eElement
                            .getElementsByTagName("targetNameSpace")
                            .item(0)
                            .getTextContent());

                    endpointConfig.setUserName(eElement
                            .getElementsByTagName("userName")
                            .item(0)
                            .getTextContent());

                    endpointConfig.setPassword(eElement
                            .getElementsByTagName("password")
                            .item(0)
                            .getTextContent());

                    endpointConfig.setConnectTimeout(Integer.parseInt(eElement
                            .getElementsByTagName("connectTimeout")
                            .item(0)
                            .getTextContent()));
                    endpointConfig.setReceiverTimeout(Integer.parseInt(eElement
                            .getElementsByTagName("receiverTimeout")
                            .item(0)
                            .getTextContent()));
                    endpointConfig.setPasswordCallbackClass(eElement
                            .getElementsByTagName("passwordCallbackClass")
                            .item(0) != null
                            ? eElement
                            .getElementsByTagName("passwordCallbackClass")
                            .item(0).getTextContent() : "");
                    if (eElement.getElementsByTagName("maxPoolSize")
                            .item(0) != null) {
                        endpointConfig.setMaxPoolSize(Integer.parseInt(eElement
                                .getElementsByTagName("maxPoolSize")
                                .item(0)
                                .getTextContent()));
                    }
                    if (eElement.getElementsByTagName("maxIdleTime")
                            .item(0) != null) {
                        endpointConfig.setMaxPoolSize(Integer.parseInt(eElement
                                .getElementsByTagName("maxIdleTime")
                                .item(0)
                                .getTextContent()));
                    }
                    if (eElement.getElementsByTagName("maxWaitMillis")
                            .item(0) != null) {
                        endpointConfig.setMaxPoolSize(Integer.parseInt(eElement
                                .getElementsByTagName("maxWaitMillis")
                                .item(0)
                                .getTextContent()));
                    }

                    mapWsConfig.put(endpointConfig.getFullClassName(), endpointConfig);
                    GenericObjectPool objectPool = new GenericObjectPool(new WsClientPoolFactory(fullClassName));
                    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
                    poolConfig.setMaxTotal(endpointConfig.getMaxPoolSize());
                    poolConfig.setMaxWaitMillis(endpointConfig.getMaxWaitMillis());
                    poolConfig.setMaxIdle(endpointConfig.getMaxIdleTime());
                    objectPool.setConfig(poolConfig);
                    mapPool.put(fullClassName, objectPool);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private synchronized static <T> WsEndpointConfig getWsEndpointConfig(Class<T> interfaceClass) {
        String fullName = interfaceClass.getCanonicalName();
        if (mapWsConfig != null) {
            return mapWsConfig.get(fullName);
        }
        mapWsConfig = new HashMap<>();
        loadConfig();
        return mapWsConfig.get(fullName);
    }

    public static Map<String, WsEndpointConfig> getMapWsConfig() {
        return mapWsConfig;
    }

    public static Map<String, GenericObjectPool> getMapPool() {
        return mapPool;
    }

}

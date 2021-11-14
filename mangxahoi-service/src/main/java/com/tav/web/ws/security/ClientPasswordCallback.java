/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.web.ws.security;

import com.tav.common.webservice.base.client.CxfWsClientFactory;
import com.tav.common.webservice.base.client.WsEndpointConfig;
import java.io.IOException;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 *
 * @author HungLQ9
 */
public class ClientPasswordCallback implements CallbackHandler {

    public void handle(javax.security.auth.callback.Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        Map<String, WsEndpointConfig> map = CxfWsClientFactory.getMapWsConfig();
        for (String key : map.keySet()) {
            WsEndpointConfig endpointConfig = map.get(key);
            if (pc.getIdentifier().equals(endpointConfig.getUserName())) {
            // set the password on the callback. This will be compared to the
                // password which was sent from the client.
                pc.setPassword(endpointConfig.getPassword());
            }
        }

    }
}

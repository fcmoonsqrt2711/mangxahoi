/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.filter;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 *
 * @author hunglq9
 */
public class AuthenticationService {

    private static final Logger logger = Logger.getLogger(AuthenticationService.class);
    private static final String ADMIN="admin";

    public boolean authenticate(String authCredentials) {

        if (null == authCredentials) {
            return false;
        }
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            logger.error(e);
        }
        boolean authenticationStatus = Boolean.FALSE;
        if (usernameAndPassword != null) {
            final String[] arrUsPs = usernameAndPassword.split(":");
            final String username = arrUsPs[0];
            final String password = arrUsPs[1];

            // implement authentication or authorize here
            authenticationStatus = ADMIN.equals(username)
                    && ADMIN.equals(password);
        }
        return authenticationStatus;
    }

}

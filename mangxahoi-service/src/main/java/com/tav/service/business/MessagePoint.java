/* 
 * Copyright 2017 Viettel Telecom. All rights reserved. 
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tav.service.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author Apple family
 */
@Component
@ServerEndpoint(value = "/message")
public class MessagePoint {

    int i = 0;
    boolean isRunning = false;
    private static final Set<Session> sessions
            = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(final Session session) throws IOException {
//        session.getBasicRemote().sendText("Connected! " + session.getBasicRemote());
        System.out.println("Clien is connected ");
        sessions.add(session);
       
       

    }

    

    public void sendAll(String sms) {
       // while (true)
        {
            try {
                Thread.sleep(7000);
                synchronized (sessions) {
                    for (Session s : sessions) {
                        try {
                            if (s.isOpen()) {
                                s.getBasicRemote().sendText(sms);
                            }
                        } catch (IOException ex) {
                        }
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("client: " + message);
        try {
            broadcastSMS(message);
        } catch (Exception ex) {
            System.out.println("loi: " + ex);
        }
    }

//    @OnMessage
//    public void processUpload(byte[] b, boolean last, Session session) {
//        // process partial data here, which check on last to see if these is more on the way
//        System.out.println("ok bean " + b);
//        String st=new String(b);
//        System.out.println("String ok bean " +st);
//        
//    }
    public void broadcastSMS(String sms) {
        synchronized (sessions) {
            for (Session s : sessions) {
                try {
                    if (s.isOpen()) {
                        s.getBasicRemote().sendText(sms);
                    }
                } catch (IOException ex) {
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        sessions.remove(session);
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessions.remove(session);
        // Do error handling here
    }

}

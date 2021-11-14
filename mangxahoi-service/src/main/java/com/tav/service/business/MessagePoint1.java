/* 
 * Copyright 2019 Viettel Telecom. All rights reserved. 
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tav.service.business;

import com.tav.service.dto.UserDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Apple family
 */
@Component
@ServerEndpoint(value = "/message1")
public class MessagePoint1 {

    static Integer count = 0;
    boolean isRunning = false;
    private static final Set<Session> sessions
            = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void onOpen(final Session session) throws IOException {
        //session.getBasicRemote().sendText("Connected! " + session.getBasicRemote());
        System.out.println("Clien is connected ");
        sendSMSToOne("Open:" + session.getId(), session);
        System.out.println("open session.getId(): " + session.getId());
        sessions.add(session);
        broadcastSMS("OnlineLoad");
    }

    public void sendAll(String sms) {
        // while (true)
        {
            try {
                //Thread.sleep(7000);
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
        
        System.out.println("onMessage from " + session.getId());
        System.out.println("client " + session.getId() + ":  " + message);
        try {
            if ((message + "").contains("messageChat")) {
                broadcastSMS("MessageLoad");
            }

            System.out.println("sessions.size(): " + sessions.size());
        } catch (Exception ex) {
            //System.out.println("loi: " + ex);
            Logger.getLogger(MessagePoint1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void sendSMSToOne(String sms, Session session) {
        System.out.println("sendSMSToOne " + session.getId());
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(sms);
            }
        } catch (IOException ex) {
        }
    }

    public void broadcastSMS(String sms) {
        System.out.println("broadcastSMS : " + sms);
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
        System.out.println("on close websocket " + session.getId());
        sessions.remove(session);
        broadcastSMS("OnlineLoad");
        
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("on error websocket " + session.getId());
        sessions.remove(session);
        broadcastSMS("OnlineLoad");
        
    }

}

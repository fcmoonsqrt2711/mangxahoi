/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.web.ws.security;

import com.tav.service.dto.ServiceResult;
import com.tav.service.bo.UserSession;
import com.tav.service.bo.UserBO;
import com.tav.service.common.Config;
import com.tav.service.common.RestRequest;
import com.tav.service.dto.UserDTO;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *
 * @author LienPTK
 */
public class SessionListener implements HttpSessionAttributeListener {

    private String restURL = "http://localhost:8084/mangxahoi-service";

    public static Map<Long, HttpSession> getSessionMap(ServletContext appContext) {
        System.out.println("da chui vao getSessionMap ");
        Map<Long, HttpSession> sessionMap = (Map<Long, HttpSession>) appContext.getAttribute("globalSessionMap");
        if (sessionMap == null) {
            sessionMap = new ConcurrentHashMap<>();
            appContext.setAttribute("globalSessionMap", sessionMap);
        }
        return sessionMap;
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("da chui vao attributeAdded ");
        Map<Long, HttpSession> sessionMap = getSessionMap(event.getSession().getServletContext());
        Object userSession = event.getValue();
        if (userSession instanceof UserSession) {
            try {
                if (userSession != null) {
                    if (sessionMap.get(event.getSession().getId()) == null) {
                        System.out.println("attributeAdded:  userId ======= " + ((UserSession) userSession).getGid());
                        sessionMap.put(((UserSession) userSession).getGid(), event.getSession());
                        updateOnlineStatus(((UserSession) userSession).getGid(), 1L);
                    }
                }
            } catch (Exception ex) {
            }

        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent hsbe) {
        System.out.println("da chui vao attributeRemoved ");
        Map<Long, HttpSession> sessionMap = getSessionMap(hsbe.getSession().getServletContext());
        Object userSession = hsbe.getValue();
        if (userSession instanceof UserSession) {
            try {
                if (userSession != null) {
                    if (sessionMap.get(hsbe.getSession().getId()) == null) {
                        System.out.println("attributeRemoved:  userId ======= " + ((UserSession) userSession).getGid());
                        updateOnlineStatus(((UserSession) userSession).getGid(), 2L);
                    }
                }
            } catch (Exception ex) {
            }

        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent hsbe) {
    }

    public void updateOnlineStatus(Long userId, Long status) {
        String url = restURL + "/userRsServiceRest/getOneById/" + userId;
        UserDTO usersDTO = (UserDTO) RestRequest.getObject(url, UserDTO.class);
        UserDTO usersBO = new UserDTO();

//    private String userName;		//
//    private String passWord;		//
//    private String fullName;		//
//    private Long gender;		//
//    private Date dateOfBirth;		//
//    private String phoneNumber;		//
//    private String email;		//
//    private String address;		//
//    private String avatarPath;		//
//    
//    
//    private Long isOnline;		//
        usersBO.setUserName(usersDTO.getUserName());
        usersBO.setPassWord(usersDTO.getPassWord());
        usersBO.setFullName(usersDTO.getFullName());
        usersBO.setGender(usersDTO.getGender());
        usersBO.setDateOfBirth(usersDTO.getDateOfBirth());
        usersBO.setDateOfBirthST(usersDTO.getDateOfBirthST());
        usersBO.setPhoneNumber(usersDTO.getPhoneNumber());
        usersBO.setEmail(usersDTO.getEmail());
        usersBO.setAddress(usersDTO.getAddress());
        usersBO.setAvatarPath(usersDTO.getAvatarPath());

        usersBO.setIsOnline(status);
        System.out.println("GET SUCESS, SET ONLINE = " + status);
        String url1 = restURL + "/userRsServiceRest/updateBO";
        ServiceResult result = (ServiceResult) RestRequest.postAndReturnObject(url1, usersBO, ServiceResult.class);

        System.out.println("UPDATE SUCESS");
    }
}

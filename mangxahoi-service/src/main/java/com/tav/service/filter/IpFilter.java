/*
* Copyright 2017 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.filter;

import com.tav.service.util.PropertiesUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: AnTV
 * @version 1.0
 * @since 14-6-17 20:00 PM
 */
public class IpFilter implements javax.servlet.Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filter) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String ipAddress= getRemoteIpAddress(httpServletRequest);
            
            boolean checkPermission=false;
            String[] ipArray=getAllIpPermission();
            for(int i=0;i<ipArray.length;i++){
                if(ipAddress.equals(ipArray[i]))
                {
                    checkPermission=true;
                    break;
                }
            }
            if (!checkPermission) {
                PrintWriter out = response.getWriter();
                out.print("You don't have permission");
            } else {
                filter.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    private String[] getAllIpPermission() {
        String ipSt = PropertiesUtil.decrypt(PropertiesUtil.getProperties().getProperty("IP"));
        return ipSt.split(",");
    }

    private String getRemoteIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}

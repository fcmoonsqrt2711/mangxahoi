/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.web.base;

/**
 *
 * @author thuannht
 */
import com.tav.common.util.Stopwatch;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(LoggerInterceptor.class);
    Stopwatch timer;

    //before the actual handler will be executed
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler)
            throws Exception {

        timer = new Stopwatch();
//        request.setAttribute("timer", timer);

        return true;
    }

    //after the handler is executed
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {

//        Stopwatch timer = (Stopwatch) request.getAttribute("timer");
//
//        long endTime = System.currentTimeMillis();
//
//        long executeTime = endTime - startTime;
//
        //log it        
        logger.info(timer.elapsedTime() + ",exectime," + request.getServletPath());
    }
}

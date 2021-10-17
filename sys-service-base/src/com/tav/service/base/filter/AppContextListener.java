/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.filter;

import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

/**
 *
 * @author thuannht
 */
public class AppContextListener implements ServletContextListener {

    protected static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ServletContext ctx = servletContextEvent.getServletContext();
            String igniteConfigurationFilePath = ctx.getInitParameter("IgniteConfigurationFilePath");
            URL url = ctx.getResource(igniteConfigurationFilePath);
            Ignite ignite = Ignition.start(url);//ignite.cache("cacheErp");
            System.out.println(ignite);
        } catch (MalformedURLException ex) {
            log.error(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        ServletContext ctx = servletContextEvent.getServletContext();

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.ignite.base;

import java.lang.reflect.Method;
import org.apache.ignite.lang.IgniteClosure;
import org.apache.log4j.Logger;

/**
 *
 * @author HungLQ9
 */
public class VtIgniteClosure implements IgniteClosure<DataBean, Object> {

    private static Logger logger = Logger.getLogger(VtIgniteClosure.class);
    private BusinessBean bizBean;

    public VtIgniteClosure(BusinessBean bizBean) {
        this.bizBean = bizBean;
    }

    @Override
    public Object apply(DataBean e) {
        try {
            Class classObj = Class.forName(bizBean.getClassName());
            Method method = classObj.getDeclaredMethod(bizBean.getMethodName(), bizBean.getMethodParam());
            return method.invoke(classObj.newInstance(), e.getData());
        } catch (Exception ex) {
            logger.error(ex);
        }
        return null;
    }

}

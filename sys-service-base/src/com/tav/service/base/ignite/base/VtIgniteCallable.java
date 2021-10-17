/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.ignite.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ignite.lang.IgniteCallable;

/**
 *
 * @author HungLQ9
 */
public class VtIgniteCallable implements IgniteCallable<Object> {

    private BusinessBean bizBean;
    private DataBean dataBean;

    public VtIgniteCallable(BusinessBean bizBean, DataBean dataBean) {
        this.bizBean = bizBean;
        this.dataBean = dataBean;

    }

    @Override
    public Object call() throws Exception {
        try {
            Class classObj = Class.forName(bizBean.getClassName());
            Method method = classObj.getDeclaredMethod(bizBean.getMethodName(), bizBean.getMethodParam());
            return method.invoke(classObj.newInstance(), dataBean.getData());
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(VtComputeJobAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(VtComputeTaskAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

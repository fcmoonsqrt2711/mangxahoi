/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.ignite.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ignite.IgniteException;
import org.apache.ignite.compute.ComputeJobAdapter;

/**
 *
 * @author HungLQ9
 */
public class VtComputeJobAdapter extends ComputeJobAdapter {

    private BusinessBean bizBean;
    private DataBean dataBean;

    public VtComputeJobAdapter(BusinessBean bizBean, DataBean dataBean) {
        this.bizBean = bizBean;
        this.dataBean = dataBean;
    }

    @Override
    public Object execute() throws IgniteException {
        // 
        try {
            Class classObj = Class.forName(bizBean.getClassName());
            Method method = classObj.getDeclaredMethod(bizBean.getMethodName(), bizBean.getMethodParam());
            return method.invoke(classObj.newInstance(), dataBean.getData());
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(VtComputeJobAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

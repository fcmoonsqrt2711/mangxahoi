/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.ignite.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskAdapter;

/**
 *
 * @author HungLQ9
 */
public class VtComputeTaskAdapter extends ComputeTaskAdapter<List, Object> {

    private BusinessBean bizBean;
    private MapReduceBean mapReduceBean;

    public VtComputeTaskAdapter(BusinessBean bizBean, MapReduceBean mapReduceBean) {
        this.bizBean = bizBean;
        this.mapReduceBean = mapReduceBean;

    }

    @Override
    public Map<? extends ComputeJob, ClusterNode> map(List<ClusterNode> subgrid, List ls) throws IgniteException {
        try {
            Class[] methodParam = new Class[]{BusinessBean.class, List.class, List.class};
            Object[] objectArg = {bizBean, subgrid, ls};
            Class classObj = Class.forName(mapReduceBean.getClassName());
            Method method = classObj.getDeclaredMethod(mapReduceBean.getMethodName(), methodParam);
            return (Map<? extends ComputeJob, ClusterNode>) method.invoke(classObj.newInstance(), objectArg);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(VtComputeJobAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List reduce(List<ComputeJobResult> results) throws IgniteException {
        List ls = new ArrayList<>();
        for (ComputeJobResult res : results) {
            ls.add(res.<List>getData());
        }
        return ls;
    }

}

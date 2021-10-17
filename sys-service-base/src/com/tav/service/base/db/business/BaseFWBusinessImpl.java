/*
 * Copyright (C) 2015 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.base.db.business;

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.base.pojo.ConditionBean;
import java.util.*;
import javax.transaction.Transactional;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;

/**
 *
 * @author kdvt_binhnt22@viettel.com.vn
 * @version 2.0
 * @param <TDAO>
 * @since Mar 2015
 */
@Transactional
public class BaseFWBusinessImpl<TDAO extends BaseFWDAOImpl, TDTO extends BaseFWDTOImpl, TModel extends BaseFWModelImpl>
        implements BaseFWBusiness<TDTO, TModel> {

    protected TDAO tDAO;
    protected TModel tModel;

    @Override
    public List<TDTO> getAll() {
        List<TModel> list = gettDAO().getAll(tModel.getClass());
        return convertListModeltoDTO(list);
    }

    @Override
    public BaseFWDTOImpl getOneById(Long costCenterId) {
        TModel rs = (TModel) gettDAO().get(tModel.getClass(), costCenterId);
        return rs.toDTO();
    }

    @Override
    public Long save(TDTO costCenterBO) {
        return gettDAO().saveObject(costCenterBO.toModel());
    }

    @Override
    public Long update(TDTO costCenterBO) {
        return gettDAO().updateObject(costCenterBO.toModel());
    }

    @Override
    public void delete(TDTO costCenterBO) {
        gettDAO().delete(costCenterBO.toModel());
    }

//    @Override
//    public List<TDTO> searchByHql(String hql, List<ConditionBean> conditionBeans) {
//        List<TModel> lst = gettDAO().findByHql(hql, conditionBeans, 0, 0);
//        return convertListModeltoDTO(lst);
//    }

//    @Override
//    public List<TDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx) {
//        List<TModel> lst = gettDAO().findByHql(hql, conditionBeans, startIdx, endIdx);
//        return convertListModeltoDTO(lst);
//    }

//    @Override
//    public Long countByHql(String hql, List<ConditionBean> conditionBeans) {
//        return gettDAO().countByHql(hql, conditionBeans);
//    }

//    @Override
//    public int executeByHql(String hql, List<ConditionBean> conditionBeans) {
//        return gettDAO().executeByHql(hql, conditionBeans);
//    }

    @Override
    public Date getSysDate() throws Exception {
        return gettDAO().getSysDate();
    }

    @Override
    public Long getNextValSequence(String sequense) {
        return gettDAO().getNextValSequence(sequense);
    }

    //gen code
    public List convertListModeltoDTO(List<TModel> listModel) {
        List<BaseFWDTOImpl> lstForm = new ArrayList<BaseFWDTOImpl>();
        if (listModel != null) {
            for (TModel model : listModel) {
                lstForm.add(model.toDTO());
            }
        }

        return lstForm;
    }

    public List convertListDTOtoModel(List<TDTO> listDTO) {

        List<BaseFWModelImpl> lstModel = new ArrayList<BaseFWModelImpl>();
        if (listDTO != null) {
            for (TDTO dto : listDTO) {
                lstModel.add(dto.toModel());
            }
        }
        return lstModel;
    }

    public TDAO gettDAO() {
        return tDAO;
    }

    public void settDAO(TDAO tDAO) {
        this.tDAO = tDAO;
    }

    public TModel gettModel() {
        return tModel;
    }

    public void settModel(TModel tModel) {
        this.tModel = tModel;
    }

    /**
     * Lay du lieu tu cache
     *
     * @param <T>
     * @param interfaceClass
     * @param key
     * @return
     */
    public <T> T getCacheByKey(Class<T> interfaceClass, String key) {
        try {
            Ignite ignite = Ignition.ignite();
            IgniteCache cache;
            Collection<String> lstcaches = ignite.cacheNames();
            if (lstcaches.contains(ERP_CACHE)) {
                cache = ignite.cache(ERP_CACHE);
            } else {
                cache = ignite.createCache(ERP_CACHE);
            }
            if (cache.containsKey(key)) {
                return (T) cache.get(key);
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Day du lieu vao cache
     *
     * @param key
     * @param value
     */
    public void setCache(String key, Object value) {
        Ignite ignite = Ignition.ignite();
        IgniteCache cache;
        Collection<String> lstcaches = ignite.cacheNames();
        if (lstcaches.contains(ERP_CACHE)) {
            cache = ignite.cache(ERP_CACHE);
        } else {
            cache = ignite.createCache(ERP_CACHE);
        }
        cache.put(key, value);
    }
    private final static String ERP_CACHE = "cacheErp";

    public ClusterGroup getRemoteNodeGroup() {
        Ignite ignite = Ignition.ignite();
        return ignite.cluster().forRemotes();
    }

    public ClusterGroup getLocalNodeGroup() {
        Ignite ignite = Ignition.ignite();
        return ignite.cluster().forLocal();
    }

    public ClusterGroup getNodeGroupByAttr(String attrName, String attrValue) {
        Ignite ignite = Ignition.ignite();
        return ignite.cluster().forAttribute(attrName, attrValue);
    }
}

/*
 * Copyright (C) 2015 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.base.db.dao;

import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.base.pojo.ConditionBean;
import com.tav.service.base.pojo.OrderBean;
import com.tav.service.base.utils.Constants;
import com.tav.service.base.utils.DataUtil;
import com.viettel.util.ParamUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author BinhNT22
 * @version: 2.0
 * @param <T> - a Model that extends BaseFWModelImpl
 * @param <ID> - id of the model
 * @since: 31/03/2015
 */
@Repository
public class BaseFWDAOImpl<T extends BaseFWModelImpl, ID extends Serializable> {

    // Trong truong hop co nhieu data source thi tao SessionFactory moi
    // extend HibernateSessionFactory va khai bao them vao hibernate-config.xml
    long i;
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Session session;
    protected static Logger log = Logger.getLogger(BaseFWDAOImpl.class);
    protected T model;

    @Transactional
    public String delete(T obj) {
        try {
            getSession().delete(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String save(T obj) {
        try {
            getSession().save(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    //
    @Transactional
    public String saveList(List<T> obj) {

        try {
            session = getSession(); // tiepnv6 edit 27/07/15 16h:51
            for (T item : obj) {
                session.saveOrUpdate(item);
            }

        } catch (HibernateException ex) {
            log.error(ex.getMessage(), ex);
            return ex.getMessage();
        }
        return ParamUtils.SUCCESS;

    }

    @Transactional
    public String update(T obj) {
        try {
            getSession().update(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String updateMerge(T obj) {
        try {
            session.merge(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition) {
        return find(boName, lstCondition, null, 0, 0);
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition, String logic) {
        return find(boName, lstCondition, null, 0, 0);
    }

    public String buildOrder(List<OrderBean> orderBeans) {
        StringBuilder sql = new StringBuilder();
        if (orderBeans != null && !orderBeans.isEmpty()) {
            sql.append(" order by ");
            for (OrderBean orderBean : orderBeans) {
                sql.append(orderBean.getField());
                sql.append(orderBean.getOperator());
                sql.append(OrderBean.DELIMITER);
            }
            return sql.substring(0, sql.length() - 1);
        }
        return "";
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition, List<OrderBean> orderBeans, int start, int maxResult) {

        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            if (lstCondition != null && lstCondition.size() > 0) {
                buildConditionQuery(sql, lstCondition);
            }
            if (orderBeans != null && !orderBeans.isEmpty()) {
                //build order
                sql.append(buildOrder(orderBeans));
            }
            Query query = getSession().createQuery(sql.toString());
            if (maxResult != 0) {
                query.setFirstResult(start);
                query.setMaxResults(maxResult);
            }
            fillConditionQuery(query, lstCondition);
            return query.list();
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return null;
        }
    }

//    public List<T> findByHql(String sql, List<ConditionBean> lstCondition, int start, int maxResult) {
//        try {
//            Query query = getSession().createQuery(sql);
//            if (maxResult != 0) {
//                query.setFirstResult(start);
//                query.setMaxResults(maxResult);
//            }
//            fillCondition4SearchByHql(query, lstCondition);
//            return query.list();
//        } catch (HibernateException he) {
//            log.error(he.getMessage(), he);
//            return null;
//        }
//    }

//    public Long countByHql(String hql, List<ConditionBean> lstCondition) {
//        try {
//            Query query = getSession().createQuery(hql);
//            fillCondition4SearchByHql(query, lstCondition);
//            return (Long) query.list().get(0);
//        } catch (HibernateException he) {
//            log.error(he.getMessage(), he);
//            return null;
//        }
//    }

//    public int executeByHql(String hql, List<ConditionBean> lstCondition) {
//        try {
//            Query query = getSession().createQuery(hql);
//            fillCondition4SearchByHql(query, lstCondition);
//            return query.executeUpdate();
//        } catch (HibernateException he) {
//            log.error(he.getMessage(), he);
//            return 0;
//        }
//    }

    @Transactional
    public String delete(String boName, List<ConditionBean> lstCondition) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            int result = 0;
            if (lstCondition != null) {
                buildConditionQuery(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = query.executeUpdate();
            }
            if (result == 0) {
                return ParamUtils.FAIL;
            } else {
                return ParamUtils.SUCCESS;
            }
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public int deleteList(String boName, List<ConditionBean> lstCondition) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            int result = 0;
            if (lstCondition != null) {
                buildConditionQuery(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = query.executeUpdate();

            }

            return result;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return 0;
        }
    }

    /**
     *
     */
    public <T> T get(final Class<T> type, final Long id) {
        return (T) getSession().get(type, id);
    }

    public long count(String boName, List<ConditionBean> lstCondition) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select count(*) from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            long result;
            if (lstCondition != null) {
                buildConditionQuery(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = (Long) query.list().get(0);
            } else {
                Query query = getSession().createQuery(sql.toString());
                result = (Long) query.list().get(0);
            }
            return result;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return 0l;
        }
    }

    public void buildConditionQuery(StringBuilder sql, List<ConditionBean> lstCondition) {
        if (lstCondition != null) {
            int index = 0;
            for (ConditionBean con : lstCondition) {
                sql.append(ParamUtils.LOGIC_AND);
                sql.append(con.getField());
                sql.append(con.getOperator());
                if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_STRING)) {
                    sql.append(":idx").append(String.valueOf(index++));
                    if (con.getOperator().equalsIgnoreCase(ParamUtils.OP_LIKE)) {
                        sql.append(" ESCAPE '\\' ");
                    }
                } else if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_DATE)) {
                    sql.append(" :idx").append(String.valueOf(index++));
                } else if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_LIST)) {
                    sql.append(" (:idx").append(String.valueOf(index++)).append(" )");
                } else {
                    sql.append(" :idx").append(String.valueOf(index++));
                }
            }

        }
    }

    public void fillConditionQuery(Query query, List<ConditionBean> lstCondition) {
        int index = 0;
        for (ConditionBean con : lstCondition) {
            if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_STRING)) {
                if (con.getOperator().equals(ParamUtils.OP_IN)) {
                    query.setParameterList("idx" + String.valueOf(index++), DataUtil.parseInputListString(con.getStringValue().toString()));
                } else {
                    query.setParameter("idx" + String.valueOf(index++), con.getStringValue());
                }
            } else if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_LIST) && con.getListValue() != null) {
                query.setParameterList("idx" + String.valueOf(index++), con.getListValue());
            } else {
                query.setParameter("idx" + String.valueOf(index++), con.getStringValue());
            }
        }
    }

    public void fillCondition4SearchByHql(Query query, List<ConditionBean> lstCondition) {
        if (lstCondition == null || lstCondition.isEmpty()) {
            return;
        }
        for (ConditionBean con : lstCondition) {
            if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_STRING)) {
                if (con.getOperator().equals(ParamUtils.OP_IN)) {
                    query.setParameterList(con.getField(), DataUtil.parseInputListString(con.getStringValue().toString()));
                } else {
                    query.setParameter(con.getField(), con.getStringValue());
                }
            } else if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_LIST) && con.getListValue() != null) {
                query.setParameterList(con.getField(), con.getListValue());
            } else if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_DATE) && con.getDateValue() != null) {
                query.setParameter(con.getField(), con.getDateValue());
            } else if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_DOUBLE) && con.getDoubleValue() != null) {
                query.setParameter(con.getField(), con.getDoubleValue());
            } else if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_INTEGER) && con.getIntValue() != null) {
                query.setParameter(con.getField(), con.getIntValue());
            } else if (con.getType().equalsIgnoreCase(ConditionBean.TYPE_LONG) && con.getLongValue() != null) {
                query.setParameter(con.getField(), con.getLongValue());
            } else {
                query.setParameter(con.getField(), con.getStringValue());
            }
        }
    }

    public Long getTransactionId() {
        StringBuilder sql = new StringBuilder("select ");
        sql.append(Constants.TRANSACTION_SEQUENCE);
        sql.append(".nextval from dual");
        Query query = getSession().createSQLQuery(sql.toString());
        return ((BigDecimal) query.list().get(0)).longValue();
    }

    public Long getNextValSequence(String sequense) {
        StringBuilder sql = new StringBuilder("select ");
        sql.append(sequense);
        sql.append(".nextval from dual");
        Query query = getSession().createSQLQuery(sql.toString());
        return ((BigDecimal) query.list().get(0)).longValue();

    }

    @Transactional
    public Long saveObject(T obj) {
        try {
            long id = (long) getSession().save(obj);
            return id;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return 0L;
        }
    }

    @Transactional
    public Long updateObject(T obj) {
        try {
            getSession().update(obj);
            return 1L;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return 0L;
        }
    }

    public String getSysDate(String pattern) throws Exception {
        String queryString = "SELECT to_char(sysdate,:id)  from dual";
        Query query = getSession().createSQLQuery(queryString);
        query.setParameter("id", pattern);
        return query.list().get(0).toString();

    }

    public Date getSysDate() throws Exception {
        String queryString = "SELECT sysdate  from dual";
        Query query = getSession().createSQLQuery(queryString);
        return (Date) query.list().get(0);

    }

    public <T> List<T> getAll(final Class<T> type) {
        final Criteria crit = getSession().createCriteria(type);
        return crit.list();
    }

    @Transactional
    public String saveListNoId(List<T> obj) {
        try {
            for (T item : obj) {
                getSession().save(item);
            }
            return ParamUtils.SUCCESS;
        } catch (HibernateException ex) {
            log.error(ex.getMessage(), ex);
            return ex.getMessage();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

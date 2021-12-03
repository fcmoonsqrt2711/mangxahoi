package com.tav.service.dao;

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.PostBO;
import com.tav.service.common.DateUtil;
import com.tav.service.dto.PostDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import com.tav.service.common.StringUtil;
import java.text.ParseException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.spatial.GeometryType;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("postDAO")
public class PostDAO extends BaseFWDAOImpl<PostBO, Long> {

    public List<PostDTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.userId as userId, ");
        sqlCommand.append("tbl.groupId as groupId, ");
        sqlCommand.append("tbl.createdTime as createdTime, ");

        sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST, ");
        sqlCommand.append("tbl.isAvatar as isAvatar, ");
        sqlCommand.append("tbl.description as description ");

        sqlCommand.append(" FROM Post tbl ");

//        sqlCommand.append(" ORDER BY tbl.createdTime DESC ");
        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and ( tbl.description like :stringKeyWord  ");
            sqlCommand.append(" )   ");
        }
        if (searchDTO.getLong2() != null) {
            sqlCommand.append(" and ( tbl.userId = :userId  ");
            sqlCommand.append(" )   ");
        }
        sqlCommand.append(" ORDER BY tbl.createdTime DESC ");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("userId", LongType.INSTANCE)
                .addScalar("groupId", LongType.INSTANCE)
                .addScalar("createdTime", DateType.INSTANCE)
                .addScalar("createdTimeST", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("isAvatar", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(PostDTO.class))
                .setFirstResult(offset);
        if (limit != null && limit != 0) {
            query.setMaxResults(limit);
        }
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        if (searchDTO.getLong2() != null) {
            query.setParameter("userId", searchDTO.getLong2());
        }
        return query.list();
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append(" COUNT(1)");
        sqlCommand.append(" FROM  Post tbl ");
        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and ( tbl.description like :stringKeyWord  ");
            sqlCommand.append(" )   ");
        }
        if (searchDTO.getLong2() != null) {
            sqlCommand.append(" and ( tbl.userId = :userId  ");
            sqlCommand.append(" )   ");
        }
        Query query = getSession().createSQLQuery(sqlCommand.toString());
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        if (searchDTO.getLong2() != null) {
            query.setParameter("userId", searchDTO.getLong2());
        }
        return ((BigInteger) query.uniqueResult()).intValue();
    }
    //get one

    public PostDTO getOneObjById(Long id) {
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.userId as userId, ");
        sqlCommand.append("tbl.groupId as groupId, ");
        sqlCommand.append("tbl.createdTime as createdTime, ");
        sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST, ");
        sqlCommand.append("tbl.isAvatar as isAvatar, ");
        sqlCommand.append("tbl.description as description ");

        sqlCommand.append(" FROM Post tbl ");
        sqlCommand.append(" WHERE tbl.gid = :gid");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("userId", LongType.INSTANCE)
                .addScalar("groupId", LongType.INSTANCE)
                .addScalar("createdTime", DateType.INSTANCE)
                .addScalar("createdTimeST", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("isAvatar", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(PostDTO.class));
        query.setParameter("gid", id);
        PostDTO item = (PostDTO) query.uniqueResult();
        return item;
    }

    //delete
    @Transactional
    public ServiceResult deleteList(List<Long> listIds) {
        ServiceResult result = new ServiceResult();
        Query q = getSession().createQuery("DELETE FROM PostBO WHERE gid IN (:listIds)");
        q.setParameterList("listIds", listIds);
        try {
            q.executeUpdate();
        } catch (ConstraintViolationException e) {
            log.error(e);
            result.setError(e.getMessage());
            result.setErrorType(ConstraintViolationException.class.getSimpleName());
            result.setConstraintName(e.getConstraintName());
        } catch (JDBCConnectionException e) {
            log.error(e);
            result.setError(e.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        }
        return result;
    }

    //update
    @Transactional
    public ServiceResult updateObj(PostDTO dto) {
        ServiceResult result = new ServiceResult();
        PostBO bo = dto.toModel();
        try {
            getSession().merge(bo);
        } catch (ConstraintViolationException e) {
            log.error(e);
            result.setError(e.getMessage());
            result.setErrorType(ConstraintViolationException.class.getSimpleName());
            result.setConstraintName(e.getConstraintName());
        } catch (JDBCConnectionException e) {
            log.error(e);
            result.setError(e.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        } catch (HibernateException e) {
            log.error(e);
            result.setError(e.getMessage());
        }
        return result;
    }

    @Transactional
    public PostBO addDTO(PostDTO dto) {
        ServiceResult result = new ServiceResult();
        Date now = new Date();
        dto.setCreatedTime(now);

        dto.setCreatedTimeST(DateUtil.getCurrentDateTime());
        Session session1 = getSession();
        PostBO BO = new PostBO();
        try {
            BO = (PostBO) session1.merge(dto.toModel());
        } catch (JDBCConnectionException e) {
            log.error(e);
            result.setError(e.getMessage());
            result.setErrorType(JDBCConnectionException.class.getSimpleName());
        } catch (ConstraintViolationException e) {
            log.error(e);
            result.setError(e.getMessage());
            result.setErrorType(ConstraintViolationException.class.getSimpleName());
            result.setConstraintName(e.getConstraintName());
        } catch (HibernateException e) {
            log.error(e);
            result.setError(e.getMessage());
        }
        return BO;
    }
}

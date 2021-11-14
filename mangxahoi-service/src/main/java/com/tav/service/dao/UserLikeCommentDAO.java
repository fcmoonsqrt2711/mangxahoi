package com.tav.service.dao;

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.UserLikeCommentBO;
import com.tav.service.dto.UserLikeCommentDTO;
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
import org.hibernate.type.LongType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userLikeCommentDAO")
public class UserLikeCommentDAO extends BaseFWDAOImpl<UserLikeCommentBO, Long> {

    public List<UserLikeCommentDTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.userId as userId, ");
        sqlCommand.append("tbl.fullName as fullName, ");
        sqlCommand.append("tbl.commentId as commentId, ");
        sqlCommand.append("to_char(tbl.likeTime, 'DD/MM/YYYY') as likeTimeST ");

        sqlCommand.append(" FROM UserLikeComment tbl ");

        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
        }

        sqlCommand.append(" ORDER BY tbl.gid ");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("userId", LongType.INSTANCE)
                .addScalar("fullName", StringType.INSTANCE)
                .addScalar("commentId", LongType.INSTANCE)
                .addScalar("likeTimeST", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(UserLikeCommentDTO.class))
                .setFirstResult(offset);
        if (limit != null && limit != 0) {
            query.setMaxResults(limit);
        }
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        return query.list();
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append(" COUNT(1)");
        sqlCommand.append(" FROM  UserLikeComment tbl ");
        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
        }
        if (searchDTO.getLong1() != null) {
            sqlCommand.append(" and ( tbl.commentId = :commentId  ");
            sqlCommand.append(" )   ");
        }

        Query query = getSession().createSQLQuery(sqlCommand.toString());
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        if (searchDTO.getLong1() != null) {
            query.setParameter("commentId", searchDTO.getLong1());
        }
        return ((BigInteger) query.uniqueResult()).intValue();
    }
    //get one

    public UserLikeCommentDTO getOneObjById(Long id) {
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.userId as userId, ");
        sqlCommand.append("tbl.fullName as fullName, ");
        sqlCommand.append("tbl.commentId as commentId, ");
        sqlCommand.append("to_char(tbl.likeTime, 'DD/MM/YYYY') as likeTimeST ");

        sqlCommand.append(" FROM UserLikeComment tbl ");
        sqlCommand.append(" WHERE tbl.gid = :gid");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("userId", LongType.INSTANCE)
                .addScalar("fullName", StringType.INSTANCE)
                .addScalar("commentId", LongType.INSTANCE)
                .addScalar("likeTimeST", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(UserLikeCommentDTO.class));
        query.setParameter("gid", id);
        UserLikeCommentDTO item = (UserLikeCommentDTO) query.uniqueResult();
        return item;
    }

    //delete
    @Transactional
    public ServiceResult deleteList(List<Long> listIds) {
        ServiceResult result = new ServiceResult();
        Query q = getSession().createQuery("DELETE FROM UserLikeCommentBO WHERE gid IN (:listIds)");
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
    public ServiceResult updateObj(UserLikeCommentDTO dto) {
        ServiceResult result = new ServiceResult();
        UserLikeCommentBO bo = dto.toModel();
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
    public UserLikeCommentBO addDTO(UserLikeCommentDTO dto) {
        ServiceResult result = new ServiceResult();
        Session session1 = getSession();
        UserLikeCommentBO BO = new UserLikeCommentBO();
        try {
            BO = (UserLikeCommentBO) session1.merge(dto.toModel());
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

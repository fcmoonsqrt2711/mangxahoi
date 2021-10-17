package com.tav.service.dao;

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.ChatBoxBO;
import com.tav.service.dto.ChatBoxDTO;
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

@Repository("chatBoxDAO")
public class ChatBoxDAO extends BaseFWDAOImpl<ChatBoxBO, Long> {

    public List<ChatBoxDTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.userID1 as userID1, ");
        sqlCommand.append("tbl.avatarPath1 as avatarPath1, ");
        sqlCommand.append("tbl.fullName1 as fullName1, ");
        sqlCommand.append("tbl.userID2 as userID2, ");
        sqlCommand.append("tbl.avatarPath2 as avatarPath2, ");
        sqlCommand.append("tbl.fullName2 as fullName2 ");

        sqlCommand.append(" FROM ChatBox tbl ");

        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
        }

        sqlCommand.append(" ORDER BY tbl.gid ");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("userID1", LongType.INSTANCE)
                .addScalar("avatarPath1", StringType.INSTANCE)
                .addScalar("fullName1", StringType.INSTANCE)
                .addScalar("userID2", LongType.INSTANCE)
                .addScalar("avatarPath2", StringType.INSTANCE)
                .addScalar("fullName2", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ChatBoxDTO.class))
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
        sqlCommand.append(" FROM  ChatBox tbl ");
        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
        }
        Query query = getSession().createSQLQuery(sqlCommand.toString());
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        return ((BigInteger) query.uniqueResult()).intValue();
    }
    //get one

    public ChatBoxDTO getOneObjById(Long id) {
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.userID1 as userID1, ");
        sqlCommand.append("tbl.avatarPath1 as avatarPath1, ");
        sqlCommand.append("tbl.fullName1 as fullName1, ");
        sqlCommand.append("tbl.userID2 as userID2, ");
        sqlCommand.append("tbl.avatarPath2 as avatarPath2, ");
        sqlCommand.append("tbl.fullName2 as fullName2 ");

        sqlCommand.append(" FROM ChatBox tbl ");
        sqlCommand.append(" WHERE tbl.gid = :gid");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("userID1", LongType.INSTANCE)
                .addScalar("avatarPath1", StringType.INSTANCE)
                .addScalar("fullName1", StringType.INSTANCE)
                .addScalar("userID2", LongType.INSTANCE)
                .addScalar("avatarPath2", StringType.INSTANCE)
                .addScalar("fullName2", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ChatBoxDTO.class));
        query.setParameter("gid", id);
        ChatBoxDTO item = (ChatBoxDTO) query.uniqueResult();
        return item;
    }

    //delete
    @Transactional
    public ServiceResult deleteList(List<Long> listIds) {
        ServiceResult result = new ServiceResult();
        Query q = getSession().createQuery("DELETE FROM ChatBoxBO WHERE gid IN (:listIds)");
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
    public ServiceResult updateObj(ChatBoxDTO dto) {
        ServiceResult result = new ServiceResult();
        ChatBoxBO bo = dto.toModel();
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
    public ChatBoxBO addDTO(ChatBoxDTO dto) {
        ServiceResult result = new ServiceResult();
        Session session1 = getSession();
        ChatBoxBO BO = new ChatBoxBO();
        try {
            BO = (ChatBoxBO) session1.merge(dto.toModel());
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

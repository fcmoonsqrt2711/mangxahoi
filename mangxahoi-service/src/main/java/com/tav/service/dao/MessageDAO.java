package com.tav.service.dao;

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.MessageBO;
import com.tav.service.common.DateUtil;
import com.tav.service.dto.MessageDTO;
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

@Repository("messageDAO")
public class MessageDAO extends BaseFWDAOImpl<MessageBO, Long> {

    public List<MessageDTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.chatID as chatID, ");
        sqlCommand.append("tbl.userID_1 as userID_1, ");
        sqlCommand.append("tbl.userID_2 as userID_2, ");
        sqlCommand.append("tbl.isLike as isLike, ");
        sqlCommand.append("tbl.message as message, ");

        sqlCommand.append("tbl.isSeen as isSeen, ");
        sqlCommand.append("tbl.fullName1 as fullName1, ");
        sqlCommand.append("tbl.fullName2 as fullName2, ");

        sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST ");

        sqlCommand.append(" FROM Message tbl ");

        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
        }
        if (searchDTO.getLong1() != null) {
            sqlCommand.append(" and ( tbl.chatID = :chatID   ");
            sqlCommand.append(" )   ");
        }
        sqlCommand.append(" ORDER BY tbl.gid ");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("chatID", LongType.INSTANCE)
                .addScalar("userID_1", LongType.INSTANCE)
                .addScalar("userID_2", LongType.INSTANCE)
                .addScalar("isLike", LongType.INSTANCE)
                .addScalar("message", StringType.INSTANCE)
                .addScalar("isSeen", LongType.INSTANCE)
                .addScalar("fullName1", StringType.INSTANCE)
                .addScalar("fullName2", StringType.INSTANCE)
                .addScalar("createdTimeST", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(MessageDTO.class))
                .setFirstResult(offset);
        if (limit != null && limit != 0) {
            query.setMaxResults(limit);
        }
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        if (searchDTO.getLong1() != null) {
            query.setParameter("chatID", searchDTO.getLong1());
        }
        return query.list();
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append(" COUNT(1)");
        sqlCommand.append(" FROM  Message tbl ");
        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
        }
        if (searchDTO.getLong1() != null) {
            sqlCommand.append(" and ( tbl.chatID = :chatID   ");
            sqlCommand.append(" )   ");
        }
        Query query = getSession().createSQLQuery(sqlCommand.toString());
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        if (searchDTO.getLong1() != null) {
            query.setParameter("chatID", searchDTO.getLong1());
        }
        return ((BigInteger) query.uniqueResult()).intValue();
    }
    //get one

    public MessageDTO getOneObjById(Long id) {
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.chatID as chatID, ");
        sqlCommand.append("tbl.userID_1 as userID_1, ");
        sqlCommand.append("tbl.userID_2 as userID_2, ");
        sqlCommand.append("tbl.isLike as isLike, ");
        sqlCommand.append("tbl.message as message, ");

        sqlCommand.append("tbl.isSeen as isSeen, ");
        sqlCommand.append("tbl.fullName1 as fullName1, ");
        sqlCommand.append("tbl.fullName2 as fullName2, ");
        sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST ");

        sqlCommand.append(" FROM Message tbl ");
        sqlCommand.append(" WHERE tbl.gid = :gid");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("chatID", LongType.INSTANCE)
                .addScalar("userID_1", LongType.INSTANCE)
                .addScalar("userID_2", LongType.INSTANCE)
                .addScalar("isLike", LongType.INSTANCE)
                .addScalar("message", StringType.INSTANCE)
                .addScalar("isSeen", LongType.INSTANCE)
                .addScalar("fullName1", StringType.INSTANCE)
                .addScalar("fullName2", StringType.INSTANCE)
                .addScalar("createdTimeST", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(MessageDTO.class));
        query.setParameter("gid", id);
        MessageDTO item = (MessageDTO) query.uniqueResult();
        return item;
    }

    //delete
    @Transactional
    public ServiceResult deleteList(List<Long> listIds) {
        ServiceResult result = new ServiceResult();
        Query q = getSession().createQuery("DELETE FROM MessageBO WHERE gid IN (:listIds)");
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
    public ServiceResult updateObj(MessageDTO dto) {
        ServiceResult result = new ServiceResult();
        Long gid = dto.getGid();
        
        
        MessageDTO get_one = getOneObjById(gid);
        get_one.setIsSeen(dto.getIsSeen());
//        MessageBO bo = get_one.toModel();
        try {
            getSession().merge(get_one.toModel());
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
    public MessageBO addDTO(MessageDTO dto) {
        ServiceResult result = new ServiceResult();
        Date now = new Date();
        dto.setCreatedTime(now);

        dto.setCreatedTimeST(DateUtil.getCurrentDateTime());
        dto.setIsSeen(0L);

        Session session1 = getSession();
        MessageBO BO = new MessageBO();
        try {
            BO = (MessageBO) session1.merge(dto.toModel());
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

    public List<MessageDTO> getAll_notified(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.chatID as chatID, ");
        sqlCommand.append("tbl.userID_1 as userID_1, ");
        sqlCommand.append("tbl.userID_2 as userID_2, ");
        sqlCommand.append("tbl.isLike as isLike, ");
        sqlCommand.append("tbl.message as message, ");

        sqlCommand.append("tbl.isSeen as isSeen, ");
        sqlCommand.append("tbl.fullName1 as fullName1, ");
        sqlCommand.append("tbl.fullName2 as fullName2, ");

        sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST ");

        sqlCommand.append(" FROM Message tbl ");

        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
        }
        if (searchDTO.getLong1() != null) {
            sqlCommand.append(" and ( tbl.chatID = :chatID   ");
            sqlCommand.append(" )   ");
        }
        sqlCommand.append(" ORDER BY tbl.gid ");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("chatID", LongType.INSTANCE)
                .addScalar("userID_1", LongType.INSTANCE)
                .addScalar("userID_2", LongType.INSTANCE)
                .addScalar("isLike", LongType.INSTANCE)
                .addScalar("message", StringType.INSTANCE)
                .addScalar("isSeen", LongType.INSTANCE)
                .addScalar("fullName1", StringType.INSTANCE)
                .addScalar("fullName2", StringType.INSTANCE)
                .addScalar("createdTimeST", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(MessageDTO.class))
                .setFirstResult(offset);
        if (limit != null && limit != 0) {
            query.setMaxResults(limit);
        }
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        if (searchDTO.getLong1() != null) {
            query.setParameter("chatID", searchDTO.getLong1());
        }
        return query.list();
    }
}

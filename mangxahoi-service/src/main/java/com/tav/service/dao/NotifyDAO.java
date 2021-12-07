package com.tav.service.dao;
// có userID1(thằng hành động ) + postID
// them truong PostID trong DTO (hung du lieu)

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.NotifyBO;
import com.tav.service.common.DateUtil;
import com.tav.service.dto.NotifyDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import com.tav.service.common.StringUtil;
import com.tav.service.dto.MessageDTO;
import com.tav.service.dto.PostDTO;
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
import org.hibernate.type.DateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("notifyDAO")
public class NotifyDAO extends BaseFWDAOImpl<NotifyBO, Long> {

    @Autowired
    private PostDAO postDAO;

    public List<NotifyDTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.userID1 as userID1, ");
        sqlCommand.append("tbl.userID2 as userID2, ");
        sqlCommand.append("tbl.avatarFriendPath as avatarFriendPath, ");
        sqlCommand.append("tbl.fullNameFriend as fullNameFriend, ");
        sqlCommand.append("tbl.action as action, ");
        sqlCommand.append("tbl.pathDetail as pathDetail, ");
        sqlCommand.append("tbl.isSeen as isSeen, ");
        sqlCommand.append("tbl.createdTime as createdTime, ");
        sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST ");

        sqlCommand.append(" FROM Notify tbl ");

        sqlCommand.append(" WHERE 1=1 ");
        //String
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
        }

        if (searchDTO.getLong2() != null) {
            sqlCommand.append(" and ( tbl.userID2 = :userID2  ");
            sqlCommand.append(" )   ");
        }

        sqlCommand.append(" ORDER BY tbl.gid DESC");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("userID1", LongType.INSTANCE)
                .addScalar("userID2", LongType.INSTANCE)
                .addScalar("avatarFriendPath", StringType.INSTANCE)
                .addScalar("fullNameFriend", StringType.INSTANCE)
                .addScalar("action", LongType.INSTANCE)
                .addScalar("pathDetail", StringType.INSTANCE)
                .addScalar("isSeen", LongType.INSTANCE)
                .addScalar("createdTime", DateType.INSTANCE)
                .addScalar("createdTimeST", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(NotifyDTO.class))
                .setFirstResult(offset);
        if (limit != null && limit != 0) {
            query.setMaxResults(limit);
        }
        if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
        }
        if (searchDTO.getLong2() != null) {
            query.setParameter("userID2", searchDTO.getLong2());
        }
        return query.list();
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append(" COUNT(1)");
        sqlCommand.append(" FROM  Notify tbl ");
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

    public NotifyDTO getOneObjById(Long id) {
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append("tbl.gid as gid, ");
        sqlCommand.append("tbl.userID1 as userID1, ");
        sqlCommand.append("tbl.userID2 as userID2, ");
        sqlCommand.append("tbl.avatarFriendPath as avatarFriendPath, ");
        sqlCommand.append("tbl.fullNameFriend as fullNameFriend, ");
        sqlCommand.append("tbl.action as action, ");
        sqlCommand.append("tbl.pathDetail as pathDetail, ");
        sqlCommand.append("tbl.isSeen as isSeen, ");
        sqlCommand.append("tbl.createdTime as createdTime, ");
        sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST ");

        sqlCommand.append(" FROM Notify tbl ");
        sqlCommand.append(" WHERE tbl.gid = :gid");
        Query query = getSession().createSQLQuery(sqlCommand.toString())
                .addScalar("gid", LongType.INSTANCE)
                .addScalar("userID1", LongType.INSTANCE)
                .addScalar("userID2", LongType.INSTANCE)
                .addScalar("avatarFriendPath", StringType.INSTANCE)
                .addScalar("fullNameFriend", StringType.INSTANCE)
                .addScalar("action", LongType.INSTANCE)
                .addScalar("pathDetail", StringType.INSTANCE)
                .addScalar("isSeen", LongType.INSTANCE)
                .addScalar("createdTime", DateType.INSTANCE)
                .addScalar("createdTimeST", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(NotifyDTO.class));
        query.setParameter("gid", id);
        NotifyDTO item = (NotifyDTO) query.uniqueResult();
        return item;
    }

    //delete
    @Transactional
    public ServiceResult deleteList(List<Long> listIds) {
        ServiceResult result = new ServiceResult();
        Query q = getSession().createQuery("DELETE FROM NotifyBO WHERE gid IN (:listIds)");
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
    public ServiceResult updateObj(NotifyDTO dto) {
        ServiceResult result = new ServiceResult();
        NotifyBO bo = dto.toModel();

        Long gid = dto.getGid();

        NotifyDTO get_one = getOneObjById(gid);
        get_one.setIsSeen(dto.getIsSeen());

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
    public NotifyBO addDTO(NotifyDTO dto) {
        ServiceResult result = new ServiceResult();
        Session session1 = getSession();
        NotifyBO BO = new NotifyBO();
        try {
            dto.setIsSeen(0L);

            Date now = new Date();
            dto.setCreatedTime(now);

            dto.setCreatedTimeST(DateUtil.getCurrentDateTime());
            if (dto.getPostId() != null) {
                PostDTO tmp = postDAO.getOneObjById(dto.getPostId());
                dto.setUserID2(tmp.getUserId());
            }

            BO = (NotifyBO) session1.merge(dto.toModel());
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

package com.tav.service.dao;

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.MessNotifyBO;
import com.tav.service.dto.MessNotifyDTO;
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

@Repository("messNotifyDAO")
public class MessNotifyDAO extends BaseFWDAOImpl<MessNotifyBO, Long>{
    
    public List<MessNotifyDTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
		sqlCommand.append(" SELECT ");
		sqlCommand.append("tbl.gid as gid, ");
		sqlCommand.append("tbl.userID as userID, ");
		sqlCommand.append("tbl.avatarFriendPath as avatarFriendPath, ");
		sqlCommand.append("tbl.fullNameFriend as fullNameFriend, ");
		sqlCommand.append("tbl.action as action, ");
		sqlCommand.append("tbl.chatID as chatID, ");
		sqlCommand.append("tbl.isSeen as isSeen, ");
		sqlCommand.append("to_char(tbl.notifyTime, 'DD/MM/YYYY') as notifyTimeST ");

		sqlCommand.append(" FROM MessNotify tbl ");

		sqlCommand.append(" WHERE 1=1 ");
	//String
 	if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
	    sqlCommand.append(" )   ");
        }

		sqlCommand.append(" ORDER BY tbl.gid ");
		Query query = getSession().createSQLQuery(sqlCommand.toString())
			.addScalar("gid", LongType.INSTANCE)
			.addScalar("userID", LongType.INSTANCE)
			.addScalar("avatarFriendPath", StringType.INSTANCE)
			.addScalar("fullNameFriend", StringType.INSTANCE)
			.addScalar("action", StringType.INSTANCE)
			.addScalar("chatID", LongType.INSTANCE)
			.addScalar("isSeen", LongType.INSTANCE)
			.addScalar("notifyTimeST", StringType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(MessNotifyDTO.class))
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
        sqlCommand.append(" FROM  MessNotify tbl ");
        sqlCommand.append(" WHERE 1=1 ");
	//String
 	if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
            sqlCommand.append(" )   ");
}        Query query = getSession().createSQLQuery(sqlCommand.toString());
	if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
		query.setParameter("stringKeyWord", "%" + searchDTO.getStringKeyWord() + "%");
	}
        return ((BigInteger) query.uniqueResult()).intValue();
}
	//get one
	public MessNotifyDTO getOneObjById(Long id) {
		StringBuilder sqlCommand = new StringBuilder();
		sqlCommand.append(" SELECT ");
		sqlCommand.append("tbl.gid as gid, ");
		sqlCommand.append("tbl.userID as userID, ");
		sqlCommand.append("tbl.avatarFriendPath as avatarFriendPath, ");
		sqlCommand.append("tbl.fullNameFriend as fullNameFriend, ");
		sqlCommand.append("tbl.action as action, ");
		sqlCommand.append("tbl.chatID as chatID, ");
		sqlCommand.append("tbl.isSeen as isSeen, ");
		sqlCommand.append("to_char(tbl.notifyTime, 'DD/MM/YYYY') as notifyTimeST ");

		sqlCommand.append(" FROM MessNotify tbl ");
		sqlCommand.append(" WHERE tbl.gid = :gid");
		Query query = getSession().createSQLQuery(sqlCommand.toString())
			.addScalar("gid", LongType.INSTANCE)
			.addScalar("userID", LongType.INSTANCE)
			.addScalar("avatarFriendPath", StringType.INSTANCE)
			.addScalar("fullNameFriend", StringType.INSTANCE)
			.addScalar("action", StringType.INSTANCE)
			.addScalar("chatID", LongType.INSTANCE)
			.addScalar("isSeen", LongType.INSTANCE)
			.addScalar("notifyTimeST", StringType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(MessNotifyDTO.class));
		query.setParameter("gid", id);
		MessNotifyDTO item = (MessNotifyDTO) query.uniqueResult();
		return item;
	}

	//delete
	@Transactional
	public ServiceResult deleteList(List<Long> listIds) {
		ServiceResult result = new ServiceResult();
		Query q = getSession().createQuery("DELETE FROM MessNotifyBO WHERE gid IN (:listIds)");
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
	public ServiceResult updateObj(MessNotifyDTO dto) {
		ServiceResult result = new ServiceResult();
		MessNotifyBO bo = dto.toModel();
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
	public MessNotifyBO addDTO(MessNotifyDTO dto) {
		ServiceResult result = new ServiceResult();
		Session session1 = getSession();
		MessNotifyBO BO = new MessNotifyBO();
		try {
			BO = (MessNotifyBO) session1.merge(dto.toModel());
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
package com.tav.service.dao;

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.GroupBO;
import com.tav.service.dto.GroupDTO;
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

@Repository("groupDAO")
public class GroupDAO extends BaseFWDAOImpl<GroupBO, Long>{
    
    public List<GroupDTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
		sqlCommand.append(" SELECT ");
		sqlCommand.append("tbl.gid as gid, ");
		sqlCommand.append("tbl.groupName as groupName, ");
		sqlCommand.append("tbl.createdUserId as createdUserId, ");
		sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST ");

		sqlCommand.append(" FROM Group tbl ");

		sqlCommand.append(" WHERE 1=1 ");
	//String
 	if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
	    sqlCommand.append(" )   ");
        }

		sqlCommand.append(" ORDER BY tbl.gid ");
		Query query = getSession().createSQLQuery(sqlCommand.toString())
			.addScalar("gid", LongType.INSTANCE)
			.addScalar("groupName", StringType.INSTANCE)
			.addScalar("createdUserId", LongType.INSTANCE)
			.addScalar("createdTimeST", StringType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(GroupDTO.class))
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
        sqlCommand.append(" FROM  Group tbl ");
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
	public GroupDTO getOneObjById(Long id) {
		StringBuilder sqlCommand = new StringBuilder();
		sqlCommand.append(" SELECT ");
		sqlCommand.append("tbl.gid as gid, ");
		sqlCommand.append("tbl.groupName as groupName, ");
		sqlCommand.append("tbl.createdUserId as createdUserId, ");
		sqlCommand.append("to_char(tbl.createdTime, 'DD/MM/YYYY') as createdTimeST ");

		sqlCommand.append(" FROM Group tbl ");
		sqlCommand.append(" WHERE tbl.gid = :gid");
		Query query = getSession().createSQLQuery(sqlCommand.toString())
			.addScalar("gid", LongType.INSTANCE)
			.addScalar("groupName", StringType.INSTANCE)
			.addScalar("createdUserId", LongType.INSTANCE)
			.addScalar("createdTimeST", StringType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(GroupDTO.class));
		query.setParameter("gid", id);
		GroupDTO item = (GroupDTO) query.uniqueResult();
		return item;
	}

	//delete
	@Transactional
	public ServiceResult deleteList(List<Long> listIds) {
		ServiceResult result = new ServiceResult();
		Query q = getSession().createQuery("DELETE FROM GroupBO WHERE gid IN (:listIds)");
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
	public ServiceResult updateObj(GroupDTO dto) {
		ServiceResult result = new ServiceResult();
		GroupBO bo = dto.toModel();
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
	public GroupBO addDTO(GroupDTO dto) {
		ServiceResult result = new ServiceResult();
		Session session1 = getSession();
		GroupBO BO = new GroupBO();
		try {
			BO = (GroupBO) session1.merge(dto.toModel());
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
package com.tav.service.dao;

import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.UserBO;
import com.tav.service.dto.UserDTO;
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

@Repository("userDAO")
public class UserDAO extends BaseFWDAOImpl<UserBO, Long>{
    
    public List<UserDTO> getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
		sqlCommand.append(" SELECT ");
		sqlCommand.append("tbl.gid as gid, ");
		sqlCommand.append("tbl.userName as userName, ");
		sqlCommand.append("tbl.passWord as passWord, ");
		sqlCommand.append("tbl.fullName as fullName, ");
		sqlCommand.append("tbl.gender as gender, ");
		sqlCommand.append("to_char(tbl.dateOfBirth, 'DD/MM/YYYY') as dateOfBirthST, ");
		sqlCommand.append("tbl.phoneNumber as phoneNumber, ");
		sqlCommand.append("tbl.email as email, ");
		sqlCommand.append("tbl.address as address, ");
		sqlCommand.append("tbl.avatarPath as avatarPath ");

		sqlCommand.append(" FROM User_mxh tbl ");

		sqlCommand.append(" WHERE 1=1 ");
	//String
 	if (!StringUtil.isEmpty(searchDTO.getStringKeyWord())) {
            sqlCommand.append(" and (   ");
	    sqlCommand.append(" )   ");
        }

		sqlCommand.append(" ORDER BY tbl.gid ");
		Query query = getSession().createSQLQuery(sqlCommand.toString())
			.addScalar("gid", LongType.INSTANCE)
			.addScalar("userName", StringType.INSTANCE)
			.addScalar("passWord", StringType.INSTANCE)
			.addScalar("fullName", StringType.INSTANCE)
			.addScalar("gender", LongType.INSTANCE)
			.addScalar("dateOfBirthST", StringType.INSTANCE)
			.addScalar("phoneNumber", StringType.INSTANCE)
			.addScalar("email", StringType.INSTANCE)
			.addScalar("address", StringType.INSTANCE)
			.addScalar("avatarPath", StringType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(UserDTO.class))
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
        sqlCommand.append(" FROM  User_mxh tbl ");
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
	public UserDTO getOneObjById(Long id) {
		StringBuilder sqlCommand = new StringBuilder();
		sqlCommand.append(" SELECT ");
		sqlCommand.append("tbl.gid as gid, ");
		sqlCommand.append("tbl.userName as userName, ");
		sqlCommand.append("tbl.passWord as passWord, ");
		sqlCommand.append("tbl.fullName as fullName, ");
		sqlCommand.append("tbl.gender as gender, ");
		sqlCommand.append("to_char(tbl.dateOfBirth, 'DD/MM/YYYY') as dateOfBirthST, ");
		sqlCommand.append("tbl.phoneNumber as phoneNumber, ");
		sqlCommand.append("tbl.email as email, ");
		sqlCommand.append("tbl.address as address, ");
		sqlCommand.append("tbl.avatarPath as avatarPath ");

		sqlCommand.append(" FROM User_mxh tbl ");
		sqlCommand.append(" WHERE tbl.gid = :gid");
		Query query = getSession().createSQLQuery(sqlCommand.toString())
			.addScalar("gid", LongType.INSTANCE)
			.addScalar("userName", StringType.INSTANCE)
			.addScalar("passWord", StringType.INSTANCE)
			.addScalar("fullName", StringType.INSTANCE)
			.addScalar("gender", LongType.INSTANCE)
			.addScalar("dateOfBirthST", StringType.INSTANCE)
			.addScalar("phoneNumber", StringType.INSTANCE)
			.addScalar("email", StringType.INSTANCE)
			.addScalar("address", StringType.INSTANCE)
			.addScalar("avatarPath", StringType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(UserDTO.class));
		query.setParameter("gid", id);
		UserDTO item = (UserDTO) query.uniqueResult();
		return item;
	}

	//delete
	@Transactional
	public ServiceResult deleteList(List<Long> listIds) {
		ServiceResult result = new ServiceResult();
		Query q = getSession().createQuery("DELETE FROM UserBO WHERE gid IN (:listIds)");
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
	public ServiceResult updateObj(UserDTO dto) {
		ServiceResult result = new ServiceResult();
		UserBO bo = dto.toModel();
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
	public UserBO addDTO(UserDTO dto) {
		ServiceResult result = new ServiceResult();
		Session session1 = getSession();
		UserBO BO = new UserBO();
		try {
			BO = (UserBO) session1.merge(dto.toModel());
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
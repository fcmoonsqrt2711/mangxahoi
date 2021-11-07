package com.tav.service.dao;

import com.sun.mail.util.MailSSLSocketFactory;
import com.tav.service.base.db.dao.BaseFWDAOImpl;
import com.tav.service.bo.UserBO;
import com.tav.service.dto.UserDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import com.tav.service.common.StringUtil;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;
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
public class UserDAO extends BaseFWDAOImpl<UserBO, Long> {
    
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
        
        if (!StringUtil.isEmpty(searchDTO.getString20())) { // username
            sqlCommand.append(" and ( tbl.userName =  :userName  ");
            sqlCommand.append(" )   ");
        }
        
        if (!StringUtil.isEmpty(searchDTO.getString19())) { // pass
            sqlCommand.append(" and (  tbl.passWord =  :passWord ");
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
        
        if (!StringUtil.isEmpty(searchDTO.getString20())) {
            query.setParameter("userName", searchDTO.getString20());
        }
        if (!StringUtil.isEmpty(searchDTO.getString19())) {
            query.setParameter("passWord", searchDTO.getString19());
        }
        return query.list();
    }
    
    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder sqlCommand = new StringBuilder();
        sqlCommand.append(" SELECT ");
        sqlCommand.append(" COUNT(1)");
        sqlCommand.append(" FROM  User tbl ");
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
        if (dto.getUserName() != null) {
            SearchCommonFinalDTO searchDTO = new SearchCommonFinalDTO();
            searchDTO.setString20(dto.getUserName());
            List<UserDTO> lst = getAll(searchDTO, 0, 0);
            
            UserDTO temp = lst.get(lst.size() - 1);
            
            temp.setPassWord(dto.getPassWord());
            
            UserBO bo = temp.toModel();
            result.setId(bo.getGid());
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
        }
        return result;
    }
    
    private static BufferedImage createRGBImage(byte[] bytes, int width, int height) {
        DataBufferByte buffer = new DataBufferByte(bytes, bytes.length);
        ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]{8, 8, 8}, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        return new BufferedImage(cm, Raster.createInterleavedRaster(buffer, width, height, width * 3, 3, new int[]{0, 1, 2}, null), false, null);
    }
    
    @Transactional
    public UserBO addDTO(UserDTO dto) throws FileNotFoundException, IOException {
        
        ServiceResult result = new ServiceResult();
        Session session1 = getSession();
        UserBO BO = new UserBO();
        try {
            BO = (UserBO) session1.merge(dto.toModel());
            if (dto.getAvatarPath() != null) {
                BufferedImage bImage = ImageIO.read(new File(dto.getAvatarPath()));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bImage, "jpg", bos);
                dto.setDataImg(bos.toByteArray());
                
                ByteArrayInputStream bis = new ByteArrayInputStream(dto.getDataImg());
                BufferedImage bImage2 = ImageIO.read(bis);

//            System.out.println("33333333333" + BO.getGid().toString());
                ImageIO.write(bImage2, "jpg", new File("avatar" + BO.getGid().toString() + ".jpg"));
                
                System.out.println("image created");
            }
            
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

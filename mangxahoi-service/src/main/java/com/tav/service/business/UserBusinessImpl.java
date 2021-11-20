package com.tav.service.business;

import com.sun.mail.util.MailSSLSocketFactory;
import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.UserBO;
import com.tav.service.common.Constants;
import com.tav.service.common.DateUtil;
import com.tav.service.dao.UserDAO;
import com.tav.service.dto.UserDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import com.tav.service.dto.UserCommon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;



@Service("userBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserBusinessImpl extends
        BaseFWBusinessImpl<UserDAO, UserDTO, UserBO> implements UserBusiness {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDAO gettDAO() {
        return userDAO;
    }

    public List<UserDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<UserDTO> lstDTO = userDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public List<UserCommon> getAll_BirthDay(SearchCommonFinalDTO searchDTOTmp) {
        List<UserDTO> lstDTO = userDAO.getAll(searchDTOTmp, 0, 0);
        List<UserCommon> res = new ArrayList<>();
        Date d = new Date();
        DateUtil now = new DateUtil();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = now.dateToString(d, formatter);
        System.out.println("todayyyyyyyyyyy " + today);
        if (lstDTO != null && lstDTO.size() > 0) {
            for (UserDTO i : lstDTO) {
                if (i.getDateOfBirthST() != null && i.getDateOfBirthST() != "") {
                    System.out.println("qqqqqqqqqqqq " + i.getDateOfBirthST());
                    if (today.substring(0, 4).equals(i.getDateOfBirthST().substring(0, 4))) {
                        UserCommon temp = new UserCommon();
                        temp.setGid(i.getGid());
                        temp.setFullName(i.getFullName());
                        int age = Integer.parseInt(today.substring(6)) - Integer.parseInt(i.getDateOfBirthST().substring(6));
//                        System.out.println("aaaaaaaaaaaaaaa  " + age);
                        temp.setAge(age);
                        res.add(temp);
                    }
                }
            }
        }

        return res;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return userDAO.getCount(searchDTO);
    }

    //GET ONE
    public UserDTO getOneObjById(Long gid) {
        UserDTO dto = userDAO.getOneObjById(gid);
        return dto;
    }
/////////////////////////sua
    //add

    public ServiceResult checkUserName(UserDTO userDTO) throws IOException, FileNotFoundException, GeneralSecurityException {
        SearchCommonFinalDTO searchDTOTmp = new SearchCommonFinalDTO();
        ServiceResult serviceResult = new ServiceResult();
        List<UserDTO> lstDTO = userDAO.getAll(searchDTOTmp, 0, 0);
        for (UserDTO i : lstDTO) {
            if (i.getUserName().equals(userDTO.getUserName())) {
                serviceResult.setId(-1L); // da ton tai usserName
                return serviceResult;
            }
        }
        serviceResult.setId(1L); // chua ton tai user
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(UserDTO userDTO) throws IOException, FileNotFoundException, GeneralSecurityException {

        ServiceResult result = userDAO.updateObj(userDTO);

        return result;
    }

    public ServiceResult addObj(UserDTO userDTO) throws IOException, FileNotFoundException, GeneralSecurityException {
        ServiceResult result;
        UserBO bo = userDAO.addDTO(userDTO);
        result = new ServiceResult();
        result.setId(bo.getGid());
        return result;
    }

    public ServiceResult addFile(MultipartHttpServletRequest multipartRequest,
            HttpServletRequest request) throws IOException, FileNotFoundException, GeneralSecurityException {
        ServiceResult result;

        System.out.println("test   user_id:  ");
        if (request.getParameter("user_id") != null) {
            System.out.println("user_id:  " + Long.parseLong(request.getParameter("user_id")));
        }
//        UserBO bo = userDAO.addDTO(userDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = userDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    //send email to the user email
    public ServiceResult send_mail(UserDTO userDTO) throws GeneralSecurityException, IOException {

        ServiceResult result = new ServiceResult();
        boolean check = false;
        String code = getRandom();

        int c = Integer.parseInt(code);
        result = checkUserName(userDTO);

        if (result.getId() != null && result.getId() == -1L) {
            return result; // trung user
        } else if (userDTO.getEmail() != null && userDTO.getEmail() != "") {
            String toEmail = userDTO.getEmail();
//        System.out.println("aaaaaaaa" + userDTO.getEmail());

            String fromEmail = "nguyenhonghue18112000@gmail.com";
            String password = "Hue18112000";
            MailSSLSocketFactory sf = new MailSSLSocketFactory();

            sf.setTrustAllHosts(true);
            try {

                // your host email smtp server details
                Properties pr = new Properties();

                pr.put("mail.smtp.ssl.trust", "*");
                pr.put("mail.smtp.ssl.socketFactory", sf);
                pr.put("mail.smtp.host", "smtp.gmail.com");
                pr.put("mail.smtp.socketFactory.port", "587");
                pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                pr.put("mail.smtp.auth", "true");
                pr.put("mail.smtp.port", 587);
                pr.setProperty("mail.smtp.starttls.enable", "true");

                javax.mail.Session session = javax.mail.Session.getInstance(pr, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });

                //set email message details
                Message mess = new MimeMessage(session);

                //set from email address
                mess.setFrom(new InternetAddress(fromEmail));
                //set to email address or destination email address
                mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

                //set email subject
                mess.setSubject("User Email Verification");

                //set message text
                mess.setText("Registered successfully.Please verify your account using this code: " + code);
                //send the message
                Transport.send(mess);
                check = true;

            } catch (Exception e) {
                result.setError(e.getMessage());
            }
            try {
                if (check == true) {
                    if (result.getId() != null && result.getId() > 0L) {
//                        UserBO bo = userDAO.addDTO(userDTO);
                        result.setId(Long.valueOf(c));
                    }
                } else {
                    result.setId(-2L); // loi gui mail
                }
            } catch (Exception e) {
                result.setError(e.getMessage());
            }
        }
        return result;
    }

    public ServiceResult send_mail_change_pw(UserDTO userDTO) throws GeneralSecurityException, IOException {

        ServiceResult result = new ServiceResult();
        boolean check = false;
        String code = getRandom();

        int c = Integer.parseInt(code);
        result = checkUserName(userDTO);

        if (result.getId() != null && result.getId() == 1L) { //==1 chua ton tai user
            result.setId(-3L);// chua ton tai user -> ko doi dk mat khau
            return result; // trung user
        } else if (userDTO.getEmail() != null && userDTO.getEmail() != "") {
            String toEmail = userDTO.getEmail();
//        System.out.println("aaaaaaaa" + userDTO.getEmail());

            String fromEmail = "nguyenhonghue18112000@gmail.com";
            String password = "Hue18112000";
            MailSSLSocketFactory sf = new MailSSLSocketFactory();

            sf.setTrustAllHosts(true);
            try {

                // your host email smtp server details
                Properties pr = new Properties();

                pr.put("mail.smtp.ssl.trust", "*");
                pr.put("mail.smtp.ssl.socketFactory", sf);
                pr.put("mail.smtp.host", "smtp.gmail.com");
                pr.put("mail.smtp.socketFactory.port", "587");
                pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                pr.put("mail.smtp.auth", "true");
                pr.put("mail.smtp.port", 587);
                pr.setProperty("mail.smtp.starttls.enable", "true");

                javax.mail.Session session = javax.mail.Session.getInstance(pr, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });

                //set email message details
                Message mess = new MimeMessage(session);

                //set from email address
                mess.setFrom(new InternetAddress(fromEmail));
                //set to email address or destination email address
                mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

                //set email subject
                mess.setSubject("User Email Verification");

                //set message text
                mess.setText("Registered successfully.Please verify your account using this code: " + code);
                //send the message
                Transport.send(mess);
                check = true;

            } catch (Exception e) {
                result.setError(e.getMessage());
            }
            try {
                if (check == true) {
                    if (result.getId() != null && result.getId() == -1L) { // da co userName trong DB
//                        UserBO bo = userDAO.addDTO(userDTO);
                        result.setId(Long.valueOf(c));
                    }
                } else {
                    result.setId(-2L); // loi gui mail
                }
            } catch (Exception e) {
                result.setError(e.getMessage());
            }
        }
        return result;
    }

}

package com.tav.service.rest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.tav.service.business.UserBusinessImpl;
import com.tav.service.common.CommonFunction;
import com.tav.service.dto.UserDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import com.tav.service.dto.UserCommon;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UserRsServiceImpl implements UserRsService {

    @Autowired
    private UserBusinessImpl userBusinessImpl;

    @Override
    public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        List<UserDTO> lst = userBusinessImpl.getAll(searchDTO, offset, limit);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
    }

    @Override
    public Response getCount(SearchCommonFinalDTO searchDTO) {
        int result = userBusinessImpl.getCount(searchDTO);
        return Response.ok(result).build();
    }

    @Override
    public Response getOneById(Long id) {
        UserDTO result = userBusinessImpl.getOneObjById(id);
        return Response.ok(result).build();
    }

    @Override
    public Response deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = userBusinessImpl.deleteList(searchDTO);
        if ("FAIL".equals(result.getError())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(result).build();
        }
    }

    @Override
    public Response updateObj(UserDTO userDTO) {
        ServiceResult result = null;
        try {
            result = userBusinessImpl.updateObj(userDTO);
        } catch (IOException ex) {
            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(result).build();
    }

    @Override
    public Response addDTO(UserDTO userDTO) {
        ServiceResult result = null;
        try {
            result = userBusinessImpl.addObj(userDTO);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(result).build();
    }

    @Override
    public Response send_mail(UserDTO userDTO) {
        ServiceResult result = null;
        try {
            result = userBusinessImpl.send_mail(userDTO);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(result).build();
    }

    @Override
    public Response send_mail_change_pw(UserDTO userDTO) {
        ServiceResult result = null;
        try {
            result = userBusinessImpl.send_mail_change_pw(userDTO);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(result).build();
    }

    @Override
    public Response getAll_BirthDay(SearchCommonFinalDTO searchDTO) {
        List<UserCommon> lst = userBusinessImpl.getAll_BirthDay(searchDTO);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
    }

    @Override
    public Response uploadFile(@FormDataParam("uploadFile") InputStream fileInputStream) {

        System.out.println("fileeeeeeeeeeeeeeeee   : ");

        ServiceResult result = null;
        OutputStream outStream = null;
        File targetFile = new File("avatar.txt");

//        try {
//            outStream = new FileOutputStream(targetFile);
//            byte[] buffer = new byte[8 * 1024];
//            int bytesRead;
//            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
//                outStream.write(buffer, 0, bytesRead);
//            }
//            IOUtils.closeQuietly(fileInputStream);
//            IOUtils.closeQuietly(outStream);
//            //here you got your file
//            return Response.ok(result).build();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                outStream.close();
//            } catch (IOException ex) {
//                Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
// khong xem dk anh
//        try (OutputStream outputStream = new FileOutputStream(targetFile)) {
//            IOUtils.copy(fileInputStream, outputStream);
//            
//            
//        } catch (FileNotFoundException e) {
//            // handle exception here
//        } catch (IOException e) {
//            // handle exception here
//        }
        try {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(targetFile);
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // handle exception here
        } catch (IOException e) {
            // handle exception here
        }
        return null;
    }

    @RequestMapping(value = {"/upload1"}, method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addOBJ(MultipartHttpServletRequest multipartRequest,
            HttpServletRequest request) throws ParseException {

        String rs = null;
        
        System.out.println("aaaaaaaaaaaaa day r");
        
        return rs;

    }
}

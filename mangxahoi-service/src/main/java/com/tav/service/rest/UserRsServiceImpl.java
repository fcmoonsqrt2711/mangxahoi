package com.tav.service.rest;

import com.google.common.io.CharStreams;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.tav.service.business.UserBusinessImpl;
import com.tav.service.common.CommonFunction;
import com.tav.service.dto.UserDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.PostDTO;
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
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

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
    public Response upload(@Multipart("userId") String userId,
            @Multipart("upfile") Attachment attachment) {

        ServiceResult result = new ServiceResult();

        if (userId != null) {

            Long user_Id = Long.parseLong(userId);

            String filename = attachment.getContentDisposition().getParameter("filename");
            String[] parts = filename.split("\\.");

            UserDTO userDTO = userBusinessImpl.getOneObjById(user_Id);
            System.out.println("user_Id  : " + userDTO.getGid());
            userDTO.setIsAvatar(1L);
            try {
                result = userBusinessImpl.updateObj(userDTO);
            } catch (IOException ex) {
                Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            String file_name_save = "avatar_" + user_Id + "." + parts[parts.length - 1];

            System.out.println("fileeeeeeeeeeeeeeeee   : " + file_name_save);

            java.nio.file.Path path = Paths.get(file_name_save);
            try {
                Files.deleteIfExists(path);
                InputStream in = attachment.getObject(InputStream.class);

                Files.copy(in, path);
            } catch (IOException ex) {
                Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.ok(result).build();
    }

}

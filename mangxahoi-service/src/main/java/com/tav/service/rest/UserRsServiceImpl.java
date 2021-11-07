package com.tav.service.rest;

import com.tav.service.business.UserBusinessImpl;
import com.tav.service.dto.UserDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

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

}

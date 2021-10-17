package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.UserBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.UserDAO;
import com.tav.service.dto.UserDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

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

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return userDAO.getCount(searchDTO);
    }

    //GET ONE
    public UserDTO getOneObjById(Long gid) {
        UserDTO dto = userDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(UserDTO userDTO) {
        UserBO bo = userDAO.addDTO(userDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(UserDTO userDTO) {
        ServiceResult result;
        UserBO bo = userDAO.addDTO(userDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = userDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

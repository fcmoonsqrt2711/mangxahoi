package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.UserLikeCommentBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.UserLikeCommentDAO;
import com.tav.service.dto.UserLikeCommentDTO;
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

@Service("userLikeCommentBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserLikeCommentBusinessImpl extends
        BaseFWBusinessImpl<UserLikeCommentDAO, UserLikeCommentDTO, UserLikeCommentBO> implements UserLikeCommentBusiness {

    @Autowired
    private UserLikeCommentDAO userLikeCommentDAO;

    @Override
    public UserLikeCommentDAO gettDAO() {
        return userLikeCommentDAO;
    }

    public List<UserLikeCommentDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<UserLikeCommentDTO> lstDTO = userLikeCommentDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return userLikeCommentDAO.getCount(searchDTO);
    }

    //GET ONE
    public UserLikeCommentDTO getOneObjById(Long gid) {
        UserLikeCommentDTO dto = userLikeCommentDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(UserLikeCommentDTO userLikeCommentDTO) {
        UserLikeCommentBO bo = userLikeCommentDAO.addDTO(userLikeCommentDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(UserLikeCommentDTO userLikeCommentDTO) {
        ServiceResult result;
        UserLikeCommentBO bo = userLikeCommentDAO.addDTO(userLikeCommentDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = userLikeCommentDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.UserLikePostBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.NotifyDAO;
import com.tav.service.dao.PostDAO;
import com.tav.service.dao.UserLikePostDAO;
import com.tav.service.dto.NotifyDTO;
import com.tav.service.dto.UserLikePostDTO;
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

@Service("userLikePostBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserLikePostBusinessImpl extends
        BaseFWBusinessImpl<UserLikePostDAO, UserLikePostDTO, UserLikePostBO> implements UserLikePostBusiness {
    
    @Autowired
    private UserLikePostDAO userLikePostDAO;
    
    @Autowired
    private NotifyDAO notifyDAO;
    
    @Override
    public UserLikePostDAO gettDAO() {
        return userLikePostDAO;
    }
    
    public List<UserLikePostDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<UserLikePostDTO> lstDTO = userLikePostDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }
    
    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return userLikePostDAO.getCount(searchDTO);
    }

    //GET ONE
    public UserLikePostDTO getOneObjById(Long gid) {
        UserLikePostDTO dto = userLikePostDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(UserLikePostDTO userLikePostDTO) {
        UserLikePostBO bo = userLikePostDAO.addDTO(userLikePostDTO);
        
        
        NotifyDTO notifyDTO = new NotifyDTO();
        notifyDTO.setUserID1(userLikePostDTO.getUserId());
        notifyDTO.setPostId(userLikePostDTO.getPostId());
        notifyDTO.setAction(1L);
        notifyDAO.addDTO(notifyDTO);
        
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(UserLikePostDTO userLikePostDTO) {
        ServiceResult result;
        UserLikePostBO bo = userLikePostDAO.addDTO(userLikePostDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = userLikePostDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }
    
}

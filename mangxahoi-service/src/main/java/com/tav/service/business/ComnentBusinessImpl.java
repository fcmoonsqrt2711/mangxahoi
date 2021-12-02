package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.ComnentBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.ComnentDAO;
import com.tav.service.dao.NotifyDAO;
import com.tav.service.dao.UserDAO;
import com.tav.service.dto.ComnentDTO;
import com.tav.service.dto.NotifyDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import com.tav.service.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("comnentBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ComnentBusinessImpl extends
        BaseFWBusinessImpl<ComnentDAO, ComnentDTO, ComnentBO> implements ComnentBusiness {

    @Autowired
    private ComnentDAO comnentDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private NotifyDAO notifyDAO;

    @Override
    public ComnentDAO gettDAO() {
        return comnentDAO;
    }

    public List<ComnentDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<ComnentDTO> lstDTO = comnentDAO.getAll(searchDTOTmp, offset, limit);

        for (ComnentDTO i : lstDTO) {
            if (i.getUserID() != null) {
                UserDTO userDTO = userDAO.getOneObjById(i.getUserID());
                i.setFullName(userDTO.getFullName());
            }
        }
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return comnentDAO.getCount(searchDTO);
    }

    //GET ONE
    public ComnentDTO getOneObjById(Long gid) {
        ComnentDTO dto = comnentDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(ComnentDTO comnentDTO) {
        ComnentBO bo = comnentDAO.addDTO(comnentDTO);
        
              
        NotifyDTO notifyDTO = new NotifyDTO();
        notifyDTO.setUserID1(comnentDTO.getUserID());
        notifyDTO.setPostId(comnentDTO.getPostID());
        notifyDTO.setAction(2L);
        notifyDAO.addDTO(notifyDTO);
        
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(ComnentDTO comnentDTO) {
        ServiceResult result;
        ComnentBO bo = comnentDAO.addDTO(comnentDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = comnentDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

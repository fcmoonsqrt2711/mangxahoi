package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.NotifyBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.NotifyDAO;
import com.tav.service.dto.NotifyDTO;
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

@Service("notifyBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NotifyBusinessImpl extends
        BaseFWBusinessImpl<NotifyDAO, NotifyDTO, NotifyBO> implements NotifyBusiness {

    @Autowired
    private NotifyDAO notifyDAO;

    @Override
    public NotifyDAO gettDAO() {
        return notifyDAO;
    }

    public List<NotifyDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<NotifyDTO> lstDTO = notifyDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return notifyDAO.getCount(searchDTO);
    }

    //GET ONE
    public NotifyDTO getOneObjById(Long gid) {
        NotifyDTO dto = notifyDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(NotifyDTO notifyDTO) {
        NotifyBO bo = notifyDAO.addDTO(notifyDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(NotifyDTO notifyDTO) {
        ServiceResult result;
        NotifyBO bo = notifyDAO.addDTO(notifyDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = notifyDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

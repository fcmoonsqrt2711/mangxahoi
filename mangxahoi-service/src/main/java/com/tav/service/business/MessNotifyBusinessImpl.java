package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.MessNotifyBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.MessNotifyDAO;
import com.tav.service.dto.MessNotifyDTO;
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

@Service("messNotifyBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessNotifyBusinessImpl extends
        BaseFWBusinessImpl<MessNotifyDAO, MessNotifyDTO, MessNotifyBO> implements MessNotifyBusiness {

    @Autowired
    private MessNotifyDAO messNotifyDAO;

    @Override
    public MessNotifyDAO gettDAO() {
        return messNotifyDAO;
    }

    public List<MessNotifyDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<MessNotifyDTO> lstDTO = messNotifyDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return messNotifyDAO.getCount(searchDTO);
    }

    //GET ONE
    public MessNotifyDTO getOneObjById(Long gid) {
        MessNotifyDTO dto = messNotifyDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(MessNotifyDTO messNotifyDTO) {
        MessNotifyBO bo = messNotifyDAO.addDTO(messNotifyDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(MessNotifyDTO messNotifyDTO) {
        ServiceResult result;
        MessNotifyBO bo = messNotifyDAO.addDTO(messNotifyDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = messNotifyDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

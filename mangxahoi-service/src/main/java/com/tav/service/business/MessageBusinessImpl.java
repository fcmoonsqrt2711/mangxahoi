package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.MessageBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.MessageDAO;
import com.tav.service.dto.MessageDTO;
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

@Service("messageBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageBusinessImpl extends
        BaseFWBusinessImpl<MessageDAO, MessageDTO, MessageBO> implements MessageBusiness {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public MessageDAO gettDAO() {
        return messageDAO;
    }

    public List<MessageDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<MessageDTO> lstDTO = messageDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return messageDAO.getCount(searchDTO);
    }

    //GET ONE
    public MessageDTO getOneObjById(Long gid) {
        MessageDTO dto = messageDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(MessageDTO messageDTO) {
        MessageBO bo = messageDAO.addDTO(messageDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(MessageDTO messageDTO) {
        ServiceResult result;
        MessageBO bo = messageDAO.addDTO(messageDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = messageDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

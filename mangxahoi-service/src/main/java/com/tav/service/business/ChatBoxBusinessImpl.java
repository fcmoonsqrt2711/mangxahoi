package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.ChatBoxBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.ChatBoxDAO;
import com.tav.service.dto.ChatBoxDTO;
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

@Service("chatBoxBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChatBoxBusinessImpl extends
        BaseFWBusinessImpl<ChatBoxDAO, ChatBoxDTO, ChatBoxBO> implements ChatBoxBusiness {

    @Autowired
    private ChatBoxDAO chatBoxDAO;

    @Override
    public ChatBoxDAO gettDAO() {
        return chatBoxDAO;
    }

    public List<ChatBoxDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<ChatBoxDTO> lstDTO = chatBoxDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return chatBoxDAO.getCount(searchDTO);
    }

    //GET ONE
    public ChatBoxDTO getOneObjById(Long gid) {
        ChatBoxDTO dto = chatBoxDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(ChatBoxDTO chatBoxDTO) {
        ChatBoxBO bo = chatBoxDAO.addDTO(chatBoxDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(ChatBoxDTO chatBoxDTO) {
        ServiceResult result;
        ChatBoxBO bo = chatBoxDAO.addDTO(chatBoxDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = chatBoxDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

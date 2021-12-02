package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.MessageBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.ChatBoxDAO;
import com.tav.service.dao.MessageDAO;
import com.tav.service.dto.ChatBoxDTO;
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
    @Autowired
    private ChatBoxDAO chatBoxDAO;

    @Override
    public MessageDAO gettDAO() {
        return messageDAO;
    }

    public List<MessageDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<MessageDTO> lstDTO = messageDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public List<MessageDTO> getAll_notified(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {

        SearchCommonFinalDTO searchDTOTmp_chatBox = new SearchCommonFinalDTO();
        searchDTOTmp_chatBox.setLong3(searchDTOTmp.getLong2());
        List<ChatBoxDTO> lst_chatbox = chatBoxDAO.getAll(searchDTOTmp_chatBox, 0, 0); // tat ca chatID cua USERID2

        List<MessageDTO> res = new ArrayList<>();
        for (ChatBoxDTO i : lst_chatbox) {
            MessageDTO temp = new MessageDTO();
            searchDTOTmp.setLong1(i.getGid());
            List<MessageDTO> lst_tmp = messageDAO.getAll_notified(searchDTOTmp, offset, limit); // 
            temp = lst_tmp.get(lst_tmp.size() - 1); // tin nhan moi nhat
            res.add(temp);
        }

        return res;
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
        result = messageDAO.updateObj(messageDTO);
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = messageDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.MessageBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.ChatBoxDAO;
import com.tav.service.dao.MessageDAO;
import com.tav.service.dao.UserDAO;
import com.tav.service.dto.ChatBoxDTO;
import com.tav.service.dto.MessageDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import com.tav.service.dto.UserDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    @Autowired
    private UserDAO userDAO;

    @Override
    public MessageDAO gettDAO() {
        return messageDAO;
    }

    public List<MessageDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<MessageDTO> lstDTO = messageDAO.getAll(searchDTOTmp, offset, limit);
        for (MessageDTO i : lstDTO) {
            if (i.getUserID_1() != null) {

                UserDTO tmp = userDAO.getOneObjById(i.getUserID_1());
                i.setFullName1(tmp.getFullName());
            }
            if (i.getUserID_2() != null) {
                UserDTO tmp2 = userDAO.getOneObjById(i.getUserID_2());
                i.setFullName2(tmp2.getFullName());
            }
        }

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

            if(lst_tmp.size() > 0){
                temp = lst_tmp.get(lst_tmp.size() - 1); // tin nhan moi nhat
            if (temp.getUserID_1() != null) {
                System.out.println("getUserID_1getUserID_1 : " + temp.getUserID_1());
                UserDTO tmp = userDAO.getOneObjById(temp.getUserID_1());
                temp.setFullName1(tmp.getFullName());
            }
            if (temp.getUserID_2() != null) {
                System.out.println("getUserID_2getUserID_2 : " + temp.getUserID_2());
                UserDTO tmp2 = userDAO.getOneObjById(temp.getUserID_2());
                temp.setFullName2(tmp2.getFullName());
            }

            res.add(temp);
            }
            

            

        }

        Collections.sort(res, new Comparator<MessageDTO>() {
            @Override
            public int compare(MessageDTO o1, MessageDTO o2) {
                return (int) (o2.getGid() - o1.getGid());

            }
        });

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

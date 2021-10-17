package com.tav.service.rest;

import com.tav.service.business.ChatBoxBusinessImpl;
import com.tav.service.dto.ChatBoxDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class ChatBoxRsServiceImpl implements ChatBoxRsService {

    @Autowired
    private ChatBoxBusinessImpl chatBoxBusinessImpl;

    @Override
    public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        List<ChatBoxDTO> lst = chatBoxBusinessImpl.getAll(searchDTO, offset, limit);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
    }

    @Override
    public Response getCount(SearchCommonFinalDTO searchDTO) {
        int result = chatBoxBusinessImpl.getCount(searchDTO);
        return Response.ok(result).build();
    }

    @Override
    public Response getOneById(Long id) {
        ChatBoxDTO result = chatBoxBusinessImpl.getOneObjById(id);
        return Response.ok(result).build();
    }

    @Override
    public Response deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = chatBoxBusinessImpl.deleteList(searchDTO);
        if ("FAIL".equals(result.getError())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(result).build();
        }
    }

    @Override
    public Response updateObj(ChatBoxDTO chatBoxDTO) {
        ServiceResult result = chatBoxBusinessImpl.updateObj(chatBoxDTO);
        return Response.ok(result).build();
    }

    @Override
    public Response addDTO(ChatBoxDTO chatBoxDTO) {
        ServiceResult result = chatBoxBusinessImpl.addDTO(chatBoxDTO);
        return Response.ok(result).build();
    }

}

package com.tav.service.rest;

import com.tav.service.business.MessageBusinessImpl;
import com.tav.service.dto.MessageDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageRsServiceImpl implements MessageRsService {

    @Autowired
    private MessageBusinessImpl messageBusinessImpl;

    @Override
    public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        List<MessageDTO> lst = messageBusinessImpl.getAll(searchDTO, offset, limit);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
    }

    @Override
    public Response getAll_notified(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        List<MessageDTO> lst = messageBusinessImpl.getAll_notified(searchDTO, offset, limit);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
    }

    @Override
    public Response getCount(SearchCommonFinalDTO searchDTO) {
        int result = messageBusinessImpl.getCount(searchDTO);
        return Response.ok(result).build();
    }

    @Override
    public Response getOneById(Long id) {
        MessageDTO result = messageBusinessImpl.getOneObjById(id);
        return Response.ok(result).build();
    }

    @Override
    public Response deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = messageBusinessImpl.deleteList(searchDTO);
        if ("FAIL".equals(result.getError())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(result).build();
        }
    }

    @Override
    public Response updateObj(MessageDTO messageDTO) {
        ServiceResult result = messageBusinessImpl.updateObj(messageDTO);
        return Response.ok(result).build();
    }

    @Override
    public Response addDTO(MessageDTO messageDTO) {
        ServiceResult result = messageBusinessImpl.addDTO(messageDTO);
        return Response.ok(result).build();
    }

}

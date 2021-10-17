package com.tav.service.rest;

import com.tav.service.business.NotifyBusinessImpl;
import com.tav.service.dto.NotifyDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class NotifyRsServiceImpl implements NotifyRsService{

	@Autowired
	private NotifyBusinessImpl notifyBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<NotifyDTO> lst = notifyBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = notifyBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		NotifyDTO result = notifyBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = notifyBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(NotifyDTO notifyDTO) {
		ServiceResult result = notifyBusinessImpl.updateObj(notifyDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(NotifyDTO notifyDTO) {
		ServiceResult result = notifyBusinessImpl.addDTO(notifyDTO);
		return Response.ok(result).build();
	}

}
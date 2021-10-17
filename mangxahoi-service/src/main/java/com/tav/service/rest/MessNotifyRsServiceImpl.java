package com.tav.service.rest;

import com.tav.service.business.MessNotifyBusinessImpl;
import com.tav.service.dto.MessNotifyDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class MessNotifyRsServiceImpl implements MessNotifyRsService{

	@Autowired
	private MessNotifyBusinessImpl messNotifyBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<MessNotifyDTO> lst = messNotifyBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = messNotifyBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		MessNotifyDTO result = messNotifyBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = messNotifyBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(MessNotifyDTO messNotifyDTO) {
		ServiceResult result = messNotifyBusinessImpl.updateObj(messNotifyDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(MessNotifyDTO messNotifyDTO) {
		ServiceResult result = messNotifyBusinessImpl.addDTO(messNotifyDTO);
		return Response.ok(result).build();
	}

}
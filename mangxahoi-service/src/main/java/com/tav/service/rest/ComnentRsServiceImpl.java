package com.tav.service.rest;

import com.tav.service.business.ComnentBusinessImpl;
import com.tav.service.dto.ComnentDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class ComnentRsServiceImpl implements ComnentRsService{

	@Autowired
	private ComnentBusinessImpl comnentBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<ComnentDTO> lst = comnentBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = comnentBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		ComnentDTO result = comnentBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = comnentBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(ComnentDTO comnentDTO) {
		ServiceResult result = comnentBusinessImpl.updateObj(comnentDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(ComnentDTO comnentDTO) {
		ServiceResult result = comnentBusinessImpl.addDTO(comnentDTO);
		return Response.ok(result).build();
	}

}
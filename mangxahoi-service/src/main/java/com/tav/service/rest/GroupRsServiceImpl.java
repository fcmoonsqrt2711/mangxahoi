package com.tav.service.rest;

import com.tav.service.business.GroupBusinessImpl;
import com.tav.service.dto.GroupDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupRsServiceImpl implements GroupRsService{

	@Autowired
	private GroupBusinessImpl groupBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<GroupDTO> lst = groupBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = groupBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		GroupDTO result = groupBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = groupBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(GroupDTO groupDTO) {
		ServiceResult result = groupBusinessImpl.updateObj(groupDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(GroupDTO groupDTO) {
		ServiceResult result = groupBusinessImpl.addDTO(groupDTO);
		return Response.ok(result).build();
	}

}
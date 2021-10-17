package com.tav.service.rest;

import com.tav.service.business.GroupAdminBusinessImpl;
import com.tav.service.dto.GroupAdminDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupAdminRsServiceImpl implements GroupAdminRsService{

	@Autowired
	private GroupAdminBusinessImpl groupAdminBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<GroupAdminDTO> lst = groupAdminBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = groupAdminBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		GroupAdminDTO result = groupAdminBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = groupAdminBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(GroupAdminDTO groupAdminDTO) {
		ServiceResult result = groupAdminBusinessImpl.updateObj(groupAdminDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(GroupAdminDTO groupAdminDTO) {
		ServiceResult result = groupAdminBusinessImpl.addDTO(groupAdminDTO);
		return Response.ok(result).build();
	}

}
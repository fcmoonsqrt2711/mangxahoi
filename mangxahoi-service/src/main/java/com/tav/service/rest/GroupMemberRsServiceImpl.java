package com.tav.service.rest;

import com.tav.service.business.GroupMemberBusinessImpl;
import com.tav.service.dto.GroupMemberDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupMemberRsServiceImpl implements GroupMemberRsService{

	@Autowired
	private GroupMemberBusinessImpl groupMemberBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<GroupMemberDTO> lst = groupMemberBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = groupMemberBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		GroupMemberDTO result = groupMemberBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = groupMemberBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(GroupMemberDTO groupMemberDTO) {
		ServiceResult result = groupMemberBusinessImpl.updateObj(groupMemberDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(GroupMemberDTO groupMemberDTO) {
		ServiceResult result = groupMemberBusinessImpl.addDTO(groupMemberDTO);
		return Response.ok(result).build();
	}

}
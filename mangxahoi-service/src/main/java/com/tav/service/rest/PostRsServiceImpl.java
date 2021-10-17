package com.tav.service.rest;

import com.tav.service.business.PostBusinessImpl;
import com.tav.service.dto.PostDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class PostRsServiceImpl implements PostRsService{

	@Autowired
	private PostBusinessImpl postBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<PostDTO> lst = postBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = postBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		PostDTO result = postBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = postBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(PostDTO postDTO) {
		ServiceResult result = postBusinessImpl.updateObj(postDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(PostDTO postDTO) {
		ServiceResult result = postBusinessImpl.addDTO(postDTO);
		return Response.ok(result).build();
	}

}
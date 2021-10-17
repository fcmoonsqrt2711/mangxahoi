package com.tav.service.rest;

import com.tav.service.business.UserLikePostBusinessImpl;
import com.tav.service.dto.UserLikePostDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLikePostRsServiceImpl implements UserLikePostRsService{

	@Autowired
	private UserLikePostBusinessImpl userLikePostBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<UserLikePostDTO> lst = userLikePostBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = userLikePostBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		UserLikePostDTO result = userLikePostBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = userLikePostBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(UserLikePostDTO userLikePostDTO) {
		ServiceResult result = userLikePostBusinessImpl.updateObj(userLikePostDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(UserLikePostDTO userLikePostDTO) {
		ServiceResult result = userLikePostBusinessImpl.addDTO(userLikePostDTO);
		return Response.ok(result).build();
	}

}
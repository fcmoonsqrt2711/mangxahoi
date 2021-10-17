package com.tav.service.rest;

import com.tav.service.business.UserLikeCommentBusinessImpl;
import com.tav.service.dto.UserLikeCommentDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLikeCommentRsServiceImpl implements UserLikeCommentRsService{

	@Autowired
	private UserLikeCommentBusinessImpl userLikeCommentBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<UserLikeCommentDTO> lst = userLikeCommentBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = userLikeCommentBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		UserLikeCommentDTO result = userLikeCommentBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = userLikeCommentBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(UserLikeCommentDTO userLikeCommentDTO) {
		ServiceResult result = userLikeCommentBusinessImpl.updateObj(userLikeCommentDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(UserLikeCommentDTO userLikeCommentDTO) {
		ServiceResult result = userLikeCommentBusinessImpl.addDTO(userLikeCommentDTO);
		return Response.ok(result).build();
	}

}
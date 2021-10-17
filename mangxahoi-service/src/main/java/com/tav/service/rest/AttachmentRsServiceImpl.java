package com.tav.service.rest;

import com.tav.service.business.AttachmentBusinessImpl;
import com.tav.service.dto.AttachmentDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class AttachmentRsServiceImpl implements AttachmentRsService{

	@Autowired
	private AttachmentBusinessImpl attachmentBusinessImpl;

	@Override
	public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
		List<AttachmentDTO> lst = attachmentBusinessImpl.getAll(searchDTO, offset, limit);
		if (lst == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(lst).build();
		}
	}

	@Override
	public Response getCount(SearchCommonFinalDTO searchDTO) {
		int result = attachmentBusinessImpl.getCount(searchDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response getOneById(Long id) {
		AttachmentDTO result = attachmentBusinessImpl.getOneObjById(id);
		return Response.ok(result).build();
	}

	@Override
	public Response deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = attachmentBusinessImpl.deleteList(searchDTO);
		if ("FAIL".equals(result.getError())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response updateObj(AttachmentDTO attachmentDTO) {
		ServiceResult result = attachmentBusinessImpl.updateObj(attachmentDTO);
		return Response.ok(result).build();
	}

	@Override
	public Response addDTO(AttachmentDTO attachmentDTO) {
		ServiceResult result = attachmentBusinessImpl.addDTO(attachmentDTO);
		return Response.ok(result).build();
	}

}
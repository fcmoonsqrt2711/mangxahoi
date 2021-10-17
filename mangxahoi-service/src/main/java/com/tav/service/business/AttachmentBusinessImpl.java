package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.AttachmentBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.AttachmentDAO;
import com.tav.service.dto.AttachmentDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("attachmentBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AttachmentBusinessImpl extends
		BaseFWBusinessImpl<AttachmentDAO, AttachmentDTO, AttachmentBO> implements AttachmentBusiness {

	@Autowired
	private AttachmentDAO attachmentDAO;

	@Override
	public AttachmentDAO gettDAO() { return attachmentDAO; }

	public List<AttachmentDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
		List<AttachmentDTO> lstDTO = attachmentDAO.getAll(searchDTOTmp, offset, limit);
		return lstDTO;
	}

	public Integer getCount(SearchCommonFinalDTO searchDTO) { return attachmentDAO.getCount(searchDTO); }

	//GET ONE
	public AttachmentDTO getOneObjById(Long gid) {
		AttachmentDTO dto = attachmentDAO.getOneObjById(gid);
		return dto;
	}

	//add
	public ServiceResult addDTO(AttachmentDTO attachmentDTO) {
		AttachmentBO bo = attachmentDAO.addDTO(attachmentDTO);
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setId(bo.getGid());
		return serviceResult;
	}

	//update
	public ServiceResult updateObj(AttachmentDTO attachmentDTO) {
		ServiceResult result;
		AttachmentBO bo = attachmentDAO.addDTO(attachmentDTO);
		result = new ServiceResult();
		return result;
	}

	//delete
	public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
		ServiceResult result = attachmentDAO.deleteList(searchDTO.getLstFirst());
		return result;
	}

}
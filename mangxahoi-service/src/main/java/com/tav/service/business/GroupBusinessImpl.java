package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.GroupBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.GroupDAO;
import com.tav.service.dto.GroupDTO;
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

@Service("groupBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GroupBusinessImpl extends
        BaseFWBusinessImpl<GroupDAO, GroupDTO, GroupBO> implements GroupBusiness {

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public GroupDAO gettDAO() {
        return groupDAO;
    }

    public List<GroupDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<GroupDTO> lstDTO = groupDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return groupDAO.getCount(searchDTO);
    }

    //GET ONE
    public GroupDTO getOneObjById(Long gid) {
        GroupDTO dto = groupDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(GroupDTO groupDTO) {
        GroupBO bo = groupDAO.addDTO(groupDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(GroupDTO groupDTO) {
        ServiceResult result;
        GroupBO bo = groupDAO.addDTO(groupDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = groupDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.GroupAdminBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.GroupAdminDAO;
import com.tav.service.dto.GroupAdminDTO;
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

@Service("groupAdminBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GroupAdminBusinessImpl extends
        BaseFWBusinessImpl<GroupAdminDAO, GroupAdminDTO, GroupAdminBO> implements GroupAdminBusiness {

    @Autowired
    private GroupAdminDAO groupAdminDAO;

    @Override
    public GroupAdminDAO gettDAO() {
        return groupAdminDAO;
    }

    public List<GroupAdminDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<GroupAdminDTO> lstDTO = groupAdminDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return groupAdminDAO.getCount(searchDTO);
    }

    //GET ONE
    public GroupAdminDTO getOneObjById(Long gid) {
        GroupAdminDTO dto = groupAdminDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(GroupAdminDTO groupAdminDTO) {
        GroupAdminBO bo = groupAdminDAO.addDTO(groupAdminDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(GroupAdminDTO groupAdminDTO) {
        ServiceResult result;
        GroupAdminBO bo = groupAdminDAO.addDTO(groupAdminDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = groupAdminDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

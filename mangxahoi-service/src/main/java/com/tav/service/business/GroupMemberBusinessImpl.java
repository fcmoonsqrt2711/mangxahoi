package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.GroupMemberBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.GroupMemberDAO;
import com.tav.service.dto.GroupMemberDTO;
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

@Service("groupMemberBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GroupMemberBusinessImpl extends
        BaseFWBusinessImpl<GroupMemberDAO, GroupMemberDTO, GroupMemberBO> implements GroupMemberBusiness {

    @Autowired
    private GroupMemberDAO groupMemberDAO;

    @Override
    public GroupMemberDAO gettDAO() {
        return groupMemberDAO;
    }

    public List<GroupMemberDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<GroupMemberDTO> lstDTO = groupMemberDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return groupMemberDAO.getCount(searchDTO);
    }

    //GET ONE
    public GroupMemberDTO getOneObjById(Long gid) {
        GroupMemberDTO dto = groupMemberDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(GroupMemberDTO groupMemberDTO) {
        GroupMemberBO bo = groupMemberDAO.addDTO(groupMemberDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(GroupMemberDTO groupMemberDTO) {
        ServiceResult result;
        GroupMemberBO bo = groupMemberDAO.addDTO(groupMemberDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = groupMemberDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

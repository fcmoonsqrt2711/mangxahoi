package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.PostBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.PostDAO;
import com.tav.service.dto.PostDTO;
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

@Service("postBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PostBusinessImpl extends
        BaseFWBusinessImpl<PostDAO, PostDTO, PostBO> implements PostBusiness {

    @Autowired
    private PostDAO postDAO;

    @Override
    public PostDAO gettDAO() {
        return postDAO;
    }

    public List<PostDTO> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<PostDTO> lstDTO = postDAO.getAll(searchDTOTmp, offset, limit);
        return lstDTO;
    }

    public Integer getCount(SearchCommonFinalDTO searchDTO) {
        return postDAO.getCount(searchDTO);
    }

    //GET ONE
    public PostDTO getOneObjById(Long gid) {
        PostDTO dto = postDAO.getOneObjById(gid);
        return dto;
    }

    //add
    public ServiceResult addDTO(PostDTO postDTO) {
        PostBO bo = postDAO.addDTO(postDTO);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setId(bo.getGid());
        return serviceResult;
    }

    //update
    public ServiceResult updateObj(PostDTO postDTO) {
        ServiceResult result;
        PostBO bo = postDAO.addDTO(postDTO);
        result = new ServiceResult();
        return result;
    }

    //delete
    public ServiceResult deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = postDAO.deleteList(searchDTO.getLstFirst());
        return result;
    }

}

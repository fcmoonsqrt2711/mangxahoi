package com.tav.service.business;

import com.tav.service.base.db.business.BaseFWBusinessImpl;
import com.tav.service.bo.PostBO;
import com.tav.service.common.Constants;
import com.tav.service.dao.ComnentDAO;
import com.tav.service.dao.PostDAO;
import com.tav.service.dao.UserLikePostDAO;
import com.tav.service.dto.PostDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.PostCommon;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ServiceResult;
import com.tav.service.dto.UserLikePostDTO;
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
    @Autowired
    private UserLikePostDAO userLikePostDAO;
    @Autowired
    private ComnentDAO comnentDAO;

    @Override
    public PostDAO gettDAO() {
        return postDAO;
    }

    public List<PostCommon> getAll(SearchCommonFinalDTO searchDTOTmp, Integer offset, Integer limit) {
        List<PostDTO> lstDTO = postDAO.getAll(searchDTOTmp, offset, limit);

        List<PostCommon> res = new ArrayList<>();
        for (PostDTO i : lstDTO) {
            PostCommon temp = new PostCommon();
            temp.setGid(i.getGid());
            temp.setUserId(i.getUserId());
            temp.setCreatedTime(i.getCreatedTime());
            temp.setCreatedTimeST(i.getCreatedTimeST());
            temp.setDescription(i.getDescription());
            temp.setIsAvatar(i.getIsAvatar());

            SearchCommonFinalDTO searchDTO = new SearchCommonFinalDTO();
            searchDTO.setLong1(i.getGid());
            Integer count_like = userLikePostDAO.getCount(searchDTO);
            Integer count_Cmt = comnentDAO.getCount(searchDTO);

            temp.setCountCmt(count_Cmt);
            temp.setCountLike(count_like);
            List<UserLikePostDTO> lst_user = userLikePostDAO.getAll(searchDTO, 0, 0);
            temp.setLstUserLike(lst_user);

            res.add(temp);
        }

        return res;
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

package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.NotifyBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NotifyDTO")
public class NotifyDTO extends BaseFWDTOImpl<NotifyBO> {

    private Long gid;		//Khóa tự sinh
    private Long userID1;		//
    private Long userID2;		//

    private String avatarFriendPath;		//
    private String fullNameFriend;		//
    private Long action;		//
    private String pathDetail;		//
    private Long isSeen;		//
    private Date createdTime;		//

    
    private Long postId;		//
    
    private String createdTimeST;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getUserID1() {
        return userID1;
    }

    public void setUserID1(Long userID1) {
        this.userID1 = userID1;
    }

    public Long getUserID2() {
        return userID2;
    }

    public void setUserID2(Long userID2) {
        this.userID2 = userID2;
    }

    public String getAvatarFriendPath() {
        return avatarFriendPath;
    }

    public void setAvatarFriendPath(String avatarFriendPath) {
        this.avatarFriendPath = avatarFriendPath;
    }

    public String getFullNameFriend() {
        return fullNameFriend;
    }

    public void setFullNameFriend(String fullNameFriend) {
        this.fullNameFriend = fullNameFriend;
    }

    public Long getAction() {
        return action;
    }

    public void setAction(Long action) {
        this.action = action;
    }

    public String getPathDetail() {
        return pathDetail;
    }

    public void setPathDetail(String pathDetail) {
        this.pathDetail = pathDetail;
    }

    public Long getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Long isSeen) {
        this.isSeen = isSeen;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTimeST() {
        return createdTimeST;
    }

    public void setCreatedTimeST(String createdTimeST) {
        this.createdTimeST = createdTimeST;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public NotifyBO toModel() {
        NotifyBO notifyBO = new NotifyBO();
        notifyBO.setGid(gid);
        notifyBO.setUserID1(userID1);
        notifyBO.setUserID2(userID2);
        notifyBO.setAvatarFriendPath(avatarFriendPath);
        notifyBO.setFullNameFriend(fullNameFriend);
        notifyBO.setAction(action);
        notifyBO.setPathDetail(pathDetail);
        notifyBO.setIsSeen(isSeen);
        notifyBO.setCreatedTime(createdTime);
        return notifyBO;
    }

    @Override
    public Long getFWModelId() {
        return getGid();
    }

    @Override
    public String catchName() {
        return gid.toString();
    }
}

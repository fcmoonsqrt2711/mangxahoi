package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.ChatBoxBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ChatBoxDTO")
public class ChatBoxDTO extends BaseFWDTOImpl<ChatBoxBO> {

    private Long gid;		//Khóa tự sinh
    private Long userID1;		//
    private String avatarPath1;		//
    private String fullName1;		//
    private Long userID2;		//
    private String avatarPath2;		//
    private String fullName2;		//

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

    public String getAvatarPath1() {
        return avatarPath1;
    }

    public void setAvatarPath1(String avatarPath1) {
        this.avatarPath1 = avatarPath1;
    }

    public String getFullName1() {
        return fullName1;
    }

    public void setFullName1(String fullName1) {
        this.fullName1 = fullName1;
    }

    public Long getUserID2() {
        return userID2;
    }

    public void setUserID2(Long userID2) {
        this.userID2 = userID2;
    }

    public String getAvatarPath2() {
        return avatarPath2;
    }

    public void setAvatarPath2(String avatarPath2) {
        this.avatarPath2 = avatarPath2;
    }

    public String getFullName2() {
        return fullName2;
    }

    public void setFullName2(String fullName2) {
        this.fullName2 = fullName2;
    }

    @Override
    public ChatBoxBO toModel() {
        ChatBoxBO chatBoxBO = new ChatBoxBO();
        chatBoxBO.setGid(gid);
        chatBoxBO.setUserID1(userID1);
        chatBoxBO.setAvatarPath1(avatarPath1);
        chatBoxBO.setFullName1(fullName1);
        chatBoxBO.setUserID2(userID2);
        chatBoxBO.setAvatarPath2(avatarPath2);
        chatBoxBO.setFullName2(fullName2);
        return chatBoxBO;
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

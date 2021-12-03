package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.MessageBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MessageDTO")
public class MessageDTO extends BaseFWDTOImpl<MessageBO> {

    private Long gid;		//Khóa tự sinh
    private Long chatID;		//
    private Long userID_1;		//
    private Long userID_2;		//
    private Long isLike;		//
    private String message;		//
    private Date createdTime;		//
    private String createdTimeST;

    private Long isSeen;		//
    private String fullName1;		//
    private String fullName2;		//



    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getChatID() {
        return chatID;
    }

    public void setChatID(Long chatID) {
        this.chatID = chatID;
    }

    public Long getUserID_1() {
        return userID_1;
    }

    public void setUserID_1(Long userID_1) {
        this.userID_1 = userID_1;
    }

    public Long getUserID_2() {
        return userID_2;
    }

    public void setUserID_2(Long userID_2) {
        this.userID_2 = userID_2;
    }

    public Long getIsLike() {
        return isLike;
    }

    public void setIsLike(Long isLike) {
        this.isLike = isLike;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Long getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Long isSeen) {
        this.isSeen = isSeen;
    }

    public String getFullName1() {
        return fullName1;
    }

    public void setFullName1(String fullName1) {
        this.fullName1 = fullName1;
    }

    public String getFullName2() {
        return fullName2;
    }

    public void setFullName2(String fullName2) {
        this.fullName2 = fullName2;
    }

    @Override
    public MessageBO toModel() {
        MessageBO messageBO = new MessageBO();
        messageBO.setGid(gid);
        messageBO.setChatID(chatID);
        messageBO.setUserID_1(userID_1);
        messageBO.setUserID_2(userID_2);
        messageBO.setIsLike(isLike);
        messageBO.setMessage(message);
        messageBO.setCreatedTime(createdTime);
        messageBO.setIsSeen(isSeen);
        messageBO.setFullName1(fullName1);
        messageBO.setFullName2(fullName2);
        return messageBO;
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

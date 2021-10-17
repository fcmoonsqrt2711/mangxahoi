package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.MessNotifyBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MessNotifyDTO")
public class MessNotifyDTO extends BaseFWDTOImpl<MessNotifyBO> {
	private Long gid;		//Khóa tự sinh
	private Long userID;		//
	private String avatarFriendPath;		//
	private String fullNameFriend;		//
	private String action;		//
	private Long chatID;		//
	private Long isSeen;		//
	private Date notifyTime;		//
	private String notifyTimeST;

	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	public Long getUserID(){
		return userID;
	}

	public void setUserID(Long userID){
		this.userID = userID;
	}

	public String getAvatarFriendPath(){
		return avatarFriendPath;
	}

	public void setAvatarFriendPath(String avatarFriendPath){
		this.avatarFriendPath = avatarFriendPath;
	}

	public String getFullNameFriend(){
		return fullNameFriend;
	}

	public void setFullNameFriend(String fullNameFriend){
		this.fullNameFriend = fullNameFriend;
	}

	public String getAction(){
		return action;
	}

	public void setAction(String action){
		this.action = action;
	}

	public Long getChatID(){
		return chatID;
	}

	public void setChatID(Long chatID){
		this.chatID = chatID;
	}

	public Long getIsSeen(){
		return isSeen;
	}

	public void setIsSeen(Long isSeen){
		this.isSeen = isSeen;
	}

	public Date getNotifyTime(){
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime){
		this.notifyTime = notifyTime;
	}

	public String getNotifyTimeST(){
		return notifyTimeST;
	}

	public void setNotifyTimeST(String notifyTimeST){
		this.notifyTimeST = notifyTimeST;
	}


	@Override
	public MessNotifyBO toModel() {
		MessNotifyBO messNotifyBO = new MessNotifyBO();
		messNotifyBO.setGid(gid);
		messNotifyBO.setUserID(userID);
		messNotifyBO.setAvatarFriendPath(avatarFriendPath);
		messNotifyBO.setFullNameFriend(fullNameFriend);
		messNotifyBO.setAction(action);
		messNotifyBO.setChatID(chatID);
		messNotifyBO.setIsSeen(isSeen);
		messNotifyBO.setNotifyTime(notifyTime);
		return messNotifyBO;
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

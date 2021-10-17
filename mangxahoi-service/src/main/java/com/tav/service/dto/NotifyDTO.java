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
	private Long userID;		//
	private String avatarFriendPath;		//
	private String fullNameFriend;		//
	private String action;		//
	private String pathDetail;		//
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

	public String getPathDetail(){
		return pathDetail;
	}

	public void setPathDetail(String pathDetail){
		this.pathDetail = pathDetail;
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
	public NotifyBO toModel() {
		NotifyBO notifyBO = new NotifyBO();
		notifyBO.setGid(gid);
		notifyBO.setUserID(userID);
		notifyBO.setAvatarFriendPath(avatarFriendPath);
		notifyBO.setFullNameFriend(fullNameFriend);
		notifyBO.setAction(action);
		notifyBO.setPathDetail(pathDetail);
		notifyBO.setIsSeen(isSeen);
		notifyBO.setNotifyTime(notifyTime);
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

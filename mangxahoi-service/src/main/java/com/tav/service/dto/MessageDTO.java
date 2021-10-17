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
	private Long userID;		//
	private Long isLike;		//
	private String message;		//
	private Date createdTime;		//
	private String createdTimeST;

	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	public Long getChatID(){
		return chatID;
	}

	public void setChatID(Long chatID){
		this.chatID = chatID;
	}

	public Long getUserID(){
		return userID;
	}

	public void setUserID(Long userID){
		this.userID = userID;
	}

	public Long getIsLike(){
		return isLike;
	}

	public void setIsLike(Long isLike){
		this.isLike = isLike;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public Date getCreatedTime(){
		return createdTime;
	}

	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}

	public String getCreatedTimeST(){
		return createdTimeST;
	}

	public void setCreatedTimeST(String createdTimeST){
		this.createdTimeST = createdTimeST;
	}


	@Override
	public MessageBO toModel() {
		MessageBO messageBO = new MessageBO();
		messageBO.setGid(gid);
		messageBO.setChatID(chatID);
		messageBO.setUserID(userID);
		messageBO.setIsLike(isLike);
		messageBO.setMessage(message);
		messageBO.setCreatedTime(createdTime);
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

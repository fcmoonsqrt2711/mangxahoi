package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.ComnentBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ComnentDTO")
public class ComnentDTO extends BaseFWDTOImpl<ComnentBO> {
	private Long gid;		//Khóa tự sinh
	private Long postID;		//
	private Long userID;		//
	private String fullName;		//
	private String avatarPath;		//
	private String content;		//
	private Date createdTime;		//
	private String createdTimeST;

	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	public Long getPostID(){
		return postID;
	}

	public void setPostID(Long postID){
		this.postID = postID;
	}

	public Long getUserID(){
		return userID;
	}

	public void setUserID(Long userID){
		this.userID = userID;
	}

	public String getFullName(){
		return fullName;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getAvatarPath(){
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath){
		this.avatarPath = avatarPath;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
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
	public ComnentBO toModel() {
		ComnentBO comnentBO = new ComnentBO();
		comnentBO.setGid(gid);
		comnentBO.setPostID(postID);
		comnentBO.setUserID(userID);
		comnentBO.setFullName(fullName);
		comnentBO.setAvatarPath(avatarPath);
		comnentBO.setContent(content);
		comnentBO.setCreatedTime(createdTime);
		return comnentBO;
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

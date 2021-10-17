package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.UserLikeCommentBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserLikeCommentDTO")
public class UserLikeCommentDTO extends BaseFWDTOImpl<UserLikeCommentBO> {
	private Long gid;		//Khóa tự sinh
	private Long userId;		//
	private String fullName;		//
	private Long commentId;		//
	private Date likeTime;		//
	private String likeTimeST;

	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	public Long getUserId(){
		return userId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public String getFullName(){
		return fullName;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public Long getCommentId(){
		return commentId;
	}

	public void setCommentId(Long commentId){
		this.commentId = commentId;
	}

	public Date getLikeTime(){
		return likeTime;
	}

	public void setLikeTime(Date likeTime){
		this.likeTime = likeTime;
	}

	public String getLikeTimeST(){
		return likeTimeST;
	}

	public void setLikeTimeST(String likeTimeST){
		this.likeTimeST = likeTimeST;
	}


	@Override
	public UserLikeCommentBO toModel() {
		UserLikeCommentBO userLikeCommentBO = new UserLikeCommentBO();
		userLikeCommentBO.setGid(gid);
		userLikeCommentBO.setUserId(userId);
		userLikeCommentBO.setFullName(fullName);
		userLikeCommentBO.setCommentId(commentId);
		userLikeCommentBO.setLikeTime(likeTime);
		return userLikeCommentBO;
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

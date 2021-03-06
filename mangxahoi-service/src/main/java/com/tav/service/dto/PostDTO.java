package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.PostBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PostDTO")
public class PostDTO extends BaseFWDTOImpl<PostBO> {
	private Long gid;		//Khóa tự sinh
	private Long userId;		//
	private Long groupId;		//
	private Date createdTime;		//
	private String createdTimeST;
	private String description;		//

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

	public Long getGroupId(){
		return groupId;
	}

	public void setGroupId(Long groupId){
		this.groupId = groupId;
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

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}


	@Override
	public PostBO toModel() {
		PostBO postBO = new PostBO();
		postBO.setGid(gid);
		postBO.setUserId(userId);
		postBO.setGroupId(groupId);
		postBO.setCreatedTime(createdTime);
		postBO.setDescription(description);
		return postBO;
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

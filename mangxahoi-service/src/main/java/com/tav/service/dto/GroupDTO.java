package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.GroupBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GroupDTO")
public class GroupDTO extends BaseFWDTOImpl<GroupBO> {
	private Long gid;		//Khóa tự sinh
	private String groupName;		//
	private Long createdUserId;		//
	private Date createdTime;		//
	private String createdTimeST;

	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	public String getGroupName(){
		return groupName;
	}

	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	public Long getCreatedUserId(){
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId){
		this.createdUserId = createdUserId;
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
	public GroupBO toModel() {
		GroupBO groupBO = new GroupBO();
		groupBO.setGid(gid);
		groupBO.setGroupName(groupName);
		groupBO.setCreatedUserId(createdUserId);
		groupBO.setCreatedTime(createdTime);
		return groupBO;
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

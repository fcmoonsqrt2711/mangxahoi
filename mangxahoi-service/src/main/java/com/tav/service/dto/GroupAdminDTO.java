package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.GroupAdminBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GroupAdminDTO")
public class GroupAdminDTO extends BaseFWDTOImpl<GroupAdminBO> {
	private Long gid;		//Khóa tự sinh
	private Long userId;		//
	private Long groupID;		//

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

	public Long getGroupID(){
		return groupID;
	}

	public void setGroupID(Long groupID){
		this.groupID = groupID;
	}


	@Override
	public GroupAdminBO toModel() {
		GroupAdminBO groupAdminBO = new GroupAdminBO();
		groupAdminBO.setGid(gid);
		groupAdminBO.setUserId(userId);
		groupAdminBO.setGroupID(groupID);
		return groupAdminBO;
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

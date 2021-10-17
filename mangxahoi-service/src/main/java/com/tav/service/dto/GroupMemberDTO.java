package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.GroupMemberBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GroupMemberDTO")
public class GroupMemberDTO extends BaseFWDTOImpl<GroupMemberBO> {
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
	public GroupMemberBO toModel() {
		GroupMemberBO groupMemberBO = new GroupMemberBO();
		groupMemberBO.setGid(gid);
		groupMemberBO.setUserId(userId);
		groupMemberBO.setGroupID(groupID);
		return groupMemberBO;
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

package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.GroupMemberDTO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "GroupMember")
public class GroupMemberBO extends BaseFWModelImpl {
	private Long gid;		//Khóa tự sinh
	private Long userId;		//
	private Long groupID;		//

	public GroupMemberBO(){
		setColId("gid");
		setColName("gid");
		setUniqueColumn(new String[]{"gid"});
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence",
		parameters = {
			@Parameter(name = "sequence", value = "GroupMember_seq")
		}
	)

	@Column(name = "gid", length = 200)
	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	@Column(name = "userId", length = 200)
	public Long getUserId(){
		return userId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	@Column(name = "groupID", length = 20)
	public Long getGroupID(){
		return groupID;
	}

	public void setGroupID(Long groupID){
		this.groupID = groupID;
	}

	@Override
	public BaseFWDTOImpl toDTO() {
		GroupMemberDTO groupMemberDTO = new GroupMemberDTO();
		groupMemberDTO.setGid(gid);
		groupMemberDTO.setUserId(userId);
		groupMemberDTO.setGroupID(groupID);
		return groupMemberDTO;
	}
}

package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.ComnentDTO;
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
@Table(name = "Comnent")
public class ComnentBO extends BaseFWModelImpl {
	private Long gid;		//Khóa tự sinh
	private Long postID;		//
	private Long userID;		//
	private String fullName;		//
	private String avatarPath;		//
	private String content;		//
	private Date createdTime;		//

	public ComnentBO(){
		setColId("gid");
		setColName("gid");
		setUniqueColumn(new String[]{"gid"});
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence",
		parameters = {
			@Parameter(name = "sequence", value = "Comnent_seq")
		}
	)

	@Column(name = "gid", length = 200)
	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	@Column(name = "postID", length = 200)
	public Long getPostID(){
		return postID;
	}

	public void setPostID(Long postID){
		this.postID = postID;
	}

	@Column(name = "userID", length = 20)
	public Long getUserID(){
		return userID;
	}

	public void setUserID(Long userID){
		this.userID = userID;
	}

	@Column(name = "fullName", length = 200)
	public String getFullName(){
		return fullName;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	@Column(name = "avatarPath", length = 500000)
	public String getAvatarPath(){
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath){
		this.avatarPath = avatarPath;
	}

	@Column(name = "content", length = 200)
	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	@Column(name = "createdTime", length = 500000)
	public Date getCreatedTime(){
		return createdTime;
	}

	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}

	@Override
	public BaseFWDTOImpl toDTO() {
		ComnentDTO comnentDTO = new ComnentDTO();
		comnentDTO.setGid(gid);
		comnentDTO.setPostID(postID);
		comnentDTO.setUserID(userID);
		comnentDTO.setFullName(fullName);
		comnentDTO.setAvatarPath(avatarPath);
		comnentDTO.setContent(content);
		comnentDTO.setCreatedTime(createdTime);
		return comnentDTO;
	}
}

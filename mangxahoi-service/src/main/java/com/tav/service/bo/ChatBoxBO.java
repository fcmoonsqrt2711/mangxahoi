package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.ChatBoxDTO;
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
@Table(name = "ChatBox")
public class ChatBoxBO extends BaseFWModelImpl {
	private Long gid;		//Khóa tự sinh
	private Long userID1;		//
	private String avatarPath1;		//
	private String fullName1;		//
	private Long userID2;		//
	private String avatarPath2;		//
	private String fullName2;		//

	public ChatBoxBO(){
		setColId("gid");
		setColName("gid");
		setUniqueColumn(new String[]{"gid"});
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence",
		parameters = {
			@Parameter(name = "sequence", value = "ChatBox_seq")
		}
	)

	@Column(name = "gid", length = 200)
	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	@Column(name = "userID1", length = 200)
	public Long getUserID1(){
		return userID1;
	}

	public void setUserID1(Long userID1){
		this.userID1 = userID1;
	}

	@Column(name = "avatarPath1", length = 20)
	public String getAvatarPath1(){
		return avatarPath1;
	}

	public void setAvatarPath1(String avatarPath1){
		this.avatarPath1 = avatarPath1;
	}

	@Column(name = "fullName1", length = 200)
	public String getFullName1(){
		return fullName1;
	}

	public void setFullName1(String fullName1){
		this.fullName1 = fullName1;
	}

	@Column(name = "userID2", length = 500000)
	public Long getUserID2(){
		return userID2;
	}

	public void setUserID2(Long userID2){
		this.userID2 = userID2;
	}

	@Column(name = "avatarPath2", length = 500000)
	public String getAvatarPath2(){
		return avatarPath2;
	}

	public void setAvatarPath2(String avatarPath2){
		this.avatarPath2 = avatarPath2;
	}

	@Column(name = "fullName2", length = 500000)
	public String getFullName2(){
		return fullName2;
	}

	public void setFullName2(String fullName2){
		this.fullName2 = fullName2;
	}

	@Override
	public BaseFWDTOImpl toDTO() {
		ChatBoxDTO chatBoxDTO = new ChatBoxDTO();
		chatBoxDTO.setGid(gid);
		chatBoxDTO.setUserID1(userID1);
		chatBoxDTO.setAvatarPath1(avatarPath1);
		chatBoxDTO.setFullName1(fullName1);
		chatBoxDTO.setUserID2(userID2);
		chatBoxDTO.setAvatarPath2(avatarPath2);
		chatBoxDTO.setFullName2(fullName2);
		return chatBoxDTO;
	}
}

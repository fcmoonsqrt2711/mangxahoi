package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.NotifyDTO;
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
@Table(name = "Notify")
public class NotifyBO extends BaseFWModelImpl {
	private Long gid;		//Khóa tự sinh
	private Long userID;		//
	private String avatarFriendPath;		//
	private String fullNameFriend;		//
	private String action;		//
	private String pathDetail;		//
	private Long isSeen;		//
	private Date notifyTime;		//

	public NotifyBO(){
		setColId("gid");
		setColName("gid");
		setUniqueColumn(new String[]{"gid"});
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence",
		parameters = {
			@Parameter(name = "sequence", value = "Notify_seq")
		}
	)

	@Column(name = "gid", length = 200)
	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	@Column(name = "userID", length = 200)
	public Long getUserID(){
		return userID;
	}

	public void setUserID(Long userID){
		this.userID = userID;
	}

	@Column(name = "avatarFriendPath", length = 20)
	public String getAvatarFriendPath(){
		return avatarFriendPath;
	}

	public void setAvatarFriendPath(String avatarFriendPath){
		this.avatarFriendPath = avatarFriendPath;
	}

	@Column(name = "fullNameFriend", length = 200)
	public String getFullNameFriend(){
		return fullNameFriend;
	}

	public void setFullNameFriend(String fullNameFriend){
		this.fullNameFriend = fullNameFriend;
	}

	@Column(name = "action", length = 500000)
	public String getAction(){
		return action;
	}

	public void setAction(String action){
		this.action = action;
	}

	@Column(name = "pathDetail", length = 200)
	public String getPathDetail(){
		return pathDetail;
	}

	public void setPathDetail(String pathDetail){
		this.pathDetail = pathDetail;
	}

	@Column(name = "isSeen", length = 500000)
	public Long getIsSeen(){
		return isSeen;
	}

	public void setIsSeen(Long isSeen){
		this.isSeen = isSeen;
	}

	@Column(name = "notifyTime", length = 500000)
	public Date getNotifyTime(){
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime){
		this.notifyTime = notifyTime;
	}

	@Override
	public BaseFWDTOImpl toDTO() {
		NotifyDTO notifyDTO = new NotifyDTO();
		notifyDTO.setGid(gid);
		notifyDTO.setUserID(userID);
		notifyDTO.setAvatarFriendPath(avatarFriendPath);
		notifyDTO.setFullNameFriend(fullNameFriend);
		notifyDTO.setAction(action);
		notifyDTO.setPathDetail(pathDetail);
		notifyDTO.setIsSeen(isSeen);
		notifyDTO.setNotifyTime(notifyTime);
		return notifyDTO;
	}
}

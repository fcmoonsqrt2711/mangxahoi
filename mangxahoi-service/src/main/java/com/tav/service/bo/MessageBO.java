package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.MessageDTO;
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
@Table(name = "Message")
public class MessageBO extends BaseFWModelImpl {
	private Long gid;		//Khóa tự sinh
	private Long chatID;		//
	private Long userID;		//
	private Long isLike;		//
	private String message;		//
	private Date createdTime;		//

	public MessageBO(){
		setColId("gid");
		setColName("gid");
		setUniqueColumn(new String[]{"gid"});
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence",
		parameters = {
			@Parameter(name = "sequence", value = "Message_seq")
		}
	)

	@Column(name = "gid", length = 200)
	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	@Column(name = "chatID", length = 200)
	public Long getChatID(){
		return chatID;
	}

	public void setChatID(Long chatID){
		this.chatID = chatID;
	}

	@Column(name = "userID", length = 20)
	public Long getUserID(){
		return userID;
	}

	public void setUserID(Long userID){
		this.userID = userID;
	}

	@Column(name = "isLike", length = 200)
	public Long getIsLike(){
		return isLike;
	}

	public void setIsLike(Long isLike){
		this.isLike = isLike;
	}

	@Column(name = "message", length = 500000)
	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
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
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setGid(gid);
		messageDTO.setChatID(chatID);
		messageDTO.setUserID(userID);
		messageDTO.setIsLike(isLike);
		messageDTO.setMessage(message);
		messageDTO.setCreatedTime(createdTime);
		return messageDTO;
	}
}

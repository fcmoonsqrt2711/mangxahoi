package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.UserLikeCommentDTO;
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
@Table(name = "UserLikeComment")
public class UserLikeCommentBO extends BaseFWModelImpl {
	private Long gid;		//Khóa tự sinh
	private Long userId;		//
	private String fullName;		//
	private Long commentId;		//
	private Date likeTime;		//

	public UserLikeCommentBO(){
		setColId("gid");
		setColName("gid");
		setUniqueColumn(new String[]{"gid"});
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence",
		parameters = {
			@Parameter(name = "sequence", value = "UserLikeComment_seq")
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

	@Column(name = "fullName", length = 20)
	public String getFullName(){
		return fullName;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	@Column(name = "commentId", length = 200)
	public Long getCommentId(){
		return commentId;
	}

	public void setCommentId(Long commentId){
		this.commentId = commentId;
	}

	@Column(name = "likeTime", length = 500000)
	public Date getLikeTime(){
		return likeTime;
	}

	public void setLikeTime(Date likeTime){
		this.likeTime = likeTime;
	}

	@Override
	public BaseFWDTOImpl toDTO() {
		UserLikeCommentDTO userLikeCommentDTO = new UserLikeCommentDTO();
		userLikeCommentDTO.setGid(gid);
		userLikeCommentDTO.setUserId(userId);
		userLikeCommentDTO.setFullName(fullName);
		userLikeCommentDTO.setCommentId(commentId);
		userLikeCommentDTO.setLikeTime(likeTime);
		return userLikeCommentDTO;
	}
}

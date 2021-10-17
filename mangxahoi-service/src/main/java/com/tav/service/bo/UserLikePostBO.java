package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.UserLikePostDTO;
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
@Table(name = "UserLikePost")
public class UserLikePostBO extends BaseFWModelImpl {
	private Long gid;		//Khóa tự sinh
	private Long userId;		//
	private String fullName;		//
	private Long postId;		//
	private Date likeTime;		//

	public UserLikePostBO(){
		setColId("gid");
		setColName("gid");
		setUniqueColumn(new String[]{"gid"});
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence",
		parameters = {
			@Parameter(name = "sequence", value = "UserLikePost_seq")
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

	@Column(name = "postId", length = 200)
	public Long getPostId(){
		return postId;
	}

	public void setPostId(Long postId){
		this.postId = postId;
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
		UserLikePostDTO userLikePostDTO = new UserLikePostDTO();
		userLikePostDTO.setGid(gid);
		userLikePostDTO.setUserId(userId);
		userLikePostDTO.setFullName(fullName);
		userLikePostDTO.setPostId(postId);
		userLikePostDTO.setLikeTime(likeTime);
		return userLikePostDTO;
	}
}

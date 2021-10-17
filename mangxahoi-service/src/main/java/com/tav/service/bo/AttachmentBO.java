package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.AttachmentDTO;
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
@Table(name = "Attachment")
public class AttachmentBO extends BaseFWModelImpl {
	private Long gid;		//Khóa tự sinh
	private Long type;		//
	private Long postId;		//
	private String attachmentPath;		//

	public AttachmentBO(){
		setColId("gid");
		setColName("gid");
		setUniqueColumn(new String[]{"gid"});
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence",
		parameters = {
			@Parameter(name = "sequence", value = "Attachment_seq")
		}
	)

	@Column(name = "gid", length = 200)
	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	@Column(name = "type", length = 200)
	public Long getType(){
		return type;
	}

	public void setType(Long type){
		this.type = type;
	}

	@Column(name = "postId", length = 20)
	public Long getPostId(){
		return postId;
	}

	public void setPostId(Long postId){
		this.postId = postId;
	}

	@Column(name = "attachmentPath", length = 200)
	public String getAttachmentPath(){
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath){
		this.attachmentPath = attachmentPath;
	}

	@Override
	public BaseFWDTOImpl toDTO() {
		AttachmentDTO attachmentDTO = new AttachmentDTO();
		attachmentDTO.setGid(gid);
		attachmentDTO.setType(type);
		attachmentDTO.setPostId(postId);
		attachmentDTO.setAttachmentPath(attachmentPath);
		return attachmentDTO;
	}
}

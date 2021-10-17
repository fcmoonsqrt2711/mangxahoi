package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.AttachmentBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AttachmentDTO")
public class AttachmentDTO extends BaseFWDTOImpl<AttachmentBO> {
	private Long gid;		//Khóa tự sinh
	private Long type;		//
	private Long postId;		//
	private String attachmentPath;		//

	public Long getGid(){
		return gid;
	}

	public void setGid(Long gid){
		this.gid = gid;
	}

	public Long getType(){
		return type;
	}

	public void setType(Long type){
		this.type = type;
	}

	public Long getPostId(){
		return postId;
	}

	public void setPostId(Long postId){
		this.postId = postId;
	}

	public String getAttachmentPath(){
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath){
		this.attachmentPath = attachmentPath;
	}


	@Override
	public AttachmentBO toModel() {
		AttachmentBO attachmentBO = new AttachmentBO();
		attachmentBO.setGid(gid);
		attachmentBO.setType(type);
		attachmentBO.setPostId(postId);
		attachmentBO.setAttachmentPath(attachmentPath);
		return attachmentBO;
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

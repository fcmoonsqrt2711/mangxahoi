package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.PostDTO;
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
@Table(name = "Post")
public class PostBO extends BaseFWModelImpl {

    private Long gid;		//Khóa tự sinh
    private Long userId;		//
    private Long groupId;		//
    private Date createdTime;		//
    private String description;		//
    private Long isAvatar;		//

    public PostBO() {
        setColId("gid");
        setColName("gid");
        setUniqueColumn(new String[]{"gid"});
    }

    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "Post_seq")
            }
    )

    @Column(name = "gid", length = 200)
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    @Column(name = "userId", length = 200)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "groupId", length = 20)
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Column(name = "createdTime", length = 200)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "description", length = 500000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "isAvatar", length = 500000)
    public Long getIsAvatar() {
        return isAvatar;
    }

    public void setIsAvatar(Long isAvatar) {
        this.isAvatar = isAvatar;
    }

    @Override
    public BaseFWDTOImpl toDTO() {
        PostDTO postDTO = new PostDTO();
        postDTO.setGid(gid);
        postDTO.setUserId(userId);
        postDTO.setGroupId(groupId);
        postDTO.setCreatedTime(createdTime);
        postDTO.setDescription(description);
        postDTO.setIsAvatar(isAvatar);
        return postDTO;
    }
}

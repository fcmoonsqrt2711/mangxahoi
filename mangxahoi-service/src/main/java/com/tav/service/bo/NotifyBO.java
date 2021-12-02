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
    private Long userID1;		//
    private Long userID2;		//
    
    
    private String avatarFriendPath;		//
    private String fullNameFriend;		//
    private Long action;		//
    private String pathDetail;		//
    private Long isSeen;		//
    private Date createdTime;		//
    
    

    public NotifyBO() {
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
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    @Column(name = "userID1", length = 200)
    public Long getUserID1() {
        return userID1;
    }

    public void setUserID1(Long userID1) {
        this.userID1 = userID1;
    }
    
    @Column(name = "userID2", length = 200)
    public Long getUserID2() {
        return userID2;
    }

    public void setUserID2(Long userID2) {
        this.userID2 = userID2;
    }
    

    @Column(name = "avatarFriendPath", length = 20)
    public String getAvatarFriendPath() {
        return avatarFriendPath;
    }

    public void setAvatarFriendPath(String avatarFriendPath) {
        this.avatarFriendPath = avatarFriendPath;
    }

    @Column(name = "fullNameFriend", length = 200)
    public String getFullNameFriend() {
        return fullNameFriend;
    }

    public void setFullNameFriend(String fullNameFriend) {
        this.fullNameFriend = fullNameFriend;
    }

    @Column(name = "action", length = 500000)
    public Long getAction() {
        return action;
    }

    public void setAction(Long action) {
        this.action = action;
    }

    @Column(name = "pathDetail", length = 200)
    public String getPathDetail() {
        return pathDetail;
    }

    public void setPathDetail(String pathDetail) {
        this.pathDetail = pathDetail;
    }

    @Column(name = "isSeen", length = 500000)
    public Long getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Long isSeen) {
        this.isSeen = isSeen;
    }

    @Column(name = "createdTime", length = 500000)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public BaseFWDTOImpl toDTO() {
        NotifyDTO notifyDTO = new NotifyDTO();
        notifyDTO.setGid(gid);
        notifyDTO.setUserID1(userID1);
        notifyDTO.setUserID2(userID2);
        notifyDTO.setAvatarFriendPath(avatarFriendPath);
        notifyDTO.setFullNameFriend(fullNameFriend);
        notifyDTO.setAction(action);
        notifyDTO.setPathDetail(pathDetail);
        notifyDTO.setIsSeen(isSeen);
        notifyDTO.setCreatedTime(createdTime);
        return notifyDTO;
    }
}

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
    private Long userID_1;		//
    private Long userID_2;		//
    private Long isLike;		//
    private String message;		//
    private Date createdTime;		//

    private Long isSeen;		//
    private String fullName1;		//
    private String fullName2;		//

    public MessageBO() {
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
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    @Column(name = "chatID", length = 200)
    public Long getChatID() {
        return chatID;
    }

    public void setChatID(Long chatID) {
        this.chatID = chatID;
    }

    @Column(name = "userID_1", length = 20)
    public Long getUserID_1() {
        return userID_1;
    }

    public void setUserID_1(Long userID_1) {
        this.userID_1 = userID_1;
    }

    @Column(name = "userID_2", length = 20)
    public Long getUserID_2() {
        return userID_2;
    }

    public void setUserID_2(Long userID_2) {
        this.userID_2 = userID_2;
    }

    @Column(name = "isLike", length = 200)
    public Long getIsLike() {
        return isLike;
    }

    public void setIsLike(Long isLike) {
        this.isLike = isLike;
    }

    @Column(name = "message", length = 500000)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "createdTime", length = 500000)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "isSeen", length = 500000)
    public Long getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Long isSeen) {
        this.isSeen = isSeen;
    }

    @Column(name = "fullName1", length = 500000)
    public String getFullName1() {
        return fullName1;
    }

    public void setFullName1(String fullName1) {
        this.fullName1 = fullName1;
    }
    @Column(name = "fullName2", length = 500000)
    public String getFullName2() {
        return fullName2;
    }

    public void setFullName2(String fullName2) {
        this.fullName2 = fullName2;
    }

    @Override
    public BaseFWDTOImpl toDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setGid(gid);
        messageDTO.setChatID(chatID);
        messageDTO.setUserID_1(userID_1);
        messageDTO.setUserID_2(userID_2);
        messageDTO.setIsLike(isLike);
        messageDTO.setMessage(message);
        messageDTO.setCreatedTime(createdTime);
        messageDTO.setIsSeen(isSeen);
        messageDTO.setFullName1(fullName1);
        messageDTO.setFullName2(fullName2);
        return messageDTO;
    }
}

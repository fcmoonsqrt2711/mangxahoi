package com.tav.service.bo;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.dto.UserDTO;
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
@Table(name = "User_mxh")
public class UserBO extends BaseFWModelImpl {

    private Long gid;		//Khóa tự sinh
    private String userName;		//
    private String passWord;		//
    private String fullName;		//
    private Long gender;		//
    private Date dateOfBirth;		//
    private String phoneNumber;		//
    private String email;		//
    private String address;		//
    private String avatarPath;		//
    
    
    private Long isOnline;		//
    private Long isAvatar;		//
    
    
    private byte[] dataImg;		//

    public UserBO() {
        setColId("gid");
        setColName("gid");
        setUniqueColumn(new String[]{"gid"});
    }

    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "User_seq")
            }
    )

    @Column(name = "gid", length = 200)
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    @Column(name = "userName", length = 200)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "passWord", length = 20)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Column(name = "fullName", length = 200)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "gender", length = 500000)
    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    @Column(name = "dateOfBirth", length = 500000)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "phoneNumber", length = 500000)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "email", length = 500000)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address", length = 500000)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "avatarPath", length = 500000)
    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Column(name = "dataImg", length = 500000)
    public byte[] getDataImg() {
        return dataImg;
    }

    public void setDataImg(byte[] dataImg) {
        this.dataImg = dataImg;
    }

    @Column(name = "isOnline", length = 500000)
    public Long getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Long isOnline) {
        this.isOnline = isOnline;
    }

    @Column(name = "isAvatar", length = 500000)
    public Long getIsAvatar() {
        return isOnline;
    }

    public void setIsAvatar(Long isAvatar) {
        this.isAvatar = isAvatar;
    }
    @Override
    public BaseFWDTOImpl toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setGid(gid);
        userDTO.setUserName(userName);
        userDTO.setPassWord(passWord);
        userDTO.setFullName(fullName);
        userDTO.setGender(gender);
        userDTO.setDateOfBirth(dateOfBirth);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setEmail(email);
        userDTO.setAddress(address);
        userDTO.setAvatarPath(avatarPath);
        userDTO.setDataImg(dataImg);
        userDTO.setIsOnline(isOnline);
        userDTO.setIsAvatar(isAvatar);
        return userDTO;
    }
}

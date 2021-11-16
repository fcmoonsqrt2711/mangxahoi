package com.tav.service.dto;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.bo.UserBO;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.tav.service.common.GeometryUtil;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserDTO")
public class UserDTO extends BaseFWDTOImpl<UserBO> {

    private Long gid;		//Khóa tự sinh
    private String userName;		//
    private String passWord;		//
    private String fullName;		//
    private Long gender;		//
    private Date dateOfBirth;		//
    private String dateOfBirthST;
    private String phoneNumber;		//
    private String email;		//
    private String address;		//
    private String avatarPath;		//

    private byte[] dataImg;		//

    private Long isOnline;		//

    public Long getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Long isOnline) {
        this.isOnline = isOnline;
    }
    
    private Long isAvatar;		//

    public Long getIsAvatar() {
        return isAvatar;
    }

    public void setIsAvatar(Long isAvatar) {
        this.isAvatar = isAvatar;
    }
    
    public byte[] getDataImg() {
        return dataImg;
    }

    public void setDataImg(byte[] dataImg) {
        this.dataImg = dataImg;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirthST() {
        return dateOfBirthST;
    }

    public void setDateOfBirthST(String dateOfBirthST) {
        this.dateOfBirthST = dateOfBirthST;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Override
    public UserBO toModel() {
        UserBO userBO = new UserBO();
        userBO.setGid(gid);
        userBO.setUserName(userName);
        userBO.setPassWord(passWord);
        userBO.setFullName(fullName);
        userBO.setGender(gender);
        userBO.setDateOfBirth(dateOfBirth);
        userBO.setPhoneNumber(phoneNumber);
        userBO.setEmail(email);
        userBO.setAddress(address);
        userBO.setAvatarPath(avatarPath);
        userBO.setIsOnline(isOnline);
        userBO.setIsAvatar(isAvatar);
        return userBO;
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

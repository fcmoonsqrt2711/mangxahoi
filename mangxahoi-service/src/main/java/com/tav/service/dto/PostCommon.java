/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author KhietDT
 */
public class PostCommon {

    private Long gid;		//Khóa tự sinh
    private Long userId;		//
    private Long groupId;		//
    private Date createdTime;		//
    private String createdTimeST;
    private String description;		//
    private Long isAvatar;		//
    
    private Integer countLike;		//
    private Integer countCmt;		//
    private List<UserLikePostDTO> lstUserLike;		//

    private List<ComnentDTO> lstCmt1;		//

    private List<ComnentDTO> lstCmt2;		//

    private String fullName;		//
    
    
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTimeST() {
        return createdTimeST;
    }

    public void setCreatedTimeST(String createdTimeST) {
        this.createdTimeST = createdTimeST;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCountLike() {
        return countLike;
    }

    public void setCountLike(Integer countLike) {
        this.countLike = countLike;
    }

    public Integer getCountCmt() {
        return countCmt;
    }

    public void setCountCmt(Integer countCmt) {
        this.countCmt = countCmt;
    }

    public List<UserLikePostDTO> getLstUserLike() {
        return lstUserLike;
    }

    public void setLstUserLike(List<UserLikePostDTO> lstUserLike) {
        this.lstUserLike = lstUserLike;
    }

    public Long getIsAvatar() {
        return isAvatar;
    }

    public void setIsAvatar(Long isAvatar) {
        this.isAvatar = isAvatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<ComnentDTO> getLstCmt1() {
        return lstCmt1;
    }

    public void setLstCmt1(List<ComnentDTO> lstCmt1) {
        this.lstCmt1 = lstCmt1;
    }

    public List<ComnentDTO> getLstCmt2() {
        return lstCmt2;
    }

    public void setLstCmt2(List<ComnentDTO> lstCmt2) {
        this.lstCmt2 = lstCmt2;
    }

}

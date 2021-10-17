package com.tav.common.util.bean;

import java.util.Date;

/**
 * @author truongtx5
 * 
 */
public interface ICommonEntity {

	public Date getCreatedDate();

	public void setCreatedDate(Date createdDate);

	public Long getCreatedBy();

	public void setCreatedBy(Long createdUser);

	public Date getUpdatedDate();

	public void setUpdatedDate(Date modifiedDate);

	public Long getUpdatedBy();

	public void setUpdatedBy(Long modifiedUser);

	public Integer getDelFlag();

	public void setDelFlag(Integer delFlag);

	public Long getDeletedBy();

	public void setDeletedBy(Long deletedBy);

	public Date getDeletedDate();

	public void setDeletedDate(Date deleletedDate);
}

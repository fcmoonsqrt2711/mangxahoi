/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.base.db.business;

import com.tav.service.base.db.dto.BaseFWDTOImpl;
import com.tav.service.base.db.model.BaseFWModelImpl;
import com.tav.service.base.pojo.ConditionBean;
import com.tav.service.base.pojo.OrderBean;
import java.util.Date;

import java.util.List;

/**
 *
 * @author kdvt_binhnt22@viettel.com.vn
 * @version 1.0
 * @since May 2012
 */
public interface BaseFWBusiness<TDTO extends BaseFWDTOImpl, TModel extends BaseFWModelImpl> {

    List<TDTO> getAll();

    BaseFWDTOImpl getOneById(Long costCenterId);

    Long save(TDTO costCenterBO);

    Long update(TDTO costCenterBO);

    void delete(TDTO costCenterBO);

//    List<TDTO> searchByHql(String hql, List<ConditionBean> conditionBeans);
//
//    List<TDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx);
//
//    Long countByHql(String hql, List<ConditionBean> conditionBeans);
//
//    int executeByHql(String hql, List<ConditionBean> conditionBeans);

    Date getSysDate() throws Exception;

    Long getNextValSequence(String sequense);
}

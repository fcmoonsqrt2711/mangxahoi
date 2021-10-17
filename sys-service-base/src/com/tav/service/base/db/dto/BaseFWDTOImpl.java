/*
 * Copyright (C) 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tav.service.base.db.dto;

import com.tav.service.base.db.model.BaseFWModelImpl;
import java.io.Serializable;

/**
 *
 * @author kdvt_binhnt22@viettel.com.vn
 * @version 1.0
 * @param <TBO>
 * @since May 2012
 */
public abstract class BaseFWDTOImpl<TBO extends BaseFWModelImpl> implements BaseFWDTO<TBO>, Serializable {

    protected String defaultSortField = "name";

    public String getDefaultSortField() {
        return defaultSortField;
    }

    public void setDefaultSortField(String defaultSortField) {
        this.defaultSortField = defaultSortField;
    }

    @Override
    public int compareTo(BaseFWDTO o) {
        return catchName().compareTo(o.catchName());
    }

}

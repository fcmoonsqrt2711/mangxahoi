/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.tav.service.base.db.dto;

import com.tav.service.base.db.model.BaseFWModelImpl;
import java.util.Locale;

/**
*
* @author kdvt_binhnt22@viettel.com.vn
* @version 1.0
* @since since_text
*/
public interface BaseFWDTO<TModel extends BaseFWModelImpl> extends Comparable<BaseFWDTO> {
    TModel toModel();
    Long getFWModelId();
    String catchName();
   
}
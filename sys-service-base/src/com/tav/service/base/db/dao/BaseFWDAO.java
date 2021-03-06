/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.tav.service.base.db.dao;

import java.util.List;

/**
*
* @author kdvt_binhnt22@viettel.com.vn
* @version 1.0
* @since since_text
*/
public interface BaseFWDAO<T extends Object> {
    public T findById(long id);
    public T findByName(String name);
    public List<T> findAll();
    public String delete(long id);
    public Long countAll();
}
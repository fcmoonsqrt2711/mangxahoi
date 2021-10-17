/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.base.ignite.base;

import org.apache.ignite.lang.IgniteRunnable;

/**
 *
 * @author HungLQ9
 */
public class VtIgniteRunnable implements IgniteRunnable {

    private String e;

    public VtIgniteRunnable(String e) {
        this.e = e;
    }

    @Override
    public void run() {
        System.out.println("e:" + e);
    }

}

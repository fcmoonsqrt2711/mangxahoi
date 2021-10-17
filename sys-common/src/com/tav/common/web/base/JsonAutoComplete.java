/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.web.base;

/**
 * Du lieu vi du: value: "1", label: "nguyen van ah", desc: "the official user",
 * icon: "a_32x32.png"
 *
 * @author thuannht
 */
public class JsonAutoComplete {

    private String value;
    private String label;
    private String desc;
    private String icon;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}

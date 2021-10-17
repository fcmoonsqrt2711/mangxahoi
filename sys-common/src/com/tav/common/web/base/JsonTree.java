/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.common.web.base;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuannht
 */
public class JsonTree {

    private String label;
    private boolean expanded;
    private boolean selected;
    private String value;
    private List<JsonTree> items = new ArrayList<>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<JsonTree> getItems() {
        return items;
    }

    public void setItems(List<JsonTree> items) {
        this.items = items;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Ham chuyen doi 1 node thanh mot node lazy de no khong tu tao ra node con
     */
    public static void convert2LazyNode(JsonTree parent) {
        JsonTree child = new JsonTree();
        child.setLabel("Loading...");
        child.setValue(parent.getValue());
        parent.getItems().add(child);
    }
}

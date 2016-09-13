package com.nykj.base.example.entity.web.form;

import java.util.Map;

/**
 * Created by cq on 4/20/16.
 */
public class SelectOneFormat extends AbstractType {

    private String type = "select_one";
    private String name;
    private String title;
    private Boolean required = false;

    // name : title
    private Map<String, String> options;

    public SelectOneFormat(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public SelectOneFormat(String name, String title, Boolean required, Map<String, String> options) {
        this.name = name;
        this.title = title;
        this.required = required;
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}

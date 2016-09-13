package com.nykj.base.example.entity.web.form;

/**
 * Created by cq on 4/20/16.
 */
public class InputTextFormat extends AbstractType {

    private String type = "input_text";
    private String name;
    private String title;
    private Boolean required = false;

    public InputTextFormat(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public InputTextFormat(String name, String title, Boolean required) {
        this.name = name;
        this.title = title;
        this.required = required;
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
}

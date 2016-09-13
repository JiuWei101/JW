package com.nykj.base.example.entity.web.form;

/**
 * Created by cq on 4/20/16.
 */
public class TextareaFormat extends AbstractType {

    private String type = "textarea";
    private String name;
    private String title;
    private Boolean required = false;
    private Integer rows = 3;

    public TextareaFormat(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public TextareaFormat(String name, String title, Boolean required, Integer rows) {
        this.name = name;
        this.title = title;
        this.required = required;
        this.rows = rows;
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

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}

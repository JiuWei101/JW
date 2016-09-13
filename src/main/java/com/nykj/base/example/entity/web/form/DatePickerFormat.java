package com.nykj.base.example.entity.web.form;

/**
 * Created by cq on 4/20/16.
 */
public class DatePickerFormat extends AbstractType {

    private String type = "datepicker";
    private String name;
    private String title;

    public DatePickerFormat(String name, String title) {
        this.name = name;
        this.title = title;
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
}

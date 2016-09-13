package com.nykj.base.example.entity.web.form;

/**
 * Created by cq on 4/20/16.
 */
public class InputHiddenFormat extends AbstractType {

    private String type = "input_hidden";
    private String name;

    public InputHiddenFormat(String name) {
        this.name = name;
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
}

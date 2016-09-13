package com.nykj.base.example.entity.web.datatable;

/**
 * Created by cq on 4/20/16.
 */
public class DataTableFormat {

    private String name;
    private String title;
    private Boolean visible = true;

    public DataTableFormat(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public DataTableFormat(String name, String title, Boolean visible) {
        this.name = name;
        this.title = title;
        this.visible = visible;
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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}

package com.nykj.base.example.entity.web.template;

/**
 * Created by cq on 4/21/16.
 */
public class TemplatePageA {

    private String title = "";
    private String selfUrl = "";
    private String queryUrl = "";
    private String addUrl = "";
    private String editUrl = "";
    private String deleteUrl = "";
    private Object[][] queryForm;
    private Object[] resultTable;
    private Object[][] addForm;
    private Object[][] editForm;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getAddUrl() {
        return addUrl;
    }

    public void setAddUrl(String addUrl) {
        this.addUrl = addUrl;
    }

    public String getEditUrl() {
        return editUrl;
    }

    public void setEditUrl(String editUrl) {
        this.editUrl = editUrl;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public Object[][] getQueryForm() {
        return queryForm;
    }

    public void setQueryForm(Object[][] queryForm) {
        this.queryForm = queryForm;
    }

    public Object[] getResultTable() {
        return resultTable;
    }

    public void setResultTable(Object[] resultTable) {
        this.resultTable = resultTable;
    }

    public Object[][] getAddForm() {
        return addForm;
    }

    public void setAddForm(Object[][] addForm) {
        this.addForm = addForm;
    }

    public Object[][] getEditForm() {
        return editForm;
    }

    public void setEditForm(Object[][] editForm) {
        this.editForm = editForm;
    }
}

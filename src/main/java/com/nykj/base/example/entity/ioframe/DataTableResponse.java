package com.nykj.base.example.entity.ioframe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cq on 4/18/16.
 */
public class DataTableResponse {

    private Long draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<Object> data = new ArrayList<Object>();

    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}

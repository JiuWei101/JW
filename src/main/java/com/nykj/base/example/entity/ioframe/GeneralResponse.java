package com.nykj.base.example.entity.ioframe;


import com.nykj.base.example.namespace.SystemNames;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cq on 4/18/16.
 */
public class GeneralResponse {

    private Long timestamp = System.currentTimeMillis();
    private String version = SystemNames.VERSION_1_0;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<String, Object>();

    public GeneralResponse() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
    }

    public GeneralResponse(ResponseCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

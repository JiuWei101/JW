package com.nykj.base.example.entity.ioframe;

/**
 * Created by cq on 3/30/16.
 */
public enum ResponseCode {

    SUCCESS(0, "success"),

    // 系统级错误
    SERVER_ERROR(1, "server error"),
    SERVER_INTERNAL_ERROR(500, "服务器内部错误"),

    // 数据级错误
    INVALID_USER(400001, "用户身份无法识别"),
    NO_GRANT_FOR_PAGE(400002, "没有权限访问指定页面"),

    // 展示层错误
    NO_PAGE_FOUND(404, "这个页面我们也找不到"),
    NO_EDIT_ID(500001, "需指定待编辑的文章id"),
    NO_DELETE_ID(500002, "需指定待删除的文章id"),
    NO_SHOW_ID(500003, "需指定待查看的文章id"),
    ;

    private Integer code;
    private String message;

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

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.company.project.core.result;

public enum ResResultCode {

    SUCCESS(200,""),
    CUSTOM_ERROR_MESSAGE(500,"请求操作失败!"),
    DEFAULT_ERROR_MESSAGE(500,"系统繁忙，请稍后再试");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

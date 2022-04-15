package com.company.project.core.execption;

public class CommonException extends RuntimeException {

    /**错误码*/
    private String code;

    /**错误信息*/
    private String msg;

    /**错误对象*/
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CommonException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CommonException(String msg, Object data) {
        super(msg);
        this.msg = msg;
        this.data = data;
    }

    public CommonException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public CommonException(String msg, Object data, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
        this.data = data;
    }
}

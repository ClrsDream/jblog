package com.xiaoteng.blog.enums;

public enum ApiErrorEnum {
    ADMINISTRATOR_PASSWORD_ERROR(1001, "密码错误"),
    SYSTEM_ERROR(1002, "系统错误"),
    MEMBER_NOT_FOUND(1003, "用户不存在");

    private int code;
    private String message;

    ApiErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

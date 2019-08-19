package com.xiaoteng.blog.enums;

public enum UserStatusEnum {
    NORMAL(0, "正常"),
    BAN_REPLY(1, "禁止回复"),
    BAN_SUBMIT(2, "禁止发帖"),
    BAN_REPLY_AND_SUBMIT(3, "禁止发帖和回复"),
    BAN_LOGIN(4, "禁止登录");

    private int status;

    private String des;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    UserStatusEnum(Integer statusCode, String description) {
        this.status = statusCode;
        this.des = description;
    }
}

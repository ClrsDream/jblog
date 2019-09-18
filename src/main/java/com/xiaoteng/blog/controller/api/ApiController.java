package com.xiaoteng.blog.controller.api;

import com.xiaoteng.blog.enums.ApiErrorEnum;

public class ApiController {

    public Response successEmptyMes(Object data) {
        return success(data, null);
    }

    public Response success(Object data, String message) {
        return new Response(0, message, data);
    }

    public Response error(ApiErrorEnum errorEnum) {
        return new Response(errorEnum.getCode(), errorEnum.getMessage(), null);
    }

    public static class Paginator {
        private int page;
        private int pageSize;
        private long count;
        private Object data;

        public Paginator(int page, int pageSize, long count, Object data) {
            this.page = page;
            this.pageSize = pageSize;
            this.count = count;
            this.data = data;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public static class Response {
        private int code;
        private String message;
        private Object data;

        public Response(int code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
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

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

}

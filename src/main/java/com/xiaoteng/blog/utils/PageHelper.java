package com.xiaoteng.blog.utils;

public class PageHelper {

    private Long count;

    private int page;

    private int pageSize;

    private int pageCount;

    public PageHelper(Long count, int page, int pageSize) {
        this.count = count;
        this.page = page;
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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

    // 是否有上一页
    public boolean hasPrevPage() {
        return page > 1;
    }

    // 是否有下一页
    public boolean hasNextPage() {
        return page < pageCount();
    }

    public int getPageCount() {
        return pageCount();
    }

    private int pageCount() {
        if (pageCount == 0) {
            pageCount = (int) Math.ceil(count / pageSize);
            pageCount = pageCount == 0 ? 1 : pageCount;
        }
        return pageCount;
    }
}

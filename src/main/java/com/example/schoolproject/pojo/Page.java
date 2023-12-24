package com.example.schoolproject.pojo;

public class Page<T> {
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 当前页码
     */
    private int currentPage;
    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 数据列表
     */
    private T data;

    /**
     * 是否有前一页
     * @return
     */
    public boolean hasPrevious(){
        return currentPage!=1;
    }

    /**
     * 是否有下一页
     * @return
     */
    public boolean hasNext(){
        return currentPage != totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

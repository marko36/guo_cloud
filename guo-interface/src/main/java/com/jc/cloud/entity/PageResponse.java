package com.jc.cloud.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

public class PageResponse<T> implements Serializable {
	public PageResponse(){}
    public PageResponse(IPage<T> page){

        this.current= ((int) (page.getCurrent()));
        this.pages= ((int) (page.getPages()));
        this.records=page.getRecords();
        this.size= ((int) (page.getSize()));
    }
    /**
     * 总页数
     */
    private int pages;
    /**
     * 当前页
     */
    private int current;
    /**
     * 每页大小
     */
    private int size;

    private List<T> records;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
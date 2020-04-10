package com.jc.cloud.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;

/**
 * 高级查询基类提供分页对像
 * @ClassName BaseSearch
 * @Description TODO
 * @Author fangliai
 * @Date 2018/9/1 12:32
 * @Vesion 1
**/
public abstract class BaseSearch<T> implements Serializable  {
    private Integer current = 1;
    private Integer size=30;


    public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}


	public final Page<T> initPage(){
        return new Page<>(this.current==null?1:current,this.size==null?10:size);
    }
    
	public abstract QueryWrapper initWrapper();
    
    
}
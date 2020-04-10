package com.jc.cloud.common.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeNode
 * @Description
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
@Data
public abstract class TreeNode {

    @TableField(exist = false)
    private  List<TreeNode> children=new ArrayList<>();

    public <T extends  TreeNode> void add(T node){
        children.add(node);
    }

    public <T extends  TreeNode> void addAll(List<T> node){
        children.addAll(node);
    }

    @JSONField(serialize = false)
    abstract public Object getTreeId();
    @JSONField(serialize = false)
    abstract public Object getParentId();


}

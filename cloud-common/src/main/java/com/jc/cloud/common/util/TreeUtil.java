package com.jc.cloud.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeUtil
 * @Description
 *  树型结构数据转换工具类
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class TreeUtil{

  public static <T extends TreeNode> List<T> bulid(List<T> nodeList, Object root) {
    // 根节点列表
    List<T> list = new ArrayList<>();
    // 顺序遍历节点列表，如果之前是有序的，那么构建树后同层级之间有序
    for (int i = 0; i < nodeList.size(); i++) {
      T node = nodeList.get(i);
      //递归入口， String.valueOf防止null值
      if (String.valueOf(node.getParentId()).equals(String.valueOf(root))) {
        // parentID作为入口
        List<T> children = bulid(nodeList, node.getTreeId());
        node.addAll(children);
        list.add(node);
      }
    }
    return list;
  }
}

package com.jc.cloud.school.util;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

/**
 * 创建HTML，实现静态化文章
 * @Author chenjian
 * @Date 2019/6/13
 **/
public class EstablishHtml {

    /**
     * 测试参数
     */
    public void contextLoads() {
        String title ="静态化测试";
        String text = "<h1>测试测试</h1>"+"<h2>大门大门</h2>"+"<h3>大明大明</h3>";
        String fileName = "测试";
        MultipartFile file = MakeHtml(title,text,fileName);

    }

    /**
     * @Title: MakeHtml
     * @Description: 根据传入的参数替换模板文件
     * @param    title 标题
     * @param    text 添加的内容
     * @param    fileName  生成html文件名字
     * @return   MultipartFile    文件流
     * @throws
     */
    public static MultipartFile MakeHtml(String title,String text,String fileName ){
        MultipartFile multipartFile = null;
        try {
            FileInputStream fileinputstream = new FileInputStream("E:\\t.html");// 读取模板文件

            int lenght = fileinputstream.available();
            byte bytes[] = new byte[lenght];
            fileinputstream.read(bytes);
            fileinputstream.close();

            String templateContent = "";
            templateContent = new String(bytes);//获取文件内容

            //把模板页面上的 share 替换成 title 里的内容,替换标题
            templateContent = templateContent.replaceAll("Title", title);

            //把模板页面上的 ###text### 替换成 title 里的内容,替换文件内容
            templateContent = templateContent.replaceAll("###text###", text);

            fileName = fileName + ".html";//文件名称加后缀

            multipartFile = new MockMultipartFile(fileName,templateContent.getBytes());
            return multipartFile;
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return multipartFile;
    }

}

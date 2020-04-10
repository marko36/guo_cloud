package com.jc.cloud.school;

import com.jc.cloud.oss.util.OSSUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @Author chenjian
 * @Date 2019/6/15
 **/
@SpringBootTest
@EnableConfigurationProperties
@RunWith(SpringRunner.class)
public class OOSTest {

 @Autowired
 private OSSUtil ossUtil;

 private String bucket="guo-img";
 @Test
 public void Test(){
//    int i = 0;
//    while (i++<10){
//       new Thread(new TestThree()).start();
//    }
     ossUtil.uploadByFile(new File("E:\\t.html"),bucket,"t.html");
     ossUtil.uploadByFile(new File("C:\\Users\\Administrator\\Desktop\\65G58PIC4Az_1024.png"),bucket,"24.png");
//     ossUtil.deleteFile(bucket,"t.html");
//     System.out.println(ossUtil.FileExist(bucket,"t.html") ? "存在":"不存在");
 }

 class  TestThree implements   Runnable{

  @Override
  public void run() {

   ossUtil.uploadByFile(new File("E:\\t.html"),bucket,null);
  }
 }

}

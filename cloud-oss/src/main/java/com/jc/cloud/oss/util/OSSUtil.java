package com.jc.cloud.oss.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 阿里云OSS工具类，OOS没有修改文件功能，只能上传覆盖
 * @Author chenjian
 * @Date 2019/6/15
 **/
@Component
public class OSSUtil {

    //private String bucket="guo-img";//bucket是在阿里云OSS的一个存储空间名称，现在开发中暂时只有这个

    private final static  String ENDPOINT="http://oss-cn-beijing.aliyuncs.com";
    @Value("${aliyun.appid}")
    private String accessKeyId;
    @Value("${aliyun.secret}")
    private String accessKeySecret;

    /**
     * 获取OSS操作的连接通道
     * @return
     */
    private  OSSClient getClient(){
        ClientConfiguration config = new ClientConfiguration();
        return new OSSClient(ENDPOINT,new DefaultCredentialProvider(accessKeyId, accessKeySecret),config);
    }

    /**
     *
     * @Title: uploadByNetworkStream
     * @Description: 通过网络流上传文件
     * @param url 			URL
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名）例如“test/index.html”
     * @return void 		返回类型
     * @throws
     */
    public void uploadByNetworkStream( URL url, String bucketName, String objectName) {
        OSSClient client=getClient();
        try {
            InputStream inputStream = url.openStream();
            client.putObject(bucketName, objectName, inputStream);
            client.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }

    /**
     *
     * @Title: uploadByInputStream
     * @Description: 通过输入流上传文件
     * @param inputStream 	输入流
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return void 		返回类型
     * @throws
     */
    public  void uploadByInputStream( InputStream inputStream, String bucketName,
                                           String objectName) {
        OSSClient client=getClient();
        try {
            client.putObject(bucketName, objectName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }

    /**
     *
     * @Title: uploadByFile
     * @Description: 通过file上传文件
     * @param file 			上传的文件
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return void 		返回类型
     */
    public void uploadByFile(File file, String bucketName, String objectName) {
        OSSClient client=getClient();
        try {
           PutObjectResult result= client.putObject(bucketName, objectName, file);
           System.out.println(JSONObject.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                client.shutdown();
            }
        }
    }


    /**
     *
     * @Title: deleteFile
     * @Description: 根据key删除oss服务器上的文件
     * @param ossClient		oss客户端
     * @param bucketName		bucket名称
     * @param key    		文件路径/名称，例如“test/a.txt”
     * @return void    		返回类型
     * @throws
     */
    public void deleteFile(String bucketName, String key) {
        OSSClient client=getClient();
        client.deleteObject(bucketName, key);
        client.shutdown();
    }

    /**
     *
     * @Title: getInputStreamByOSS
     * @Description:根据key获取服务器上的文件的输入流
     * @param ossClient 	oss客户端
     * @param bucketName 	bucket名称
     * @param key 			文件路径和名称
     * @return InputStream 	文件输入流
     * @throws
     */
    public InputStream getInputStreamByOSS(String bucketName, String key) {
        OSSClient client=getClient();
        InputStream content = null;
        try {
            OSSObject ossObj = client.getObject(bucketName, key);
            content = ossObj.getObjectContent();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.shutdown();
        }
        return content;
    }

    /**
     *
     * @Title: queryAllObject
     * @Description: 查询某个bucket里面的所有文件
     * @param ossClient		oss客户端
     * @param bucketName		bucket名称
     * @return List<String>  文件路径和大小集合
     * @throws
     */
    public static List<String> queryAllObject(OSSClient ossClient, String bucketName) {
        List<String> results = new ArrayList<String>();
        try {
            // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
            ObjectListing objectListing = ossClient.listObjects(bucketName);
            // objectListing.getObjectSummaries获取所有文件的描述信息。
            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                results.add(" - " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return results;
    }

    /**
     * 判断文件是否存在
     * @param bucketNamem   bucket名称
     * @param objectPath   文件路径/名称，例如“test/a.txt”
     * @return
     */
    public boolean FileExist(String bucketNamem,String objectPath){
        OSSClient client=getClient();
        boolean isExist = client.doesObjectExist(bucketNamem,objectPath);
        client.shutdown();
        return isExist;
    }

}

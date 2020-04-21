package com.example.test;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.*;
import java.util.Properties;

/**
 * FastDFS API的编写
 */
public class FastdfsTest {
    public static void main(String[] args) {
        try {
            //设置配置属性
            /*Properties properties = new Properties();
            properties.setProperty("fastdfs.connect_timeout_in_seconds", "10");
            properties.setProperty("fastdfs.network_timeout_in_seconds", "30");
            properties.setProperty("fastdfs.charset", "UTF-8");
            properties.setProperty("fastdfs.http_tracker_http_port", "8080");
            properties.setProperty("fastdfs.tracker_servers", "192.168.189.128:22122");*/
            //1.通过ClientGlobal类来加载配置文件
            /*ClientGlobal.initByProperties(properties);*/
            ClientGlobal.init("fastdfsdemo/src/main/java/com/example/test/fastdfs.conf");
            //2.创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //3.获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //4.获取StorageServer对象
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            //5.获取StorageClient对象
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            //6.进行文件上传操作
            String[] messages = storageClient.upload_file("C:/Users/Administrator/Desktop/a.png", "png", null);
            String group = messages[0];
            String path = messages[1];
            String newPath = group + "/" + path;
            System.out.println(newPath);
            //7.进行文件的下载操作
            /*byte[] bytes = storageClient.download_file("group1", "M00/00/00/wKi9gF28L0GAWPc8ADBYdST2Acs984.png");
            FileOutputStream stream = new FileOutputStream("a.png");
            stream.write(bytes, 0, bytes.length-1);
            System.out.println("下载成功！！！");*/
            //8.进行文件的删除操作
            /*int rows = storageClient.delete_file("group1", "M00/00/00/wKi9gF28L0GAWPc8ADBYdST2Acs984.png");
            System.out.println(rows);*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }
}

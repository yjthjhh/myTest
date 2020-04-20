package com.example;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.Properties;

/**
 * FastDFS API的编写
 */
public class test {
    public static void main(String[] args) {
        try {
            //设置配置属性
            Properties properties = new Properties();
            properties.setProperty("fastdfs.connect_timeout_in_seconds", "10");
            properties.setProperty("fastdfs.network_timeout_in_seconds", "30");
            properties.setProperty("fastdfs.charset", "UTF-8");
            properties.setProperty("fastdfs.http_tracker_http_port", "8080");
            properties.setProperty("fastdfs.tracker_servers", "192.168.189.128:22122");
            //1.通过ClientGlobal类来加载配置文件
            ClientGlobal.initByProperties(properties);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }
}

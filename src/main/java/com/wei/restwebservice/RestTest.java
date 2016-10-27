package com.wei.restwebservice;

/**
 * Created by weiyinglei on 2016-10-27.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * Http客户端测试类
 * @author ypqiao
 *
 */
public class RestTest {


    public static void main(String[] args) throws Exception {

        /** 测试方法为调用REST风格的WebService **/

        HttpClient httpClient = new HttpClientImpl();

        // 发GET请求，返回文本数据(html/xml)
        //System.out.println(httpClient.get("http://server.arcgisonline.com/arcgis/rest/services"));

        // 发GET请求，返回文本数据(json)
        //System.out.println(httpClient.get("http://server.arcgisonline.com/arcgis/rest/services?f=json"));

        // 发送GET请求，返回二进制数据（image）,存放路径为c:/
        /*byte[] bytes = httpClient.getByte("http://sampleserver1c.arcgisonline.com/arcgisoutput/_ags_map5e57267ff6fb4227a8f8685915856213.png");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream( new File("c:/export.png")));
        out.write(bytes);
        out.flush();
        out.close();*/

        // 发送POST请求，返回文本数据
        //System.out.println(httpClient.post("http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/Specialty/ESRI_StateCityHighway_USA/MapServer/find", "searchText=New York&layers=1"));
        System.out.println(httpClient.post("http://122.225.51.173:23005/GetAllParkingList","{version:V1.0}"));
    }




}

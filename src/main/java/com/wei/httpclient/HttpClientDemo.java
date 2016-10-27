package com.wei.httpclient;

/**
 * Created by weiyinglei on 2016-10-27.
 */
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpClientDemo {

    public static void main(String[] args) throws Exception  {
        //call GetAllParkingList
        String url = "http://122.225.51.173:23005/GetAllParkingList";

        JSONObject ljo = new JSONObject();
        ljo.put("version","V1.0");

        postJson(url, ljo.toString());


        //call GetParkingInfo
        String url2 = "http://122.225.51.173:23005/GetParkingInfo";

        JSONObject list1 = new JSONObject();
        list1.put("parkingCode","112233");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(list1);
        JSONObject gjo = new JSONObject();
        gjo.put("version","1.0");
        gjo.put("totalNum",1);
        gjo.put("data",jsonArray);
        System.out.println(gjo.toString());

        String json2 = "{\"version\":\"1.0\",\"totalNum\":1,data:[{\"parkingCode\":\"161019021957043570\"}]}";
        System.out.println(json2);
        postJson(url2,json2);
    }

    /**
     * post方式提交json代码
     * @throws Exception
     */
    public static void postJson(String url, String json) throws Exception{
        //创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        //接收响应结果
        CloseableHttpResponse response = null;
        try {
            //创建httppost
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded");
            //参数
            StringEntity se = new StringEntity(json);
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");//发送json需要设置contentType
            httpPost.setEntity(se);
            response = httpclient.execute(httpPost);
            //解析返结果
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String resStr = EntityUtils.toString(entity, "UTF-8");
                System.out.println(resStr);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            httpclient.close();
            response.close();
        }
    }

    /**
     * post方式提交表单（模拟用户登录请求）
     * @throws Exception
     */
    public static void postForm() throws Exception  {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        //发送请求
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            // 创建httppost
            String url= "http://localhost:8080/search/ajx/user.htm";
            HttpPost httppost = new HttpPost(url);
            // 创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("username", "admin"));
            formparams.add(new BasicNameValuePair("password", "123456"));
            //参数转码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity, "UTF-8"));
            }
            //释放连接
        } catch (Exception e) {
            throw e;
        }finally{
            httpclient.close();
            response.close();
        }
    }

    /**
     * 发送 get请求
     * @throws Exception
     */
    public static void get() throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            // 创建httpget.
            HttpGet httpget = new HttpGet("http://www.baidu.com/");
            // 执行get请求.
            response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();

            // 打印响应状态
            System.out.println(response.getStatusLine().getStatusCode());
            if (entity != null) {
                // 打印响应内容
                System.out.println("Response content: " + EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            throw e;
        }finally{
            httpclient.close();
            response.close();
        }
    }
}

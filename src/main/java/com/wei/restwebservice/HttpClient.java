package com.wei.restwebservice;

/**
 * Created by weiyinglei on 2016-10-27.
 */

/**
 *
 * Http客户端接口
 * @author ypqiao
 *
 */
public interface HttpClient {

    /** 发送GET请求，返回 文本数据 **/
    public abstract String get(String urlStr) throws Exception;

    /** 发送GET请求，返回二进制数据  **/
    public abstract byte[] getByte(String urlStr) throws Exception;

    /** 发送POST请求，返回文本数据 **/
    public abstract String post(String urlStr, String params) throws Exception;



}

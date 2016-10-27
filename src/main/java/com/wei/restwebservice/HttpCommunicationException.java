package com.wei.restwebservice;

/**
 * Created by weiyinglei on 2016-10-27.
 */
/**
 *
 * Http运行时通信异常
 *
 */
public class HttpCommunicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public HttpCommunicationException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "返回码："+code+"消息描述："+msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

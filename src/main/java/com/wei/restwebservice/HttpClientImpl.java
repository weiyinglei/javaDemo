package com.wei.restwebservice;

/**
 * Created by weiyinglei on 2016-10-27.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * Http客户端实现
 * @author ypqiao
 *
 */
public class HttpClientImpl implements HttpClient {

    protected int cach_size = 3*1024*1024;
    protected int con_timeout = 36000;
    protected int read_timeout = 30000;
    protected String charset = "UTF-8";


    public HttpClientImpl() {}

    /* (non-Javadoc)
     * @see com.http.client.HttpClient#get(java.lang.String)
     */
    public String get(String urlStr) throws Exception{

        String response = null;

        URL url = null;
        HttpURLConnection con = null;
        BufferedReader ins = null;

        url = new URL(urlStr);

        con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(con_timeout);
        con.setReadTimeout(read_timeout);
        con.connect();

        ins = new BufferedReader(
                new InputStreamReader(con.getInputStream()),cach_size);


        if( con.getResponseCode() == 200 ){

            String line = null;
            StringBuilder rspStr = new StringBuilder();

            while( (line = ins.readLine()) != null ){
                rspStr.append(line);
            }

            response = rspStr.toString();
        }
        else {
            throw
                    new HttpCommunicationException(con.getResponseCode(),
                            con.getResponseMessage());
        }

        ins.close();
        con.disconnect();

        return response;
    }

    /* (non-Javadoc)
     * @see com.http.client.HttpClient#post(java.lang.String, java.lang.String)
     */
    public String post(String urlStr,String params ) throws Exception {

        String response = null;

        URL url = null;
        HttpURLConnection con = null;
        BufferedReader ins = null;
        OutputStream ous = null;

        url = new URL(urlStr);

        con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(con_timeout);
        con.setReadTimeout(read_timeout);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setUseCaches(false);
        con.connect();

        ous = con.getOutputStream();
        ous.write(params.getBytes(charset));
        ous.flush();
        ous.close();

        ins = new BufferedReader(
                new InputStreamReader(con.getInputStream()),cach_size);

        if( con.getResponseCode() == 200 ){

            String line = null;
            StringBuilder rspStr = new StringBuilder();

            while( (line = ins.readLine()) != null ){
                rspStr.append(line);
            }

            response = rspStr.toString();
        }
        else {
            throw
                    new HttpCommunicationException(con.getResponseCode(),
                            con.getResponseMessage());
        }

        ins.close();
        con.disconnect();

        return response;
    }

    /* (non-Javadoc)
     * @see com.http.client.HttpClient#getByte(java.lang.String)
     */
    public byte[] getByte(String urlStr) throws Exception {

        byte[] response = null;

        URL url = null;
        HttpURLConnection con = null;
        BufferedInputStream ins = null;

        url = new URL(urlStr);

        con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(con_timeout);
        con.setReadTimeout(read_timeout);
        con.connect();

        ins = new BufferedInputStream(con.getInputStream(),cach_size);


        if( con.getResponseCode() == 200 ){

            int b = 0;
            int index = 0;
            byte[] response_tmp = null;
            response = new byte[cach_size];
            while( (b=ins.read()) != -1 ){

                if( index > response.length - 1){

                    response_tmp = response;
                    response = new byte[response_tmp.length*2];
                    for( int i=0; i<response_tmp.length; i++){
                        response[i] = response_tmp[i];
                    }

                }

                response[index] = (byte)b;
                index++;
            }

            response_tmp = response;
            response = new byte[index];
            for( int i=0; i<index; i++){
                response[i] = response_tmp[i];
            }

        }
        else {
            throw
                    new HttpCommunicationException(con.getResponseCode(),
                            con.getResponseMessage());
        }

        ins.close();
        con.disconnect();

        return response;
    }


}

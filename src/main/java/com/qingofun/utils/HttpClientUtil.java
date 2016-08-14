package com.qingofun.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送HTTP请求
 *
 * @author fumingjun
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final String encoding = "UTF-8";


    public static void main(String[] args) {

        String msg = "{\"address\"=\"1\", \"custName=\"姓名193215\", \"extend1\"=\"2\"}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("address", "address");
        map.put("custName", "姓名193215");
        map.put("extend1", "2");
        String url = "http://127.0.0.1:9090/openAccDefault";
        postJsonStr("MD5", "abc", msg, url);

    }

    public static String postJson(Map<String, String> paraMap, String signMethod, String key, String url) {
        try {
            StringBuffer sb = new StringBuffer("{");
            String signStr = "";
            for (String paraKey : paraMap.keySet()) {
                // 中文使用urlencode转换，否则会有乱码
                sb.append("\"").append(paraKey).append("\":\"").append(paraMap.get(paraKey)).append("\",");

            }

            String msg = sb.substring(0, sb.length() - 1) + "}";
            System.out.println("msg" + msg);
            String signInfo = "signInfo";
            String returnStr = postJsonStr(signMethod, signInfo, msg, url);
            System.out.println("return:" + returnStr);
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * json 格式提交
     *
     * @param signMethod
     * @param signInfo
     * @param msg
     * @param url
     * @return
     */
    public static String postJsonStr(String signMethod, String signInfo, String msg, String url) {
        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity("{\"signMethod\":\"" + signMethod + "\",\"signInfo\":\"" + signInfo
                    + "\",\"msg\":" + msg + "}", "UTF-8");
            request.addHeader("content-type", "application/json;charset=UTF-8");
            request.setEntity(params);
            response = httpClient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            String retStr = EntityUtils.toString(httpEntity);
            System.out.println(retStr);
            return retStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送Post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, Object> params) {

        String resStr = "";

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        try {
            // 传递参数
            if ((null != params) && !params.isEmpty()) {

                List<NameValuePair> formparams = new ArrayList<NameValuePair>();

                for (Entry<String, Object> entry : params.entrySet()) {

                    String value = String.valueOf(((null == entry.getValue()) ? "" : entry.getValue()));

                    formparams.add(new BasicNameValuePair(entry.getKey(), value));

                    System.out.println("[key=" + entry.getKey() + "],[value=" + value + "]");
                }

                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, encoding);

                httppost.setEntity(uefEntity);
            }

            // 发送请求
            HttpResponse response = httpClient.execute(httppost);

            int status = response.getStatusLine().getStatusCode();

            if (200 != status) {
                // throw new IOException("请求错误[StatusCode=" + status + "]");
            }

            HttpEntity entity = response.getEntity();

            if (null != entity) {
                resStr = EntityUtils.toString(entity, encoding);
            }
            logger.debug("httpPost返回:" + resStr);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != httpClient) {
                HttpClientUtils.closeQuietly(httpClient);
            }
        }
        return resStr;
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        String resStr = "";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            // 发送请求
            HttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (200 != status) {
                // throw new IOException("请求错误[StatusCode=" + status + "]");
            }
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                resStr = EntityUtils.toString(entity, encoding);
            }
            logger.debug("httpGet返回:" + resStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != httpClient) {
                HttpClientUtils.closeQuietly(httpClient);
            }
        }
        return resStr;
    }
}

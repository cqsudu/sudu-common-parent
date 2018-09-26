package com.ledo.utils;

import com.ledo.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-13 21:22
 */
@Slf4j
@Getter
@Setter
public class HttpRequestUtil {
    /**
     * 连接超时时间
     */
    private static final int CONNECT_TIMEOUT = 30000;
    /**
     * 读取超时时间
     */
    private static final int READ_TIMEOUT = 30000;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 参数
     */
    private Queue<GenericMap<String, String>> queue;
    /**
     * 方法(HttpGet/HttpPost)
     */
    private HttpMethod method;

    /**
     * 构造方法
     * @param url
     * @param queue
     * @param method
     */
    public HttpRequestUtil(String url, Queue<GenericMap<String, String>> queue, HttpMethod method){
        this.url = url;
        this.queue = queue;
        this.method = method;
    }

    /**
     * 获取请求结果
     * @return
     * @throws Exception
     */
    public String getResponseText() {
        String responseText = "";
        if(this.getMethod() == HttpMethod.POST){
            responseText = post(this.getQueue());
        }else if(this.getMethod() == HttpMethod.GET){
            responseText = get();
        }else{
            throw new BusinessException("小程序接口调用方法不支持");
        }
        return responseText;
    }

    /**
     * POST方法
     * @return
     * @throws IOException
     */
    private String post(Queue<GenericMap<String, String>> queue)  {
        String result = "";
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) getUrlConnection();
            setConnectionProperties(httpURLConnection, HttpMethod.POST);
            writer = new PrintWriter(httpURLConnection.getOutputStream());
            writer.print(getParamString(queue));
            writer.flush();
            reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while (!StringUtils.isBlank(line = reader.readLine())){
                result += line;
            }
            log.info(String.format("http响应: %s", result));
        }catch (Exception e){
            log.info(String.format("发送http请求出现异常，%s", e.getMessage()));
            throw new BusinessException("小程序接口调用失败");
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(writer != null){
                writer.close();
            }
        }
        return result;
    }

    /**
     * GET方法
     * @return
     */
    private String get() {
        String result = "";
        BufferedReader reader = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) getUrlConnection();
            setConnectionProperties(httpURLConnection, HttpMethod.POST);
            httpURLConnection.connect();
            Map<String, List<String>> map = httpURLConnection.getHeaderFields();
            for (String key : map.keySet()) {
                log.info(String.format("%s = %s", key, map.get(key)));
            }
            reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while (!StringUtils.isBlank(line = reader.readLine())){
                result += line;
            }
            log.info(String.format("http响应: %s", result));
        }catch (Exception e){
            log.info(String.format("发送http请求出现异常，%s", e.getMessage()));
            throw new BusinessException("小程序接口调用失败");
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    /**
     * 获取连接对象
     * @return
     * @throws IOException
     */
    private URLConnection getUrlConnection() throws IOException {
        if(this.getMethod() == HttpMethod.GET){
            this.setUrl(this.getUrl() + "?" + getParamString(this.getQueue()));
        }
        URL url = new URL(this.getUrl());
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

        return httpURLConnection;
    }

    /**
     * 设置连接对象属性
     * @param httpURLConnection
     */
    private void setConnectionProperties(HttpURLConnection httpURLConnection, HttpMethod method) throws ProtocolException {
        httpURLConnection.setRequestMethod(method.name());
        if(method.equals(HttpMethod.POST)){
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
        }
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
        httpURLConnection.setReadTimeout(READ_TIMEOUT);
        httpURLConnection.setRequestProperty("accept", "*/*");
        httpURLConnection.setRequestProperty("connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        httpURLConnection.setRequestProperty("Content-type" , "application/x-java-serialized-object");
    }

    /**
     * 获取参数字符串
     * @param queue
     * @return
     */
    private String getParamString(Queue<GenericMap<String, String>> queue){
        StringBuilder sbParam = new StringBuilder();
        while (queue!= null && queue.size() > 0){
            GenericMap<String, String> entry = queue.poll();
            sbParam.append(String.format("%s=%s", entry.getKey(), entry.getValue()));
            if(queue.size() > 1){
                sbParam.append("&");
            }
        }
        log.info(String.format("队列转字符串: %s", sbParam.toString()));

        return sbParam.toString();
    }
}
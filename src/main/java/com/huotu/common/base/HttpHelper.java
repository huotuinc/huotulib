/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huotu.common.base;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * http请求处理
 * <p>
 * Created by Administrator on 2015/9/17.
 */
public class HttpHelper {
    /**
     * http post请求
     *
     * @param url
     * @param data
     * @return
     * @throws IOException
     */
    public static String postRequest(String url, Map<String, String> data) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        BasicNameValuePair[] basicNameValuePairs = new BasicNameValuePair[data.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            basicNameValuePairs[i] = new BasicNameValuePair(entry.getKey(), entry.getValue());
            i++;
        }
        post.setEntity(
                EntityBuilder.create()
                        .setContentEncoding("UTF-8")
                        .setContentType(ContentType.create("application/x-www-form-urlencoded", Charset.forName("UTF-8")))
                        .setParameters(basicNameValuePairs)
                        .build()
        );
        HttpResponse resultData = httpClient.execute(post);

        InputStream inputStream = resultData.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));


        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }
        return stringBuffer.toString();

    }

    /**
     * http get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        InputStream inputStream = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }


    /**
     * 异步http get请求
     * @param url
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String getAsyncRequest(String url) throws IOException, ExecutionException, InterruptedException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        Future<HttpResponse> future = httpClient.execute(httpGet, null);
        HttpResponse response = future.get();

        InputStream inputStream = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }
        httpClient.close();
        return stringBuffer.toString();
    }


}

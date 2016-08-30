package com.torn.farmer.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpRequest {

	private static final String CONTENT_TYPE_TEXT_JSON = "text/json; charset=utf-8";

	public static String sendInfoForGet(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		String str = "";
		try {
			// 响应
			CloseableHttpResponse response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				str = EntityUtils.toString(entity, "utf-8");
			}
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String sendInfoForPost(String url, String strOrJson) {
		String str = "";
		// 创建客户端
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 请求方式
		HttpPost httpPost = new HttpPost(url);
		// 请求数据
		StringEntity se = new StringEntity(strOrJson, "utf-8");// 请求数据格式
		se.setContentType(CONTENT_TYPE_TEXT_JSON);// 设置head
		httpPost.setEntity(se);
		try {
			// 响应
			CloseableHttpResponse response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				str = EntityUtils.toString(entity, "utf-8");
			}
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
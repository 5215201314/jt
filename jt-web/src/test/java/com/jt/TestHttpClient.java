package com.jt;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {

	@Test
	public void testGet() throws ClientProtocolException, IOException {
		CloseableHttpClient client= HttpClients.createDefault();
		//1.请求路径
		String url="http://item.jd.com/8790543.html";
		//2.请求方式：
		HttpGet get=new HttpGet(url);
		//3.发送请求，接收响应结果
		CloseableHttpResponse response=client.execute(get);
		//4.判定响应状态
		if(response.getStatusLine().getStatusCode()==200) {
			//4.转换响应结果为json格式
		String result=EntityUtils.toString(response.getEntity());
		System.out.println(result);
		}else {
			System.out.println("请求失败！！！");
		}
	}
}

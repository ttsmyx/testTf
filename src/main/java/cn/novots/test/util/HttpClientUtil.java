package cn.novots.test.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * http连接请求辅助
 */
@Slf4j
public class HttpClientUtil {
	private HttpClientUtil() {

	}

	public static String doPostWithParam(String url, String value, Map<String, String> headerMap) {
		// 创建一个post对象
		HttpPost post = new HttpPost(url);
		if (headerMap != null) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				post.addHeader(entry.getKey(), entry.getValue());
			}
		}
		// 包装成一个Entity对象

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			StringEntity entity = new StringEntity(value, "UTF-8");
			entity.setContentType("application/json");// 发送json数据需要设置contentType
			// 设置请求的内容
			post.setEntity(entity);

			httpClient = HttpClients.createDefault();

			// 执行post请求
			response = httpClient.execute(post);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			log.error("doPostWithParam请求异常,", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("response请求异常", e);
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error("httpClient关闭异常", e);
				}
			}
		}
		return null;
	}

}

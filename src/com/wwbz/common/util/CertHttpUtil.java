package com.wwbz.common.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jfinal.kit.PropKit;



public class CertHttpUtil {
	public static String ssl(String url,String data){
		System.out.println("url="+url);
		System.out.println("data="+data);
		StringBuffer message = new StringBuffer();
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File("E:/cert/apiclient_cert.p12"));
			keyStore.load(instream, PropKit.get("mch_id").toCharArray());
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, PropKit.get("mch_id").toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);	
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httpost = new HttpPost(url);
			httpost.addHeader("Connection", "keep-alive");
			httpost.addHeader("Accept", "*/*");
			httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpost.addHeader("Host", "api.mch.weixin.qq.com");
			httpost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpost.addHeader("Cache-Control", "max-age=0");
			httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			httpost.setEntity(new StringEntity(data, "UTF-8"));
			System.out.println("executing request" + httpost.getRequestLine());

			CloseableHttpResponse response = httpclient.execute(httpost);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: " + entity.getContentLength());
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						message.append(text);
					}
				}
				EntityUtils.consume(entity);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return message.toString();		
	}
//	public CloseableHttpClient certHttpUtil(String mchId, String certPath) throws Exception {
//        System.out.println("path =========="+certPath);
//
//        ConnectionKeepAliveStrategy connectionKeepAliveStrategy =(httpResponse, httpContext)->{
//            // tomcat默认keepAliveTimeout为20s
//            return 30 * 1000;
//        };
//        // 证书密码，默认为商户ID
//        String key = mchId;
//        // 证书的路径
//        String path = certPath;
//        // 指定读取证书格式为PKCS12
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        // 读取本机存放的PKCS12证书文件
//        FileInputStream instream = new FileInputStream(new File(path));
//        try {
//            // 指定PKCS12的密码(商户ID)
//            keyStore.load(instream, key.toCharArray());
//        } finally {
//            instream.close();
//        }
//        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, key.toCharArray()).build();
//        SSLConnectionSocketFactory sslsf =
//                new SSLConnectionSocketFactory(sslcontext, new String[] {"TLSv1"}, null,
//                        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//       return   HttpClients.custom().setSSLSocketFactory(sslsf).setKeepAliveStrategy(connectionKeepAliveStrategy).build();
//    }
}

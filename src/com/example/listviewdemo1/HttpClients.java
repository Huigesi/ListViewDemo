package com.example.listviewdemo1;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpClients {
	public HttpClients() {

	}

	public static String sendMessage(String url, String postUrl) {
		String result = "";
		/*
		 * HttpClient mHttpClient=new DefaultHttpClient(); HttpPost post =new
		 * HttpPost(url); try { post.setEntity(new StringEntity(postUrl));
		 * HttpResponse response=mHttpClient.execute(post);
		 * if(response.getStatusLine().getStatusCode()==200){
		 * result=EntityUtils.toString(response.getEntity()); } } catch
		 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (ClientProtocolException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace();
		 * }finally{ mHttpClient.getConnectionManager().shutdown(); }
		 */
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		HttpsURLConnection httpsURLConnection = null;
		String lines = null;

		try {
			httpsURLConnection = (HttpsURLConnection) new URL(url)
					.openConnection();
			httpsURLConnection.setRequestMethod("post");
			httpsURLConnection.setRequestProperty("Content-type",
					"application/json");
			httpsURLConnection.setDoOutput(true);
			httpsURLConnection.connect();

			OutputStream outputStream = httpsURLConnection.getOutputStream();
			outputStream.write(postUrl.getBytes("utf-8"));
			outputStream.flush();
			InputStream is = httpsURLConnection.getInputStream();
			if (httpsURLConnection.getResponseCode() == 200) {
				
				/* BufferedReader reader=new BufferedReader(new
				 InputStreamReader(httpsURLConnection.getInputStream()));
				 lines=reader.readLine();*/
				 
				byte[] data = new byte[1024];
				int len = 0;
				while ((len = is.read()) != -1) {
					bao.write(data, 0, len);
				}
				is.close();
			} else {
				Log.i("---->", "Ê§°Ü");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new String(bao.toByteArray());
	}

}

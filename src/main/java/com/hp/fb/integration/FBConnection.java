package com.hp.fb.integration;

import static com.hp.fb.integration.utils.Constants.FB_APP_ID;
import static com.hp.fb.integration.utils.Constants.FB_APP_SECRET;
import static com.hp.fb.integration.utils.Constants.REDIRECT_URI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FBConnection {
	// PageId:730714793682130  
   
 
	static String accessToken = "";

	public String getFBAuthUrl() {

		System.getProperties().put("http.proxyHost", "proxy.sgp.hp.com");
		System.getProperties().put("http.proxyPort", "8080");

		String fbLoginUrl = "";
		try {
			System.out.println("inside try1");
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id=" + FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8") + "&scope=email,user_friends";
			System.out.println("inside try11");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			System.out.println("inside try111");
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8") + "&client_secret=" + FB_APP_SECRET + "&code=" + code;
			System.out.println("inside try1111cccc" + code);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {

		if ("".equals(accessToken)) {
			URL url;
			try {
				url = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection urlConnection;
			StringBuffer b = null;
			try {
				System.getProperties().put("https.proxyHost", "proxy.sgp.hp.com");
				System.getProperties().put("https.proxyPort", "8080");

				urlConnection = url.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					b.append(inputLine + "\n");
				}
				in.close();

			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook " + e);
			}
			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: " + accessToken);
			}
		}
		return accessToken;
	}
}

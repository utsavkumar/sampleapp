package com.hp.fb.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FBGraph {
	private final String accessToken;

	public FBGraph(String accessToken)// Parameterized constructor
	{
		this.accessToken = accessToken;
	}

	// personal info fetching url
	public String getFBGraph() {
		String graph = null;
		try {
			String g = "https://graph.facebook.com/v2.5/me?fields=id,name,first_name,last_name,gender,email,locale&" + accessToken;
			URL u = new URL(g);
			System.out.println("Access token..... " + accessToken);
			System.out.println("URL.... " + g);
			// If you are no want append appsecret_proof parameter then you can
			// change set to no use appsecret_proof parameter on your app
			// management site. The management site menu is
			// setting>advanced>Require AppSecret Proof for Server API calls ->
			// set to disabled.
			URLConnection c = u.openConnection();// open the connection
			BufferedReader in = new BufferedReader(new java.io.InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				b.append(inputLine + "\n");
			}
			System.out.println("Input line......" + inputLine);
			in.close();
			graph = b.toString();
			System.out.println("Graph data......." + graph);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	// fetching personal info in map
	public Map<String, String> getGraphData(String fbGraph) {
		System.out.println("Json String .... " + fbGraph);
		Map<String, String> fbProfile = new HashMap<String, String>();
		try {
			JSONObject json = new JSONObject(fbGraph);

			fbProfile.put("id", json.getString("id"));
			if (json.has("first_name")) {
				fbProfile.put("first_name", json.getString("first_name"));
			}
			if (json.has("email")) {
				fbProfile.put("email", json.getString("email"));
			}

			if (json.has("gender")) {
				fbProfile.put("gender", json.getString("gender"));
			}
			fbProfile.put("last_name", json.getString("last_name"));
			fbProfile.put("locale", json.getString("locale"));
			fbProfile.put("name", json.getString("name"));

		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile;
	}

	// friends fetching url

	public String getFbFriends() {
		String graph2 = null;
		try {

			String g2 = "https://graph.facebook.com/v2.5/me/friends?" + accessToken;
			URL u2 = new URL(g2);
			URLConnection c2 = u2.openConnection();// open the connection
			BufferedReader in2 = new BufferedReader(new java.io.InputStreamReader(c2.getInputStream()));
			String inputLine2;
			StringBuffer b2 = new StringBuffer();
			while ((inputLine2 = in2.readLine()) != null) {
				b2.append(inputLine2 + "\n");
			}
			in2.close();
			graph2 = b2.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB friends data. " + e);
		}
		return graph2;
	}

	// fetching friends in map
	public Map<String, Object> getFriendsGraphData(String fbGraph) {
		System.out.println("Friends Json String .... " + fbGraph);
		Map<String, Object> fbFriends = new HashMap<String, Object>();
		try {
			JSONObject json = new JSONObject(fbGraph);
			fbFriends.put("total_friends", json.getJSONObject("summary").get("total_count"));
			JSONArray array = json.getJSONArray("data");
			ArrayList<String> friends = new ArrayList<String>();
			int length = array.length() - 1;
			int i = 0;
			String friendName = new String();

			while (i <= length) {
				friendName = array.getJSONObject(i).getString("name").toString();
				friends.add(friendName);
				i++;

			}

			fbFriends.put("friends", friends);
			System.out.println(fbFriends);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbFriends;
	}

	// likes fetching url

	public String getLikes() {
		String graphLikes = null;
		try {

			String gLikes = "https://graph.facebook.com/v2.5/me/likes?fields=name,likes&" + accessToken;
			URL uLikes = new URL(gLikes);
			System.out.println("Likes Access token..... " + accessToken);
			System.out.println("Likes URL.... " + gLikes);
			URLConnection cLikes = uLikes.openConnection();// open the
															// connection
			BufferedReader inLikes = new BufferedReader(new java.io.InputStreamReader(cLikes.getInputStream()));
			String inputLineLikes;
			StringBuffer bLikes = new StringBuffer();
			System.out.println("Likes string buffer: " + bLikes);
			while ((inputLineLikes = inLikes.readLine()) != null) {
				bLikes.append(inputLineLikes + "\n");
			}
			System.out.println("Likes Input line......" + inputLineLikes);
			inLikes.close();
			graphLikes = bLikes.toString();
			System.out.println("Likes Graph data......." + graphLikes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB Likes data. " + e);
		}
		return graphLikes;
	}

	// fetching likes in map
	public Map<String, Object> getLikesData(String fbGraph) {
		System.out.println("Likes Json String .... " + fbGraph);
		Map<String, Object> fbLikes = new HashMap<String, Object>();
		try {
			JSONObject json = new JSONObject(fbGraph);
			System.out.println("json likes:" + json);
			JSONArray array = json.getJSONArray("data");
			ArrayList<String> likedPages = new ArrayList<String>();
			ArrayList<String> likesOnPages = new ArrayList<String>();
			int length = array.length() - 1;
			int lengthLikesOnPages = length;
			int i = 0;
			String pageName = new String();
			String pageLikes = new String();
			while (i <= length) {
				pageName = array.getJSONObject(i).getString("name").toString();
				pageLikes = array.getJSONObject(i).get("likes").toString();
				likedPages.add(pageName);
				likesOnPages.add(pageLikes);
				i++;

			}

			fbLikes.put("likedPages", likedPages);
			fbLikes.put("likesOnPages", likesOnPages);
			System.out.println(fbLikes);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbLikes;
	}

	// get profile picture url
	public String getPicture() {
		String graphPicture = null;
		try {

			String gPicture = "https://graph.facebook.com/v2.5/me/picture?redirect=false&" + accessToken;
			URL uPicture = new URL(gPicture);
			System.out.println("Likes Access token..... " + accessToken);
			System.out.println("Likes URL.... " + gPicture);
			URLConnection cPicture = uPicture.openConnection();// open the
																// connection
			BufferedReader inPicture = new BufferedReader(new java.io.InputStreamReader(cPicture.getInputStream()));
			String inputLinePicture;
			StringBuffer bPicture = new StringBuffer();
			System.out.println("Likes string buffer: " + bPicture);
			while ((inputLinePicture = inPicture.readLine()) != null) {
				bPicture.append(inputLinePicture + "\n");
			}
			System.out.println("Likes Input line......" + inputLinePicture);
			inPicture.close();
			graphPicture = bPicture.toString();
			System.out.println("Likes Graph data......." + graphPicture);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB Likes data. " + e);
		}
		return graphPicture;
	}

	// get profile picture data in Map
	public Map<String, Object> getProfilePictureData(String fbGraph) {
		System.out.println("Profile picture Json String .... " + fbGraph);
		Map<String, Object> fbPicture = new HashMap<String, Object>();
		try {

			JSONObject json = new JSONObject(fbGraph);
			fbPicture.put("pic_url", json.getJSONObject("data").get("url"));
			System.out.println("json Picture:" + json);

		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbPicture;
	}

	public String getFBGraph2(String id, String accessToken2) {
		String graph3 = null;
		try {

			String g3 = new StringBuilder("https://graph.facebook.com/v2.2/").append(id).append("/picture?accessToken=").append(accessToken2)
					.toString();
			URL u3 = new URL(g3);
			System.out.println("Access token..... " + accessToken);
			System.out.println("URL.... " + g3);

			// If you are no want append appsecret_proof parameter then you can
			// change set to no use appsecret_proof paameter on your app
			// management site. The management site menu is
			// setting>advanced>Require AppSecret Proof for Server API calls ->
			// set to disabled.
			URLConnection c3 = u3.openConnection();// open the connection
			BufferedReader in = new BufferedReader(new java.io.InputStreamReader(c3.getInputStream()));
			String inputLine;
			StringBuffer b3 = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				b3.append(inputLine + "\n");
			}
			System.out.println("Input line......" + inputLine);
			in.close();
			graph3 = b3.toString();
			System.out.println("Graph data......." + graph3);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph3;
	}

	public String getUserInfo(String access_token) throws MalformedURLException, ProtocolException, IOException {
		try {
			String connection = connectionGet("https://graph.facebook.com/me?access_token=" + access_token, "");
			return connection;
		} catch (Exception e) {
			return null;
		}
	}

	public String connectionGet(String url, String parameter) throws MalformedURLException, ProtocolException, IOException {

		URL url1 = new URL(url);
		HttpURLConnection request1 = (HttpURLConnection) url1.openConnection();
		request1.setRequestMethod("GET");
		request1.connect();
		String responseBody = convertStreamToString(request1.getInputStream());
		return responseBody;
	}

	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException e) {
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}

		return sb.toString();
	}

	public Map<String, Object> getGraphData2(String fbGraph2) {
		System.out.println("Json2 String .... " + fbGraph2);
		Map<String, Object> fbProfile2 = new HashMap<String, Object>();
		try {
			JSONObject json1 = new JSONObject(fbGraph2);

			fbProfile2.put("Profile_Photos", json1.getJSONObject("Profile_Photos"));
			if (json1.has("Profile_Photos")) {
				fbProfile2.get("Profile_Photos");
			}

		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile2;
	}

	public String getMessage(final String name) {

		return new StringBuilder(name).append(", Welcome to DevOps").toString();
	}

}

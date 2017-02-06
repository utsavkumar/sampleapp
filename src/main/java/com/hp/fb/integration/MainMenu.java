package com.hp.fb.integration;

import static com.hp.fb.integration.utils.Constants.FB_APP_ID;
import static com.hp.fb.integration.utils.Constants.FB_APP_SECRET;
import java.io.IOException;
import java.util.Map;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
@WebService
public class MainMenu extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private static final int likesCount = 0;
	// private static final String accessToken2 = null;
	private String code = " ";
	private final String accessToken1 = " ";

	@SuppressWarnings("deprecation")
	FacebookClient client1 = new DefaultFacebookClient();

	@Override
	@SuppressWarnings("restriction")
	@WebMethod
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		code = req.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException("ERROR: Didn't get code parameter in callback.");
		}

		System.getProperties().put("http.proxyHost", "proxy.sgp.hp.com");
		System.getProperties().put("http.proxyPort", "8080");

		System.out.println("inside try111111" + code);
		// String post_id="804373672976973_804777062936634";
		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);
		System.out.println("accessToken" + accessToken);
		FBGraph fbGraph = new FBGraph(accessToken);// get the user accessToken
		String graph = fbGraph.getFBGraph();
		System.out.println("accessTokensss:" + graph);
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		ServletOutputStream out = res.getOutputStream();
		out.println("<h1>Facebook Login using Java</h1>");
		out.println("<h2>Application Main Menu</h2>");
		out.println("<div>Id:" + fbProfileData.get("id"));
		out.println("<div>Name:" + fbProfileData.get("name"));
		out.println("<div>First Name:" + fbProfileData.get("first_name"));
		out.println("<div>Your Email: " + fbProfileData.get("email"));
		out.println("<div>Gender:" + fbProfileData.get("gender"));
		out.println("<div> Last Name:" + fbProfileData.get("last_name"));
		out.println("<div>Locale:" + fbProfileData.get("locale"));
		Facebook1 Client = new Facebook1();
		AccessToken pageToken = Client.getPageAccessToken(accessToken1);
		System.out.println("\n pageToken--->" + pageToken);
		String pageTokenModified = pageToken.toString();
		System.out.println("\n pageTokenModified -- >" + pageTokenModified);
		String pageAccessToken = pageTokenModified.substring(24, 68);
		System.out.println("\n Getting the pageAccessToken From FaceBook Page------>" + pageAccessToken);
		FBAccessToken ac = new FBAccessToken();
		String acc = "https://graph.facebook.com/v2.2/oauth/access_token?grant_type=fb_exchange_token&client_id=1017716151612815&client_secret=2cd4ceb1e5fef5c3dabd1eaf601cb9e0&fb_exchange_token="
				+ code;
		System.out.println(" XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + acc);
		AccessToken accessToken4 = new DefaultFacebookClient().obtainAppAccessToken(FB_APP_ID, FB_APP_SECRET);
		System.out.println(" XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX444" + accessToken4);

	}
}

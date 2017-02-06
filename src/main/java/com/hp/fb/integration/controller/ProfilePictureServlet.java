package com.hp.fb.integration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hp.fb.integration.FBGraph;

/**
 * Servlet implementation class ProfilePictureServlet
 */
public class ProfilePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilePictureServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String accessToken = session.getAttribute("accessToken").toString();

		FBGraph fbGraph = new FBGraph(accessToken);// get the user accessToken
		String graph = fbGraph.getFBGraph();
		ArrayList<String> profilePictureArray = new ArrayList<String>();

		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

		profilePictureArray.add(fbProfileData.get("first_name"));
		profilePictureArray.add(fbProfileData.get("last_name"));
		profilePictureArray.add(fbProfileData.get("name"));

		String graphPictures = fbGraph.getPicture();
		Map<String, Object> profilePictureData = fbGraph.getProfilePictureData(graphPictures);
		String pic_url = profilePictureData.get("pic_url").toString();
		profilePictureArray.add(pic_url);
		request.setAttribute("profilePictureArray", profilePictureArray);
		RequestDispatcher rd = request.getRequestDispatcher("ProfilePicture.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
 * Servlet implementation class LikesServlet
 */
public class LikesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikesServlet() {
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
		String graph = fbGraph.getLikes();
		System.out.println("Likes graph:" + graph);
		Map<String, Object> fbLikesData = fbGraph.getLikesData(graph);
		System.out.println("Likes data:" + fbLikesData);
		ArrayList<String> likedPages = (ArrayList<String>) fbLikesData.get("likedPages");
		ArrayList<String> likesOnPages = (ArrayList<String>) fbLikesData.get("likesOnPages");
		request.setAttribute("likedPages", likedPages);
		request.setAttribute("likesOnPages", likesOnPages);
		RequestDispatcher rd = request.getRequestDispatcher("Likes.jsp");
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

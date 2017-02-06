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
 * Servlet implementation class PersonalInfoServlet
 */
public class PersonalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonalInfoServlet() {
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
		System.out.println("accessTokensss:" + graph);
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		ArrayList<String> personalInfoArray = new ArrayList<String>();
		personalInfoArray.add(fbProfileData.get("id"));
		personalInfoArray.add(fbProfileData.get("first_name"));
		personalInfoArray.add(fbProfileData.get("last_name"));
		personalInfoArray.add(fbProfileData.get("name"));
		personalInfoArray.add(fbProfileData.get("gender"));
		personalInfoArray.add(fbProfileData.get("email"));
		personalInfoArray.add(fbProfileData.get("locale"));
		request.setAttribute("personalInfoArray", personalInfoArray);
		RequestDispatcher rd = request.getRequestDispatcher("PersonalInfo.jsp");
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

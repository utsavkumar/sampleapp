package com.hp.fb.integration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hp.fb.integration.FBConnection;

/**
 * Servlet implementation class Home
 */
public class HomeServlet extends HttpServlet {
	private final static Logger LOG = Logger.getLogger(HomeServlet.class);
	private static final long serialVersionUID = 1L;
	private String code = " ";

	@Override
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.info("Entering into servlet");

		// begin facebook code for getting access tokens
		code = request.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException("ERROR: Didn't get code parameter in callback.");
		}
		System.getProperties().put("http.proxyHost", "proxy.sgp.hp.com");
		System.getProperties().put("http.proxyPort", "8080");
		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);
		System.out.println("accessToken" + accessToken);

		// create a session with access token's value so that it is accessible
		// to all pages to fetch values
		HttpSession session = request.getSession();
		session.setAttribute("accessToken", accessToken);

		RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
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

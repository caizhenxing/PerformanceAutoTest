package com.systoon.qc.servlet;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServletContext
 */
public class TestServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServletContext() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext servletContext = config.getServletContext();
		System.out.println("********" + servletContext.getContextPath());
		try {
			System.out.println("!!!!" + servletContext.getRealPath("/web.xml"));
			System.out.println("!!!!" + servletContext.getRealPath("/classes/filepath.properties"));
			System.out.println("!!!!" + servletContext.getResource("/classes/filepath.properties"));
			System.out.println("!!!!" + servletContext.getResource("/WEB-INF/classes/filepath.properties"));
			System.out.println("!!!!" + servletContext.getResource("/web.xml"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("********" + servletContext.getResourcePaths("filepath.properties"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

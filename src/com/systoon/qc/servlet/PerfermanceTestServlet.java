package com.systoon.qc.servlet;

import java.io.File;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.systoon.qc.business.ExecuteShell;
import com.systoon.qc.jmxhandler.*;

/**
 * Servlet implementation class PerfermanceTestServlet
 */
@WebServlet("/perfermanceTestServlet")
public class PerfermanceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PerfermanceTestServlet() {
		super();

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 获取配置文件基本参数
		 */
		String loaderIP1 = getServletContext().getInitParameter("loaderIP1");
		String loaderIP2 = getServletContext().getInitParameter("loaderIP2");
		String xslTemple = getServletContext().getInitParameter("xslTemple");
		String jmxPlanTemple = getServletContext().getInitParameter("jmxPlanTemple");
		String baseJmeterPath = getServletContext().getInitParameter("baseJmeterPath");
		String baseJmxPath = getServletContext().getInitParameter("baseJmxPath");
		String baseJtlPath = getServletContext().getInitParameter("baseJtlPath");
		String baseHtmlPath = getServletContext().getInitParameter("baseHtmlPath");
		String baseLogPath = getServletContext().getInitParameter("baseLogPath");


		/**
		 * （二期优化）传参格式变成json
		 * 获取UI传递的参数
		 * getParameter()  根据请求参数中的名字 “name”，获取参数值
		 * getParameterValues(String name) 根据参数名，获取对应字符串数组
		 */
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		String path = request.getParameter("path");
		String parameters = request.getParameter("parameters");
		String method = request.getParameter("method");
		String vuser = request.getParameter("vuser");
		String assertion = request.getParameter("assertion");

		/**
		 * 重命名 Jmeter 计划文件，结果文件
		 */
		JmxFileHander jmxFileHander = new JmxFileHander();
		String jmxPlanName = jmxFileHander.renameJmxPlan(path);
		String jtlResultName = jmxPlanName.substring(0, jmxPlanName.indexOf(".")) + ".jtl";
		String htmlReportName = jmxPlanName.substring(0, jmxPlanName.indexOf(".")) + ".html";
		String logFileName = jmxPlanName.substring(0, jmxPlanName.indexOf(".")) + ".log";
		
		String jmxPlan = baseJmxPath + jmxPlanName;
		String jtlResult = baseJtlPath + jtlResultName;
		String htmlReport = baseHtmlPath + htmlReportName;
		String logFile = baseLogPath + logFileName;
		
		String methods = request.getParameter("methods");
		System.out.println("***************");
		System.out.println(methods);
		System.out.println("***************");

		
		System.out.println(jmxPlan);
		System.out.println(jtlResult);
		System.out.println(htmlReport);
		System.out.println(logFile);
		System.out.println(jmxPlanTemple);
		
		System.out.println("ip" + ip);
		System.out.println("port" + port);
		System.out.println("path" + path);
		System.out.println("parameters" + parameters);
		System.out.println("method" + method);
		System.out.println("vuser" + vuser);
		System.out.println("assertion" + assertion);
		
		
		/**
		 * 生成JMX计划文件
		 * （二期优化）将参数类型变成json
		 */
		new JmxParserDom4jHandler().createJmxPlan(jmxPlanTemple, jmxPlan, ip, port, path, method, parameters, vuser, assertion);

		
		// 执行jmeter命令(绝对路径)
		String jmeterRemoteExecute = baseJmeterPath + "jmeter.sh" 
									+ " -n -t " + jmxPlan 
									+ " -l " + jtlResult;
		
//		String jmeterRemoteExecute = baseJmeterPath + "jmeter.sh" 
//				+ " -n -t " + baseJmxPath + "baidu.jmx" 
//				+ " -l " + baseJtlPath + "1000.jtl" ;


		/**
		 * 后端调用shell ／ 批处理文件 执行 jmeter -n (no GUI)
		 */
		
		Process pid = null;
        try {
            pid = new ExecuteShell().executeShell(jmeterRemoteExecute); 
        } catch (IOException e) {
            e.printStackTrace();
        }
            

		request.setAttribute("process", pid);
		request.setAttribute("logFile", logFile);
		request.setAttribute("jtlResult", jtlResult);
		request.setAttribute("htmlReport", htmlReport);
		request.setAttribute("htmlReportName", htmlReportName);
		request.getRequestDispatcher("console2.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}

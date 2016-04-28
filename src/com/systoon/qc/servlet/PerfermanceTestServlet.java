package com.systoon.qc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.membership.StaticMember;


import com.systoon.qc.bak.JavaShellUtil;
import com.systoon.qc.business.ConvertJtlToHtml;
import com.systoon.qc.business.ExecuteJmeter;
import com.systoon.qc.business.JmeterPlanHandle;

/**
 * Servlet implementation class PerfermanceTestServlet
 */
@WebServlet("/perfermanceTestServlet")
public class PerfermanceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String loaderIP1 = null;
	private String loaderIP2 = null;
	private String JmxPlanTemple = null;
	private String baseJmeterPath = null;
	private String baseJmxPath = null;
	private String baseJtlPath = null;
    private String baseLogPath = null;
    private String JmxPlanName = null;
    private String JtlResultName = null;
    private String executeLogFile = null;



	public PerfermanceTestServlet() {
		super();

	}
	
	@Override
	public void init(ServletConfig servletconfig) throws ServletException{
		/**基本路径写入配置文件web.xml，从配置文件中读取
		 * 方法一：通过servletContext 读取配置参数
		 */
		ServletContext servletContext = servletconfig.getServletContext();
		loaderIP1 = servletContext.getInitParameter("loaderIP1");
		loaderIP2 = servletContext.getInitParameter("loaderIP2");
		
		// 基本路径
		// private static final String basePath = "/root/";
		JmxPlanTemple = servletContext.getInitParameter("JmxPlanTemple");
		baseJmeterPath = servletContext.getInitParameter("baseJmeterPath");
		baseJmxPath = servletContext.getInitParameter("baseJmxPath");
		baseJtlPath = servletContext.getInitParameter("baseJtlPath");
		baseLogPath = servletContext.getInitParameter("baseLogPath");
		
		
		
		
		/**方法二：通过ClassLoader 获取外部配置文件参数
		 * 注意：filepath.properties必须在 src 目录下
		 */
//		try {
//			Properties properties = new Properties();
//			ClassLoader classLoader = getClass().getClassLoader();
//			InputStream is = classLoader.getResourceAsStream("filepath.properties");
//			properties.load(is);
//			loaderIP1 = properties.getProperty("loaderIP1");
//			loaderIP2 = properties.getProperty("loaderIP2");
//			baseJmeterPath = properties.getProperty("baseJmeterPath");
//			baseJMXPath = properties.getProperty("baseJmeterPath");
//			baseJtlPath = properties.getProperty("baseJtlPath");
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		/**方法三：通过ServletContext  获取外部配置文件参数
		 * getResourceAsStream(String path)  :path 的／ 为当前WEB 应用根目录
		 */
//		try {
//			Properties properties = new Properties();
//			InputStream ins = servletContext.getResourceAsStream("/WEB-INF/classes/filepath.properties");
//			properties.load(ins);
//			loaderIP1 = properties.getProperty("loaderIP1");
//			loaderIP2 = properties.getProperty("loaderIP2");
//			baseJmeterPath = properties.getProperty("baseJmeterPath");
//			baseJMXPath = properties.getProperty("baseJmeterPath");
//			baseJtlPath = properties.getProperty("baseJtlPath");
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
	
		// 记录Shell执行状况的日志文件的位置(绝对路径)
		executeLogFile = baseJmeterPath + "executeShell.log";
		System.out.println(loaderIP1 + baseJmeterPath + baseJmxPath + baseJtlPath);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
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
		JmeterPlanHandle jph = new JmeterPlanHandle();
		JmxPlanName = jph.renameJmxPlan(path);
		JtlResultName = jph.renameJtlResult(path);
		
		
		/**
		 * 处理JMX文件
		 */
		if(!jph.handleJmxFile(JmxPlanTemple, path, assertion, ip, port, path, method, parameters, vuser, assertion)){
			System.out.println("测试计划文件处理失败。。。。");
		}else{
			System.out.println("测试计划处理完成。。。。");
		}
		
		// 执行jmeter命令(绝对路径)
//		String jmeterRemoteExecute = baseJmeterPath + "jmeter.sh" 
//									+ " -n -t " + baseJmxPath + JmxPlanName 
//									+ " -l " + baseJtlPath + JtlResultName ;
		
		String jmeterRemoteExecute = baseJmeterPath + "jmeter.sh" 
				+ " -n -t " + baseJmxPath + "baidu.jmx" 
				+ " -l " + baseJtlPath + "1000.jtl" ;


		/**
		 * 后端调用shell ／ 批处理文件 执行 jmeter -n (no GUI)
		 */
		
		Process pid = null;
        try {
            pid = new ExecuteJmeter().executeShell(jmeterRemoteExecute,executeLogFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//		response.setCharacterEncoding("GBK");
//		PrintWriter out = response.getWriter();
	    

		request.setAttribute("process", pid);
		request.getRequestDispatcher("console.jsp").forward(request, response);
//		request.getRequestDispatcher("index.jsp").forward(request,response);

	}

}

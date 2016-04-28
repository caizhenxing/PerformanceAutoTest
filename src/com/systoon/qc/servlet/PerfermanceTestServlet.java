package com.systoon.qc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * Servlet implementation class PerfermanceTestServlet
 */
@WebServlet("/perfermanceTestServlet")
public class PerfermanceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String loaderIP1 = null;
	private String loaderIP2 = null;
	private String baseJmeterPath = null;
	private String baseJMXPath = null;
	private String baseJtlPath = null;
    private String executeShellLogFile = null;



	public PerfermanceTestServlet() {
		super();

	}
	
	public void init(ServletConfig servletconfig) throws ServletException{
		ServletContext servletContext = servletconfig.getServletContext();
		loaderIP1 = servletContext.getInitParameter("loaderIP1");
		loaderIP2 = servletContext.getInitParameter("loaderIP2");
		
		/*基本路径写入配置文件，从配置文件中读取
		 * 
		 */
		// 基本路径
		// private static final String basePath = "/root/";
		baseJmeterPath = servletContext.getInitParameter("baseJmeterPath");
		baseJMXPath = servletContext.getInitParameter("baseJmeterPath");
		baseJtlPath = servletContext.getInitParameter("baseJtlPath");
		
		// 记录Shell执行状况的日志文件的位置(绝对路径)
		String executeShellLogFile = baseJmeterPath + "executeShell.log";
		
		System.out.println(loaderIP1 + baseJmeterPath + baseJMXPath + baseJtlPath);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 获取UI传递的参数
		 */
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		String path = request.getParameter("path");
		String param = request.getParameter("param");
		String vuser = request.getParameter("vuser");

		/**
		 * 定义Jmeter路径，计划文件，结果文件
		 */
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyymmdd");
		String ctime = simpleDate.format(new Date());
		
		String jmxPlanTemple = "baidu.jmx";
		
		String jtlName = jmxPlanTemple.substring(0, jmxPlanTemple.indexOf('.')) + "_" + ctime + ".jtl";
		System.out.println(jtlName);

		// 执行jmeter命令(绝对路径)
		String jmeterRemoteExecute = baseJmeterPath + "jmeter.sh" + " -n -t " + baseJMXPath + "baidu.jmx" + " -l " + baseJtlPath + "jtlName" ;


		/**
		 * 后端调用shell ／ 批处理文件 执行 jmeter -n (no GUI)
		 */
		
		Process pid = null;
        try {
            pid = new ExecuteJmeter().executeShell(jmeterRemoteExecute,executeShellLogFile);
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

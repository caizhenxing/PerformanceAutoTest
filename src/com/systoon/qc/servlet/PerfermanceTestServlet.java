package com.systoon.qc.servlet;

import java.io.File;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.systoon.qc.business.ExecuteShell;
import com.systoon.qc.business.JmeterPlanHandle;

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
		String jmxPlanTemple = getServletContext().getInitParameter("jmxPlanTemple");
		String baseJmeterPath = getServletContext().getInitParameter("baseJmeterPath");
		String baseJmxPath = getServletContext().getInitParameter("baseJmxPath");
		String baseJtlPath = getServletContext().getInitParameter("baseJtlPath");
		String baseLogPath = getServletContext().getInitParameter("baseLogPath");
		
		/*如果目录不存在则创建目录*/
		String[] basePaths = new String[4];
		basePaths[0] = baseJmeterPath;
		basePaths[1] = baseJmxPath;
		basePaths[2] = baseJtlPath;
		basePaths[3] = baseLogPath;
		
		for(int i = 0;i < basePaths.length; i++){
			if(!new File(basePaths[i]).exists())
				new File(basePaths[i]).mkdirs();
		}

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
		String jmxPlanName = jph.renameJmxPlan(path);
//		String jtlResultName = jmxPlanName.substring(0, jmxPlanName.indexOf(".")) + ".jtl";
//		String logFileName = jmxPlanName.substring(0, jmxPlanName.indexOf(".")) + ".log";
		
//		String logFile = baseLogPath + logFileName;
		String logFile = "1.log";
		
		
		/**
		 * 处理JMX文件
		 */
		if(!jph.handleJmxFile(jmxPlanTemple, path, assertion, ip, port, path, method, parameters, vuser, assertion)){
			System.out.println("测试计划文件处理失败。。。。");
		}else{
			System.out.println("测试计划处理完成。。。。");
		}
		
		// 执行jmeter命令(绝对路径)
//		String jmeterRemoteExecute = baseJmeterPath + "jmeter.sh" 
//									+ " -n -t " + baseJmxPath + jmxPlanName 
//									+ " -l " + baseJtlPath + jtlResultName ;
		
		String jmeterRemoteExecute = baseJmeterPath + "jmeter.sh" 
				+ " -n -t " + baseJmxPath + "baidu.jmx" 
				+ " -l " + baseJtlPath + "1000.jtl" ;


		/**
		 * 后端调用shell ／ 批处理文件 执行 jmeter -n (no GUI)
		 */
		
		Process pid = null;
        try {
            pid = new ExecuteShell().executeShell(jmeterRemoteExecute, logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
            

		request.setAttribute("process", pid);
		request.setAttribute("logFile", logFile);
		request.getRequestDispatcher("console.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}

package com.systoon.qc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.systoon.qc.business.ConvertJtlToHtml;
import com.systoon.qc.business.ExecuteJmeter;

/**
 * Servlet implementation class PerfermanceTestServlet
 */
@WebServlet("/perfermanceTestServlet")
public class PerfermanceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PerfermanceTestServlet() {
        super();

    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		/**
		 * 获取UI传递的参数
		 */
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		String path = request.getParameter("path");
		String param = request.getParameter("param");
		String vuser = request.getParameter("vuser");
		String url = "http://" + ip +":" + port + path + "?" + param;
		
		/**
		 * 定义Jmeter路径，计划文件，结果文件
		 */
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyymmdd");
		String ctime = simpleDate.format(new Date());		
		String jmeterExe = "jmeter";
		String jmxPlan = "Jmeter-template.jmx";
//		String jmxRealPlan = request.getRealPath(jmxPlan);
		String jmxRealPlan = "D:\\" + jmxPlan;
		String jmxPlanName = path.replaceAll("/", "_") + "_" + vuser + "_" + ctime + ".jmx";
		String jtlName = jmxPlan.substring(0, jmxPlan.indexOf('.')) + "_" + ctime + ".jtl";
//		String jtlResult = "/jtlResult/" + jtlName ;
		String jtlResult = "D:\\jtlResult\\" + jtlName ;
		String htmlResult = "D:\\htmlResult";
		
		System.out.println(request.getRealPath(jtlResult));
		System.out.println();
		System.out.println();

		
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		
		/**
		 * 处理JMX计划文件，替换相关参数
		 */
//		JMXHandle jmx = new JMXHandle();
//		jmx.handleJmxFile(ip, port, path, param, vuser);
//		String generatjmxfilename = jmx.getGeneratjmxfilename();
		
		/**
		 * 后端调用shell ／ 批处理文件  执行 jmeter -n (no GUI)
		 */
		ExecuteJmeter ex = new ExecuteJmeter();
		Process process = ex.executeJmeter(jmeterExe, jmxRealPlan, jtlResult);
		
		/**
		 * 实时打印log至页面
		 */
		request.setAttribute("process", process);
		request.setAttribute("url", url);
		request.getRequestDispatcher("console.jsp").forward(request, response);
		
//		PrintLog pl = new PrintLog();
//		pl.printLog(process,out);
		
		/**
		 * 获取测试结果
		 */

		
		/**
		 * 转换jtl 到 html
		 */
		ConvertJtlToHtml jtoh = new ConvertJtlToHtml();
		jtoh.convert(jtlResult,htmlResult);
		
	
		out.print("报告地址" + jtoh.getReportUrl());
		
		
		
		
		
		System.out.println("ip : " + ip + "\n" + "port : " + port + "\n" + "path : " + path + "\n" + "param : " + param + "\n" + "vuser : " + vuser + "\n");
		System.out.println(url);
	}

}



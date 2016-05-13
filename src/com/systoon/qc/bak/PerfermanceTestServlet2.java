package com.systoon.qc.bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.membership.StaticMember;


import com.systoon.qc.business.ExecuteShell;
import com.systoon.qc.jtlhandle.ConvertXmlToHtml;

/**
 * Servlet implementation class PerfermanceTestServlet
 */
@WebServlet("/perfermanceTestServlet2")
public class PerfermanceTestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PerfermanceTestServlet2() {
        super();

    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String LOADERIP1 = "172.28.16.150"; 
		final String LOADERIP2 = "172.28.16.130"; 
		
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
		String jmeterExe ="jmeter";
//		String jmxPlan = "Jmeter-template.jmx";
		String jmxPlan = "saveOperInfo_ok.jmx";
//		String jmxRealPlan = request.getRealPath(jmxPlan);
		String jmxRealPlan = "/Users/perfermance/JmeterTest/script/" + jmxPlan;
		String jmxPlanName = path.replaceAll("/", "_") + "_" + vuser + "_" + ctime + ".jmx";
		String jtlName = jmxPlan.substring(0, jmxPlan.indexOf('.')) + "_" + ctime + ".jtl";
//		String jtlResult = "/jtlResult/" + jtlName ;
		String jtlResult = "/Users/perfermance/JmeterTest/results/jtl/" + jtlName ;
		String htmlResult = "D:\\htmlResult";
		
		System.out.println(jtlResult);
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
		ExecuteShell ex = new ExecuteShell();
     	Process process = (Process)ex.executeShell(jmeterExe);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String msg = null;
		try {
			while((msg = br.readLine()) != null){
				System.out.println(msg);
//				out.println("<br><br>");
			}
//			br.close();  
	        process.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			br.close();
		}
		
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
		ConvertXmlToHtml jtoh = new ConvertXmlToHtml();

		
		
		
		
		
		System.out.println("ip : " + ip + "\n" + "port : " + port + "\n" + "path : " + path + "\n" + "param : " + param + "\n" + "vuser : " + vuser + "\n");
		System.out.println(url);
	}

}



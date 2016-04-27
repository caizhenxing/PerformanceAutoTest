<%@page import="java.io.PrintWriter"%>
<%@page import="com.systoon.qc.business.JavaShellUtil"%>
<%@page import="org.apache.tomcat.jni.ProcErrorCallback"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jmeter console</title>
<style type="text/css">
			div{
				width:800px;
				height:800px;
				background:;
				margin:10px auto;
				border: 1px solid black;
/* 				border-style:solid; 
				border-width:1px; 
				border-color:#000 */
			}

			span{
				color:red;
				font-size:20px;
				font-stretch:strong;				
			}
		</style>
</head>
<body>
	<div >
		<h2 style="text-align:left">控制台输出</h2>
		<h5 style="text-align:left">请求地址：
			<%
				out.println(request.getAttribute("url"));
			%>
		</h5>
		<p style="text-align:left">
			<%
			
				/* 前端调用shell执行指令，并打印日志 */
			    String jmeterRemoteExecute = (String)request.getAttribute("jmeterRemoteExecute");
			    String executeShellLogFile = (String)request.getAttribute("executeShellLogFile");
			    PrintWriter out1 = response.getWriter();

			
 		        try {
		            new JavaShellUtil().executeShell(jmeterRemoteExecute,executeShellLogFile,out1);
		        } catch (Exception e) {
		            e.printStackTrace();
		        } 

				out.println(jmeterRemoteExecute + executeShellLogFile);
				
				
			%>
		</p>
	</div>

</body>
</html>
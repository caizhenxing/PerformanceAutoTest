<%@page import="java.io.PrintWriter"%>
<%@page import="com.systoon.qc.business.PrintLog"%>
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
				width:80%;
				height:80%;
				background:;
				margin:10px auto;
				border: 1px solid black;
/* 				border-style:solid; 
				border-width:1px; 
				border-color:#000 */
				overflow: auto;
				
			}

			span{
				color:#000;
				font-size:10px;
				font-stretch:strong;				
			}
			
			p{
				color:red;
				font-size:10px;
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
				Process process = (Process)request.getAttribute("process");
				String logFile = (String)request.getAttribute("logFile");
				PrintWriter printWriter = response.getWriter();
				PrintLog pl = new PrintLog();
				pl.printLog(process, out,logFile);
			%>
		
		</p>
	</div>

</body>
</html>
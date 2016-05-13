<%@page import="com.systoon.qc.jtlhandle.ConvertXmlToHtml"%>
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
div {
	width: 80%;
	height: 800px;
	background:;
	margin: 10px auto;
	border: 1px solid black;
	/* 				border-style:solid; 
				border-width:1px; 
				border-color:#000 */
	overflow: auto;
}

span {
	color: #000;
	font-size: 10px;
	font-stretch: strong;
}

p {
	color: red;
	font-size: 10px;
	font-stretch: strong;
}
</style>
<script type="text/javascript">
	//当失去交点以后  让文本框内的文字获得焦点  并且光标移到最后一个字后面
	function myfocus(obj) {
		if (isNav) {
			document.getElementById(obj).focus();// 获取焦点  

		} else {
			setFocus.call(document.getElementById(obj));
		}
	}
	var isNav = (window.navigator.appName.toLowerCase().indexOf("netscape") >= 0);
	var isIE = (window.navigator.appName.toLowerCase().indexOf("microsoft") >= 0);
	
	function setFocus() {
		var range = this.createTextRange(); //建立文本选区
		range.moveStart('character', this.value.length); //选区的起点移到最后去
		range.collapse(true);
		range.select();
	}
</script>

</head>
<body>
	<div>
		<h2 style="text-align: left">控制台输出</h2>

		<p style="text-align: left" id="log" >
			<%
				Process process = (Process) request.getAttribute("process");
				String logFile = (String) request.getAttribute("logFile");
				String jtlResult = (String) request.getAttribute("jtlResult");
				String htmlReport = (String) request.getAttribute("htmlReport");
				String htmlReportName = (String) request.getAttribute("htmlReportName");
				String xslTemple = getServletContext().getInitParameter("xslTemple");

				//			PrintWriter printWriter = response.getWriter();
				PrintLog pl = new PrintLog();
				pl.printLog(process, out, logFile);
			%>
			<br>
			<br>
			<%
				out.println("执行完毕。。。。");
			%>
			<br>
			<br>
			<%
				out.println("正在生成测试结果。。。。");

				if(new ConvertXmlToHtml().convertJtlToHtml(jtlResult, xslTemple, htmlReport)){
			%>		
					<br><br>
				<%
					out.println("测试结果已生成完毕");
				%>
					<a style="font-size: 14px" href="http://qc.systoon.com/<%=htmlReportName%>">测试结果地址</a>
				<%
				}
				%>	
		
			
			<br>
			<br>
			<br>
			<br>

		</p>
	</div>

</body>
</html>
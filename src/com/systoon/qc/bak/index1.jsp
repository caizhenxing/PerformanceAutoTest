<%@page import="java.io.PrintWriter"%>
<%@page import="com.systoon.qc.business.PrintLog"%>
<%@page import="org.apache.tomcat.jni.ProcErrorCallback"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PerformanceTestPlatform</title>
<style type="text/css">
div {
	width: 40%;
	height: 800px;
	background:;
	margin: 10px;
	border: 1px solid black;
	float: left;
	overflow: auto;
}
#log span {
	color: #000;
	font-size: 10px;
	font-stretch: strong;
}

#log p {
	color: red;
	font-size: 10px;
	font-stretch: strong;
}
</style>
</head>
<body>
	<div id=menu>
		<h2 style="text-align: center">自动化性能测试平台</h2>
		<form style="text-align: center" action="perfermanceTestServlet" method="post">
			<br> <br> 
			IP:<input type="text" name="ip"><br><br> 
			port:<input type="text" name="port"><br> <br>
			path:<input type="text" name="path"><br> <br>
			param:<br> <br>
			<textarea rows="10" cols="30" name="param"></textarea>
			<br> <br> Concurrent users: <br>
			<input type="radio" name="vuser" value="50">50 
			<input
				type="radio" name="vuser" value="100">100 <input
				type="radio" name="vuser" value="200">200 <input
				type="radio" name="vuser" value="500">500 <input
				type="radio" name="vuser" value="1000">1000 <br> <br>
			
			<input type="checkbox" name="intereing" value="basket">basket
			<input type="checkbox" name="intereing" value="TV">TV
			<input type="checkbox" name="intereing" value="bike">bike
			<input type="checkbox" name="intereing" value="football">football
			<br><br>
			
			<input type="submit" value="执行">
		</form>
	</div>

	<div id=log>
		<iframe src="console.jsp"></iframe>
	</div>


</body>
</html>
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
	/* overflow: auto; */
}

textarea{
	width:100%;
	height:50px;	
	
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
#submitbtn {
	font-size: 18px;
    text-align: center;
    padding: 3px 10px;
    line-height: normal;
    /*All for IE6&7&8 HACK 居中对齐*/
    *padding: 3px 0 0 0;
    padding-bottom: 1px\0;/*only for IE 8*/
     /*END --All for IE6&7&8 HACK 居中对齐*/
}

#btn{
	border:0px;
	padding:0px 40%;
}




</style>
</head>
<body>
	<div id=menu>
		<h2 style="text-align: center">自动化性能测试平台</h2>
		<form action="perfermanceTestServlet" method="post">
			<h4>HTTP Requtest</h4>
			<fieldset>
			<legend>Web Server:</legend>
			Server Name or IP: <input type="text" name="ip" size="30"><br>
			Port Number: <input type="text" name="port" size="30"><br>
			</fieldset><br>
			
			<fieldset>
			<legend>HTTP Request:</legend>
			Path: <input type="text" name="path" size="30"><br>
			Method: <select name="method">
				<option value="get">GET</option>
				<option value="post">POST</option>
			</select><br>
			Parameters:<br>
			<textarea name="parameters"></textarea>
			</fieldset><br>
			
			<h4>Thread Properties</h4>
			<fieldset>
			<legend>Number of Threads(users)</legend>
			<input type="radio" name="vuser" value="100">100 
			<input type="radio" name="vuser" value="200">200 
			<input type="radio" name="vuser" value="500">500 
			<input type="radio" name="vuser" value="1000">1000 
			</fieldset><br>
			
			<h4>Response Assertion</h4>
			<fieldset>
			<legend>Patterns to Test</legend>
			<textarea name="assertion"></textarea>
			</fieldset>
			<div id="btn">
				<input type="submit" id="submitbtn" value="执行">
			</div>

		</form>
	</div>

	<div id=log>
		<iframe src="console.jsp"></iframe>
	</div>


</body>
</html>
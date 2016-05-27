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
<script type="text/javascript">
	function Change(obj) {
		if (obj == 1) {
			document.getElementById("defineValue").value="";
			document.getElementById("defineValue").type = "text";
		} else {
			document.getElementById("defineValue").type = "hidden";
		}
	}
	
	function assignValue(){
		var value=document.getElementById("defineValue").value;
		document.getElementById("define").value = value;

		
	}
</script>
<style type="text/css">
#menu {
	width: 70%;
	height: 800px;
	background:;
	margin-left: auto;
	margin-right: auto;
	border: 1px solid black;

	/* float: left; */
	/* overflow: auto; */
}

fieldset {
	margin-left: auto;
	margin-right: auto;
	width: 90%
}

textarea {
	width: 100%;
	height: 50px;
}

h4 {
	padding: 0px 30px;
}

#submitbtn {
	font-size: 18px;
	text-align: center;
	padding: 3px 10px;
	line-height: normal;
	/*All for IE6&7&8 HACK 居中对齐*/
	*padding: 3px 0 0 0;
	padding-bottom: 1px\0; /*only for IE 8*/
	/*END --All for IE6&7&8 HACK 居中对齐*/
}

#btn {
	border: 0px;
	padding: 0px 48%;
}
</style>
</head>
<body>
	<div id=menu>
		<h2 style="text-align: center">自动化性能测试平台</h2>
		<form name="param" action="perfermanceTestServlet?methods=add"
			method="post">
			<h4>配置 HTTP 请求</h4>
			<fieldset>
				<legend>Web Server:</legend>
				<table>
					<tr>
						<td>Project Name:</td>
						<td><select name="warname">
								<option >请选择项目名称</option>
								<option >businessAPi</option>
								<option >Author</option>
	
						</select></td>
					</tr>
				
					<tr>
						<td>Server Name or IP:</td>
						<td><input type="text" name="ip" size="30"></td>
					</tr>

					<tr>
						<td>Port Number:</td>
						<td><input type="text" name="port" size="30" value="8081"></td>
					</tr>

				</table>

			</fieldset>
			<br>

			<fieldset>
				<legend>HTTP Request:</legend>
				<table>
					<tr>
						<td>Path:</td>
						<td><input type="text" name="path" size="30" value="/"></td>
					</tr>

					<tr>
						<td>Method:</td>
						<td><select name="method">
								<option value="get">GET</option>
								<option value="post">POST</option>
						</select></td>
					</tr>
				</table>
				Parameters:<br>
				<textarea name="parameters"></textarea>
			</fieldset>
			<br>

			<h4>设置并发用户数</h4>
			<fieldset>
				<legend>Number of Threads(users)</legend>
				<input type="radio" name="vuser" value="10" onclick="Change('0')" />10
				<input type="radio" name="vuser" value="50" onclick="Change('0')" />50
				<input type="radio" name="vuser" value="100" onclick="Change('0')" />100
				<input type="radio" name="vuser" value="200" onclick="Change('0')" />200
				<input type="radio" name="vuser" value="500" onclick="Change('0')" />500
				<input type="radio" name="vuser" value="1000" onclick="Change('0')" />1000
				<input id="define" type="radio" name="vuser" onclick="Change('1')" />自定义<input
					id="defineValue" type="hidden" name="vuser" size="30" onchange="assignValue()">
			</fieldset>
			<br>

			<h4>设置响应断言</h4>
			<fieldset>
				<legend>Patterns to Test</legend>
				<textarea name="assertion"></textarea>
			</fieldset>
			<div id="btn">
				<input type="submit" id="submitbtn" value="执行">
			</div>

		</form>
	</div>



</body>
</html>
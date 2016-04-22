<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PerformanceTestPlatform</title>
</head>
<body>
	<div id = 1>
	<h2 style="text-align:center">自动化性能测试平台</h2>
	<form style="text-align:center" action="perfermanceTestServlet" method="post"><br><br>
		IP:<input type="text" name="ip"><br><br>
		port:<input type="text" name="port"><br><br>
		path:<input type="text" name="path"><br><br>
		param:<br><br>
		<textarea rows="10" cols="30" name="param"></textarea><br><br>
		Concurrent users:
		<input type="radio" name="vuser" value="50">50
		<input type="radio" name="vuser" value="100">100
		<input type="radio" name="vuser" value="200">200
		<input type="radio" name="vuser" value="500">500
		<input type="radio" name="vuser" value="1000">1000 <br><br>
		<input type="submit" value="执行">		
	</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
        function Change(obj)
        {
           if(obj == 1){
            document.getElementById("defineValue").type = "text";
           }else{
            document.getElementById("defineValue").type = "hidden";
           }
        }
    </script>

</head>
<body>

	<form action="">
		<input type="radio" name="vuser" value="10" onclick="Change(0)"/>10 
		<input type="radio" name="vuser" value="50" onclick="Change(0)"/>50 
		<input type="radio" name="vuser" value="100" onclick="Change(0)"/>100 
		<input type="radio" name="vuser" value="200" onclick="Change(0)"/>200 
		<input type="radio" name="vuser" value="500" onclick="Change(0)"/>500 
		<input type="radio" name="vuser" value="1000" onclick="Change(0)"/>1000 
		<input id="define" type="radio" name="vuser" onclick="Change(1)"/>自定义<input id="defineValue" type="hidden" name="vuser" size="30">
		
	</form>
	

</body>
</html>
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
            var s = document.getElementsByName("s");
           
            for (var i = 0; i < s.length; i++)
            {
                if (s[i].id == obj)
                {
                    document.getElementById(s[i].id).style.display = "block";
                }
                else
                    document.getElementById(s[i].id).style.display = "none";
            }
             
        }
    </script>
</head>
<body>
	<div style="width:230px">
        <input name="1" type="radio" onclick="Change('1')" />第一个<input id="1" name="s" style="display:none;float:right" /><br />
        <input name="1" type="radio" onclick="Change('2')" />第二个<input id="2" name="s" style="display:none;float:right" /><br />
        <input name="1" type="radio" onclick="Change('3')" />第三个<input id="3" name="s" style="display:none;float:right" /><br />
        <input name="1" type="radio" onclick="Change('4')" />第四个<input id="4" name="s" style="display:none;float:right" /><br />
        <input name="1" type="radio" onclick="Change('5')" />第五个<input id="5" name="s" style="display:none;float:right" /><br />
    </div>
</body>
</html>
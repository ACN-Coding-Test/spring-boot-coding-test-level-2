<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="h" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h:form action="save" modelAttribute="lg">
<table border="2" bgcolor="yellow">
<tr><td>UID</td><td><h:input path="uid"/></td></tr>
<tr><td>Pass</td><td><h:password path="pass"/></td></tr>
<tr><td><input type="submit"></td>
<td><a href=view>VIEW</a> </td>
</tr>
<a href="openadd">Add</a>



</table>
</h:form>


</body>
</html>
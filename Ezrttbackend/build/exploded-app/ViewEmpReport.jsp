<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.empdemo.EmpeeEndpoint"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	String empeeNum = request.getParameter("employeenum");
	String datetoday = request.getParameter("datetoday");
	
	String toDate = request.getParameter("todate");
%>
<title>Weekly report of <%=empeeNum%> and date <%=datetoday%></title>
</head>
<body>

</body>

</html>
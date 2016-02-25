<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html xmlns="http://www.w3.org/1999/xhtml">

<link href="css/default.css" rel="stylesheet" type="text/css"
	media="all" />

<nav class="navbar navbar-default">
<div id="header" class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Emp Gaurd</a>

	</div>
	<ul class="nav navbar-nav">

		<li>
			<% String guestbookName = request.getParameter("guestbookName");
			UserService userService = UserServiceFactory.getUserService(); 
			User user =	userService.getCurrentUser(); if (user != null) {
			pageContext.setAttribute("user", user); %>

			<p>
				Hello, ${fn:escapeXml(user.nickname)}! (You can <a
					href="<%=userService.createLogoutURL(request.getRequestURI())%>">sign
					out</a>.)
			</p> <% } else { %>
			<p>
				Hello! <a
					href="<%=userService.createLoginURL(request.getRequestURI())%>">Sign
					in</a>
			</p> <% } %>
		</li>
	</ul>
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="#" accesskey="1" title="">Homepage</a></li>
			<li><a href="#" accesskey="2" title="">Our Clients</a></li>
			<li><a href="#" accesskey="3" title="">About Us</a></li>
			<li><a href="#" accesskey="5" title="">Contact Us</a></li>
		</ul>
	</div>
</div>

</nav>
</html>
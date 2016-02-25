<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Work Safety</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900"
	rel="stylesheet" />
<link href="css/default.css" rel="stylesheet" type="text/css"
	media="all" />

<link rel="stylesheet" href="/css/bootstrap.css" />

<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />

<!--[if IE 6]>
    <link href="default_ie6.css" rel="stylesheet" type="text/css"/><![endif]-->

</script>
<script async src="/js/base.js"></script>

<script src="/js/jquery.js"></script>

<script>
	function init() {
		google.appengine.samples.hello.init('//' + window.location.host
				+ '/_ah/api');
	}
</script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>



</script>
<meta name="google-signin-client_id"
	content="301735781390-ohah992i62vjbr6shu24g58dppggjb0e.apps.googleusercontent.com">
</meta>

</head>


<body>
	<div id="header-wrapper">

		<div id="header" class="container">

			<nav class="navbar navbar-default">
			<div id="header" class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Emp Gaurd</a>

				</div>

				<div id="menu">
					<ul>
						<li class="current_page_item"><a href="#" accesskey="1"
							title="">Homepage</a></li>
						<li><a href="#" accesskey="2" title="">Our Clients</a></li>
						<li><a href="#" accesskey="3" title="">About Us</a></li>
						<li><a href="#" accesskey="5" title="">Contact Us</a></li>
					</ul>
					<div style="float: right">
						<ul>

							<li>
								<%
									String guestbookName = request.getParameter("guestbookName");
									UserService userService = UserServiceFactory.getUserService();
									User user = userService.getCurrentUser();
									if (user != null) {
										session.setAttribute("user", user);
								%>
								<div>
									<p>Hello,</p>
									<p id="loggeduser">${fn:escapeXml(user.nickname)}</p>
									 (You can <a
										href="<%=userService.createLogoutURL(request.getRequestURI())%>">sign
										out</a>.)
									</p>
								</div>
								<%
									} else {
								%>
								<p>
									Hello! <a
										href="<%=userService.createLoginURL(request.getRequestURI())%>">Sign
										in</a>
								</p> <%
 	}
 %>
							</li>
						</ul>
					</div>
				</div>


			</div>

			</nav>

		</div>
	</div>
	<%
		if (user == null) {
	%>
	<div id="header-featured"></div>
	<%
		} else {
	%>

	<div class="container" id="board">

		<script src="/js/base.js">
			google.appengine.samples.hello.getallempee();
		</script>

	</div>
	<%
		}
	%>
	<div id="wrapper">
		<div id="featured-wrapper">
			<div id="featured" class="container">
				<div class="column1">
					<span class="icon icon-cogs"></span>

					<div class="title">
						<h2>Maecenas lectus sapien</h2>
					</div>
					<p>In posuere eleifend odio. Quisque semper augue mattis wisi.
						Pellentesque viverra vulputate enim. Aliquam erat volutpat.</p>
				</div>
				<div class="column2">
					<span class="icon icon-legal"></span>

					<div class="title">
						<h2>Praesent scelerisque</h2>
					</div>
					<p>In posuere eleifend odio. Quisque semper augue mattis wisi.
						Pellentesque viverra vulputate enim. Aliquam erat volutpat.</p>
				</div>
				<div class="column3">
					<span class="icon icon-unlock"></span>

					<div class="title">
						<h2>Fusce ultrices fringilla</h2>
					</div>
					<p>In posuere eleifend odio. Quisque semper augue mattis wisi.
						Pellentesque viverra vulputate enim. Aliquam erat volutpat.</p>
				</div>
				<div class="column4">
					<span class="icon icon-wrench"></span>

					<div class="title">
						<h2>Etiam posuere augue</h2>
					</div>
					<p>In posuere eleifend odio. Quisque semper augue mattis wisi.
						Pellentesque viverra vulputate enim. Aliquam erat volutpat.</p>
				</div>
			</div>
		</div>
		<div id="extra" class="container">
			<h2>Maecenas vitae orci vitae tellus feugiat eleifend</h2>
			<span>Quisque dictum integer nisl risus, sagittis convallis,
				rutrum id, congue, and nibh</span>

			<p>
				This is <strong>Breadth</strong>, a free, fully standards-compliant
				CSS template designed by <a href="http://templated.co"
					rel="nofollow">TEMPLATED</a>. The photos in this template are from
				<a href="http://fotogrph.com/"> Fotogrph</a>. This free template is
				released under the <a href="http://templated.co/license">Creative
					Commons Attribution</a> license, so you're pretty much free to do
				whatever you want with it (even use it commercially) provided you
				give us credit for it. Have fun :)
			</p>

			<a href="#" class="button">Etiam posuere</a>
		</div>
	</div>

	<div id="copyright" class="container">
		<p>&copy; Ringen Corp. All rights reserved.</p>
	</div>
</body>
</html>

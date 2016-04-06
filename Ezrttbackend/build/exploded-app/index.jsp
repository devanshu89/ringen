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
<title>Ez Real Time Tracking</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900"
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
		ezrtt.appengine.emper.init('//' + window.location.host
				+ '/_ah/api');
	}
</script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>



</script>
<meta name="google-signin-client_id"
	content="119346301347-jegi7nhdfsp1mcrkimav77kdde6hjp2g.apps.googleusercontent.com">
</meta>

</head>


<body>
	<div id="header-wrapper">

		<div id="header" class="container">

			<nav class="navbar navbar-default">
			<div id="header" class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">EzRTT</a>

				</div>

				<div id="menu">
					<ul>
						<li class="current_page_item"><a href="#" accesskey="1"
							title="">Homepage</a></li>
						<li><a href="#" accesskey="2" title="">Our Clients</a></li>
						<li><a href="#" accesskey="3" title="">About Us</a></li>
						<li><a href="#" accesskey="5" title="">Contact Us</a></li>
						<li>
							<div>
								<p id="loggeduser">Hello!</p>
								<a id="signinButton" href="javascript:void(0);" class="btn"
									onclick="ezrtt.appengine.emper.auth()">Sign in</a>

							</div>
						</li>

					</ul>

				</div>
			</div>
			</nav>

		</div>
	</div>

	<div class="container" id="board"></div>
	<div class="container" id="board-table"></div>
	<div id="copyright" class="container">
		<p>&copy; Ringen Corp. All rights reserved.</p>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.google.appengine.api.users.User"%>

<html>
<link rel="stylesheet" href="/css/bootstrap.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
	<script async src="/js/base.js"></script>
	<div class="container">
		<form onsubmit="return false;">

			<div class="form-group">
				<input type="text" class="form-control" id="firstname"
					required="required" placeholder="First Name*">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" id="lastname"
					required="required" placeholder="Last Name*">
			</div>
			<div class="form-group">
				<input type="email" class="form-control" id="email"
					required="required" placeholder="Email*">
			</div>
			<div class="form-group">
				<input type="number" class="form-control" id="mobile"
					required="required" placeholder="Mobile No*">
			</div>
			<div class="form-group">
				<label class="form-group">In Time</label> <input class="form-group"
					id="intime" name="intime" type="time" value="09:00"
					onchange="google.appengine.samples.hello.setouttime()"> <label
					class="form-group">Out Time</label> <input class="form-group"
					id="outtime" name="outtime" type="time" value="09:00"
					onchange="google.appengine.samples.hello.validateouttime()">

			</div>
			<div class="mbr-buttons mbr-buttons--right" id="addemper">
				<button type="submit" class="mbr-buttons__btn btn btn-lg btn-danger"
					onclick="google.appengine.samples.hello.sendempeedata();">Submit</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		// window.close();
		//ADDED START

		window.onunload = refreshParent;
		function refreshParent() {
			window.opener.location.reload();
			// window.close();
		}
		//ADDED END
	</script>
</body>
</html>
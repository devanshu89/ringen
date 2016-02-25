<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<link rel="stylesheet" href="/css/bootstrap.css" />
<body>

	<div id="empee-add" class="container">
		<button type="button" class="btn btn-primary"
			onclick="google.appengine.samples.hello.addempee()">Add
			Employee</button>
	</div>

	<div id="empee-list" class="container">

		<h2>Employee List</h2>
		<table id="empee-table" class="table table-striped">
			<thead>
				<tr>
					<th id="name">Name</th>
					<th id="status">Status</th>
					<th id="number">Number</th>
					<th id="report">Report</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>

	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login Success</title>
<link href="/eLearningApplication/assets/css/bootstrap-united.css" rel="stylesheet" />

</head>
<body>
	<script src="/eLearningApplication/jquery-1.8.3.js">
		
	</script>

	<script src="/eLearningApplication/bootstrap/js/bootstrap.js">
		
	</script>

	<div class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search">
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/eLearningApplication">Home</a></li>
				<li class="active"><a href="LogOutController">Logout</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Admin Access<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="AdminCourseController">View Course</a></li>
						<li class="divider"></li>
						<li><a href="AdminContactController">View Contact</a></li>
						<li class="divider"></li>
						<li><a href="AdminFeedbackController">View Feedback</a></li>
						<li class="divider"></li>
						<li><a href="AdminUsersController">View Users</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>

	<!-- 
	<legend>Student Enrollment Login Success</legend>
	 -->
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">Student Enrollment success</h3>
		</div>
		<div class="panel-body">
		<div class="alert alert-dismissable alert-success">
              <button type="button" class="close" data-dismiss="alert">×</button>
              <strong>Well done! ${userName}</strong> ${successMessage}
            </div>
		</div>
	</div>
	<div></div>
	<div></div>

</body>
</html>
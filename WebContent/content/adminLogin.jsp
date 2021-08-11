<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/eLearningApplication/assets/css/bootstrap-united.css" rel="stylesheet" />

<style>
.error {
	color: #ff0000;
	font-size: 0.9em;
	font-weight: bold;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<title>Admin Login</title>
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
				<li><a href="signup.jsp">Signup</a></li>
				<li><a href="login.jsp">Login</a></li>
				<li class="active"><a href="adminLogin.jsp">Admin Login</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>

	<div class="container">
		<div class="jumbotron">
			<div>
				<h1>Welcome to Admin Login</h1>
				<p>Login to view, update or delete data</p>
			</div>
		</div>

		<div></div>
	</div>

	<div class="col-lg-6 col-lg-offset-3">
		<div class="well">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<form id="myForm" method="post"
							class="bs-example form-horizontal" action="../AdminController">
							<fieldset>
								<legend>Admin Login Form</legend>
								
								<input type="hidden" name="pageName" value="login">

								<div class="form-group">
									<label for="adminUserNameInput" class="col-lg-3 control-label">E Mail:</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="adminEmail"
											id="adminUserNameInput" placeholder="Admin User Name" />
									</div>
								</div>

								<div class="form-group">
									<label for="adminPasswordInput" class="col-lg-3 control-label">Password</label>
									<div class="col-lg-9">
										<input type="password" class="form-control"
											name="adminPassword" id="adminPasswordInput" placeholder="Admin Password" />
									</div>
								</div>

								<div class="col-lg-9 col-lg-offset-3">

									<button class="btn btn-primary">Login</button>
								</div>

							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Add Course</title>
<link href="/eLearningApplication/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="/eLearningApplication/datepicker/css/datepicker.css" rel="stylesheet" />
<link href="/eLearningApplication/assets/css/bootstrap-united.css" rel="stylesheet" />

<style>
.green {
	font-weight: bold;
	color: green;
}

.message {
	margin-bottom: 10px;
}

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
</head>
<body>

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

	<script src="/eLearningApplication/jquery-1.8.3.js">
		
	</script>

	<script src="/eLearningApplication/bootstrap/js/bootstrap.js">
		
	</script>

	<script src="/eLearningApplication/datepicker/js/bootstrap-datepicker.js">
		
	</script>


	<div class="container">
		<div class="jumbotron">
			<div>
				<h1>Admin Add Course</h1>
			</div>
		</div>

		<div></div>
	</div>

	<c:if test="${not empty message}">
		<div class="message green">${message}</div>
	</c:if>

	<div class="col-lg-6 col-lg-offset-3">
		<div class="well">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<form id="myForm" method="post" class="bs-example form-horizontal"
							action="AdminCourseAddController">
							<fieldset>
								<legend>Course Addition Form</legend>
								
								<div class="form-group">
									<label for="CourseNameInput" class="col-lg-3 control-label">
										Course Name</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="courseName"
											id="CourseNameInput" placeholder="Name" />
									</div>
								</div>


								<div class="form-group">
									<label for="courseDespInput" class="col-lg-3 control-label">
										Course Desp</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name=courseDesp
											id="courseDespInput" placeholder="Course Desp" />
									</div>
								</div>

								<div class="form-group">
									<label for="CourseFeesInput" class="col-lg-3 control-label">Course
										Fees</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="courseFees"
											id="CourseFeesInput" placeholder="Course Fees" />
									</div>
								</div>

								<div class="form-group">
									<label for="ResourceInput" class="col-lg-3 control-label">
										Course Resource</label>
									<div class="col-lg-9">
										<input type="text" class="form-control" name="courseResource"
											id="ResourceInput" placeholder="Resource" onkeyup="success()" />
									</div>
								</div>
								

								<div class="col-lg-9 col-lg-offset-3">

									<button class="btn btn-primary" data-toggle="modal"
										data-target="#themodal">Submit</button>
									<div id="themodal" class="modal fade" data-backdrop="static">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h3>Add Course</h3>
												</div>
												<div class="modal-body">
													<p>Do you want to add this Course?</p>
													<div class="progress progress-striped active">
														<div id="doitprogress" class="progress-bar"></div>
													</div>
												</div>
												<div class="modal-footer">
													<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
													<input type="submit" value="Yes" id="yesbutton"
														class="btn btn-primary" data-loading-text="Saving.."
														data-complete-text="Submit Complete!" disabled>
												</div>
											</div>
										</div>
									</div>

								</div>

							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(function() {
			$('#dateOfBirthInput').datepicker();
		});
	</script>
	
	<script type="text/javascript">
	function success() {
	 if(document.getElementById("CourseNameInput").value==="" && 
			 document.getElementById("courseDespInput").value==="" &&
			 document.getElementById("CourseFeesInput").value==="" &&
			 document.getElementById("ResourceInput").value==="") { 
            document.getElementById('yesbutton').disabled = true; 
        } else { 
            document.getElementById('yesbutton').disabled = false;
        }
    }
	</script>
	
	<script type="text/javascript">
		$(function() {
			var yesButton = $("#yesbutton");
			var progress = $("#doitprogress");

			yesButton.click(function() {
				yesButton.button("loading");

				var counter = 0;
				var countDown = function() {
					counter++;
					if (counter == 11) {
						yesButton.button("complete");
					} else {
						progress.width(counter * 10 + "%");
						setTimeout(countDown, 100);
					}
				};

				setTimeout(countDown, 100);
			});

		});
	</script>


</body>
</html>
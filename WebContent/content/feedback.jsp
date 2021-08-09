<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isThreadSafe="false"  %>
<%@ page isELIgnored="false" %> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Feedback</title>
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
				<li class="active"><a href="signup.jsp">Signup</a></li>
				<li><a href="login.jsp">Login</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Explore<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="contact">Add Contact</a></li>
						<li class="divider"></li>
						<li><a href="enroll">Enroll Course</a></li>
						<li class="divider"></li>
						<li><a href="feedback">Add Feedback</a></li>
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
				<h1>Add Feedbacks</h1>
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
							action="feedback">
							<fieldset>
								<legend>Enter your feedback</legend>								
								<!-- <select name='course'>
								    <option value="${selected}" selected>${selected}</option>
								    <c:forEach items="${listCourse}" var="course">
								        <c:if test="${course != selected}">
								            <option value="${course}">${course}</option>
								        </c:if>
								    </c:forEach>
								</select>  -->
								
								
								<div class="form-group">
									<label for="course" class="col-lg-3 control-label">Choose
										Course</label>
									<div class="col-lg-9">
										<select name="course" id="course">
										  <option value="JAVA">JAVA</option>
										  <option value="C Programming">C Programming</option>
										  <option value="C++ Programming">C++ Programming</option>
										  <option value="General Feedback">General Feedback</option>
										</select>
									</div>
								</div>


								<div class="form-group">
									<label for="FeedbackMessageInput" class="col-lg-3 control-label">
										Message</label>
									<div class="col-lg-9">
										<input required type="text" class="form-control" name="feedbackMessage"
											id="FeedbackMessageInput" placeholder="Enter your feedback" onkeyup="success()"/>
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
													<h3>Add Feedback</h3>
												</div>
												<div class="modal-body">
													<p>Do you want to add this feedback?</p>
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
	 if(document.getElementById("FeedbackMessageInput").value==="") { 
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
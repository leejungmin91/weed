<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<title>BOOTSTRAP CHAT EXAMPLE</title>
<!-- BOOTSTRAP CORE STYLE CSS -->
<link
	href="http://localhost:8080/test/resources/bootstrap/chatting/assets/css/bootstrap.css"
	rel="stylesheet" />

</head>
<body style="font-family: Verdana">
	<div class="container">
		<div class="row " style="padding-top: 40px;">
			<h3 class="text-center">BOOTSTRAP CHAT EXAMPLE</h3>
			<br /> <br />
			<div class="col-md-8">
				<div class="panel panel-info">
					<div class="panel-heading">RECENT CHAT HISTORY</div>
					<div class="panel-body">
						<ul class="media-list">

							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle "
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.png" />
										</a>
										<div class="media-body" id ="message">
											
										
										
										
											message  <br /> <small
												class="text-muted">name | time</small>
											<hr />
										</div>
									</div>

								</div>
							</li>
							<!-- 
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle "
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.gif" />
										</a>
										<div class="media-body">
											Donec sit amet ligula enim. Duis vel condimentum massa. Donec
											sit amet ligula enim. Duis vel condimentum massa.Donec sit
											amet ligula enim. Duis vel condimentum massa. Donec sit amet
											ligula enim. Duis vel condimentum massa. <br /> <small
												class="text-muted">Jhon Rexa | 23rd June at 5:00pm</small>
											<hr />
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle "
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.png" />
										</a>
										<div class="media-body">
											Donec sit amet ligula enim. Duis vel condimentum massa. Donec
											sit amet ligula enim. Duis vel condimentum massa.Donec sit
											amet ligula enim. Duis vel condimentum massa. Donec sit amet
											ligula enim. Duis vel condimentum massa. <br /> <small
												class="text-muted">Alex Deo | 23rd June at 5:00pm</small>
											<hr />
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle "
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.gif" />
										</a>
										<div class="media-body">
											Donec sit amet ligula enim. Duis vel condimentum massa. Donec
											sit amet ligula enim. Duis vel condimentum massa.Donec sit
											amet ligula enim. Duis vel condimentum massa. Donec sit amet
											ligula enim. Duis vel condimentum massa. <br /> <small
												class="text-muted">Jhon Rexa | 23rd June at 5:00pm</small>
											<hr />
										</div>
									</div>

								</div>
							</li>-->
						</ul>
					</div>
					<div class="panel-footer">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Enter Message" name="chat" /> <span
								class="input-group-btn">
								<button class="btn btn-info" type="button" id="sendBtn">SEND</button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">ONLINE USERS</div>
					<div class="panel-body">
						<!-- <ul class="media-list">

							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" style="max-height: 40px;"
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.png" />
										</a>
										<div class="media-body">
											<h5>Alex Deo | User</h5>

											<small class="text-muted">Active From 3 hours</small>
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" style="max-height: 40px;"
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.gif" />
										</a>
										<div class="media-body">
											<h5>Jhon Rexa | User</h5>

											<small class="text-muted">Active From 3 hours</small>
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" style="max-height: 40px;"
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.png" />
										</a>
										<div class="media-body">
											<h5>Alex Deo | User</h5>

											<small class="text-muted">Active From 3 hours</small>
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" style="max-height: 40px;"
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.gif" />
										</a>
										<div class="media-body">
											<h5>Jhon Rexa | User</h5>

											<small class="text-muted">Active From 3 hours</small>
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" style="max-height: 40px;"
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.png" />
										</a>
										<div class="media-body">
											<h5>Alex Deo | User</h5>

											<small class="text-muted">Active From 3 hours</small>
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" style="max-height: 40px;"
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.gif" />
										</a>
										<div class="media-body">
											<h5>Jhon Rexa | User</h5>

											<small class="text-muted">Active From 3 hours</small>
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" style="max-height: 40px;"
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.png" />
										</a>
										<div class="media-body">
											<h5>Alex Deo | User</h5>

											<small class="text-muted">Active From 3 hours</small>
										</div>
									</div>

								</div>
							</li>
							<li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle" style="max-height: 40px;"
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.gif" />
										</a>
										<div class="media-body">
											<h5>Jhon Rexa | User</h5>

											<small class="text-muted">Active From 3 hours</small>
										</div>
									</div>

								</div>
							</li>
						</ul> -->
					</div>
				</div>

			</div>
		</div>
	</div>
	<script
		src="http://localhost:8080/test/resources/bootstrap/home/js/jquery-1.11.1.js"></script>
	<script src="http://localhost:8080/test/resources/js/socket.io.js"></script>
	<script>
		$(document).ready(function() {

			var socket = io.connect("http://localhost:9090");

			socket.on('response', function(msg) {

				console.log("receive message :: " + msg.msg);
				$('#message').text(msg.msg);

			});

			$("#sendBtn").bind("click", function() {

				var msg = $("input[name=chat]").val();

				socket.emit('msg', {
					msg : msg
				});

			});

		});
	</script>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session="true"%>
<%@ page import="java.net.URLEncoder "%>
<%@page import="org.apache.log4j.*"%>
<%@ page import="org.apache.http.impl.client.BasicResponseHandler"%>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient"%>
<%@ page import="org.apache.http.client.methods.HttpGet"%>
<%@ page import="org.apache.http.client.methods.HttpPost"%>
<%@ page import="java.util.HashMap"%>
<%!static Logger logger = Logger.getLogger("chat.jsp"); //log4j를 위해%>

<%
	HashMap<String , String> map = new HashMap<String, String>();
	map = (HashMap)session.getAttribute("session_map");
	
	String session_fb_id;
	String session_email;
	String session_ko_name;
	String session_gender;

	logger.info(map);
	
	if (map == null || session.equals("")) {
		
		//response.sendRedirect("home.do");
		session_fb_id = null;
		session_email = null;
		session_ko_name = null;
		session_gender = null;
	}
	else{
	session.setMaxInactiveInterval(60 * 60);	
	session_fb_id = map.get("fb_id");
	session_email = map.get("email");
	session_ko_name = map.get("ko_name");
	session_gender = map.get("gender");
	//session.setAttribute("session_map",map);
	
	}
%>
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
<title>JABCHO</title>
<!-- BOOTSTRAP CORE STYLE CSS -->
<link
	href="http://localhost:8080/test/resources/bootstrap/chatting/assets/css/bootstrap.css"
	rel="stylesheet" />

</head>
<body style="font-family: Verdana">
	<div class="container">
		<div class="row " style="padding-top: 40px;">
			<h3 class="text-center">JABCHo</h3>
			<br /> <br />
			<div class="col-md-8">
				<div class="panel panel-info">
					<div class="panel-heading">RECENT CHAT HISTORY</div>
					<div class="panel-body">
						<ul class="media-list">

							<li class="media" id="message"></li>




							<!-- <li class="media">

								<div class="media-body">

									<div class="media">
										<a class="pull-left" href="#"> <img
											class="media-object img-circle "
											src="http://localhost:8080/test/resources/bootstrap/chatting/assets/img/user.gif" />
										</a>
										<div class="media-body">
											Donec sit amet ligula enim. Duis vel condimentum massa. Donec
											sit amet . <br /> <small
												class="text-muted">Jhon Rexa | 23rd June at 5:00pm</small>
											<hr />
										</div>
									</div>

								</div>
							</li> -->
						</ul>
					</div>
					<div class="panel-footer">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Enter Message" name="chat" id="chat" /> <span
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
	<!-- <script src="http://localhost:8080/test/resources/js/chat.js"></script> -->
	<script>
		$(document)
				.ready(
						function() {
							
							var socket = io.connect("http://localhost:8888");
							
			                 var roomname = "${map.get('session_team')}";  
							 var username = "${map.get('session_ko_name')}";
							 var roomPK = "${map.get('session_team_PK')}";
			                 
			                 
			                // Server 로 접속 유저아이디 와 접속한 채팅방 이름을 전송 합니다.
			                 socket.emit('join', { 
			                	 //'username': username, 
			                	 'roomname': roomname 
			                	 });
											
							

							socket
									.on(
											'connection',
											function(msg) {
												
												/* console.log("receive message :: "
														+ msg.username + " : " + msg.msg); */
												$('#message')
														.append(
																'<div class="media-body"><div class="media"><a class="pull-left" href="#"> <img	class="media-object img-circle " src='+msg.userpic+'/></a><div class="media-body">'
																		+ msg.msg
																		+ '<br/><small	class="text-muted"> '
																		+ msg.username
																		+ "\t\t\t\t\t\t\t\t\t\t|\t\t\t\t\t\t\t\t\t\t"
																		+ msg.reg_date_time
																		+ '</small></div></div></div><hr/>');

											});
							$('#chat').keypress(function(e) {
								var key = e.which;
								if (key == 13) // the enter key code
								{
									$('#sendBtn').click();
									return false;
								}
							});

							$("#sendBtn")
									.bind(
											"click",
											function() {

												var msg = $("input[name=chat]")
														.val();
												$("input[name=chat]").val("");
												
												var time = new Date();
												var totaltime = time.toString();
												var date = time.toLocaleString();
												socket
														.emit(
																'msg',
																{
																	userpic : "https://graph.facebook.com/${map.get('session_fb_id')}/picture",
																	username : username,
																	msg : msg ,
																	reg_date_time : date,
																	reg_date_total : totaltime,
																	roomname : roomname,
																	roomPK : roomPK
																});

											});

						});
	</script>
</body>
</html>

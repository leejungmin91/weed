<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
	<%@ page import="org.apache.http.impl.client.BasicResponseHandler"%>
	<%@ page import="org.apache.http.impl.client.DefaultHttpClient"%>
	<%@ page import="org.apache.http.client.methods.HttpGet"%>
	<%@ page import="org.apache.http.client.methods.HttpPost"%>
	<%@ page import="com.restfb.exception.FacebookOAuthException"%>
	<%@ page import="com.restfb.Parameter"%>
	<%@ page import="com.restfb.types.FacebookType"%>
	<%@ page import="com.restfb.DefaultFacebookClient"%>
	<%@ page import="com.restfb.FacebookClient"%>
	<%@ page import="com.restfb.types.*"%>
	<%@ page import="java.io.*"%>
	<%@ page import="org.json.*"%>
	<%@ page import="java.text.ParseException"%>
	<%@ page import="java.util.HashMap"%>
	<%@ page import="java.nio.charset.StandardCharsets"%>
	<%@page import="org.apache.log4j.*"%>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />


<link rel="stylesheet" href="http://localhost:8080/test/resources/testlogin/css/reset.css">

<link rel='stylesheet prefetch'
	href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<link rel="stylesheet" href="http://localhost:8080/test/resources/testlogin/css/style.css">




</head>

<body>


	
	<!-- Form Module-->
	<div class="module form-module">
		<div class="toggle">
			<i class="fa fa-times fa-pencil"></i>
			<div class="tooltip">Click Me</div>
		</div>
		<div class="form">
			<h2>Login to your account</h2>
			<form name="sendForm" method="post">
				<input type="text" placeholder="fb_id" id="fb_id" name = "fb_id"/> <input type="text" id="name" name="name"
					placeholder="name" /><input type="hidden" id="gender" name ="gender"
					value="남" /><input type="hidden" id="roomPK" name ="roomPK"
					value="tteam" />
				<button onClick="login()">Login</button>
			</form>
		</div>
		<div class="form">
			<h2>Create an account</h2>
			<form>
				<input type="text" placeholder="Username" /> <input type="password"
					placeholder="Password" /> <input type="email"
					placeholder="Email Address" /> <input type="tel"
					placeholder="Phone Number" />
				<button>Register</button>
			</form>
		</div>
		
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="http://localhost:8080/test/resources/testlogin/js/index.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function login() {
			document.sendForm.action = "http://localhost:8080/test/team.do";
			document.sendForm.submit();
		}
	</script>


</body>
</html>

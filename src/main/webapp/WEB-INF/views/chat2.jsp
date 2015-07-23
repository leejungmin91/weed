<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>

<script
		src="http://localhost:8080/test/resources/bootstrap/home/js/jquery-1.11.1.js"></script>

<script src="http://localhost:8080/test/resources/js/socket.io.js"></script>

<script>
	$(document).ready(function() {

		var socket = io.connect("http://localhost:9090");

		socket.on('response', function(msg) {

			console.log("receive message :: " + msg.msg);

		});

		$("#sendBtn").bind("click", function() {

			var msg = $("input[name=chat]").val();

			socket.emit('msg', {
				msg : msg
			});

		});

	});
</script>

</head>

<body>

	<h1>Main</h1>

	<input type="text" name="chat" />

	<input type="button" value="send" id="sendBtn" />

</body>

</html>s
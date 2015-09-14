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
<link
	href="http://localhost:8080/test/resources/bootstrap/chatting/assets/css/file.css"
	rel="stylesheet" />

</head>
<body style="font-family: Verdana">
	<div class="container">
		<div class="row " style="padding-top: 40px;">
			<h3 class="text-center">JABCHO</h3>
			<br /> <br />
			<div class="col-md-8">
				<div class="panel panel-info">
					<div class="panel-heading">RECENT CHAT HISTORY</div>
					<div class="panel-body" id="dragAndDropDiv">
						<ul class="media-list">

							<li class="media" id="message"></li>

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
					<div class="panel-heading">File UP</div>
					<div class="panel-body" id="dragAndDropResult"/>
				</div>
			</div>

		</div>
	</div>
	</div>
	<script
		src="http://localhost:8080/test/resources/bootstrap/home/js/jquery-1.11.1.js"></script>
	<script src="http://localhost:8080/test/resources/js/socket.io.js"></script>

	<script>
		$(document)
				.ready(
						function() {
							var objDragAndDrop = $("#dragAndDropDiv");
							var dragAndDropResult = $("#dragAndDropResult");
							var roomname = "${map.get('session_team')}";
							var fb_id = "${map.get('session_fb_id')}";
							var roomPK = "${map.get('session_team_PK')}";
							var username = "${map.get('session_ko_name')}";
							var socket = io.connect("http://localhost:8888");

							$(document).on(
									"dragenter",
									"#dragAndDropDiv",
									function(e) {
										e.stopPropagation();
										e.preventDefault();
										$(this).css('border',
												'2px solid #0B85A1');
									});
							$(document).on("dragover", "#dragAndDropDiv",
									function(e) {
										e.stopPropagation();
										e.preventDefault();
									});
							$(document)
									.on(
											"drop",
											"#dragAndDropDiv",
											function(e) {

												$(this).css('border',
														'2px dotted #0B85A1');
												e.preventDefault();
												var files = e.originalEvent.dataTransfer.files;

												handleFileUpload(files,
														dragAndDropResult);
												$(this).css('border', '');
											});

							$(document).on('dragenter', function(e) {
								e.stopPropagation();
								e.preventDefault();
							});
							$(document).on(
									'dragover',
									function(e) {
										e.stopPropagation();
										e.preventDefault();
										objDragAndDrop.css('border',
												'2px dotted #0B85A1');
									});
							$(document).on('drop', function(e) {
								e.stopPropagation();
								e.preventDefault();
							});

							function handleFileUpload(files, obj) {
								for (var i = 0; i < files.length; i++) {
									var fd = new FormData();
									fd.append('file', files[i]);
									console.log(files[i]);
									 var status = new createStatusbar(obj); //Using this we can set progress.
									status.setFileNameSizeReg_date(files[i].name,
										files[i].size,"\t 전송중..");  
									sendFileToServer(fd,  status, 
									files[i].name, files[i].size);

								}
							}

							var rowCount = 0;
							function createStatusbar(obj) {

								rowCount++;
								/* var row = "odd";
								if (rowCount % 2 == 0)
									row = "even"; */
								this.statusbar = $("<div class='statusbar' style='cursor:pointer; cursor:hand;'></div>");
								this.filename = $(
										"<div class='filename'></div>")
										.appendTo(this.statusbar);
								this.size = $("<div class='filesize'></div>")
										.appendTo(this.statusbar);
								this.progressBar = $(
										"<div class='progressBar'><div></div></div>")
										.appendTo(this.statusbar);
								this.reg_date = $("<div class='reg_date'></div>")
								.appendTo(this.statusbar);
								this.abort = $("<div class='abort'>중지</div>")
										.appendTo(this.statusbar);

								obj.after(this.statusbar);

								this.setFileNameSizeReg_date = function(name, size, rd) {
									var sizeStr = "";
									var sizeKB = size / 1024;
									if (parseInt(sizeKB) > 1024) {
										var sizeMB = sizeKB / 1024;
										sizeStr = sizeMB.toFixed(2) + " MB";
									} else {
										sizeStr = sizeKB.toFixed(2) + " KB";
									}

									this.filename.html(name);
									this.size.html(sizeStr);
									this.reg_date.html(rd);
								}

								this.setProgress = function(progress) {
									var progressBarWidth = progress
											* this.progressBar.width() / 100;
									this.progressBar.find('div').animate({
										width : progressBarWidth
									}, 10).html(progress + "% ");
									if (parseInt(progress) >= 100) {
										this.abort.hide();
									}
								}

								this.setAbort = function(jqxhr) {
									var sb = this.statusbar;
									this.abort.click(function() {
										jqxhr.abort();
										sb.hide();
									});
								}
								this.sethide = function(){
									var sb = this.statusbar;
									sb.hide();
								}
								
								this.setClick = function(fileindex){
									this.statusbar.click(function(){
										console.log(fileindex);
										
										document.location.href="http://localhost:8080/test/filedown.do?fileName="+fileindex+"";
									})
								}

							}

							function sendFileToServer(formData,  status, 
							filename, filesize) {
								formData.append('roomPK',
										"${map.get('session_team_PK')}");
								var uploadURL = "http://localhost:8080/test/fileupload.do"; //Upload URL
								var jqXHR = $
										.ajax({
											xhr : function() {
												var xhrobj = $.ajaxSettings
														.xhr();
												if (xhrobj.upload) {
													xhrobj.upload
															.addEventListener(
																	'progress',
																	function(
																			event) {
																		var percent = 0;
																		var position = event.loaded
																				|| event.position;
																		var total = event.total;
																		if (event.lengthComputable) {
																			percent = Math
																					.ceil(position
																							/ total
																							* 100);
																		}

																		//Set progress
																		status.setProgress(percent);
																	}, false);
												}
												return xhrobj;
											},
											url : uploadURL,
											type : "POST",
											contentType : false,
											processData : false,
											cache : false,
											data : formData,
											success : function(hm) {
												//status.setProgress(100);
												console.log("filename ==>"
														+ filename);
												console.log("filesize ==>"
														+ filesize);
												var time = new Date();
												var totaltime = time.toString();
												var date = time
														.toLocaleString();
												console.log("ran_filename===>"+hm.ran_filename);
												socket.emit('filestatus', {
													//'username': username, 
													'roomPK' : roomPK,
													'filename' : filename,
													'filesize' : filesize,
													'reg_date' : date,
													'ran_filename' : hm.ran_filename
													//'status_percent' : 100

												});
												status.sethide();
												//$("#status1").append("File upload Done<br>");           
											}
										});

								status.setAbort(jqXHR);
							}


							// Server 로 접속 유저아이디 와 접속한 채팅방 이름을 전송 합니다.
							socket.emit('join', {
								//'username': username, 
								'roomPK' : roomPK,
								'roomname' : roomname

							});
							

							socket.on('filestatus', function(filestatus) {
								var status = new createStatusbar(dragAndDropResult);
								status.setFileNameSizeReg_date(filestatus.filename,
										filestatus.filesize, filestatus.reg_date);
								status.setProgress(100);
								status.setClick(filestatus.fileindex);
								
								console.log('success');
							});
							
							//join filedata load
							socket.on('filedata', function(filedata) {
								var status = new createStatusbar(dragAndDropResult);
								status.setFileNameSizeReg_date(filedata.filename,
										filedata.filesize, filedata.reg_date);
								status.setProgress(100);
								status.setClick(filedata.fileindex);
								
								console.log('success');
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
							socket
									.on(
											'chatdata',
											function(chatdata) {
												$('#message')
														.append(
																'<div class="media-body"><div class="media"><a class="pull-left" href="#"> <img	class="media-object img-circle " src='+chatdata.userpic+'/></a><div class="media-body">'
																		+ chatdata.msg
																		+ '<br/><small	class="text-muted"> '
																		+ chatdata.username
																		+ "\t\t\t\t\t\t\t\t\t\t|\t\t\t\t\t\t\t\t\t\t"
																		+ chatdata.reg_date_time
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
												var date = time
														.toLocaleString();

												socket
														.emit(
																'msg',
																{
																	userpic : "https://graph.facebook.com/${map.get('session_fb_id')}/picture",
																	fb_id : fb_id,
																	username : username,
																	msg : msg,
																	reg_date_time : date,
																	reg_date_total : totaltime,
																	roomname : roomname,
																	roomPK : roomPK
																});

											});

						});
		//filedown?fileName=${ list.getFileName() }
	</script>
</body>
</html>

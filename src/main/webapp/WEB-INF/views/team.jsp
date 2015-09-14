<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page session="true"%>
<%@ page import="java.net.URLEncoder "%>
<%@ page import="org.apache.log4j.*"%>
<%@ page import="org.apache.http.impl.client.BasicResponseHandler"%>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient"%>
<%@ page import="org.apache.http.client.methods.HttpGet"%>
<%@ page import="org.apache.http.client.methods.HttpPost"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.springframework.beans.factory.annotation.Value"%>
<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page import="com.mytest.DTO.RoomUserDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page
	import="org.springframework.web.context.support.SpringBeanAutowiringSupport"%>
<%@ page import="com.mytest.DAO.RoomUserDAOImpl"%>
<%@ page import="com.mytest.DAO.UserDAOImpl"%>
<%@ page import="com.mytest.DAO.RoomDAOImpl"%>
<%!static Logger logger = Logger.getLogger("team.jsp"); //log4j를 위해%>
<%!
    public void jspInit() 
    {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        getServletContext());
    }

    @Autowired
    private RoomUserDAOImpl roomuserDAOImpl;
    @Autowired
    private RoomDAOImpl roomDAOImpl;
    @Autowired
    private UserDAOImpl userDAOImpl;
%>

<%
	String accessToken = (String) session.getAttribute("fbtoken");
	String logoutURL = "https://www.facebook.com/logout.php?next="
	+ URLEncoder.encode("http://localhost:8080/test/home.do",
	"UTF-8") + "&access_token=" + accessToken;
	HashMap<String , String> map = new HashMap<String, String>();
	map = (HashMap)session.getAttribute("session_map");
	String session_fb_id = map.get("fb_id");
	String session_ko_name = map.get("ko_name");
	String session_gender = map.get("gender");
	List<RoomUserDTO> roomlist = new ArrayList<RoomUserDTO>(); 
	List<RoomUserDTO> roomlist_user = new ArrayList<RoomUserDTO>(); 

	
	String roomuserfb_id=null;
	String roomuserPK=null;
	String roomusername=null;
	if(roomuserDAOImpl.getRoomUserDAOFb_id(session_fb_id)==null){
		System.out.println("roomuserDAO null");
	}
	else{
		roomlist = roomuserDAOImpl.getRoomUsers(session_fb_id);
		/* roomuserfb_id = roomuserDAOImpl.getRoomUserDAOFb_id(session_fb_id).getRoomUserFb_id();
		roomuserPK = roomuserDAOImpl.getRoomUserDAOPK(session_fb_id).getRoomUserPK();
		roomusername = roomuserDAOImpl.getRoomUserDAOName(session_fb_id).getRoomUserName(); */
	}

%>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>잡초</title>
<!-- BOOTSTRAP STYLES-->
<link
	href="http://localhost:8080/test/resources/bootstrap/main/css/bootstrap.css"
	rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link
	href="http://localhost:8080/test/resources/bootstrap/main/css/font-awesome.css"
	rel="stylesheet" />
<!-- MORRIS CHART STYLES-->
<link
	href="http://localhost:8080/test/resources/bootstrap/main/js/morris/morris-0.4.3.min.css"
	rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link
	href="http://localhost:8080/test/resources/bootstrap/main/css/custom.css"
	rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link href='http://localhost:8080/test/resources/pop/popModal.min.css'
	rel='stylesheet' type='text/css' />
<link href='http://localhost:8080/test/resources/plain/plain.css'
	rel='stylesheet' />
<link
	href='http://localhost:8080/test/resources/treetable/css/jquery.treetable.css'
	rel='stylesheet' />
<link
	href='http://localhost:8080/test/resources/treetable/css/jquery.treetable.theme.default.css'
	rel='stylesheet' />

</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">잡초</a>
			</div>

			<div
				style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">

				<td>${sessionScope.session_map.ko_name}</td> &nbsp;님 환영합니다!&nbsp; <a href="#"
					class="btn btn-danger square-btn-adjust" onClick="ulogout()">Logout</a>
			</div>
		</nav>
		<!-- /. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li class="text-center">
						<!--  <img
						src="http://localhost:8080/test/resources/bootstrap/main/img/find_user.png"
						class="user-image img-responsive" />--> <img
						src="https://graph.facebook.com/${sessionScope.session_map.fb_id}/picture"
						class="user-image img-responsive" height="60" width="60" />
					</li>


					<li><a href="#"><i class="fa fa-dashboard fa-3x"></i>
							Profile</a></li>
					<li><a href="#" class="active-menu"><i
							class="fa fa-users fa-3x"></i> Team</a></li>
					<li><a href="#" onClick="chat_admin()"><i
							class="fa fa-users fa-3x"></i> Chat</a></li>

					<!-- <li><a href="#" onClick="chat()"><i
							class="fa fa-users fa-3x"></i> Chat</a></li> -->

				</ul>

			</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<%-- <div class="col-md-12">
						<h3>File UPLOAD</h3>
						<form action="fileup.do" method="post"
							enctype="multipart/form-data">
							<input type="file" name="uploadfile" required="required">
							<input type="submit" value="작성">

						</form>

						</br>
						<h3>File DOWNLOAD LIST</h3>
						</br>
						<fieldset>
							<table style="border: 1px; width: 400px;">
								<tr>
									<th style="width: 100px"></th>
									<th style="width: 300px">파일명</th>
									<th style="width: 100px">파일사이즈</th>
								</tr>

								<c:if test="${ !empty list }">
									<c:forEach items="${ list }" var="list">
										<tr>
											<td width="100px"></td>
											<td width="300px"><a
												href="filedown.do?fileName=${ list.getFileName() }">${ list.getFileName() }</a></td>
											<td width="100px">${ list.getFileSize() }</td>
										</tr>
									</c:forEach>
								</c:if>

								<c:if test="${ empty list }">
									<tr>
										<td colspan="5">등록된 게시물이 없습니다.</td>
									</tr>
								</c:if>
							</table>
						</fieldset>
						</br> </br>

					</div> --%>
				</div>
				<!-- /. ROW  -->
				
				 <a href="#"> <i class="fa fa-plus-circle" id="team_plus">팀구성하기</i>
					<div id="demo" style="font-family: Verdana">
						<div class="plainmodal-close"></div>
						팀구성하기
						<hr />
						팀 이름 : <br /> <br />
						<form name="sendFormRoomCreater" method="post">
							<input type="hidden" name="fb_id" id="fb_id"
								value="${sessionScope.session_map.fb_id}"><input type="text"
								class="form-control" placeholder="Team name" name="roomname"
								id="roomname" /> <br /> <br /> 팀 도메인 : <br /> <br /> <input
								type="text" class="form-control" placeholder="Team name domain"
								name="roomPK" id="roomPK" /> <br />
							<center>
								<span class="input-group-btn">
									<button class="btn btn-info" type="button" id="sendBtn"
										onClick="teamcreater()">확인</button>
									<button class="btn btn-default" type="button" id="cancelBtn">취소</button>
								</span>
							</center>
							<br />
						</form>
					</div>
				</a> 
				<hr />
				<%for(int roomnum = 0; roomnum<roomlist.size(); roomnum++){ %>
				<table class="table table-striped table-hover" width="744" id="table_project">
					<thead>
						<tr>
							<th></th>
							<th>팀 이름</th>
							<!-- <th>개설자</th> -->
							<th>팀 도메인</th>
						</tr>
					</thead>
					<tbody>
						<tr id = "tr_project" onclick="javascript:clickProjectEvent('<%=roomlist.get(roomnum).getRoomUserPK()%>')">
							<td><i class="fa fa-users fa-3x"></i></td>
							<td><%=roomlist.get(roomnum).getRoomUserName() %></td>
							<%-- <td><%=userDAOImpl.getUserDAOName(roomlist.get(roomnum).getRoomUserFb_id()).getUserName() %></td> --%>
							<td><%=roomlist.get(roomnum).getRoomUserPK() %></td>
						</tr>
					</tbody>
				</table>
				<%} %>
				<hr />


				<!-- <div class="row">
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="panel panel-back noti-box">
							<span class="icon-box bg-color-red set-icon"> <i
								class="fa fa-envelope-o"></i>
							</span>
							<div class="text-box">
								<p class="main-text">120 New</p>
								<p class="text-muted">Messages</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="panel panel-back noti-box">
							<span class="icon-box bg-color-green set-icon"> <i
								class="fa fa-bars"></i>
							</span>
							<div class="text-box">
								<p class="main-text">30 Tasks</p>
								<p class="text-muted">Remaining</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="panel panel-back noti-box">
							<span class="icon-box bg-color-blue set-icon"> <i
								class="fa fa-bell-o"></i>
							</span>
							<div class="text-box">
								<p class="main-text">240 New</p>
								<p class="text-muted">Notifications</p>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="panel panel-back noti-box">
							<span class="icon-box bg-color-brown set-icon"> <i
								class="fa fa-rocket"></i>
							</span>
							<div class="text-box">
								<p class="main-text">3 Orders</p>
								<p class="text-muted">Pending</p>
							</div>
						</div>
					</div>
				</div> -->
			</div>

		</div>

	</div>
	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<form name="sendForm" method="post">
					<input type="hidden" name="session_fb_id" id="session_fb_id"
						value="${sessionScope.session_map.fb_id}">
						<input type="hidden"
						class="form-control"  name="session_team"
						id="session_team" value="" /><input type="hidden"
						class="form-control"  name="session_ko_name"
						id="session_ko_name" value="${sessionScope.session_map.ko_name}" /><input
						type="hidden" class="form-control" placeholder="Team name domain"
						name="session_team_PK" id="session_team_PK" value="" />
						<input
						type="hidden" class="form-control" placeholder="Team name domain"
						name="logoutURL" id="logoutURL" value="<%=logoutURL %>" /> <br />
				</form>



	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script
		src="http://localhost:8080/test/resources/bootstrap/main/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->

	<script
		src="http://localhost:8080/test/resources/bootstrap/main/js/jquery.metisMenu.js"></script>
	<script src="http://localhost:8080/test/resources/pop/popModal.min.js"></script>
	<script
		src="http://localhost:8080/test/resources/plain/jquery.plainmodal.js"></script>
	<script
		src="http://localhost:8080/test/resources/treetable/jquery.treetable.js"></script>
	<script src="http://localhost:8080/test/resources/treetable/grid.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script language="javascript"
		src="http://connect.facebook.net/ko_KR/all.js"></script>
	<script type="text/javascript">
	function clickProjectEvent(roomPK){
		var fb_id = ${sessionScope.session_map.fb_id};
		if(fb_id=="1234"){
			chat_admin();
		} 
		chat(roomPK);
		
	}
</script>
	<script type="text/javascript">
	function ulogout() {
		document.sendForm.action = "http://localhost:8080/test/logout.do";
		document.sendForm.submit();
		}
	</script>

	<script type="text/javascript">
		function chat(roomPK) {
			document.sendForm.session_team_PK.value = roomPK;
			document.sendForm.action = "http://localhost:8080/test/chat/"+roomPK+".do";
			document.sendForm.submit();
		
		}
		function chat_admin() {
			document.sendForm.session_team_PK.value = "admin";
			document.sendForm.action = "http://localhost:8080/test/chat/admin.do";
			document.sendForm.submit();
		}
		function teamcreater(){
			document.sendFormRoomCreater.action = "http://localhost:8080/test/teamcreater.do";
			document.sendFormRoomCreater.submit();
		}
	</script>

	<script>
		$('#team_plus').click(function() {
			$('#demo').plainModal('open');
		});

		$('#session_team').keypress(function(e) {
			var key = e.which;
			if (key == 13) // the enter key code
			{
				$('#sendBtn').click();
				return false;
			}
		});
		$('#session_team_PK').keypress(function(e) {
			var key = e.which;
			if (key == 13) // the enter key code
			{
				$('#sendBtn').click();
				return false;
			}
		});
	</script>
</body>
</html>

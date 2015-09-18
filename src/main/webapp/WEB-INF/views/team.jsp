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
	List<RoomUserDTO> all_roomlist = new ArrayList<RoomUserDTO>();

	
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
	if(roomuserDAOImpl.getRooms()==null){
		System.out.println("all_roomuserDAO null");
	}
	else{
		all_roomlist = roomuserDAOImpl.getRooms();
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
				style="color: white; padding: 15px 50px 5px 50px; float: left; font-size: 16px;">

				<td>${sessionScope.session_map.ko_name}</td> &nbsp;님 환영합니다!&nbsp; 
			</div >
			<span style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;" onClick="Message()" class="dropdown">
			<i class="fa fa-comments  fa-2x" data-toggle="dropdown"><span class="caret"></span></i>
			<ul class="dropdown-menu" role="menu">
								<div style="width:350px;padding:5px;">  
             <a href=""><img src="" width="50px" height="50px"/></a>&nbsp;<a href=""> has sent you a message!</a>  
             <a href="" onclick="" style="float:right;"><i class="icon-trash"></i></a>  
           </div>  

							</ul>
			
			
			</span>
			<span
				style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
			<a
					href="#" class="btn btn-danger square-btn-adjust"
					onClick="ulogout()">Logout</a>
					</span>
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

				<a href="#"> <i class="fa fa-plus-circle" id="team_plus">&nbsp;&nbsp;팀구성하기</i>
				</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"> <i
					class="fa fa-search" id="team_search">&nbsp;&nbsp;팀검색하기</i>
				</a>
				<hr />




				<div class="panel panel-primary filterable">
					<div class="panel-heading">
						<h3 class="panel-title">내 팀 목록</h3>

					</div>
					<table class="table table-striped table-hover" width="744"
						id="table_project">
						<thead>
							<tr>
								<th>#</th>
								<th>팀 이름</th>
								<th>팀 도메인</th>
							</tr>
						</thead>
						<%for(int roomnum = 0; roomnum<roomlist.size(); roomnum++){ %>

						<tbody>
							<tr id="tr_project"
								onclick="javascript:clickProjectEvent('<%=roomlist.get(roomnum).getRoomUserPK()%>')">
								<td><i class="fa fa-users fa-3x"></i></td>
								<td><%=roomlist.get(roomnum).getRoomUserName() %></td>
								<%-- <td><%=userDAOImpl.getUserDAOName(roomlist.get(roomnum).getRoomUserFb_id()).getUserName() %></td> --%>
								<td><%=roomlist.get(roomnum).getRoomUserPK() %></td>
							</tr>
						</tbody>
						<%} %>
					</table>


				</div>
			</div>


		</div>

	</div>
	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	 <form name="sendForm" method="post">
		<input type="hidden" name="session_fb_id" id="session_fb_id"
			value="${sessionScope.session_map.fb_id}"> <input
			type="hidden" class="form-control" name="session_team"
			id="session_team" value="" /><input type="hidden"
			class="form-control" name="session_ko_name" id="session_ko_name"
			value="${sessionScope.session_map.ko_name}" /><input type="hidden"
			class="form-control" placeholder="Team name domain"
			name="session_team_PK" id="session_team_PK" value="" /> <input
			type="hidden" class="form-control" placeholder="Team name domain"
			name="logoutURL" id="logoutURL" value="<%=logoutURL %>" /> <br />
	</form> 
	<div id="demo" style="font-family: Verdana">
		<div class="plainmodal-close"></div>
		팀 구성하기
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

	<div id="demo2" style="font-family: Verdana">
		<div class="plainmodal-close"></div>
		<div class="panel panel-primary filterable">
			<div class="panel-heading">
				<h5 class="panel-title">
					팀 목록
					<div class="pull-right">
						<button class="btn btn-default btn-filter">
							<span class="glyphicon glyphicon-filter"></span> 검색
						</button>
					</div>
				</h5>
				<br />
			</div>
			<table class="table table-striped table-hover" width="744"
				id="table_project2">
				<thead>
					<tr class="filters">
						<th><input type="text" class="form-control" placeholder="#"
							id="in" disabled></th>
						<th><input type="text" class="form-control"
							placeholder="팀 이름" disabled></th>
						<th><input type="text" class="form-control"
							placeholder="팀 도메인" disabled></th>
						<th><input type="text" class="form-control"
							placeholder="팀 개설자" disabled></th>
					</tr>
				</thead>
				<%for(int roomnum = 0; roomnum<all_roomlist.size(); roomnum++){ %>
				<tbody>
					<tr id="tr_project2">
						<td><i class="fa fa-users fa-3x"></i></td>
						<td><%=all_roomlist.get(roomnum).getRoomUserName() %></td>
						<%-- <td><%=userDAOImpl.getUserDAOName(roomlist.get(roomnum).getRoomUserFb_id()).getUserName() %></td> --%>
						<td><%=all_roomlist.get(roomnum).getRoomUserPK() %></td>
						<td><%=userDAOImpl.getUserDAOName(all_roomlist.get(roomnum).getRoomUserFb_id()).getUserName() %></td>
						<td><button class="btn btn-info"
								onclick="javascript:clickSearchEvent('<%=all_roomlist.get(roomnum).getRoomUserFb_id()%>','<%=all_roomlist.get(roomnum).getRoomUserPK()%>')">
								<i class="fa fa-sign-in"></i>
							</button></td>
					</tr>
				</tbody>
				<%} %>
				<tbody id="t1">

				</tbody>
			</table>


		</div>
	</div>

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
	<script
		src="http://localhost:8080/test/resources/bootstrap/home/js/custom.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script language="javascript"
		src="http://connect.facebook.net/ko_KR/all.js"></script>
	<script type="text/javascript">
		function clickProjectEvent(roomPK) {
			var fb_id = ${sessionScope.session_map.fb_id};
			chat(roomPK);
			
		}
		function clickSearchEvent(recv_fb_id,roomPK) {
			var fd = new FormData();
			var send_fb_id = ${sessionScope.session_map.fb_id};
			var time = new Date();
			var date = time
					.toLocaleString();
			
			
			fd.append('recv_fb_id',recv_fb_id);
			fd.append('send_fb_id',send_fb_id);
			fd.append('roomPK',roomPK);
			fd.append('reg_date',date);
			 $.ajax({
			        type: 'POST',
			        url:  'http://localhost:8080/test/message.do',
			        data: fd,
			        contentType : false,
					processData : false,
					cache : false,
					success: function (message) {
			            alert('신청되었습니다');

			        }
			    });
		}
	</script>
	<script type="text/javascript">
		function ulogout() {
			<%-- var fd = new FormData();
			fd.append('logoutURL','<%=logoutURL%>');
			$.ajax({
		        type: 'POST',
		        url:  'http://localhost:8080/test/logout.do',
		        data: fd,
		        contentType : false,
				processData : false,
				cache : false,
				success: function (message) {
		            console.log("ajax send logout Success");

		        }
		    }); --%>
			 document.sendForm.action = "http://localhost:8080/test/logout.do";
			document.sendForm.submit(); 
		}
	</script>
	<script type="text/javascript">
		function chat(roomPK) {
			/* var fd = new FormData();
			fd.append('session_fb_id','${sessionScope.session_map.fb_id}');
			fd.append('session_team','');
			fd.append('session_ko_name','${sessionScope.session_map.ko_name}');
			fd.append('session_team_PK',roomPK);
			
			
			$.ajax({
		        type: 'POST',
		        url:  'http://localhost:8080/test/chat/'+roomPK+'.do',
		        data: fd,
		        contentType : false,
				processData : false,
				cache : false,
				success: function (message) {
		            console.log("ajax send chat Success");
		            location.href='http://localhost:8080/test/chat/'+roomPK+'.do';

		        }
		    }); */
			document.sendForm.session_team_PK.value = roomPK;
			document.sendForm.action = "http://localhost:8080/test/chat/"
					+ roomPK + ".do";
			document.sendForm.submit();

		}
		function teamcreater() {
			alert('teamcreater');
			document.sendFormRoomCreater.action = "http://localhost:8080/test/teamcreater.do";
			document.sendFormRoomCreater.submit();
		}
		function Message(){
			
		}
	</script>

	<script>
		$('#team_plus').click(function() {
			$('#demo').show();
			$('#demo').plainModal('open');
		});
		$('#team_search').click(function() {
			$('#demo2').show();
			$('#demo2').plainModal('open');
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

		/*
		Please consider that the JS part isn't production ready at all, I just code it to show the concept of merging filters and titles together !
		 */
		$(document)
				.ready(
						function() {
							$('#demo').hide();
							$('#demo2').hide();
							$('.filterable .btn-filter')
									.click(
											function() {
												var $panel = $(this).parents(
														'.filterable'), $filters = $panel
														.find('.filters input'), $tbody = $panel
														.find('.table tbody');
												if ($filters.prop('disabled') == true) {
													$filters.prop('disabled',
															false);
													$filters.first().focus();
												} else {
													$filters.val('').prop(
															'disabled', true);
													$tbody.find('.no-result')
															.remove();
													$tbody.find('tr').show();
												}
												$('#in').attr('disabled',true);
											});

							$('.filterable .filters input')
									.keyup(
											function(e) {
												/* Ignore tab key */
												var code = e.keyCode || e.which;
												if (code == '9')
													return;
												/* Useful DOM data and selectors */
												var $input = $(this), inputContent = $input
														.val().toLowerCase(), $panel = $input
														.parents('.filterable'), column = $panel
														.find('.filters th')
														.index(
																$input
																		.parents('th')), $table = $panel
														.find('.table'), $rows = $table
														.find('tbody tr');
												/* Dirtiest filter function ever ;) */
												var $filteredRows = $rows
														.filter(function() {
															var value = $(this)
																	.find('td')
																	.eq(column)
																	.text()
																	.toLowerCase();
															return value
																	.indexOf(inputContent) === -1;
														});
												/* Clean previous no-result if exist */
												$table.find('tbody .no-result')
														.remove();
												/* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
												$rows.show();
												$filteredRows.hide();
												/* Prepend no-result row if all rows are filtered */
												if ($filteredRows.length === $rows.length) {
													$table
															.find('#t1')
															.prepend(
																	$('<tr class="no-result text-center"><td colspan="'
																			+ $table
																					.find('.filters th').length
																			+ '">검색 결과를 찾을 수 없습니다</td></tr>'));
												}
											});
						});
	</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style>
.affix {
	top: 0;
	width: 100%;
	z-index: 9999 !important;
}
.affix+.container-fluid {
	padding-top: 70px;
	
}
#bg_img_div{
color: #FFFFFF;
background: #232323;
text-shadow: 0 0 5px #FFF, 0 0 10px #FFF, 0 0 15px #FFF, 0 0 20px #49ff18, 0 0 30px #49FF18, 0 0 40px #49FF18, 0 0 55px #49FF18, 0 0 75px #49ff18;
color: #FFFFFF;
background: #232323;

}

 h1{
font-size: 400%;
} 

</style>

<title>ALL STAR COMPUTERS</title>
</head>
<body>

	<div class="container-fluid"  id="bg_img_div" style=" color: #fff; height: 150px;">
	
		<h1 >All star computers</h1>
		<br/>
		<h4 align="right" >
		<c:if test="${logiran_user!=null}">
			 Dobrodošao ${logiran_user.firstName}
    	</c:if>
		</h4>
	</div>



	<nav class="navbar navbar-inverse" data-spy="affix"
		data-offset-top="197">
		<ul class="nav navbar-nav">
			<!--    	HOME -->
			<li class="active"><a href="ControlerServlet?subcategory_id=-1"><span
					class="glyphicon glyphicon-home"></span> HOME</a></li>

			<!--     POVIJEST KUPOVINE -->
			<c:if test='${logiran_user != null}'>
				<li><a href="HistoryShoppingServlet" class="shopping_history"><span
						class="glyphicon glyphicon-list-alt"></span> Povijest
						kupovine</a></li>
			</c:if>

			<!--   UREDI PROFIL -->
			<c:if test='${logiran_user != null}'>
				<li><a href="editUser.jsp"><span
						class="glyphicon glyphicon-edit"></span> Uredi profil</a></li>
			</c:if>


			<!--   ADMIN POVIJEST PRIJAVA -->
			<c:if test='${logiran_user.user_level_id == 2}'>
				<li><a href="HistoryLoginServlet" class="login_history"><span
						class="glyphicon glyphicon-list-alt"></span> Povijest Prijava</a></li>
			</c:if>
			<!--   ADMIN: DODAJ PROIZVOD -->
			<c:if test='${logiran_user.user_level_id == 2}'>
				<li><a href="DodajProizvodServlet"><span
						class="glyphicon glyphicon-plus-sign"></span> Dodaj proizvod</a></li>
			</c:if>
			<c:if test='${logiran_user.user_level_id == 2}'>
				<li><a href="AdminUserAccess"><span
						class="glyphicon glyphicon-user"></span> Uredi korisnike</a></li>
			</c:if>


		</ul>
		<ul class="nav navbar-nav navbar-right">
			<!--      KOŠARICA -->
			<li><a href="EditShoppingCartServlet"><span
					class="glyphicon glyphicon-shopping-cart"></span>
					(${brojProizvodaKosarica==null?0:brojProizvodaKosarica})</a></li>
			<!--   	   REGISTRACIJA -->
			<c:if test='${logiran_user == null}'>
				<li><a href="register.jsp"><span
						class="glyphicon glyphicon-user"></span> Registracija</a></li>
			</c:if>
			<!-- 		LOGIN LOGOUT -->
			<c:if test='${logiran_user != null}'>
				<li><a href="LogoutServlet" class="loggedIn"><span
						class="glyphicon glyphicon-log-out"></span> Odjava</a></li>
			</c:if>
			<c:if test='${anonimni!= null}'>
				<li><a href="login.jsp" class="loggedOut"><span
						class="glyphicon glyphicon-log-in"></span> Prijava</a></li>
			</c:if>
			<li><a href="#"> </a></li>
		</ul>

	</nav>

</body>
</html>
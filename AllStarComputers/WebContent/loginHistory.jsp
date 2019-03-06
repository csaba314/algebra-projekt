<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	background-color: #D5E6EF;
}

.main-layout-table {
	width: 100%;
	border: 5;
	
}

.headerSegment, .footerSegment, .main-body-element {
	vertical-align: top
}

.left-side-bar {
	width: 10%;
}

.right-side-bar {
	width: 10%;
}

.navbar {
	overflow: hidden;
	background-color: #333;
	position: fixed;
	top: 0;
	width: 100%;
}

.navbar a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.navbar a:hover {
	background: #ddd;
	color: black;
}

.main {
	padding: 16px;
	margin-bottom: 30px;
	height: 0;
}

.ime_proizvoda_td {
	width: 60%;
}

.popis_proizvoda_table {
	width: 100%;
	background-color: #f6f6f6;
}

.main_body_div{
background-color: #D5E6EF;
}
.main-body-element{
/* background-color: #f6f6f6; */
}
</style>
<meta charset="UTF-8">
<title>ComputerShop</title>
</head>
<body>
<div class="main_body_div">


	<table class="main-layout-table">
		<tr>
			<td colspan="3" class="headerSegment"><jsp:include
					page="homeHeader.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td class="left-side-bar"></td>
			
			
			<td class="main-body-element">

				<div align="center">
					<form action="HistoryLoginServlet" method="post">
					<strong>Sortiraj po:</strong> 
					<select name="sortiraj">
						<option value="2">Vrijeme prijave DESC</option>
						<option value="1">Vrijeme prijave ASC</option>
						<option value="3">ID korisnika ASC</option>
						<option value="4">ID korisnika DESC</option>
					</select> 
					<input type="submit" value="Odaberi" />
					</form>
				</div>

				<table class="popis_proizvoda_table" border="2px">
					<tr>
						<th align="center"><strong>Vrijeme prijave</strong></th>
						
						<th align="center"><strong>ID korisnika</strong></th>

						<th align="center"><strong>Ime korisnika</strong></th>

						<th align="center"><strong>e-mail</strong></th>

						<th align="center"><strong>id Sesije</strong></th>

						<th align="center"><strong>Vrijeme odjave</strong></th>

					</tr>
					<c:forEach var="historyItem" items="${login_history}">
						<tr>
							<td class="vrijeme_prijave_td" align="center">${ historyItem.login_timestamp}</td>
							
							<td class="id_korisnika" align="center">${historyItem.user_id}</td>

							<td align="center">${historyItem.user.firstName}
								${historyItem.user.lastName}</td>

							<td align="center">${historyItem.user.email}</td>

							<td align="center">${historyItem.session_id}</td>

							<td align="center">${historyItem.logout_timestamp}</td>
						</tr>
					</c:forEach>
				</table>
			</td>
			<td class="right-side-bar"></td>
		</tr>
	</table>
</div>
</body>
</html>





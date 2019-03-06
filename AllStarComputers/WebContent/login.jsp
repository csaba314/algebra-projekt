<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {width: 100%;

    background-color: #D5E6EF;
}

.main-layout-table {width: 100%;}

.headerSegment, .main-body-element {
	vertical-align: top;
}

.left-side-bar {width: 17%;}

.right-side-bar {width: 20%;}

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
	text-align: center padding 14px 16px;
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
	height: 1500px; 
}

.middleSegment {
	width: 65%;
	align-self: center;
	align-content: center;
}

.sidebarList {
	overflow: hidden;
	background-color: #f5f5f5;
	position: fixed;
	top: 300px; /***/
	width: 180px;
}

body {
	background-color: #f5f5f5;
}

</style>
<meta charset="UTF-8">
<title>ComputerShop</title>
</head>
<body>
	<table name="main-layout-table" class="main-layout-table">
		<tr>
			<td colspan="3" class="headerSegment"><jsp:include page="homeHeader.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td class="left-side-bar"></td>
			<td class="main-body-element"><jsp:include page="loginForm.jsp"></jsp:include>
				<p  align="center">
				<br/><br/><br/>
					<strong>ADMIN email: admin@admin <br/>password: admin<br/><br/>
			
					KORISNIK: email: pero@gmail.com <br/>password: 123456 <br/><br/>
					KORISNIK: email: garo@gmail.com <br/>password: 111111</strong>
				</p></td>
			<td class="right-side-bar"></td>
	</table>
</body>
</html>














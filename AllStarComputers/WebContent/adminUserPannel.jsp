<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body{
width: 100%;
align-content: center;

    background-color: #D5E6EF;

}
.main-body-element{
align-self: center;
}


.main-layout-table {
	 	width: 100%;
	
}

.headerSegment, .footerSegment {
	vertical-align: top;
        width: 100%;
}

.left-side-bar {
	width: 20%;
	
}

.right-side-bar {
	width: 20%;
}

/* - */
.navbar {
	overflow: hidden;
	background-color: #333;
	position: fixed;
	bottom: 0;
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
	height: 1500px; /* Used in this example to enable scrolling */
}

/* - */
.ime_proizvoda_td {
	width: 60%;
}

.popis_korisnika_table {
	width: 100%;
}
.table_div{
background-color: #f6f6f6;
-webkit-box-shadow: 13px 13px 15px 10px rgba(56,7,7,0.92); 
box-shadow: 13px 13px 15px 10px rgba(56,7,7,0.92);

border: 4px groove rgba(86,24,30,0.79);

}
.bg_color_div{
background-color: #D5E6EF;
height: 960px;
}
</style>
<meta charset="UTF-8">
<title>Dodaj administratora</title>
</head>
<body>
<div class="bg_color_div">
	<table name="main-layout-table" class="main-layout-table">
		<tr>
			<td colspan="3" class="headerSegment"><jsp:include page="homeHeader.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td class="left-side-bar"></td>			
			<td class="main-body-element">
				<div class="table_div">
					<fieldset>
						<legend>Uredi korisnike</legend>
							
							<table class="popis_korisnika_table" border= "2px" > 
					<tr align="center">
						
						<th><strong>ID korisnika</strong></th>

						<th><strong>Ime i Prezime</strong></th>

						<th><strong>e-mail</strong></th>
						
						<th><strong>adresa</strong></th>
                                               
						<th><strong>Tip korisnika</strong></th>

						<th width="30%">     </th>

					</tr>

					
					<c:forEach var="korisnik" items="${lista_korisnika}">
						<tr>
							<td align="center">${korisnik._id}</td>
							
							<td align="center">${korisnik.firstName}  ${korisnik.lastName}</td>
							
							<td align="center">${korisnik.email}</td>
							
							<td align="center">${korisnik.adresa}</td>
							
							<td align="center">${korisnik.user_level}</td>
							
							<td align="center" width="30%">
							
							
							<form action="AdminUserAccess" method="post">
							<input type="hidden" value="${korisnik._id}" name="korisnik_id"> 
							<select name="promjena_korisnika">
							<option value="1" selected="selected">Korisnik</option>
							<option value="2">Administrator</option>
							<option value="3">Deaktiviraj korisnika</option>
							<option value="4">Resetiraj password</option>
							</select>
							<input type="submit" value="Postavi">
							</form>
							</td>

							
						</tr>
					</c:forEach>

					


				

				</table>
						

					</fieldset>

				</div>
				
		</td>
		<td class="right-side-bar"></td>
		</tr>
	</table>
	</div>
</body>
</html>





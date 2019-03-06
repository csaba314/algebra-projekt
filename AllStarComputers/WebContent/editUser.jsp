<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	width: 100%;
	align-content: center;
}

.main-body-element {
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
	height: 0; /* Used in this example to enable scrolling */
}

.edit_user_form_div {
	align-items: center;
	width: 350px;
	height: 280px;
	background-color: #f6f6f6;
	-webkit-box-shadow: 11px 11px 19px 8px rgba(0, 0, 0, 0.88);
	box-shadow: 11px 11px 19px 8px rgba(0, 0, 0, 0.88);
	border: 6px groove rgba(3, 13, 20, 0.98);
	border-radius: 4px;
}

.bg_color_div {
	background-color: #D5E6EF;
	height: 965px;
}
</style>
<meta charset="UTF-8">
<title>Uredi korisnika</title>
</head>
<body>
	<div class="bg_color_div">
		<table name="main-layout-table" class="main-layout-table">

			<!-- 					HEADER -->
			<tr>
				<td colspan="3" class="headerSegment"><jsp:include
						page="homeHeader.jsp"></jsp:include></td>
			</tr>
			<!-- 					MIDDLE -->
			<tr>
				<!-- 					SIDEBAR -->
				<td class="left-side-bar"></td>

				<!-- 					MAIN ELEMENT -->


				<td class="main-body-element" align="center">

					<div class="edit_user_form_div">
						<fieldset>
							<legend>Edit Profile</legend>
							<!--PROMJENA ZA SQL SERVER DODANO /ComputerShopV2/-->
							<form action="EditUserServlet" method="post">
								<table>
									<tr>
										<td><input type="text" name="first_name_reg"
											value="${logiran_user.firstName }"> <strong>Ime</strong></td>
									</tr>
									<tr>
										<td><input type="text" name="last_name_reg"
											value="${logiran_user.lastName }"> <strong>Prezime</strong></td>
									</tr>
									<tr>
										<td><input type="email" name="email"
											value="${logiran_user.email }" required="required"> <c:if
												test="${param.msg eq 1}">
												<strong><span class="errorr_msg_span">${error_mail}</span></strong>
											</c:if><strong>*e-mail</td>

									</tr>
									<tr>
										<td><input type="password" name="passwd"
											value="${logiran_user.password }" required="required"><strong>*Lozinka</strong></td>
									</tr>
									<tr>
									<tr>
										<td><input type="text" name="adresa_reg"
											value="${cookie.adresa.value }"> <strong>Adresa
												i kućni broj</strong></td>
									</tr>
									<tr>
										<td><input type="text" name="mjesto_reg"
											value="${cookie.mjesto.value }"> <strong>Mjesto
												stanovanja</strong></td>
									</tr>
									<tr>
										<td><select name="nacin_placanja">
												<option value="1" selected="selected">Pay-Pal</option>
												<option value="2">Pouzeće</option>
												<option value="3">Virman</option>
												<option value="4">VISA</option>
												<option value="5">MasterCard</option>
										</select> <strong>Način plaćanja</strong></td>
									</tr>

									<tr>
										<td><input type="submit" value="Potvrdi promjenu"></td>
									</tr>
								</table>
							</form>

						</fieldset>
					</div>

				</td>
				<td class="right-side-bar"></td>
			</tr>
		</table>
	</div>
</body>
</html>





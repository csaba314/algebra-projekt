<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	width: 100%;
    background-color: #D5E6EF;
}

.main-layout-table {
	width: 100%;
	background-color: #f6f6f6;
}

.headerSegment, .main-body-element {
	vertical-align: top;
}

.left-side-bar {
	width: 15%;
}

.right-side-bar {
	width: 15%;
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

.category_table, .new_item_table {
	border-collapse: separate;
	border-spacing: 10px;
	height: 300px;
}

.desni_div, .ljevi_div {
	-webkit-box-shadow: 5px 5px 15px 5px #000000;
	box-shadow: 5px 5px 15px 5px #000000;
	background-color: #f6f6f6;
}

.middle_separator {
	width: 20px;
}
.text_input_wid{
width: 250px;
}

.main_body_div{
/* background-color: #D5E6EF; */
height: 965px;
}
</style>
<meta charset="UTF-8">
<title>ComputerShop</title>
</head>
<body>
<div class="main_body_div">

	<table class="main-layout-table">
		<tr>
			<td colspan="5" class="headerSegment"><jsp:include
					page="homeHeader.jsp"></jsp:include><br/><br/></td>
		</tr>
		<tr>
			<td class="left-side-bar"></td>
			
			<td class="main-body-element1">
				<div class="ljevi_div">
					<fieldset class="pod_kat_f">
						<legend>Dodaj novu podkategoriju</legend>
						<form action="DodajKategorijuServlet" method="post">
							<table class="category_table">
								<tr>
									<td><strong>Odaberi kategoriju</strong></td>
									<td><select name="id_kategorije" required="required">
											<c:forEach var="kat" items="${sessionScope.kategorije}">
												<option value="${kat._id}">${kat.category_name}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><strong>Ime nove podkategorije</strong></td>
									<td><input type="text" name="ime_nove_podkategorije" class="text_input_wid"
										required="required" /></td>
								</tr>
								<tr>
									<td><strong>Opis podkategorije</strong></td>
									<td><textarea rows="3" cols="34"
											name="opis_nove_podkategorije" required="required"></textarea></td>
								</tr>
								<tr>
									<td colspan="2" align="right"><input type="submit"
										value="Dodaj novu kategoriju" /></td>
								</tr>
							</table>
						</form>
					</fieldset>
				</div>
			</td>
			<td class="middle_separator"></td>
			<td class="main-body-element2">
			<div class="desni_div">

					<fieldset class="novi_proiz_f">
						<legend>Dodaj novi proizvod</legend>
						<form action="DodajProizvodServlet" method="post">
							<table class="new_item_table">
								<tr>
									<td><strong>Odaberi podkategoriju</strong></td>
									<td><select name="id_podkategorije" required="required">
											<c:forEach var="pod" items="${sessionScope.subkategorije}">
												<option value="${pod._id}">${pod.subcategory_name}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><strong>Ime proizvoda</strong></td>
									<td><input type="text" name="ime_novog_proizv" class="text_input_wid"
										required="required" />
										<strong><span style="color: red;">${sessionScope.proizv_postoji}</span></strong>
										</td>
								</tr>
								<tr>
									<td><strong>Opis proizvoda</strong></td>
									<td><textarea rows="3" cols="34" required="required" name="opis_proizvoda"></textarea></td>
								</tr>
								<tr>
									<td><strong>Cijena (u lipama)</strong></td>
									<td><input type="number" name="cijena_novog_proizv" min="1"
										required="required" /></td>
								</tr>
								<tr>
									<td><strong>Količina</strong></td>
									<td><input type="number" name="kolicina_novog_proizv" min="0" value="0"
										required="required" /></td>
								</tr>

								<tr>
									<td colspan="2" align="right"><input type="submit"
										value="Dodaj novi proizvod" />
										<strong><span style="color: green;"> ${sessionScope.uspjesno_dodano}</span></strong>
										</td>
								</tr>
							</table>
						</form>
					</fieldset>
				</div></td>

			<td class="right-side-bar"></td>
	</table>



</div>
</body>
</html>














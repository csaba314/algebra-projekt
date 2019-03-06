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

/* - */
.ime_proizvoda_td {
	width: 60%;
}

.input_text{
width: 400px;
}

.input_number{
width: 80px;}

.popis_proizvoda_table {
	border: 5px;
	border-color: black;
}
</style>
<meta charset="UTF-8">
<title>Uredi proizvod</title>
</head>
<body>

	<table class="main-layout-table">

		<tr>
			<td colspan="3" class="headerSegment"><jsp:include page="homeHeader.jsp"></jsp:include></td>
		</tr>
		<tr>
						<td class="left-side-bar"></td>
			<td class="main-body-element">

				<div>
					<fieldset>
						<legend>Uredi proizvod</legend>
						<form action="PromjeniProizvodServlet" method="post">
							<table class="edit_item_table">
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
									<td><input type="text" class="input_text" name="ime_novog_proizv" class="text_input_wid" value="${proizvod.item_name }"
										required="required" />
										</td>
								</tr>
								<tr>
									<td><strong>Opis proizvoda</strong></td>
									<td><input type="text" class="input_text" required="required" 
									name="opis_proizvoda" value="${proizvod.item_description }"/></td>
								</tr>
								<tr>
									<td><strong>Cijena (u lipama)</strong></td>
									<td><input type="number" class="input_number" name="cijena_novog_proizv" min="1"
										required="required"  value="${proizvod.item_price }"/></td>
								</tr>
								<tr>
									<td><strong>Količina</strong></td>
									<td><input type="number" class="input_number" name="kolicina_novog_proizv" min="0" 
										required="required" value="${proizvod.item_onstock }"/></td>
								</tr>

								<tr>
                                                                    <td colspan="2" align="right"> 
                                                                        <input type="hidden" value="${proizvod._id}" name="id_proizvoda"/>
                                                                            <input type="submit" value="Promjeni proizvod" />
										</td>
								</tr>
							</table>
						</form>

					</fieldset>

				</div>
				
				



		</td>

		<!-- 					SIDEBAR -->
		<td class="right-side-bar"></td>

		</tr>
		

	</table>
</body>
</html>





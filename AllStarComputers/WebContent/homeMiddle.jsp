<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.main_middle_fieldset {
	width: 99%;
	height: 97%;
}

.dostupno_class {
	width: 33%
}

.broj_za_kosaricu_class {
	width: 40px;
	
}

.dodaj_u_kosaricu {

}

.segment_table {
	width: 100%;
}

.item_fieldset {
	border: 3px outset rgba(38, 164, 15, 0.3);
}

#demo {
	-webkit-box-shadow: 16px 12px 23px 0px rgba(18, 71, 11, 0.71);
	box-shadow: 16px 12px 23px 0px rgba(18, 71, 11, 0.71);
}

#demo {
	background-color: #f4f4f4;
	display: block;
	margin: 10px auto 0;
	width: 99%;
	height: 99%;
	color: black;
	padding: 1px;
}

body {
    background-color: #D5E6EF;
}
</style>
<meta charset="UTF-8">

</head>
<body>
	<fieldset name="main_middle_fieldset" class="main_middle_fieldset">
<!-- 			NASLOV ZA FIELDSET -->
		<c:if test="${param.subcategory_id eq -1}">
			<legend>
				<b>Svi proizvodi</b>
			</legend>
		</c:if>
		<c:forEach var="sub" items="${subkategorije}">
			<c:if test="${param.subcategory_id eq sub._id}">
				<legend>
					<b>${sub.subcategory_name}</b>
				</legend>
			</c:if>
		</c:forEach>
		<!-- ----- -->
		<c:forEach var="item" items="${svi_proizvodi}">
			<div id="demo">
				<fieldset class="item_fieldset">
					<legend align="top">
					<c:forEach var="sub" items="${subkategorije}">
					<c:if test="${item.item_subcategory_id eq sub._id}">
					<b>${sub.subcategory_name}</b>
					</c:if>
					</c:forEach>
					</legend>

					<table class="segment_table">
						<tr>
							<td colspan="3" align="left" width="100%"><b>${item.item_name}</b></td>
						</tr>
						<tr>
							<td colspan="3" align="left">${item.item_description}
								<hr>
							</td>
						</tr>
						<tr>
							<td align="left"><b>Cijena: <fmt:formatNumber type='number'
										pattern='###,###.##' minFractionDigits="2"
										value='${item.item_price/100}' /> HRK
							</b></td>
							<td class="dodaj_u_kosaricu" align="center">
								<form method="post" action="ShoppingCartServlet">
								<c:if test="${item.item_onstock > 0}">
							Dodaj u košaricu: <input type="hidden" name="dodaj_item_id"
										value="${item._id}"> 
										<input type="number"name="dodaj_item_amount" min="1" max="${item.item_onstock}"
										class="broj_za_kosaricu_class" value="1" /> <input
										type="submit" value="Dodaj" />
							</c:if>
								</form>
							</td>
							<td class="dostupno_class" align="right">
							<c:if test="${logiran_user.user_level_id == 2}">
								<form action="UrediProizvodServlet" method="post">
									<input type="hidden" name="uredi_proizv_id" value="${item._id}"/>
									<input type="submit" value="Uredi proizvod"/>
								</form>
							</c:if>
							
							<c:if test="${item.item_onstock > 0}">
							Dostupno:${item.item_onstock} kom.
							</c:if>
							<c:if test="${item.item_onstock eq 0}">
							<strong><span style="color: red;">Proizvod je nedostupan</span></strong>
							</c:if>	
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
		</c:forEach>
		<!-- ---- -->
	</fieldset>
</body>
</html>
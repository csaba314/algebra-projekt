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
	background-color: #f8f8f8;
}

.main_body_div {
	background-color: #D5E6EF;
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
						<c:if test="${logiran_user.user_level_id == 2}">
							<form action="HistoryShoppingServlet" method="post">
								<strong>Sortiraj po:</strong> <select name="sortiraj">
									<option value="1">Vrijeme kupovine DESC</option>
									<option value="2">Vrijeme kupovine ASC</option>
									<option value="3">ID korisnika ASC</option>
									<option value="4">ID korisnika DESC</option>
								</select> <input type="submit" value="Odaberi" />
							</form>
						</c:if>

						<c:if test="${logiran_user.user_level_id == 1}">
							<form action="HistoryShoppingServlet" method="post">
								<strong>Sortiraj po:</strong> <select name="sortiraj">
									<option value="5">Vrijeme kupovine DESC</option>
									<option value="6">Vrijeme kupovine ASC</option>
								</select> <input type="submit" value="Odaberi" />
							</form>
						</c:if>
					</div>

					<table class="popis_proizvoda_table" width="100%" border="3">
						<tr>
							<th align="center"><strong>Id proizvoda</strong></th>

							<th align="left"><strong>Ime proizvoda</strong></th>

							<th align="center"><strong>Količina</strong></th>

							<th align="center"><strong>Cijena (HRK)</strong></th>

							<th align="center"><strong>Id korisnika</strong></th>

							<th align="center"><strong>Vrijeme kupovine</strong></th>

							<th align="center"><strong>Način plaćanja</strong></th>

							<th align="center"><strong>Ukupno (HRK)</strong></th>

						</tr>
						<c:forEach var="historyItem" items="${purchase_history}">
							<tr>
								<td class="id_proiz_td" align="center">${ historyItem.item_id}</td>

								<td class="id_proiz_td" align="center">${ historyItem.item_object.item_name}</td>

								<td align="center">${historyItem.amount}</td>

								<td align="center"><strong><fmt:formatNumber type='number'
										pattern='###,###.##' minFractionDigits="2"
										value='${historyItem.item_price/100 }' /></strong> </td>

								<td align="center">${historyItem.user_id}</td>

								<td align="center">${historyItem.purchase_timestamp}</td>

								<td align="center">${historyItem.nacin_placanja_str}</td>

								<td align="center"><strong><fmt:formatNumber type='number'
										pattern='###,###.##' minFractionDigits="2"
										value="${(historyItem.amount * (historyItem.item_price/100))}" /></strong>
									</td>
							</tr>

						</c:forEach>
						<tr>
							<td colspan="8" align="right"><h4>
									<strong>Sveukupno: <fmt:formatNumber type='number'
											pattern='###,###.##' minFractionDigits="2"
											value="${sveukupno/100}" /> HRK
									</strong>
								</h4></td>
						</tr>
					</table> <br />
				<br />
				</td>
				<td class="right-side-bar"></td>
			</tr>
		</table>
	</div>
</body>
</html>





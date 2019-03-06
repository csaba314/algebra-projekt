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
}

.main-layout-table {
	width: 100%;
}

.headerSegment, .footerSegment {
	vertical-align: top;
	width: 100%;
}

.left-side-bar {
	width: 17%;
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
	height: 1500px;
}

.ime_proizvoda_td {
	width: 60%;
}

.popis_proizvoda_table {
	width: 100%;
	padding: 5px;
	
}

.shopping_cart_amount {
	width: 40px;
}
.main_table_div{
background-color: #f6f6f6;
outline: 7px ridge rgba(15,48,11,0.68);

-webkit-box-shadow: 11px 10px 14px 8px #000000; 
box-shadow: 11px 10px 14px 8px #000000;
}

.bg_color_div{
background-color: #D5E6EF;
height: 965px;
}
th{
align-content: center;
} 
</style>
<meta charset="UTF-8">
<title>ComputerShop</title>
</head>
<body>
<div class="bg_color_div">
	<table class="main-layout-table">
		<tr>
			<td colspan="3" class="headerSegment"><jsp:include
					page="homeHeader.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td class="left-side-bar"></td>
			<td class="main-body-element">
				<div class="main_table_div">
					<table class="popis_proizvoda_table" border="2" >
						<tr>
							<th class="ime_proizvoda_td" align="center"><strong>Ime</strong></th>

							<th align="center"><strong><span class="th_span">Količina</span></strong></th>

							<th align="center"><strong>Cijena</strong></th>

							<th align="center"><strong>Ukupno</strong></th>

							<th align="center"></th>

						</tr>
						<c:forEach var="entry" items="${shopping_cart}">
							<tr>
								<td class="ime_proizvoda_td" align="center"><strong>${ entry.itemObject.item_name}</strong></td>

								<td align="center">
									<form action="EditShoppingCartServlet" method="post">
										<input type="hidden" name="promjeni_kol_item_id"
											value="${entry.item_id}"> <input type="number"
											name="promjeni_kolicinu_kos" class="shopping_cart_amount"
											value="${entry.item_amount }" min="1"
											max="${entry.itemObject.item_onstock}" /> <input
											type="submit" value="Promjeni">
									</form>
								</td>

								<td align="center"><strong><fmt:formatNumber
											type='number' pattern='###,###.##' minFractionDigits="2"
											value='${entry.itemObject.item_price/100 }' /> HRK</strong></td>
								<td align="center"><strong><fmt:formatNumber
											type='number' pattern='###,###.##' minFractionDigits="2"
											value='${(entry.itemObject.item_price *  entry.item_amount)/100}' />
										HRK</strong></td>

								<td align="center">
									<form method="post" action="EditShoppingCartServlet">
										<input type="hidden" name="ukloni_item_id"
											value="${entry.item_id}"> <input type="submit"
											value="Ukloni" />
									</form>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" class="ukupno_kosarica" align="right"><b>
									ukupno: <fmt:formatNumber type='number' pattern='###,###.##'
										minFractionDigits="2" value='${ukupno_novaca/100}' /> HRK
							</b></td>
						</tr>
						<c:if test="${logiran_user!=null }">
							<c:if test="${brojProizvodaKosarica>0}">
								<tr>
									<td colspan="5" rowspan="2" valign="middle" class="kupi_dugme" align="right">
										<form method="post" action="KupiServlet"><strong>Način plaćanja</strong>
											<select name="nacin_placanja">
												<option value="1">Pay-Pal</option>
												<option value="2">Pouzeće</option>
												<option value="3">Virman</option>
												<option value="4">VISA</option>
												<option value="5">MasterCard</option>
											</select> <input type="submit" value="Kupi" />
										</form>
									</td>
								</tr>
							</c:if>
						</c:if>

					</table>
				</div>
			</td>
			<td class="right-side-bar"></td>

		</tr>


	</table>
	
	</div>
</body>
</html>





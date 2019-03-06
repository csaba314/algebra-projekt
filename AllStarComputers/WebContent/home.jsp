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
.main-layout-table {width: 100%;}

.leftSideBar{
width: 20%px;
vertical-align: top;

}
.rightSideBar{
width: 20%;
}
.middleSegment{
width: 65%;
align-self: center;
}

.sidebarList{
overflow: hidden;
  background-color: #f8f8f8;
  position: fixed;
  top: 260px;/***/
  width: 180px;
  height: 600px;
  
  -webkit-box-shadow: 10px 11px 19px 1px rgba(0,0,0,0.89); 
box-shadow: 10px 11px 19px 1px rgba(0,0,0,0.89);

border: 2;
}

body {
/* 	background-color:#D5E6EF; */
	background: url("img/e5799698e1255e1dfefd6c87b4fa8244.jpg");
	
}


</style>

<title>ALL STAR COMPUTERS</title>
</head>
<body>


	
	
	
	<table class="main-layout-table">
	<tr>
	
	<td colspan="3" class="headerSegment"><jsp:include page="homeHeader.jsp"></jsp:include></td>
		
	
	
	
	</tr>
	<tr>
	<td class="leftSideBar">
	

      <div class="sidebarList" style="width: 230px">
      <fieldset>
      <legend align="top">Kategorije</legend>
        <ul><strong>STOLNO RAČUNALO</strong>
        				<c:if test="${subkategorije != null }">
        					<c:forEach var="sub" items="${sessionScope.subkategorije}">
								<c:if test="${sub.category_id eq 1 }">
									<li><a href="ControlerServlet?subcategory_id=${sub._id}"> ${sub.subcategory_name}</a></li>
									
								</c:if>
							</c:forEach>
						</c:if>
						</ul>
        <ul><strong>PRIJENOSNO RAČUNALO</strong>
        <c:if test="${subkategorije != null }">
        					<c:forEach var="sub" items="${subkategorije}">
								<c:if test="${sub.category_id eq 2 }">
									<li><a href="ControlerServlet?subcategory_id=${sub._id}"> ${sub.subcategory_name}</a></li>
									
								</c:if>
							</c:forEach>
						</c:if>
        </ul>
        <ul><strong>KOMPONENTE</strong>
        <c:if test="${subkategorije != null }">
        					<c:forEach var="sub" items="${subkategorije}">
								<c:if test="${sub.category_id eq 3 }">
									<li><a href="ControlerServlet?subcategory_id=${sub._id}"> ${sub.subcategory_name}</a></li>
									
								</c:if>
							</c:forEach>
						</c:if>
        </ul>
        <ul><strong>SMARTPHONE</strong>
        <c:if test="${subkategorije != null }">
        					<c:forEach var="sub" items="${subkategorije}">
								<c:if test="${sub.category_id eq 4 }">
									<li><a href="ControlerServlet?subcategory_id=${sub._id}"> ${sub.subcategory_name}</a></li>
									
								</c:if>
							</c:forEach>
						</c:if>
        </ul>
        <ul><strong>OSTALO</strong>
        <c:if test="${subkategorije != null }">
        					<c:forEach var="sub" items="${subkategorije}">
								<c:if test="${sub.category_id eq 5 }">
									<li><a href="ControlerServlet?subcategory_id=${sub._id}"> ${sub.subcategory_name}</a></li>
									
								</c:if>
							</c:forEach>
						</c:if>
     					 </ul>
     					 </fieldset>
     					 <form action="ControlerServlet" method="post">
     					 <fieldset>
     					 
      						<div align="center">
									<strong>Sortiraj po:</strong>
									<select name="sortiranje_proizvoda">
									<option value="1">Naziv: A do Z</option>
									<option value="2">Naziv: Z do A</option>
									<option value="3">Cijena: manja prema većoj</option>
									<option value="4">Cijena: veća prema manjoj</option>
									</select>
									<input type="submit" value="Odaberi"/>
					         </div>	
    						 </fieldset>
     						 </form>
     						 </div>
</td>
<td class="middleSegment">

	<jsp:include page="homeMiddle.jsp"></jsp:include>
	
<td class="rightSideBar"></td>


</tr>
</table>
</body>
</html>







<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTER</title>

<style type="text/css">
.errorr_msg_span{
color: red;
}
body{
        background-color: #D5E6EF;
}
.register_fieldset{
width: 250px;
}

.main_form_div{
width: 270px;
-webkit-box-shadow: 14px 14px 26px 7px rgba(14,111,117,0.75); 
box-shadow: 14px 14px 26px 7px rgba(14,111,117,0.75);
border: 7px ridge rgba(15,48,11,0.68);
border-radius: 16px;

}
</style>
</head>
<body>
<br/><br/>
	<div class="main_form_div" align="center" style="background-color: #f6f6f6; ">
		<fieldset class="register_fieldset">
			<legend>Registracija</legend>
			<form action="RegisterServlet" method="post">

				<table>
					<tr>
						<td><input type="text" name="first_name_reg"
							required="required" placeholder="Ime"></td>
					</tr>
					<tr>
						<td><input type="text" name="last_name_reg"
							required="required" placeholder="Prezime"></td>
					</tr>
					<tr>
						<td><input type="email" name="email" required="required"
							placeholder="email@mail.com"> 
							<c:if test="${param.msg eq 1}">
							<b><span class="errorr_msg_span">${error_mail}</span></b></c:if>							

						</td>

					</tr>
					<tr>
						<td><input type="password" name="passwd" required="required"
							placeholder="********"></td>
					</tr>
					<tr>
					<tr>
						<td><input type="text" name="adresa_reg"
							placeholder="Ulica i kućni broj"></td>
					</tr>
					<tr>
						<td><input type="text" name="mjesto_reg"
							placeholder="Mjesto stanovanja"></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" value="Registriraj se">
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>
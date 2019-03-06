<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <style type="text/css">
        
        body{
width: 100%;
   background-color: #D5E6EF;

}
.fset_class{
background-color: #f8f8f8;
align-items: center;
width: 400px;
}
td {
align: center; 
}
</style>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>

<body>
	<div align="center">
		<fieldset class="fset_class">
			<legend>Login</legend>                      
			<form action="LoginServlet" method="post">
				<table>
					<tr>
						<td align="center"><input type="email" name="email" required="required"
							value="${cookie.email.value}" placeholder="email@mail.com">
						</td>
					</tr>
					<tr>
						<td align="center"><input type="password" name="password"
							required="required" placeholder="********"></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" value="Log in">
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>





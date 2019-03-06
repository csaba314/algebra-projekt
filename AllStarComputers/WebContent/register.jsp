<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
        
        body{
width: 100%;
   background-color: #D5E6EF;

}
            .main-layout-table {
                width: 100%;

            }

            .headerSegment, .footerSegment, .main-body-element {
                vertical-align: top
            }

            .left-side-bar {
                width: 40%;
                
            }

            .right-side-bar {
                width: 30%;
            }

            /* - */
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

           
</style>
<meta charset="UTF-8">
<title>Register</title>
    </head>
    <body>
        <table name="main-layout-table" class="main-layout-table" >          
            <tr>
                <td colspan="3" class="headerSegment"><jsp:include page="homeHeader.jsp"></jsp:include></td>
                </tr>                
                <tr>                    
                    <td class="left-side-bar"></td>                            
                    <td class="main-body-element"><jsp:include page="registerForm.jsp"></jsp:include></td>             
                <td class="right-side-bar"></td>
            </tr>      
            <tr>
                <td colspan="3" class="footerSegment">
                </td>
            </tr>

        </table>
    </body>
</html>














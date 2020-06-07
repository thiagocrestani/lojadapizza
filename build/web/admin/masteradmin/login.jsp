<%-- 
    Document   : login
    Created on : 18/08/2015, 08:41:24
    Author     : thiagocrestani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form action="../../servletLoginMasterAdmin" method="post">
            <input type=text name="usuario"> 
            <input type=password name="senha"> 
        <br>
            <input type="submit"> 
            </form>
    </body>
</html>

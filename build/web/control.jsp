<%-- 
    Document   : control
    Created on : 18/08/2015, 08:41:34
    Author     : thiagocrestani
--%>

<%@page import="dao.MasterAdminDAO"%>
<%@page import="beans.MasterAdmin"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="servletLogoutMasterAdmin?t=0">logout</a>
        <form action="servletInserirMasterAdmin" method="post">
        <textarea rows="8" name="sql" cols="100">

</textarea><br>
            <input type="submit"> 
            </form>
        <form action="servletConsultarMaxId" method="post">
        
            <input type="submit" value="Consultar id maximo"> 
            </form>
        
    </body>
    
  
</html>

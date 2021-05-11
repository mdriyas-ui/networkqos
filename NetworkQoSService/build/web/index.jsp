<%-- 
    Document   : index
    Created on : 17 Sep, 2013, 1:09:43 PM
    Author     : santhosh.t
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.net.URL;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<form action="upload.jsp" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Upload File" />
</form>
    </body>
</html>

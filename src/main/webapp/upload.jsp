<%-- 
    Document   : upload
    Created on : 14 juin 2019, 20:43:42
    Author     : ISLEM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="upload" method="post" enctype="multipart/form-data">
            <input type="file" class="form-group" name="file" size="100"/><br>
            <input type="text" name="coursName" size="100"/><br>
            <button type="submit">upload</>
        </form>
    
    </body>
</html>

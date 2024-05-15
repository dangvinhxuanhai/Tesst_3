<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>MVC Shopping Cart Demo</h1>
        <form action="CartController" method="POST">
            Please, choose your favourite book:<br>
            <select name="cboBook" size="20">
                <option value="Common Gateway Interface - CGI">Common Gateway Interface - CGI</option>
                <option value="Servlet">Servlet</option>
                <option value="JavaServer Page - JSP">JavaServer Page - JSP</option>
                <option value="Tomcat Server">Tomcat Server</option>
                <option value="Struts">Struts</option>
                <option value="JavaServer Face - JSF">JavaServer Face - JSF</option>
                <option value="Integrating Java with XML - IXJ">Integrating Java with XML - IXJ</option>
                <option value="Java Web Services - JWS">Java Web Services - JWS</option>
                <option value="Enterprice Java Beans - EJB">Enterprice Java Beans - EJB</option>
                <option value="JBoss Server">JBoss Server</option>
                <option value="Glassfish Server">Glassfish Server</option>
            </select><br>
            <input type="submit" value="Add to Cart" name="action">
            <input type="submit" value="View Cart" name="action">
        </form>
    </body>
</html>
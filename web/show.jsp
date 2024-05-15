<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show</title>
    </head>
    <body>
        <h1>Your Shopping Cart</h1>
        <form action="CartController" method="POST">
            <c:set var="shop" value="${sessionScope.SHOP}"/>
            <table width="75%" border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty shop}">

                        <c:set var="count" value="0"/>
                        <c:forEach var="rows" items="${shop}">
                            <c:set var="count" value="${count+1}"/>
                            <tr>
                                <td>${count}</td>
                                <td>${rows.value.title}</td>
                                <td>${rows.value.quantity}</td>
                                <td><input type="checkbox" name="rmv" value="${rows.value.title}"></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <tr>
                        <c:url var="add" value="CartController">
                            <c:param name="action" value="AddMore"/>
                        </c:url>
                        <td colspan="2">
                            <a href="${add}">Add More</a>
                        </td>
                        <td><input type="submit" value="Remove" name="action"></td>
                        <td><input type="submit" value="AddToDB" name="action"></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
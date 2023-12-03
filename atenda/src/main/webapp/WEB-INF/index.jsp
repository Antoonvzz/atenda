<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>atenda</title>
</head>
<body>
    <form action="login" method="post">
        <label for="username">usuario:</label> 
        <input type="text" id="username" name="login" required> 
        <br> 
        <label for="password">password:</label> 
        <input type="password" id="password" name="password" required> 
        <br> 
        <input type="submit" value="login">
    </form>

    <h1>Catálogo de produtos:</h1>

    <c:if test="${not empty produtosPaginados}">
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>nome</th>
                    <th>prezo</th>
                    <th>IVA</th>
                    <th>stock</th>
                    <th>foto</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="produto" items="${produtosPaginados}">
                    <tr>
                        <td>${produto.id}</td>
                        <td>${produto.nome}</td>
                        <td>${produto.prezo}</td>
                        <td>${produto.iva}</td>
                        <td>${produto.stock}</td>
                        <td><img src="<c:url value='/images/${produto.id}.png' />" width="200px" height="200px" alt="Imagen del produto"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div>
            <c:forEach var="pageNumber" begin="1" end="${totalPages}" step="1">
                <c:url value="/index.jsp" var="paginationUrl">
                    <c:param name="page" value="${pageNumber}" />
                </c:url>
                <a href="${paginationUrl}">${pageNumber}</a>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty produtosPaginados}">
        <p>No hay productos disponibles.</p>
    </c:if>
</body>
</html>

<%@ page import="model.Produto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>atenda</title>
</head>
<body>
    <form action="login" method="post">
        <label for="username">usuario:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="login">
    </form>

    <h1>Catálogo de produtos:</h1>
<table>

</table>
    <c:forEach var="produto" items="${arrayProdutos}">
        <p>ID: ${produto.id}</p>
        <p>Categoría: ${produto.idCategoria}</p>
        <p>Marca: ${produto.idMarca}</p>
        <p>Nombre: ${produto.nome}</p>
        <p>Precio: ${produto.prezo}</p>
        <hr>
    </c:forEach>

    <c:if test="${empty arrayProdutos}">
        <p>No hay productos disponibles.</p>
    </c:if>
</body>
</html>
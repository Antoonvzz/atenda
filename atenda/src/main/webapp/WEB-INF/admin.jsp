<%@ page import="java.util.List" %>
<%@ page import="model.Produto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin Atenda</title>
</head>
<body>
	  <img src="<c:url value='/images/favicon.ico' />" alt="Favicon" width="50px" height="50px">
	   
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>

    <h2>Xestión de Produtos:</h2>

    <form action="admin" method="post" enctype="multipart/form-data">
    	 <label for="id">Id</label>
    	 <br>
        <label for="categoria">Categoria:</label>
        <select id="categoria" name="categoria" required>
                <option value="1">ferramentas</option>
                <option value="2">roupa</option>
                <option value="3">electrodomésticos</option>
        </select>
        <br>
        <label for="marca">Marca:</label>
        <select id="marca" name="marca" required>
                <option value="1L">Balay</option>
                <option value="2L">bellota</option>
                <option value="3L">Springfield</option>
        </select>
        <br>
         <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>
        <br>
         <label for="prezo">Prezo:</label>
        <input type="number" id="prezo" name="prezo" required>
        <br>
         <label for="desconto">Desconto:</label>
        <input type="number" id="desconto" name="desconto" required>
        <br>
         <label for="coste">Coste:</label>
        <input type="number" id="coste" name="coste" required>
        <br>
		<label for="iva">Iva:</label>
        <select id="iva" name="iva" required>
        <option value="1">21%</option>
        <option value="2">10%</option>
        </select>
        <br>
         <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" required>        
        <br>
        <label for="foto"> Foto:</label>
        <input type="file" id="foto" name="foto" accept="image/*" required>
		<br>
		<label for="estado">Estado:</label>
        <select id="estado" name="estado" required>
        <option value="true">BAIXA</option>
        <option value="false">ALTA</option>
        </select>
        <br>
        <input type="submit" value="crear">
        <input type="button" value="cancelar">
    </form>
    
       <c:if test="${not empty produtoEditar}">
        <h3>Editar Producto</h3>
        <form action="admin" method="post" enctype="multipart/form-data">
  <input type="hidden" name="action" value="editar">
         Id: ${produtoEditar.id}
        <br>
        <label for="categoria">Categoria:</label>
        <select id="marca" name="marca"	>
                <option value="1">ferramentas</option>
                <option value="2">roupa</option>
                <option value="3">electrodomésticos</option>
        </select>
        <br>
        <label for="marca">Marca:</label>
        <select id="marca" name="marca">
                <option value="1">Balay</option>
                <option value="2">bellota</option>
                <option value="3">Springfield</option>
        </select>
        <br>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${produtoEditar.nome}">
        <br>
        <label for="prezo">Prezo:</label>
        <input type="number" id="prezo" name="prezo" value="${produtoEditar.prezo}">
        <br>
        <label for="desconto">Desconto:</label>
        <input type="number" id="desconto" name="desconto" value="${produtoEditar.desconto}">
        <br>
        <label for="coste">Coste:</label>
		 <input type="number" id="coste" name="coste" value="${produtoEditar.coste}">
		  <br>
		<label for="iva">Iva:</label>
		<label for="iva">Iva:</label>
        <select id="iva" name="iva">
        	<option value="21">21%</option>
        	<option value="10">10%</option>
        </select>
		 <br>       
		 <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" value="${produtoEditar.stock}" >
        <br>
        <label for="estado">Estado:</label>
        <select id="estado" name="estado">
            <option value="1" >BAIXA</option>
            <option value="0" >ALTA</option>
        </select>
        <br>
		<label for="foto"> Foto:</label>
		<img src="<c:url value='/images/${produtoEditar.id}.png' />" width="200px" height="200px" alt="Imagen del produto"/>
        <input type="file" id="foto" name="foto" accept="image/*">
        <br>
        <input type="submit" value="actualizar">
        <input type="button" value="cancelar">
        </form>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Categoria</th>
                <th>Marca</th>
                <th>Nome</th>
                <th>Prezo</th>
                <th>Desconto</th>
                <th>Coste</th>
                <th>Iva</th>
                <th>Stock</th>
                <th>Foto</th>
                <th>Baixa</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="produto" items="${listaProdutos}">
                <tr>
						<td>${produto.id}</td>
						<td>${produto.idCategoria}</td>
						<td>${produto.idMarca}</td>
						<td>${produto.nome}</td>
						<td>${produto.prezo}</td>
						<td>${produto.desconto}</td>
						<td>${produto.coste}</td>
						<td>${produto.iva}</td>
						<td>${produto.stock}</td>
						<td><img src="<c:url value='/images/${produto.id}.png' />" width="200px" height="200px" alt="Imagen del produto"/></td>
						<td>${produto.baixa} </td> 
 						<td>
                		<form action="admin" method="post">
                    		<input type="hidden" name="action" value="editar">
                    		<input type="hidden" name="idProduto" value="${produto.id}">
                    		<input type="submit" value="Editar">
                		</form>
            			</td>
           				 <td>
               			 <form action="admin" method="post">
                   			 <input type="hidden" name="action" value="eliminar">
                    		<input type="hidden" name="idProduto" value="${produto.id}">
                    		<input type="submit" value="eliminar">
                		</form>
            			</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>



</body>
</html>
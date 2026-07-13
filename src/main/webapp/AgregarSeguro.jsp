
<%@page import="dominio.TipoSeguro"%>
<%@page import="dominio.Seguro"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregado de Seguros</title>
</head>
<body>
	<nav>
		<a href="Inicio.jsp">Inicio</a> | <a href="ServletSeguro?menu=agregar">Agregar
			Seguros</a> | <a href="ServletSeguro?menu=listar">Listar Seguros</a>
	</nav>


<h2> Agregar Seguros </h2>


<form method="post" action="ServletSeguro">
<input type="hidden" name="menu" value="insertar">

<table>
<tr>
    <td>Id Seguro:</td>
    <td><input type="text" name="txtIdSeguro" value="${proximoId}" readonly></td>
</tr>

<tr>
    <td>Descripción:</td>
    <td><input type="text" name="txtDescripcion"></td>
</tr>

<tr>
    <td>Tipo de seguro:</td>
    <td>
        <select name="tipoSeguro">
		    <%
		    ArrayList<TipoSeguro> tipos = (ArrayList<TipoSeguro>) request.getAttribute("listaT");
		    if (tipos != null) {
		        for (TipoSeguro ts : tipos) {
		    %>
		        <option value="<%=ts.getIdTipo()%>">
		            <%=ts.getDescripcion()%>
		        </option>
		    <%
		        } 
		    }
		    %>
		</select>
    </td>
</tr>

<tr>
    <td>Costo contratación:</td>
    <td><input type="text" name="txtCostoContratacion"></td>
</tr>

<tr>
    <td>Costo máximo:</td>
    <td><input type="text" name="txtCostoMaximo"></td>
</tr>

<tr>
    <td></td>
    <td><input type="submit" value="Aceptar"></td>
</tr>
</table>

</form>

</body>
</html>
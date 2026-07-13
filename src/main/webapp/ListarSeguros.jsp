<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.Seguro"%>
<%@ page import="dominio.TipoSeguro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Seguros</title>
</head>
<body>

	<nav>
		<a href="Inicio.jsp">Inicio</a> | <a href="ServletSeguro?menu=agregar">Agregar
			Seguros</a> | <a href="ServletSeguro?menu=listar">Listar Seguros</a>
	</nav>

	<h1>"Tipo de seguros en la base de datos"</h1>
<%
    int idSeleccionado = 0;
    if(request.getParameter("ddlTipo") != null) {
        idSeleccionado = Integer.parseInt(request.getParameter("ddlTipo"));
    }
%>
	<form action="ServletSeguro" method="get">
    <input type="hidden" name="menu" value="listar">
    
    Busqueda por tipo de seguros: 
    <select name="ddlTipo">
        <option value="0" <%= (idSeleccionado == 0) ? "selected" : "" %>>Todos</option>
        <%
        ArrayList<TipoSeguro> tipos = (ArrayList<TipoSeguro>) request.getAttribute("listaT");
        if (tipos != null) {
            for (TipoSeguro ts : tipos) {
                String seleccionado = (idSeleccionado == ts.getIdTipo()) ? "selected" : "";
        %>
            <option value="<%=ts.getIdTipo()%>" <%= seleccionado %>>
                <%=ts.getDescripcion()%>
            </option>
        <%
            }
        }
        %>
    </select> 
    <input type="submit" name="btnFiltrar" value="Filtrar">
</form>

	<br>


	<table border="1">
		<thead>
			<tr>
				<th>ID Seguro</th>
				<th>Descripción Seguro</th>
				<th>Descripción Tipo Seguro</th>
				<th>Costo Contratación</th>
				<th>Costo Máximo Asegurado</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<%
			ArrayList<Seguro> seguros = (ArrayList<Seguro>) request.getAttribute("listaS");
			if (seguros != null) {
				for (Seguro s : seguros) {
			%>
			<tr>
				<form action="ServletSeguro" method="post">
				<input type="hidden" name="menu" value="eliminar">
   				<input type="hidden" name="idSeguro" value="<%=s.getIdSeguro()%>">
					<td><%=s.getIdSeguro()%> <input type="hidden" name="idSeguro" value="<%=s.getIdSeguro()%>"></td>
					<td><%=s.getDescripcion()%></td>
					<td><%=s.getTipo().getDescripcion()%></td>
					<td><%=s.getCostoContratacion()%></td>
					<td><%=s.getCostoAsegurado()%></td>
					<td><input type="submit" value="Eliminar"> </td>
				</form>
			</tr>
			<%
			}
			}
			%>
		</tbody>
	</table>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.dto.EquipoListadoDTO" %>
<%
    ArrayList<EquipoListadoDTO> listaEquipos =
            (ArrayList<EquipoListadoDTO>) request.getAttribute("listaEquipos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Equipos - TelecomUniverse</title>
</head>
<body>

<%@ include file="navbar.jspf" %>

<h2>Listado de Equipos</h2>

<p>
    <a href="<%= request.getContextPath() %>/equipos?action=nuevo">
        <button type="button">Nuevo equipo</button>
    </a>
</p>

<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Hostname</th>
        <th>Tipo</th>
        <th>IP Gestion</th>
        <th>Estado</th>
        <th>Sede</th>
        <th>Ciudad</th>
        <th>Meses operativos</th>
    </tr>
    <%
        if (listaEquipos != null) {
            for (EquipoListadoDTO eq : listaEquipos) {
    %>
    <tr>
        <td><%= eq.getId() %></td>
        <td><%= eq.getHostname() %></td>
        <td><%= eq.getTipo() %></td>
        <td><%= eq.getIpGestion() %></td>
        <td><%= eq.getEstado() %></td>
        <td><%= eq.getSedeNombre() %></td>
        <td><%= eq.getCiudad() %></td>
        <td><%= eq.getMesesOperativos() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>

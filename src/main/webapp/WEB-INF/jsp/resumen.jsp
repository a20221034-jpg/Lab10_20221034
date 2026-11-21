<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.dto.ResumenSedeDTO" %>
<%
    ArrayList<ResumenSedeDTO> listaResumen =
            (ArrayList<ResumenSedeDTO>) request.getAttribute("listaResumen");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resumen por sede - TelecomUniverse</title>
</head>
<body>

<%@ include file="navbar.jspf" %>

<h2>Resumen de equipos por sede</h2>

<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>Sede</th>
        <th>Total equipos</th>
        <th>Operativos</th>
        <th>Caidos</th>
    </tr>
    <%
        if (listaResumen != null) {
            for (ResumenSedeDTO r : listaResumen) {
    %>
    <tr>
        <td><%= r.getSedeNombre() %></td>
        <td><%= r.getTotalEquipos() %></td>
        <td><%= r.getOperativos() %></td>
        <td><%= r.getCaidos() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>

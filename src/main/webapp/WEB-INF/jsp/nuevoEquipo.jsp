<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.beans.Sede" %>
<%
    ArrayList<Sede> listaSedes =
            (ArrayList<Sede>) request.getAttribute("listaSedes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nuevo equipo - TelecomUniverse</title>
</head>
<body>

<%@ include file="navbar.jspf" %>

<h2>Nuevo equipo</h2>

<form action="<%= request.getContextPath() %>/equipos" method="post">

    <p>
        Hostname:
        <input type="text" name="hostname" required>
    </p>

    <p>
        Tipo:
        <select name="tipo" required>
            <option value="router">router</option>
            <option value="switch">switch</option>
            <option value="firewall">firewall</option>
            <option value="access point">access point</option>
        </select>
    </p>

    <p>
        IP de gestion:
        <input type="text" name="ip_gestion" required>
    </p>

    <p>
        Estado:
        <select name="estado" required>
            <option value="operativo">operativo</option>
            <option value="en mantenimiento">en mantenimiento</option>
            <option value="fuera de servicio">fuera de servicio</option>
        </select>
    </p>

    <p>
        Fecha de alta:
        <input type="date" name="fecha_alta" required>
    </p>

    <p>
        Sede:
        <select name="id_sede" required>
            <%
                if (listaSedes != null) {
                    for (Sede s : listaSedes) {
            %>
            <option value="<%= s.getIdSede() %>"><%= s.getNombre() %> - <%= s.getCiudad() %></option>
            <%
                    }
                }
            %>
        </select>
    </p>

    <p>
        <button type="submit">Guardar</button>
        <a href="<%= request.getContextPath() %>/equipos">Cancelar</a>
    </p>

</form>

</body>
</html>

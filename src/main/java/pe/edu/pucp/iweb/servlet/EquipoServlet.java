package pe.edu.pucp.iweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.pucp.iweb.beans.EquipoRed;
import pe.edu.pucp.iweb.dao.EquipoDao;
import pe.edu.pucp.iweb.dao.SedeDao;

import java.io.IOException;

@WebServlet("/equipos")
public class EquipoServlet extends HttpServlet {

    private final EquipoDao equipoDao = new EquipoDao();
    private final SedeDao sedeDao = new SedeDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }
        if (action.equals("nuevo")) {
            // Carga las sedes para el combo del formulario
            request.setAttribute("listaSedes", sedeDao.listarSedes());
            request.getRequestDispatcher("/WEB-INF/jsp/nuevoEquipo.jsp")
                   .forward(request, response);
        } else {
            request.setAttribute("listaEquipos", equipoDao.listarEquipos());
            request.getRequestDispatcher("/WEB-INF/jsp/listarEquipos.jsp")
                   .forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Arma el equipo con los datos del formulario
        EquipoRed equipo = new EquipoRed();
        equipo.setHostname(request.getParameter("hostname"));
        equipo.setTipo(request.getParameter("tipo"));
        equipo.setIpGestion(request.getParameter("ip_gestion"));
        equipo.setEstado(request.getParameter("estado"));
        equipo.setFechaAlta(request.getParameter("fecha_alta"));
        equipo.setIdSede(Integer.parseInt(request.getParameter("id_sede")));

        equipoDao.insertarEquipo(equipo);

        // Redirige al listado
        response.sendRedirect(request.getContextPath() + "/equipos");
    }
}
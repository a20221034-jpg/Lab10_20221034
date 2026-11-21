package pe.edu.pucp.iweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.pucp.iweb.dao.EquipoDao;

import java.io.IOException;

@WebServlet("/resumen")
public class ResumenServlet extends HttpServlet {

    private final EquipoDao equipoDao = new EquipoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listaResumen", equipoDao.resumenPorSede());
        request.getRequestDispatcher("/WEB-INF/jsp/resumen.jsp")
               .forward(request, response);
    }
}

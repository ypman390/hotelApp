package com.hotel.servlet;



import com.hotel.dao.impl.ReservaDAOImpl;
import com.hotel.dao.impl.ServicioExtraDAOImpl;
import com.hotel.hotelapp.dao.ReservaDAO;
import com.hotel.hotelapp.dao.ServicioExtraDAO;
import com.hotel.model.ServicioExtra;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/servicios")
public class ServicioExtraServlet extends HttpServlet {

    private ServicioExtraDAO servicioExtraDAO;
    private ReservaDAO reservaDAO;

    @Override
    public void init() {
        servicioExtraDAO = new ServicioExtraDAOImpl();
        reservaDAO = new ReservaDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                listar(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {
            insertar(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizar(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ServicioExtra> servicios = servicioExtraDAO.findAll();
        request.setAttribute("servicios", servicios);
        request.setAttribute("reservas", reservaDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/servicios/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("reservas", reservaDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/servicios/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ServicioExtra servicio = servicioExtraDAO.findById(id);
        request.setAttribute("servicio", servicio);
        request.setAttribute("reservas", reservaDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/servicios/formulario.jsp")
                .forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ServicioExtra servicio = obtenerServicioDeRequest(request);
        servicioExtraDAO.insert(servicio);
        response.sendRedirect(request.getContextPath() + "/servicios");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ServicioExtra servicio = obtenerServicioDeRequest(request);
        servicio.setId(Integer.parseInt(request.getParameter("id")));
        servicioExtraDAO.update(servicio);
        response.sendRedirect(request.getContextPath() + "/servicios");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        servicioExtraDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/servicios");
    }

    private ServicioExtra obtenerServicioDeRequest(HttpServletRequest request) {
        ServicioExtra servicio = new ServicioExtra();
        servicio.setIdReserva(Integer.parseInt(request.getParameter("idReserva")));
        servicio.setNombre(request.getParameter("nombre"));
        servicio.setDescripcion(request.getParameter("descripcion"));
        servicio.setPrecio(new BigDecimal(request.getParameter("precio")));
        servicio.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        servicio.setFechaServicio(LocalDate.parse(request.getParameter("fechaServicio")));
        servicio.setIncluido(request.getParameter("incluido") != null);
        return servicio;
    }
}

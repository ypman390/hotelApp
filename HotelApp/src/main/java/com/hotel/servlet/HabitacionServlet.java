package com.hotel.servlet;

import com.hotel.hotelapp.dao.HabitacionDAO;
import com.hotel.dao.impl.HabitacionDAOImpl;
import com.hotel.model.Habitacion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/habitaciones")
public class HabitacionServlet extends HttpServlet {

    private HabitacionDAO habitacionDAO;

    @Override
    public void init() {
        habitacionDAO = new HabitacionDAOImpl();
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
        List<Habitacion> habitaciones = habitacionDAO.findAll();
        request.setAttribute("habitaciones", habitaciones);
        request.getRequestDispatcher("/WEB-INF/jsp/habitaciones/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/habitaciones/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Habitacion habitacion = habitacionDAO.findById(id);
        request.setAttribute("habitacion", habitacion);
        request.getRequestDispatcher("/WEB-INF/jsp/habitaciones/formulario.jsp")
                .forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Habitacion habitacion = obtenerHabitacionDeRequest(request);
        habitacionDAO.insert(habitacion);
        response.sendRedirect(request.getContextPath() + "/habitaciones");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Habitacion habitacion = obtenerHabitacionDeRequest(request);
        habitacion.setId(Integer.parseInt(request.getParameter("id")));
        habitacionDAO.update(habitacion);
        response.sendRedirect(request.getContextPath() + "/habitaciones");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        habitacionDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/habitaciones");
    }

    private Habitacion obtenerHabitacionDeRequest(HttpServletRequest request) {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero(Integer.parseInt(request.getParameter("numero")));
        habitacion.setTipo(request.getParameter("tipo"));
        habitacion.setDescripcion(request.getParameter("descripcion"));
        habitacion.setPrecioNoche(new BigDecimal(request.getParameter("precioNoche")));
        habitacion.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
        habitacion.setFechaUltimaLimpieza(LocalDate.parse(request.getParameter("fechaUltimaLimpieza")));
        habitacion.setDisponible(request.getParameter("disponible") != null);
        return habitacion;
    }
}
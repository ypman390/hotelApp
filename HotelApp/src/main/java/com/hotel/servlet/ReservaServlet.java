package com.hotel.servlet;


import com.hotel.dao.impl.ClienteDAOImpl;
import com.hotel.dao.impl.HabitacionDAOImpl;
import com.hotel.dao.impl.ReservaDAOImpl;
import com.hotel.hotelapp.dao.ClienteDAO;
import com.hotel.hotelapp.dao.HabitacionDAO;
import com.hotel.hotelapp.dao.ReservaDAO;
import com.hotel.model.Reserva;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/reservas")
public class ReservaServlet extends HttpServlet {

    private ReservaDAO reservaDAO;
    private ClienteDAO clienteDAO;
    private HabitacionDAO habitacionDAO;

    @Override
    public void init() {
        reservaDAO = new ReservaDAOImpl();
        clienteDAO = new ClienteDAOImpl();
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
            case "confirmar":
                confirmar(request, response);
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
        List<Reserva> reservas = reservaDAO.findAll();
        request.setAttribute("reservas", reservas);
        request.setAttribute("clientes", clienteDAO.findAll());
        request.setAttribute("habitaciones", habitacionDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/reservas/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("clientes", clienteDAO.findAll());
        request.setAttribute("habitaciones", habitacionDAO.findDisponibles());
        request.getRequestDispatcher("/WEB-INF/jsp/reservas/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Reserva reserva = reservaDAO.findById(id);
        request.setAttribute("reserva", reserva);
        request.setAttribute("clientes", clienteDAO.findAll());
        request.setAttribute("habitaciones", habitacionDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/reservas/formulario.jsp")
                .forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Reserva reserva = obtenerReservaDeRequest(request);
        reservaDAO.insert(reserva);
        response.sendRedirect(request.getContextPath() + "/reservas");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Reserva reserva = obtenerReservaDeRequest(request);
        reserva.setId(Integer.parseInt(request.getParameter("id")));
        reservaDAO.update(reserva);
        response.sendRedirect(request.getContextPath() + "/reservas");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        reservaDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/reservas");
    }

    private void confirmar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        reservaDAO.confirmar(id);
        response.sendRedirect(request.getContextPath() + "/reservas");
    }

    private Reserva obtenerReservaDeRequest(HttpServletRequest request) {
        Reserva reserva = new Reserva();
        reserva.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        reserva.setIdHabitacion(Integer.parseInt(request.getParameter("idHabitacion")));
        reserva.setFechaEntrada(LocalDate.parse(request.getParameter("fechaEntrada")));
        reserva.setFechaSalida(LocalDate.parse(request.getParameter("fechaSalida")));
        reserva.setPrecioTotal(new BigDecimal(request.getParameter("precioTotal")));
        reserva.setNumPersonas(Integer.parseInt(request.getParameter("numPersonas")));
        reserva.setObservaciones(request.getParameter("observaciones"));
        reserva.setConfirmada(request.getParameter("confirmada") != null);
        return reserva;
    }
}
package com.hotel.servlet;

import com.hotel.hotelapp.dao.ClienteDAO;
import com.hotel.dao.impl.ClienteDAOImpl;
import com.hotel.model.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAOImpl();
    }

    // GET → listar o mostrar formulario
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

    // POST → insertar o actualizar
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
        List<Cliente> clientes = clienteDAO.findAll();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/WEB-INF/jsp/clientes/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/clientes/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.findById(id);
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("/WEB-INF/jsp/clientes/formulario.jsp")
                .forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Cliente cliente = obtenerClienteDeRequest(request);
        clienteDAO.insert(cliente);
        response.sendRedirect(request.getContextPath() + "/clientes");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Cliente cliente = obtenerClienteDeRequest(request);
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        clienteDAO.update(cliente);
        response.sendRedirect(request.getContextPath() + "/clientes");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        clienteDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/clientes");
    }

    private Cliente obtenerClienteDeRequest(HttpServletRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setApellidos(request.getParameter("apellidos"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setDni(request.getParameter("dni"));
        cliente.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
        cliente.setSaldoPuntos(new BigDecimal(request.getParameter("saldoPuntos")));
        cliente.setNumEstancias(Integer.parseInt(request.getParameter("numEstancias")));
        cliente.setActivo(request.getParameter("activo") != null);
        return cliente;
    }
}
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4">
  <h2><i class="bi bi-calendar-check"></i> Reservas</h2>
  <a href="${pageContext.request.contextPath}/reservas?accion=nuevo"
     class="btn btn-primary">
    <i class="bi bi-plus-circle"></i> Nueva Reserva
  </a>
</div>

<div class="card">
  <div class="card-body">
    <table class="table table-hover align-middle">
      <thead>
      <tr>
        <th>#</th>
        <th>Cliente</th>
        <th>Habitación</th>
        <th>Entrada</th>
        <th>Salida</th>
        <th>Personas</th>
        <th>Precio Total</th>
        <th>Confirmada</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="reserva" items="${reservas}">
        <tr>
          <td>${reserva.id}</td>
          <td>
            <c:forEach var="cliente" items="${clientes}">
              <c:if test="${cliente.id == reserva.idCliente}">
                ${cliente.nombre} ${cliente.apellidos}
              </c:if>
            </c:forEach>
          </td>
          <td>
            <c:forEach var="habitacion" items="${habitaciones}">
              <c:if test="${habitacion.id == reserva.idHabitacion}">
                Hab. ${habitacion.numero} (${habitacion.tipo})
              </c:if>
            </c:forEach>
          </td>
          <td>${reserva.fechaEntrada}</td>
          <td>${reserva.fechaSalida}</td>
          <td>${reserva.numPersonas}</td>
          <td>${reserva.precioTotal} €</td>
          <td>
            <c:choose>
              <c:when test="${reserva.confirmada}">
                <span class="badge bg-success">Confirmada</span>
              </c:when>
              <c:otherwise>
                <span class="badge bg-warning text-dark">Pendiente</span>
              </c:otherwise>
            </c:choose>
          </td>
          <td>
            <c:if test="${!reserva.confirmada}">
              <a href="${pageContext.request.contextPath}/reservas?accion=confirmar&id=${reserva.id}"
                 class="btn btn-sm btn-success"
                 onclick="return confirm('¿Confirmar esta reserva?')">
                <i class="bi bi-check-circle"></i>
              </a>
            </c:if>
            <a href="${pageContext.request.contextPath}/reservas?accion=editar&id=${reserva.id}"
               class="btn btn-sm btn-warning">
              <i class="bi bi-pencil"></i>
            </a>
            <a href="${pageContext.request.contextPath}/reservas?accion=eliminar&id=${reserva.id}"
               class="btn btn-sm btn-danger"
               onclick="return confirm('¿Eliminar esta reserva?')">
              <i class="bi bi-trash"></i>
            </a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/plantilla_fin.jsp" %>
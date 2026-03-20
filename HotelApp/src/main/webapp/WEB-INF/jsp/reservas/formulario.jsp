<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>
        <i class="bi bi-calendar-${empty reserva ? 'plus' : 'check'}"></i>
        ${empty reserva ? 'Nueva Reserva' : 'Editar Reserva'}
    </h2>
    <a href="${pageContext.request.contextPath}/reservas" class="btn btn-secondary">
        <i class="bi bi-arrow-left"></i> Volver
    </a>
</div>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/reservas" method="post">
            <input type="hidden" name="accion" value="${empty reserva ? 'insertar' : 'actualizar'}">
            <c:if test="${not empty reserva}">
                <input type="hidden" name="id" value="${reserva.id}">
            </c:if>

            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Cliente</label>
                    <select name="idCliente" class="form-select" required>
                        <option value="">-- Selecciona cliente --</option>
                        <c:forEach var="cliente" items="${clientes}">
                            <option value="${cliente.id}"
                                ${reserva.idCliente == cliente.id ? 'selected' : ''}>
                                    ${cliente.nombre} ${cliente.apellidos}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Habitación</label>
                    <select name="idHabitacion" class="form-select" required>
                        <option value="">-- Selecciona habitación --</option>
                        <c:forEach var="habitacion" items="${habitaciones}">
                            <option value="${habitacion.id}"
                                ${reserva.idHabitacion == habitacion.id ? 'selected' : ''}>
                                Hab. ${habitacion.numero} - ${habitacion.tipo} (${habitacion.precioNoche}€/noche)
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Fecha Entrada</label>
                    <input type="date" name="fechaEntrada" class="form-control"
                           value="${reserva.fechaEntrada}" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Fecha Salida</label>
                    <input type="date" name="fechaSalida" class="form-control"
                           value="${reserva.fechaSalida}" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Nº Personas</label>
                    <input type="number" name="numPersonas" class="form-control"
                           value="${empty reserva ? '1' : reserva.numPersonas}" min="1" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Precio Total (€)</label>
                    <input type="number" step="0.01" name="precioTotal" class="form-control"
                           value="${reserva.precioTotal}" required>
                </div>
                <div class="col-md-8">
                    <label class="form-label">Observaciones</label>
                    <input type="text" name="observaciones" class="form-control"
                           value="${reserva.observaciones}">
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <div class="form-check mb-2">
                        <input type="checkbox" name="confirmada" class="form-check-input"
                               id="confirmada" ${reserva.confirmada ? 'checked' : ''}>
                        <label class="form-check-label" for="confirmada">Confirmada</label>
                    </div>
                </div>
            </div>

            <div class="mt-4">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save"></i> Guardar
                </button>
                <a href="${pageContext.request.contextPath}/reservas" class="btn btn-secondary ms-2">
                    Cancelar
                </a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/plantilla_fin.jsp" %>
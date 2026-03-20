<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>
        <i class="bi bi-star"></i>
        ${empty servicio ? 'Nuevo Servicio' : 'Editar Servicio'}
    </h2>
    <a href="${pageContext.request.contextPath}/servicios" class="btn btn-secondary">
        <i class="bi bi-arrow-left"></i> Volver
    </a>
</div>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/servicios" method="post">
            <input type="hidden" name="accion" value="${empty servicio ? 'insertar' : 'actualizar'}">
            <c:if test="${not empty servicio}">
                <input type="hidden" name="id" value="${servicio.id}">
            </c:if>

            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Reserva</label>
                    <select name="idReserva" class="form-select" required>
                        <option value="">-- Selecciona reserva --</option>
                        <c:forEach var="reserva" items="${reservas}">
                            <option value="${reserva.id}"
                                ${servicio.idReserva == reserva.id ? 'selected' : ''}>
                                Reserva #${reserva.id} (${reserva.fechaEntrada} - ${reserva.fechaSalida})
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Nombre del Servicio</label>
                    <input type="text" name="nombre" class="form-control"
                           value="${servicio.nombre}" required>
                </div>
                <div class="col-md-12">
                    <label class="form-label">Descripción</label>
                    <textarea name="descripcion" class="form-control" rows="2">${servicio.descripcion}</textarea>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Precio (€)</label>
                    <input type="number" step="0.01" name="precio" class="form-control"
                           value="${servicio.precio}" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Cantidad</label>
                    <input type="number" name="cantidad" class="form-control"
                           value="${empty servicio ? '1' : servicio.cantidad}" min="1" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Fecha del Servicio</label>
                    <input type="date" name="fechaServicio" class="form-control"
                           value="${servicio.fechaServicio}" required>
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <div class="form-check mb-2">
                        <input type="checkbox" name="incluido" class="form-check-input"
                               id="incluido" ${servicio.incluido ? 'checked' : ''}>
                        <label class="form-check-label" for="incluido">Incluido en reserva</label>
                    </div>
                </div>
            </div>

            <div class="mt-4">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save"></i> Guardar
                </button>
                <a href="${pageContext.request.contextPath}/servicios" class="btn btn-secondary ms-2">
                    Cancelar
                </a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/plantilla_fin.jsp" %>
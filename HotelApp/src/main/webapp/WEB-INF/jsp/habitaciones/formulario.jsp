<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>
        <i class="bi bi-door-${empty habitacion ? 'open' : 'closed'}"></i>
        ${empty habitacion ? 'Nueva Habitación' : 'Editar Habitación'}
    </h2>
    <a href="${pageContext.request.contextPath}/habitaciones" class="btn btn-secondary">
        <i class="bi bi-arrow-left"></i> Volver
    </a>
</div>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/habitaciones" method="post">
            <input type="hidden" name="accion" value="${empty habitacion ? 'insertar' : 'actualizar'}">
            <c:if test="${not empty habitacion}">
                <input type="hidden" name="id" value="${habitacion.id}">
            </c:if>

            <div class="row g-3">
                <div class="col-md-4">
                    <label class="form-label">Número</label>
                    <input type="number" name="numero" class="form-control"
                           value="${habitacion.numero}" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tipo</label>
                    <select name="tipo" class="form-select" required>
                        <option value="">-- Selecciona --</option>
                        <option value="Simple"   ${habitacion.tipo == 'Simple'   ? 'selected' : ''}>Simple</option>
                        <option value="Doble"    ${habitacion.tipo == 'Doble'    ? 'selected' : ''}>Doble</option>
                        <option value="Suite"    ${habitacion.tipo == 'Suite'    ? 'selected' : ''}>Suite</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Capacidad</label>
                    <input type="number" name="capacidad" class="form-control"
                           value="${habitacion.capacidad}" required>
                </div>
                <div class="col-md-12">
                    <label class="form-label">Descripción</label>
                    <textarea name="descripcion" class="form-control" rows="2">${habitacion.descripcion}</textarea>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Precio por Noche (€)</label>
                    <input type="number" step="0.01" name="precioNoche" class="form-control"
                           value="${habitacion.precioNoche}" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Fecha Última Limpieza</label>
                    <input type="date" name="fechaUltimaLimpieza" class="form-control"
                           value="${habitacion.fechaUltimaLimpieza}" required>
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <div class="form-check mb-2">
                        <input type="checkbox" name="disponible" class="form-check-input"
                               id="disponible" ${empty habitacion || habitacion.disponible ? 'checked' : ''}>
                        <label class="form-check-label" for="disponible">Disponible</label>
                    </div>
                </div>
            </div>

            <div class="mt-4">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save"></i> Guardar
                </button>
                <a href="${pageContext.request.contextPath}/habitaciones" class="btn btn-secondary ms-2">
                    Cancelar
                </a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/plantilla_fin.jsp" %>
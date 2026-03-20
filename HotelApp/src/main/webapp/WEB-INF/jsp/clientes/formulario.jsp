<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>
        <i class="bi bi-person-${empty cliente ? 'plus' : 'gear'}"></i>
        ${empty cliente ? 'Nuevo Cliente' : 'Editar Cliente'}
    </h2>
    <a href="${pageContext.request.contextPath}/clientes" class="btn btn-secondary">
        <i class="bi bi-arrow-left"></i> Volver
    </a>
</div>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/clientes" method="post">
            <input type="hidden" name="accion" value="${empty cliente ? 'insertar' : 'actualizar'}">
            <c:if test="${not empty cliente}">
                <input type="hidden" name="id" value="${cliente.id}">
            </c:if>

            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Nombre</label>
                    <input type="text" name="nombre" class="form-control"
                           value="${cliente.nombre}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Apellidos</label>
                    <input type="text" name="apellidos" class="form-control"
                           value="${cliente.apellidos}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control"
                           value="${cliente.email}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Teléfono</label>
                    <input type="text" name="telefono" class="form-control"
                           value="${cliente.telefono}">
                </div>
                <div class="col-md-6">
                    <label class="form-label">DNI</label>
                    <input type="text" name="dni" class="form-control"
                           value="${cliente.dni}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Fecha de Nacimiento</label>
                    <input type="date" name="fechaNacimiento" class="form-control"
                           value="${cliente.fechaNacimiento}" required>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Saldo Puntos</label>
                    <input type="number" step="0.01" name="saldoPuntos" class="form-control"
                           value="${empty cliente ? '0.00' : cliente.saldoPuntos}">
                </div>
                <div class="col-md-4">
                    <label class="form-label">Nº Estancias</label>
                    <input type="number" name="numEstancias" class="form-control"
                           value="${empty cliente ? '0' : cliente.numEstancias}">
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <div class="form-check mb-2">
                        <input type="checkbox" name="activo" class="form-check-input"
                               id="activo" ${empty cliente || cliente.activo ? 'checked' : ''}>
                        <label class="form-check-label" for="activo">Cliente Activo</label>
                    </div>
                </div>
            </div>

            <div class="mt-4">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save"></i> Guardar
                </button>
                <a href="${pageContext.request.contextPath}/clientes" class="btn btn-secondary ms-2">
                    Cancelar
                </a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/plantilla_fin.jsp" %>
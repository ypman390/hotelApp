<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="mb-4">
    <h2><i class="bi bi-speedometer2"></i> Dashboard</h2>
    <p class="text-muted">Bienvenido al sistema de gestión del hotel</p>
</div>

<!-- Tarjetas resumen -->
<div class="row g-4 mb-4">
    <div class="col-md-3">
        <div class="card text-white" style="background: linear-gradient(135deg, #0f3460, #16213e);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h6 class="card-title mb-1">Clientes</h6>
                    <h2 class="mb-0">Gestionar</h2>
                </div>
                <i class="bi bi-people" style="font-size: 3rem; opacity: 0.5;"></i>
            </div>
            <div class="card-footer bg-transparent border-0">
                <a href="${pageContext.request.contextPath}/clientes"
                   class="text-white text-decoration-none small">
                    Ver todos <i class="bi bi-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card text-white" style="background: linear-gradient(135deg, #0d6efd, #0a58ca);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h6 class="card-title mb-1">Habitaciones</h6>
                    <h2 class="mb-0">Gestionar</h2>
                </div>
                <i class="bi bi-door-closed" style="font-size: 3rem; opacity: 0.5;"></i>
            </div>
            <div class="card-footer bg-transparent border-0">
                <a href="${pageContext.request.contextPath}/habitaciones"
                   class="text-white text-decoration-none small">
                    Ver todas <i class="bi bi-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card text-white" style="background: linear-gradient(135deg, #198754, #146c43);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h6 class="card-title mb-1">Reservas</h6>
                    <h2 class="mb-0">Gestionar</h2>
                </div>
                <i class="bi bi-calendar-check" style="font-size: 3rem; opacity: 0.5;"></i>
            </div>
            <div class="card-footer bg-transparent border-0">
                <a href="${pageContext.request.contextPath}/reservas"
                   class="text-white text-decoration-none small">
                    Ver todas <i class="bi bi-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card text-white" style="background: linear-gradient(135deg, #ffc107, #d39e00);">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h6 class="card-title mb-1">Servicios Extra</h6>
                    <h2 class="mb-0">Gestionar</h2>
                </div>
                <i class="bi bi-star" style="font-size: 3rem; opacity: 0.5;"></i>
            </div>
            <div class="card-footer bg-transparent border-0">
                <a href="${pageContext.request.contextPath}/servicios"
                   class="text-white text-decoration-none small">
                    Ver todos <i class="bi bi-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
</div>

<!-- Accesos rápidos -->
<div class="row g-4">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h5 class="mb-0"><i class="bi bi-lightning"></i> Accesos Rápidos</h5>
            </div>
            <div class="card-body d-grid gap-2">
                <a href="${pageContext.request.contextPath}/clientes?accion=nuevo"
                   class="btn btn-outline-primary">
                    <i class="bi bi-person-plus"></i> Nuevo Cliente
                </a>
                <a href="${pageContext.request.contextPath}/habitaciones?accion=nuevo"
                   class="btn btn-outline-primary">
                    <i class="bi bi-plus-square"></i> Nueva Habitación
                </a>
                <a href="${pageContext.request.contextPath}/reservas?accion=nuevo"
                   class="btn btn-outline-success">
                    <i class="bi bi-calendar-plus"></i> Nueva Reserva
                </a>
                <a href="${pageContext.request.contextPath}/servicios?accion=nuevo"
                   class="btn btn-outline-warning">
                    <i class="bi bi-star"></i> Nuevo Servicio Extra
                </a>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h5 class="mb-0"><i class="bi bi-info-circle"></i> Información del Sistema</h5>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Aplicación</span>
                        <strong>Hotel Manager</strong>
                    </li>
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Versión</span>
                        <strong>1.0</strong>
                    </li>
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Base de Datos</span>
                        <strong>MariaDB</strong>
                    </li>
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Servidor</span>
                        <strong>Apache Tomcat 11</strong>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/plantilla_fin.jsp" %>
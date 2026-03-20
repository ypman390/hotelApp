<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2><i class="bi bi-star"></i> Servicios Extra</h2>
    <a href="${pageContext.request.contextPath}/servicios?accion=nuevo"
       class="btn btn-primary">
        <i class="bi bi-plus-circle"></i> Nuevo Servicio
    </a>
</div>

<div class="card">
    <div class="card-body">
        <table class="table table-hover align-middle">
            <thead>
            <tr>
                <th>#</th>
                <th>Reserva</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Fecha</th>
                <th>Incluido</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="servicio" items="${servicios}">
                <tr>
                    <td>${servicio.id}</td>
                    <td>
                        <c:forEach var="reserva" items="${reservas}">
                            <c:if test="${reserva.id == servicio.idReserva}">
                                Reserva #${reserva.id}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${servicio.nombre}</td>
                    <td>${servicio.descripcion}</td>
                    <td>${servicio.precio} €</td>
                    <td>${servicio.cantidad}</td>
                    <td>${servicio.fechaServicio}</td>
                    <td>
                        <c:choose>
                            <c:when test="${servicio.incluido}">
                                <span class="badge bg-success">Incluido</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-secondary">Extra</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/servicios?accion=editar&id=${servicio.id}"
                           class="btn btn-sm btn-warning">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/servicios?accion=eliminar&id=${servicio.id}"
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('¿Eliminar este servicio?')">
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2><i class="bi bi-door-closed"></i> Habitaciones</h2>
    <a href="${pageContext.request.contextPath}/habitaciones?accion=nuevo"
       class="btn btn-primary">
        <i class="bi bi-plus-circle"></i> Nueva Habitación
    </a>
</div>

<div class="card">
    <div class="card-body">
        <table class="table table-hover align-middle">
            <thead>
            <tr>
                <th>#</th>
                <th>Número</th>
                <th>Tipo</th>
                <th>Descripción</th>
                <th>Precio/Noche</th>
                <th>Capacidad</th>
                <th>Última Limpieza</th>
                <th>Disponible</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="habitacion" items="${habitaciones}">
                <tr>
                    <td>${habitacion.id}</td>
                    <td>${habitacion.numero}</td>
                    <td>
                        <c:choose>
                            <c:when test="${habitacion.tipo == 'Suite'}">
                                <span class="badge bg-warning text-dark">${habitacion.tipo}</span>
                            </c:when>
                            <c:when test="${habitacion.tipo == 'Doble'}">
                                <span class="badge bg-info text-dark">${habitacion.tipo}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-secondary">${habitacion.tipo}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${habitacion.descripcion}</td>
                    <td>${habitacion.precioNoche} €</td>
                    <td>${habitacion.capacidad} personas</td>
                    <td>${habitacion.fechaUltimaLimpieza}</td>
                    <td>
                        <c:choose>
                            <c:when test="${habitacion.disponible}">
                                <span class="badge bg-success">Disponible</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">Ocupada</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/habitaciones?accion=editar&id=${habitacion.id}"
                           class="btn btn-sm btn-warning">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/habitaciones?accion=eliminar&id=${habitacion.id}"
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('¿Eliminar esta habitación?')">
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
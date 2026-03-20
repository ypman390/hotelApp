<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jsp/plantilla.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2><i class="bi bi-people"></i> Clientes</h2>
    <a href="${pageContext.request.contextPath}/clientes?accion=nuevo"
       class="btn btn-primary">
        <i class="bi bi-plus-circle"></i> Nuevo Cliente
    </a>
</div>

<div class="card">
    <div class="card-body">
        <table class="table table-hover align-middle">
            <thead>
            <tr>
                <th>#</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Teléfono</th>
                <th>DNI</th>
                <th>Puntos</th>
                <th>Estancias</th>
                <th>Activo</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.nombre} ${cliente.apellidos}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.dni}</td>
                    <td>${cliente.saldoPuntos}</td>
                    <td>${cliente.numEstancias}</td>
                    <td>
                        <c:choose>
                            <c:when test="${cliente.activo}">
                                <span class="badge bg-success">Sí</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">No</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/clientes?accion=editar&id=${cliente.id}"
                           class="btn btn-sm btn-warning">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/clientes?accion=eliminar&id=${cliente.id}"
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('¿Eliminar este cliente?')">
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
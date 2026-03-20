<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel Manager</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .sidebar {
            min-height: 100vh;
            background: linear-gradient(180deg, #1a1a2e 0%, #16213e 100%);
        }
        .sidebar .nav-link {
            color: #adb5bd;
            padding: 12px 20px;
            border-radius: 8px;
            margin: 2px 10px;
        }
        .sidebar .nav-link:hover, .sidebar .nav-link.active {
            background-color: #0f3460;
            color: #ffffff;
        }
        .sidebar .nav-link i { margin-right: 10px; }
        .sidebar-brand {
            color: #ffffff;
            font-size: 1.4rem;
            font-weight: bold;
            padding: 20px;
            border-bottom: 1px solid #0f3460;
        }
        .main-content { padding: 30px; }
        .card { border: none; box-shadow: 0 2px 10px rgba(0,0,0,0.08); border-radius: 12px; }
        .card-header { border-radius: 12px 12px 0 0 !important; }
        .btn-primary { background-color: #0f3460; border-color: #0f3460; }
        .btn-primary:hover { background-color: #16213e; border-color: #16213e; }
        .table th { background-color: #f1f3f5; }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">

        <!-- Sidebar -->
        <div class="col-md-2 px-0 sidebar">
            <div class="sidebar-brand">
                <i class="bi bi-building"></i> Hotel Manager
            </div>
            <nav class="nav flex-column mt-3">
                <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">
                    <i class="bi bi-speedometer2"></i> Dashboard
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/clientes">
                    <i class="bi bi-people"></i> Clientes
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/habitaciones">
                    <i class="bi bi-door-closed"></i> Habitaciones
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/reservas">
                    <i class="bi bi-calendar-check"></i> Reservas
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/servicios">
                    <i class="bi bi-star"></i> Servicios Extra
                </a>
            </nav>
        </div>

        <!-- Contenido principal -->
        <div class="col-md-10 main-content">
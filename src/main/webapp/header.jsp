<%--
  Created by IntelliJ IDEA.
  User: PROGRAMACION
  Date: 09/06/2023
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand"> Instituto de Alumnos</a>
      <ul class="nav nav-pills nav-fill">
        <li class="nav-item mx-4 px-4">
          <a class=" btn btn-outline-dark" aria-current="page" href="Controller?opcion=listar">Listar</a>
        </li>
        <li class="nav-item mx-4 px-4">
          <a class="btn btn-outline-dark" href="alta.jsp">Alta</a>
        </li>
      </ul>
      <form class="d-flex" role="search" action="Controller">
        <input type="hidden" name="opcion" value="buscar">
        <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search" name="nombreBusq">
        <button class="btn-outline-dark" type="submit" name="search">Buscar</button>
      </form>
    </div>
  </nav>
</body>
</html>

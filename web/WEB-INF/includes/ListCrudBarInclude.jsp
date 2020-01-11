<%-- 
    Document   : ListCrudBarInclude
    Created on : 21/06/2018, 12:55:53
    Author     : fdippold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ListCrudBarInclude.jsp -->
<a class="btn btn-outline-success  btn-sm" href="${urlToUpdate}${param.id}" title="EDITAR">
    <span class="fas fa-edit"></span>
    <!-- Edit -->
</a>
<a class="btn btn-outline-danger  btn-sm" href="${urlToView}${param.id}" title="VISUALIZAR/APAGAR">
    <span class="fas fa-eraser"></span>
    <!-- Del -->
</a>

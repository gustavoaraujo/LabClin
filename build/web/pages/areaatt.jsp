<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="classes.Atendente"%>
<%
Atendente usu = (Atendente)session.getAttribute("atendenteLog");

if(usu == null) {
    response.sendRedirect("CentralController?page=error");
}

%>
<h3 class="txtLogout">Bem-vindo, <%= usu.getLogin() %>, à área do atendente.</h3><br>
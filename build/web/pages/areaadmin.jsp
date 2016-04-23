<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="classes.Administrador"%>
<%
Administrador usu = (Administrador)session.getAttribute("user");

if(usu == null) {
    response.sendRedirect("index.jsp?action=error");
}

%>
<div class="area">
    <h3>Bem-vindo, <%= usu.getLogin() %>, à área do administrador.</h3><br>
    <table>
        <tr class="tableheader">
            <th>Plano</th>
            <th>Faturamento</th>
        </tr>
        <c:forEach items="${fatPlano}" var="plano">
        <tr>
            <td>${plano.key.nome}</td>
            <td><fmt:formatNumber value="${plano.value}" type="currency"></fmt:formatNumber></td>
        </tr>    
        </c:forEach>
    </table>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>
</div>

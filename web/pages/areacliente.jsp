<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="classes.Cliente"%>
<%
Cliente usu = (Cliente)session.getAttribute("clienteLog");

if(usu == null) {
    response.sendRedirect("CentralController?page=error");
}

%>
<div class="area">
    <h3>Bem-vindo, <%= usu.getNome() %>, à área do cliente.</h3><br>
    <table>
        <thead>
            <tr class="tableheader">
                <th>Preço</th>
                <th>Descrição</th>
                <th>Tempo de Jejum</th>
                <th>Data de pedido</th>
                <th>Data de entrega</th>
                <th>Plano de saúde</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${listaAtt}" var="f">
                    <tr>
                        <td>
                            <c:if test="${f.planosaude == false}">
                                <fmt:formatNumber type="currency" value="${f.preco}"></fmt:formatNumber>
                            </c:if>
                            <c:if test="${f.planosaude == true}">
                                R$ 0,00 (pago pelo Plano)
                            </c:if>
                        </td>
                        <td>${f.descricao}</td>
                        <td>${f.tempojejum}</td>
                        <td><fmt:formatDate type="date" dateStyle="short" value="${f.datapedido}" /></td>
                        <td><fmt:formatDate type="date" dateStyle="short" value="${f.dataentrega}" /></td>
                        <td>
                            <c:forEach items="${listaPlano}" var="p">
                                <c:if test="${p.idplano == f.codplano}">
                                    ${p.nome}
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
        </tbody>
    </table>
</div>

<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${user == null && atendenteLog == null}">
    <c:redirect url="conrol?page=error"/>
</c:if>
<div class="icad">
    <form action="ControllerCentral" class="formleft" style="width: 80%">
        <input type="hidden" name="action" value="buscarexame"/>
        Descrição: <input type="text" name="pesqdescricao" style="width: 78%" />
        <button type="submit" name="btnFunc" value="pesqexame">Pesquisar</button>
    </form><br>
    <form action="ControllerCentral" class="formright" style="margin-top: -2%">
        <button type="submit" name="action" value="cadexame">Novo Exame</button>
    </form>
    <c:if test="${resultado != null}">
        <h3>Resultados</h3>
    </c:if>
    <table>
        <thead>
            <tr>
                <th>Preço</th>
                <th>Descrição</th>
                <th>Tempo de Jejum</th>
                <th>Data de pedido</th>
                <th>Data de entrega</th>
                <th>Plano de saúde</th>
                <th>Cliente</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${listaAtt}" var="f">
                    <tr>
                        <td><fmt:formatNumber type="currency" value="${f.preco}"></fmt:formatNumber></td>
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
                        <td>${f.idcliente.nome}</td>
                        <td style="background: transparent; width: 12%">
                            <form action="ControllerCentral">
                                <input type="hidden" value="${f.idexame}" name="idexame" />
                                <button type="submit" name="action" value="altexame">A</button>
                                <button type="submit" name="action" value="delexame">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
        </tbody>
    </table>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>    
</div>
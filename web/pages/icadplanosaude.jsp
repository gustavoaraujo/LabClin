<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${user == null && atendenteLog == null}">
    <c:redirect url="conrol?page=error"/>
</c:if>
<div class="icad">
    <form action="ControllerCentral" class="formleft" style="width:80%">
        <input type="hidden" name="action" value="buscarplanosaude"/>
        Nome: <input type="text" name="pesqNome" style="width:80%" />
        <button type="submit" name="btnFunc" value="pesqPlanosaude">Pesquisar</button>
    </form>
    <form action="ControllerCentral" class="formright">
        <button type="submit" name="action" value="cadplanosaude">Novo Plano de Saúde</button>
    </form>
    <c:if test="${resultado != null}">
        <h3>Resultados</h3>
    </c:if>
    <table>
        <thead>
            <tr>
                <th>Nome</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${listaAtt}" var="f">
                    <tr>
                        <td>${f.nome}</td>
                        <td style="background: transparent; width: 12%">
                            <form action="ControllerCentral">
                                <input type="hidden" value="${f.idplano}" name="idplano" />
                                <button type="submit" name="action" value="altplanosaude">A</button>
                                <button type="submit" name="action" value="delplanosaude">X</button>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${user == null}">
    <c:redirect url="conrol?page=error"/>
</c:if>
<div class="icad">
    <form action="ControllerCentral" class="formleft" style="width:85%">
        <input type="hidden" name="action" value="buscaratt"/>
        Login: <input type="text" name="pesqLogin" style="width: 80%" />
        <button type="submit" name="btnFunc" value="pesqAtt">Pesquisar</button>
    </form>
    <form action="ControllerCentral" class="formright">
        <button type="submit" name="action" value="cadatendente">Novo Atendente</button>
    </form>
    <c:if test="${resultado != null}">
        <h3>Resultados</h3>
    </c:if>
    <table>
        <thead>
            <tr>
                <th>Login</th>
                <th>Senha</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${listaAtt}" var="f">
                    <tr>
                        <td>${f.login}</td>
                        <td>${f.senha}</td>
                        <td style="background: transparent; width: 12%">
                            <form action="ControllerCentral" >
                                <input type="hidden" value="${f.idatendente}" name="idatendente" />
                                <button type="submit" name="action" value="altatendente">A</button>
                                <button type="submit" name="action" value="delatendente">X</button>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${user == null && atendenteLog == null}">
    <c:redirect url="conrol?page=error"/>
</c:if>
<div class="icad">
    <form action="ControllerCentral" class="formleft" style="width: 85%">
        <input type="hidden" name="action" value="buscarcli"/>
        Cpf: <input type="text" name="pesqCpf" style="width: 85%" />
        <button type="submit" name="btnFunc" value="pesqCli">Pesquisar</button>
    </form>
    <form action="ControllerCentral" class="formright">
        <button type="submit" name="action" value="cadcliente">Novo Cliente</button>
    </form>
    <c:if test="${resultado != null}">
        <h3>Resultados</h3>
    </c:if>
    <table>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Cpf</th>
                <th>Telefone</th>
                <th>Sexo</th>
                <th>Endereço</th>
                <th>Senha</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${listaAtt}" var="f">
                        <tr>
                            <td>${f.nome}</td>
                            <td>${f.cpf}</td>
                            <td>${f.telefone}</td>
                            <td>${f.sexo}</td>
                            <td>${f.endereco}</td>
                            <td>${f.senha}</td>
                            <td style="background: transparent; width: 12%">
                                <form action="ControllerCentral" >
                                    <input type="hidden" value="${f.idCliente}" name="idcliente" />
                                    <button type="submit" name="action" value="altcliente">A</button>
                                    <button type="submit" name="action" value="delcliente">X</button>
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
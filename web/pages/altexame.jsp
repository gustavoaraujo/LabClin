<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .login {
        padding: 4em;
    }
    
    .login form {
        padding: 0 24em;
    }
</style>
<div class="login">
    <h1>Alterar exame</h1>
    <form action="ControllerCentral">
        <input type="hidden" name="action" value="alterarexame" />
        <input type="hidden" name="exameId" value="${att.idexame}"/>
        Descrição: <input type="text" name="descricaoExame" value="${att.descricao}" style="width: 79%"/><br><br>
        Preço: <input type="text" name="precoExame" value="${att.preco}" style="width: 87%"/><br><br>
        Tempo de jejum: <input type="text" name="tempojejumExame" value="${att.tempojejum}" style="width: 69%"/><br><br>
        Cliente: <select name="cliExame" style="width: 85%">
            <c:forEach items="${clientes}" var="c">
                <option value="${c.idCliente}" <c:if test="${c == att.idcliente}">selected</c:if>>${c.nome}</option>
            </c:forEach>
        </select><br><br>
        Plano de saúde? <select name="planosaudeExame" style="width: 71%">
            <option value="true" <c:if test="${att.planosaude == true}">selected</c:if>>Sim</option>
            <option value="false"<c:if test="${att.planosaude == false}">selected</c:if>>Não</option>
        </select><br><br>
        Qual plano? <select name="codplanoExame" style="width: 78%">
            <c:forEach items="${planos}" var="p">
                <option value="${p.idplano}" <c:if test="${p == att.planosaude1}">selected</c:if>>${p.nome}</option>
            </c:forEach>
        </select><br><br>
        Data do Pedido: <input type="date" name="datapedidoExame" value="${pedido}" style="width: 69%"/><br><br>
        Data da Entrega: <input type="date" name="dataentregaExame" value="${entrega}" style="width: 69%"/><br><br>
        <button type="submit" value="examealterar" name="cpBtn">Alterar</button><br>
    </form>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>    
</div>

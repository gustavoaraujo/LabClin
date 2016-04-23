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
    <h1>Cadastrar exame</h1>
    <form action="ControllerCentral">
        <input type="hidden" name="action" value="cadastrarexame" />
        Descrição: <input type="text" name="descricaoExame" style="width: 79%"/><br><br>
        Preço: <input type="text" name="precoExame" style="width: 87%" /><br><br>
        Tempo de jejum: <input type="text" name="tempojejumExame" style="width: 69%" /><br><br>
        Cliente: <select name="cliExame" style="width: 85%">
            <c:forEach items="${clientes}" var="c">
                <option value="${c.idCliente}">${c.nome}</option>
            </c:forEach>
        </select><br><br>
        Plano de saúde? <select name="planosaudeExame" style="width: 71%">
            <option value="true">Sim</option>
            <option value="false">Não</option>
        </select><br><br>
        Qual plano? <select name="codplanoExame" style="width: 78%">
            <c:forEach items="${planos}" var="p">
                <option value="${p.idplano}">${p.nome}</option>
            </c:forEach>
        </select><br><br>
        Data do Pedido: <input type="date" name="datapedidoExame" style="width: 69%" /><br><br>
        Data da Entrega: <input type="date" name="dataentregaExame" style="width: 69%" /><br><br>
        <button type="submit" value="examecadastrar" name="cpBtn">Cadastrar Exame</button><br>
    </form>
    <c:if test="${msg != null}">
        <p>${msg}</p>
    </c:if>    
</div>

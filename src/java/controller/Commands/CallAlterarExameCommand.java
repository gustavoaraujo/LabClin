/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Atendente;
import classes.Cliente;
import classes.Exame;
import classes.Planosaude;
import controller.AtendenteJpaController;
import controller.ClienteJpaController;
import controller.ExameJpaController;
import controller.PlanosaudeJpaController;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class CallAlterarExameCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String descricao = request.getParameter("descricaoExame");
        BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(request.getParameter("precoExame")));
        String tempojejum = request.getParameter("tempojejumExame");
        int cliente = Integer.parseInt(request.getParameter("cliExame"));
        boolean planosaude = Boolean.parseBoolean(request.getParameter("planosaudeExame"));
        int codplano = Integer.parseInt(request.getParameter("codplanoExame"));
        
        String string = request.getParameter("datapedidoExame");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(string);
        
        Date datapedido = date;
        
        string = request.getParameter("dataentregaExame");
        date = format.parse(string);
        
        Date dataentrega = date;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");

        Exame e = new ExameJpaController(emf).findExame(Integer.parseInt(request.getParameter("exameId")));
        
        if(!descricao.equals(e.getDescricao())) {
            e.setDescricao(descricao);
        }
        if(preco != e.getPreco()) {
            e.setPreco(preco);
        }
        if(!tempojejum.equals(e.getTempojejum())) {
            e.setTempojejum(tempojejum);
        }
        if(datapedido != e.getDatapedido()) {
            e.setDatapedido(datapedido);
        }
        if(dataentrega != e.getDataentrega()) {
            e.setDataentrega(dataentrega);
        }
        
        if(planosaude) {
            e.setCodplano(codplano);
        }
        else {
            e.setCodplano(0);
        }
        
        e.setPlanosaude(planosaude);
        
        Cliente c = new ClienteJpaController(emf).findCliente(cliente);
        
        if(c != e.getIdcliente()) {
            e.setIdcliente(c);
        }
        
        new ExameJpaController(emf).edit(e);
        List<Cliente> cli = new ClienteJpaController(emf).findClienteEntities();
        List<Planosaude> plan = new PlanosaudeJpaController(emf).findPlanosaudeEntities();
        
        String entrega = new SimpleDateFormat("yyyy-MM-dd").format(e.getDataentrega());
        String pedido = new SimpleDateFormat("yyyy-MM-dd").format(e.getDatapedido());
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("msg", "Alterado com sucesso");
        request.setAttribute("page", "altexame");
        request.setAttribute("att", e);
        request.setAttribute("clientes", cli);
        request.setAttribute("planos", plan);
        request.setAttribute("pedido", pedido);
        request.setAttribute("entrega", entrega);
        rd.forward(request, response);
        
    }
    
}

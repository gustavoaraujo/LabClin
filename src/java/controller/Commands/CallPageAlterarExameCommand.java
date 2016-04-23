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
public class CallPageAlterarExameCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        Exame a = new ExameJpaController(emf).findExame(Integer.parseInt(request.getParameter("idexame")));

        List<Cliente> cli = new ClienteJpaController(emf).findClienteEntities();
        List<Planosaude> plan = new PlanosaudeJpaController(emf).findPlanosaudeEntities();
        
        String entrega = new SimpleDateFormat("yyyy-MM-dd").format(a.getDataentrega());
        String pedido = new SimpleDateFormat("yyyy-MM-dd").format(a.getDatapedido());
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "altexame");
        request.setAttribute("att", a);
        request.setAttribute("clientes", cli);
        request.setAttribute("planos", plan);
        request.setAttribute("pedido", pedido);
        request.setAttribute("entrega", entrega);
        rd.forward(request, response); 
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Cliente;
import classes.Planosaude;
import controller.ClienteJpaController;
import controller.PlanosaudeJpaController;
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
public class CallPageCadastrarExameCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        
        List<Cliente> listaC = new ClienteJpaController(emf).findClienteEntities();
        List<Planosaude> listaP = new PlanosaudeJpaController(emf).findPlanosaudeEntities();
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("planos", listaP);
        request.setAttribute("clientes", listaC);
        request.setAttribute("page", "cadexame");
        rd.forward(request, response);
        
    }
    
}

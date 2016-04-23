/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Atendente;
import classes.Cliente;
import controller.AtendenteJpaController;
import controller.ClienteJpaController;
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
public class CallPageICADClienteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        
        List<Cliente> listaAtt = new ClienteJpaController(emf).findClienteEntities();
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("listaAtt", listaAtt);
        request.setAttribute("page", "icadcliente");
        rd.forward(request, response);
        
    }
    
}

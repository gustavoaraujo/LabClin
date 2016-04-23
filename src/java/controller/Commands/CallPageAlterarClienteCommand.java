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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class CallPageAlterarClienteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        int idCliente = Integer.parseInt(request.getParameter("idcliente"));
        Cliente a = new ClienteJpaController(emf).findCliente(idCliente);

        if(a != null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("page", "altcliente");
            request.setAttribute("att", a);

            rd.forward(request, response);
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("page", "error");
            request.setAttribute("msg", "Erro de conexão com o banco de dados.");

            rd.forward(request, response);
        }
    }
    
}

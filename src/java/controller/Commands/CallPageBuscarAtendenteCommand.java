/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Atendente;
import controller.AtendenteJpaController;
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
public class CallPageBuscarAtendenteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        
        String login = request.getParameter("pesqLogin");
        
        List<Atendente> listaAtt = new AtendenteJpaController(emf).findAtendenteByLogin(login);
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "icadatendente");
        request.setAttribute("resultado", "r");
        request.setAttribute("listaAtt", listaAtt);
        rd.forward(request, response);
    }
    
}

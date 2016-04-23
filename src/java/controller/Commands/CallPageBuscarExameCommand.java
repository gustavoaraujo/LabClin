/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Atendente;
import classes.Cliente;
import classes.Exame;
import controller.AtendenteJpaController;
import controller.ClienteJpaController;
import controller.ExameJpaController;
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
public class CallPageBuscarExameCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        
        String desc = request.getParameter("pesqdescricao");
        
        List<Exame> listaAtt = new ExameJpaController(emf).findExamePorDescricao(desc);
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "icadexame");
        request.setAttribute("resultado", "r");
        request.setAttribute("listaAtt", listaAtt);
        rd.forward(request, response);
    }
    
}

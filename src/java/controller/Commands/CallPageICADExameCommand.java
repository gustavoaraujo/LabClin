/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Exame;
import classes.Planosaude;
import controller.ExameJpaController;
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
public class CallPageICADExameCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        
        List<Exame> listaAtt = new ExameJpaController(emf).findExameEntities();
        List<Planosaude> listaPlano = new PlanosaudeJpaController(emf).findPlanosaudeEntities();
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "icadexame");
        request.setAttribute("listaAtt", listaAtt);
        request.setAttribute("listaPlano", listaPlano);
        rd.forward(request, response);
        
    }
    
}

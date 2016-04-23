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
public class CallPageDeletarAtendenteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idatendente = Integer.parseInt(request.getParameter("idatendente"));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        
        new AtendenteJpaController(emf).destroy(idatendente);
        
        List<Atendente> listaAtt = new AtendenteJpaController(emf).findAtendenteEntities();
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("listaAtt", listaAtt);
        request.setAttribute("page", "icadatendente");
        request.setAttribute("msg", "Deletado com sucesso");
        rd.forward(request, response);
        
    }
    
}

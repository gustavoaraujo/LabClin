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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class CallAlterarPlanoCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nome = request.getParameter("nomeCli");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        int id = Integer.parseInt(request.getParameter("cliId"));
        Planosaude a = new PlanosaudeJpaController(emf).findPlanosaude(id);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        if(!nome.equals("") && !nome.equals(a.getNome())) {
            a.setNome(nome); 
            new PlanosaudeJpaController(emf).edit(a);
            request.setAttribute("msg", "Alterado com sucesso");
        }

        request.setAttribute("page", "altplanosaude");
        request.setAttribute("att", a);

        rd.forward(request, response);
        
    }
    
}

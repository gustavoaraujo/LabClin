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
public class CallCadastrarPlanosaudeCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nome = request.getParameter("nomePlano");
        
        if(nome != null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
            Planosaude a = new Planosaude(0, nome);
            new PlanosaudeJpaController(emf).create(a);
            request.setAttribute("msg", "Cadastrado com sucesso");
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "cadplanosaude");
        rd.forward(request, response);
    }
    
}

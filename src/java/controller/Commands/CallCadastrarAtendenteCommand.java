/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Atendente;
import controller.AtendenteJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class CallCadastrarAtendenteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("loginAtt");
        String senha = request.getParameter("senhaAtt");
        
        if(login != null && senha != null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
            Atendente a = new Atendente(0, login, senha);
            new AtendenteJpaController(emf).create(a);
            request.setAttribute("msg", "Cadastrado com sucesso.");
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "cadatendente");
        rd.forward(request, response);
    }
    
}

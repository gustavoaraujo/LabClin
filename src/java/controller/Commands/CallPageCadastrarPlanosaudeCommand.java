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
public class CallPageCadastrarPlanosaudeCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "cadplanosaude");
        rd.forward(request, response);
        
    }
    
}

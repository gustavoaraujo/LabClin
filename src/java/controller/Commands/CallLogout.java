/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Administrador;
import controller.AdministradorJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class CallLogout implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "logout");
        rd.forward(request, response);
    }
    
}

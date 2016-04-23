/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Administrador;
import classes.Atendente;
import classes.Planosaude;
import controller.AdministradorJpaController;
import controller.AtendenteJpaController;
import controller.ExameJpaController;
import controller.PlanosaudeJpaController;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class CallPageLoginAtendenteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        try {
            Atendente u = new AtendenteJpaController(emf).findUsuarioByLoginAndSenha(login, senha);

            if (u == null) {
                u = (Atendente)request.getSession().getAttribute("user");
            }
            
            if (u == null) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("page", "atendente");
                request.setAttribute("msg", "Atendente não Encontrado!");
                rd.forward(request, response);
            }
            else {
                request.getSession().setAttribute("atendenteLog", u);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("page", "areaatt");
                rd.forward(request, response);
            }
        } finally {
            if(emf != null) {
                emf.close();
            }
        }
    }
    
}

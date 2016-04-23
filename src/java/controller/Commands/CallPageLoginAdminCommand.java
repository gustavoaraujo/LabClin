/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Administrador;
import classes.Planosaude;
import controller.AdministradorJpaController;
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
public class CallPageLoginAdminCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        try {
            Administrador u = new AdministradorJpaController(emf).findUsuarioByLoginAndSenha(login, senha);

            if (u == null) {
                u = (Administrador)request.getSession().getAttribute("user");
            }
            
            if (u == null) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("page", "administrador");
                request.setAttribute("msg", "Usuário não Encontrado!");
                rd.forward(request, response);
            }
            else {
                
                List<Planosaude> listaPlano = new PlanosaudeJpaController(emf).findPlanosaudeEntities();
                Map<Planosaude,Double> mapaPlano = new ExameJpaController(emf).findFaturamentoPorPlano(listaPlano);
                
                request.getSession().setAttribute("user", u);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("page", "areaadmin");
                request.setAttribute("fatPlano", mapaPlano);
                rd.forward(request, response);
            }
        } finally {
            if(emf != null) {
                emf.close();
            }
        }
    }
    
}

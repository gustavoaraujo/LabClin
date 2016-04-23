/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Administrador;
import classes.Atendente;
import classes.Cliente;
import classes.Exame;
import classes.Planosaude;
import controller.AdministradorJpaController;
import controller.AtendenteJpaController;
import controller.ClienteJpaController;
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
public class CallPageLoginClienteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        try {
            Cliente u = new ClienteJpaController(emf).findUsuarioByLoginAndSenha(login, senha);

            if (u == null) {
                u = (Cliente)request.getSession().getAttribute("clienteLog");
            }
            
            if (u == null) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("page", "cliente");
                request.setAttribute("msg", "Cliente não Encontrado!");
                rd.forward(request, response);
            }
            else {
                List<Exame> listaEx = new ExameJpaController(emf).findExamePorCliente(u.getIdCliente());
                List<Planosaude> listaPlano = new PlanosaudeJpaController(emf).findPlanosaudeEntities();
                
                request.getSession().setAttribute("clienteLog", u);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("page", "areacliente");
                request.setAttribute("listaAtt", listaEx);
                request.setAttribute("listaPlano", listaPlano);
                rd.forward(request, response);
            }
        } finally {
            if(emf != null) {
                emf.close();
            }
        }
    }
    
}

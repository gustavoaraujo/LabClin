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
public class CallAlterarAtendenteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("attLogin");
        String senha = request.getParameter("attSenha");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        int id = Integer.parseInt(request.getParameter("attId"));
        Atendente a = new AtendenteJpaController(emf).findAtendente(id);
        if(!login.equals("") && !login.equals(a.getLogin())) {
            a.setLogin(login); 
        }
        if(!senha.equals("") && !senha.equals(a.getSenha())){
            a.setSenha(senha);
        }
        new AtendenteJpaController(emf).edit(a);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("msg", "Alterado com sucesso");
        request.setAttribute("page", "altatendente");
        request.setAttribute("att", a);
        rd.forward(request, response);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Cliente;
import controller.ClienteJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class CallCadastrarClienteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nome = request.getParameter("nomeCli");
        String cpf = request.getParameter("cpfCli");
        String telefone = request.getParameter("telCli");
        String sexo = request.getParameter("sexoCli");
        String senha = request.getParameter("senhaCli");
        String endereco = request.getParameter("endCli");
        
        if(nome != null 
                && cpf != null
                && telefone != null
                && sexo != null
                && senha != null
                && endereco != null) 
        {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
            Cliente a = new Cliente(0, nome, cpf, telefone, sexo, endereco, senha);
            new ClienteJpaController(emf).create(a);
            request.setAttribute("msg", "Cadastrado com sucesso");
        }
        
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "cadcliente");
        rd.forward(request, response);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Atendente;
import classes.Cliente;
import controller.AtendenteJpaController;
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
public class CallAlterarClienteCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nome = request.getParameter("nomeCli");
        String senha = request.getParameter("senhaCli");
        String cpf = request.getParameter("cpfCli");
        String endereco = request.getParameter("endCli");
        String telefone = request.getParameter("telCli");
        String sexo = request.getParameter("sexoCli");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
        int id = Integer.parseInt(request.getParameter("cliId"));
        Cliente a = new ClienteJpaController(emf).findCliente(id);
        if(!nome.equals("") && !nome.equals(a.getNome())) {
            a.setNome(nome); 
        }
        if(!senha.equals("") && !senha.equals(a.getSenha())){
            a.setSenha(senha);
        }
        if(!cpf.equals("") && !cpf.equals(a.getCpf())){
            a.setCpf(cpf);
        }
        if(!endereco.equals("") && !endereco.equals(a.getEndereco())){
            a.setEndereco(endereco);
        }
        if(!sexo.equals("") && !sexo.equals(a.getSexo())){
            a.setSexo(sexo);
        }
        if(!telefone.equals("") && !telefone.equals(a.getTelefone())){
            a.setTelefone(telefone);
        }
        new ClienteJpaController(emf).edit(a);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("msg", "Alterado com sucesso");
        request.setAttribute("page", "altcliente");
        request.setAttribute("att", a);
        rd.forward(request, response);
        
    }
    
}

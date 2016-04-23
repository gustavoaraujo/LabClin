/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.Commands.CallAlterarAtendenteCommand;
import controller.Commands.CallAlterarClienteCommand;
import controller.Commands.CallAlterarExameCommand;
import controller.Commands.CallAlterarPlanoCommand;
import controller.Commands.CallCadastrarAtendenteCommand;
import controller.Commands.CallCadastrarClienteCommand;
import controller.Commands.CallCadastrarExameCommand;
import controller.Commands.CallCadastrarPlanosaudeCommand;
import controller.Commands.CallLogout;
import controller.Commands.CallPageAdministradorCommand;
import controller.Commands.CallPageAlterarAtendenteCommand;
import controller.Commands.CallPageAlterarClienteCommand;
import controller.Commands.CallPageAlterarExameCommand;
import controller.Commands.CallPageAlterarPlanoCommand;
import controller.Commands.CallPageAtendenteCommand;
import controller.Commands.CallPageBuscarAtendenteCommand;
import controller.Commands.CallPageBuscarClienteCommand;
import controller.Commands.CallPageBuscarExameCommand;
import controller.Commands.CallPageBuscarPlanoSaudeCommand;
import controller.Commands.CallPageCadastrarAtendenteCommand;
import controller.Commands.CallPageCadastrarClienteCommand;
import controller.Commands.CallPageCadastrarExameCommand;
import controller.Commands.CallPageCadastrarPlanosaudeCommand;
import controller.Commands.CallPageClienteCommand;
import controller.Commands.CallPageDeletarAtendenteCommand;
import controller.Commands.CallPageDeletarClienteCommand;
import controller.Commands.CallPageDeletarExameCommand;
import controller.Commands.CallPageDeletarPlanosaudeCommand;
import controller.Commands.CallPageICADAtendenteCommand;
import controller.Commands.CallPageICADClienteCommand;
import controller.Commands.CallPageICADExameCommand;
import controller.Commands.CallPageICADPlanoSaudeCommand;
import controller.Commands.CallPageLoginAdminCommand;
import controller.Commands.CallPageLoginAtendenteCommand;
import controller.Commands.CallPageLoginClienteCommand;
import controller.Commands.CommandApp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class ControllerCentral extends HttpServlet {

    private static final Map<String, CommandApp> comandos = new HashMap<String, CommandApp>();
    
    static{
        
        comandos.put("administrador", new CallPageAdministradorCommand());
        comandos.put("atendente", new CallPageAtendenteCommand());
        comandos.put("cliente", new CallPageClienteCommand());
        
        comandos.put("altatendente", new CallPageAlterarAtendenteCommand());
        comandos.put("altcliente", new CallPageAlterarClienteCommand());
        comandos.put("alterarcli", new CallAlterarClienteCommand());
        comandos.put("alteraratt", new CallAlterarAtendenteCommand());
        comandos.put("alterarexame", new CallAlterarExameCommand());
        comandos.put("altexame", new CallPageAlterarExameCommand());
        comandos.put("altplanosaude", new CallPageAlterarPlanoCommand());
        comandos.put("alterarplano", new CallAlterarPlanoCommand());
        
        comandos.put("areaadmin", new CallPageLoginAdminCommand());
        comandos.put("areaatt", new CallPageLoginAtendenteCommand());
        comandos.put("areacliente", new CallPageLoginClienteCommand());
        
        comandos.put("buscaratt", new CallPageBuscarAtendenteCommand());
        comandos.put("buscarcli", new CallPageBuscarClienteCommand());
        comandos.put("buscarexame", new CallPageBuscarExameCommand());
        comandos.put("buscarplanosaude", new CallPageBuscarPlanoSaudeCommand());
        
        comandos.put("cadastraratt", new CallCadastrarAtendenteCommand());
        comandos.put("cadastrarexame", new CallCadastrarExameCommand());
        comandos.put("cadastrarcli", new CallCadastrarClienteCommand());
        comandos.put("cadastrarplanosaude", new CallCadastrarPlanosaudeCommand());
        comandos.put("cadatendente", new CallPageCadastrarAtendenteCommand());
        comandos.put("cadexame", new CallPageCadastrarExameCommand());
        comandos.put("cadcliente", new CallPageCadastrarClienteCommand());
        comandos.put("cadplanosaude", new CallPageCadastrarPlanosaudeCommand());
        
        comandos.put("delatendente", new CallPageDeletarAtendenteCommand());
        comandos.put("delcliente", new CallPageDeletarClienteCommand());
        comandos.put("delexame", new CallPageDeletarExameCommand());
        comandos.put("delplanosaude", new CallPageDeletarPlanosaudeCommand());
        
        comandos.put("icadatendente", new CallPageICADAtendenteCommand());
        comandos.put("icadcliente", new CallPageICADClienteCommand());
        comandos.put("icadexame", new CallPageICADExameCommand());
        comandos.put("icadplanosaude", new CallPageICADPlanoSaudeCommand());
        comandos.put("logout", new CallLogout());
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String acao = request.getParameter("action");
        try {            
            if (acao == null) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=home");
                rd.forward(request, response);
            } else{ 
                comandos.get(acao).execute(request, response);
            }            
        } catch (Exception ex) {   
            
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("action", "error");
                request.setAttribute("msg", "Erro ao acessar o Comando "+acao+" ("
                        + ex.getMessage()+")");
                rd.forward(request, response);                
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Commands;

import classes.Cliente;
import classes.Exame;
import classes.Planosaude;
import controller.ClienteJpaController;
import controller.ExameJpaController;
import controller.PlanosaudeJpaController;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cliente
 */
public class CallCadastrarExameCommand implements CommandApp {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String descricao = request.getParameter("descricaoExame");
        BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(request.getParameter("precoExame")));
        String tempojejum = request.getParameter("tempojejumExame");
        int cliente = Integer.parseInt(request.getParameter("cliExame"));
        boolean planosaude = Boolean.parseBoolean(request.getParameter("planosaudeExame"));
        int codplano = Integer.parseInt(request.getParameter("codplanoExame"));
        
        String string = request.getParameter("datapedidoExame");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(string);
        
        Date datapedido = date;
        
        string = request.getParameter("dataentregaExame");
        date = format.parse(string);
        
        Date dataentrega = date;
        if(descricao != null &&
                preco != null &&
                tempojejum != null &&
                datapedido != null &&
                dataentrega != null) 
        {
            Exame e = new Exame(0, preco, descricao, tempojejum, datapedido, dataentrega, planosaude);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabClinPU");
            if(planosaude) {
                e.setCodplano(codplano);
            }

            Cliente c = new ClienteJpaController(emf).findCliente(cliente);

            e.setIdcliente(c);

            new ExameJpaController(emf).create(e);
            
            request.setAttribute("msg", "Cadastrado com sucesso.");
        }
        
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "cadexame");
        rd.forward(request, response);
    }
}

/*
 * Copyright 2019 Tulio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package servlets;

import Util.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.EmailAddressValidator;

/**
 *
 * @author Aluno
 */
public class CadastroPessoa extends HttpServlet {

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
            throws ServletException, IOException 
    {
        String usr = request.getParameter("nome");
        String email = request.getParameter("username");
        String cpf = request.getParameter("cpf");
        String conecto = "capaiz";
        
        String sql = "insert into pessoa (nome, email, cpf) values ('" + usr + "', '" + email + "', '" + cpf + "')";
        
        Conexao c = new Conexao();
        
        boolean gravou = c.manipular(sql);
        
        if(gravou)
            conecto = "sim";
        else
            conecto = c.getMensagemErro();
        
        //EmailAddressValidator eav = EmailAddressValidator.getInstance();
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastroPessoa</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastroPessoa at " + request.getContextPath() + "</h1><br>");
            out.println("<h5>Nome:" + usr + "</h5>");
            out.println("<h5>Email :" + email + "</h5>");
            out.println("<h5>CPF :" + cpf + "</h5>");
            out.println("<h5>Conectado :" + conecto + "</h5>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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

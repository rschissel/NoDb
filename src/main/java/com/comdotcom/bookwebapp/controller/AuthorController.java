/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comdotcom.bookwebapp.controller;

import com.comdotcom.bookwebapp.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ryan Schissel
 */
@WebServlet(name = "BookWebAppController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String driverClass; //= "com.mysql.jdbc.Driver";
    private String url; // = "jdbc:mysql://localhost:3306/book";
    private String userName; // = "root";
    private String password; // = "admin";
    private static final String RESULT_PAGE = "index.jsp";
    private static final String ERR_PAGE = "errorpage.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = null;
        AuthorService as = new AuthorService(
                new AuthorDao(
                        new MySqlDbAccessor(), driverClass, url, userName,password
                )
        );
        try {
            response.setContentType("text/html;charset=UTF-8");          

            List<Author> authors = as.getAllAuthors("author", 50);
            request.setAttribute("authors", authors);

            view = request.getRequestDispatcher(RESULT_PAGE);

           
        } catch (ClassNotFoundException ex) {
            view = request.getRequestDispatcher(ERR_PAGE);
        } catch (SQLException ex) {
            view = request.getRequestDispatcher(ERR_PAGE);
        } finally {
            view.forward(request, response);
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
    }
    @Override
    public void init() throws ServletException{
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        userName = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
    }// </editor-fold>

}

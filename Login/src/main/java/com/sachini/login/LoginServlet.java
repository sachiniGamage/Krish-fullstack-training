/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachini.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 94712
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        //accept name and passwords from index file
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
               
        //database
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDB","root","");
            System.out.println("connection");
            
            //get data from db
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("select * from LoginDB where username='"+username+"' and password = '"+password+"'" );
            
            if(result.next()){
                //if username and password correct, go to home.html
                response.sendRedirect("Home.html");
            }else{
                out.println("Wrong user name or password!!! ");
            }
            
            //close the connection
            con.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    

}

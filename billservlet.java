/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;






/**
 *
 * @author hcl
 */
@WebServlet(urlPatterns = {"/billservlet"})
public class billservlet extends HttpServlet {
    Connection con;
    java.sql.Statement st;
    PreparedStatement ps,ps1;
    ResultSet rs,rs1;
    

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet billservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet billservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String billnum=request.getParameter("billnum"),msg;
        int billno=Integer.parseInt(billnum);
        out.println("<html>");
        out.println("<body>");
        try
        {
               Class.forName("org.postgresql.Driver");
               con=DriverManager.getConnection("jdbc:postgresql:ty4","postgres","postgres");
               if(con==null)
                   msg="connection to database failed";
                   else
               {
                    String searchsql="select * from billmaster where billno=?";
                    ps=con.prepareStatement(searchsql);
                    ps.setInt(1,billno);
                    rs=ps.executeQuery();
                    st=con.createStatement();
                    rs1=st.executeQuery("select * from billdeteils where billno="+billno);
                    if(rs!=null && rs1!=null)
                    {
                           rs.next();
                           int i=1;
                           out.println("bill number:"+rs.getInt(1)+"bill date:"+rs.getString(3)+"customer name:"+rs.getString(2));
                           out.println("<br><br>");
                           out.println("sr no.  item name    quantity rate  total");
                           out.println("<br>");
                           int netB=0,sumT=0;
                           while(rs1.next())
                           {
                               sumT=rs1.getInt(3)*rs1.getInt(4);
                               out.println(i+"&nbsp;&nbsp;"+rs1.getString(2)+"&nbsp;&nbsp;"+rs1.getInt(3)+"&nbsp;&nbsp;"+rs1.getInt(4)+"&nbsp;&nbsp;"+sumT);
                               out.println("<br>");
                               netB=netB+sumT;  
                               
                                       
                           }
                                out.println("<br><br>");
                                out.println("net bill:"+netB);
                    }
                       else
                        out.println("invalid bill number");
                    }
        
    }
    catch (ClassNotFoundException | SQLException e){} 
    out.println("</body>");
    out.println("</html>");
                            
               
        
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

    private static class ps {

        public ps() {
        }
    }

}

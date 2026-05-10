/*
 12c.Develop a JDBC project using JDBC to update the fields empno, empname and basicsalary into
the table Emp of the database Employee by getting the fields through JSP
 */


package com.mysql;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int empno = Integer.parseInt(request.getParameter("empno"));
        String empname = request.getParameter("empname");
        double salary = Double.parseDouble(request.getParameter("salary"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employee", "root", "ranjitha@1234");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE emp SET Emp_Name=?, Basicsalary=? WHERE Emp_no=?");

            ps.setString(1, empname);
            ps.setDouble(2, salary);
            ps.setInt(3, empno);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3>Record Updated Successfully!</h3>");
            } else {
                out.println("<h3>Record Not Found!</h3>");
            }

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
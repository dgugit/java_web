package oleukr.servlet;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//@WebServlet(name="GetBooks",urlPatterns={"/getbooks"})

public class GetBooksServlet extends HttpServlet {
    private static String driverName = "org.sqlite.JDBC";
    private static String connectionString = "jdbc:sqlite:/Users/oleguk/Documents/lab2/identifier.sqlite";
    private static String sql = "select * from main.Books where year < ";
    private static final long serialVersionUID = 1213;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String year;
        year = request.getParameter("year_of_book");
        String res = "";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        String htmlResult = "";
        try {
            // create a database connection
            connection = DriverManager.getConnection(connectionString );

            try (
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql + year + " ORDER BY name ;")) {


                htmlResult = "<table border=1 width=50% height=50%>";
                htmlResult += "<tr><th>ID</th><th>Name</th><th>Author</th><th>Year</th><tr>";


                // loop through the result set
                while (rs.next()) {
                    htmlResult += "<tr><td>" + rs.getInt("id") + "</td><td>" +
                            rs.getString("name") + "</td><td>" +
                            rs.getString("author") + "</td><td>" +
                            rs.getInt("year") + "</td></tr>";

//                System.out.println(rs.getInt("id") + "\t" +
//                            rs.getString("name") + "\t" +
//                            rs.getString("author") + "\t" +
//                            rs.getInt("year"));
                }
                htmlResult+="</table>";
                System.out.println(htmlResult);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.out.println("Can't get connection. Incorrect URL");
            System.err.println(e.getMessage());
            System.out.print(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
                System.out.print("+++" + e.getMessage());
            }
        }

        try {
            request.setAttribute("message", htmlResult);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }


}
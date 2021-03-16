package oleukr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBReadServlet {

    private static String driverName = "org.sqlite.JDBC";
    private static String connectionString = "jdbc:sqlite:identifier.sqlite";
    private static String sql = "select * from main.Books where year < ";

    public static String doGet(HttpServletRequest req, HttpServletResponse res ) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
        String year = req.getParameter("year_of_book");
        Connection connection = null;
        String htmlResult = "";
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");

            try (
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql + year + ";")) {



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
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
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
        return htmlResult;
    }
}
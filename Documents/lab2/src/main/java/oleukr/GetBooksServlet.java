package oleukr;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="GetBooks",urlPatterns={"/getbooks"})
public class GetBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1213;
    public GetBooksServlet(){

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<h2>Hello Servlet Two </h2>");
        out.close();

        System.out.println(request.getParameter("year_of_book"));
//        String res = "";
//        try {
//            res = DBReadServlet.doGet(request, response);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            request.setAttribute(res, "message");
//            getServletContext().getRequestDispatcher("/jsp/result.jsp").
//                    include(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
    }


}
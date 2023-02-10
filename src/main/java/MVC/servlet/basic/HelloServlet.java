package MVC.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("HelloServlet.service");

        System.out.println("request = " + request);
        System.out.println("response = " + response);
        String username = request.getParameter("username");     // web에서 ?= 로 queryParameter로 넘긴 값을 받음
        System.out.println("username = " + username);

        response.setContentType("text/plain");      // Http에서 배운 Content-Type
        response.setCharacterEncoding("utf-8");     // 지금부터 한글 가능
        response.getWriter().write("hello " + username);


    }
}

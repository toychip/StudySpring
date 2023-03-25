package MVC.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // Controller에서 view로 이동할때 사용하는 것
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        dispatcher.forward(request, response);   //servlet 에서 jsp 호출
        // 서버 내부에서 자기들끼리 호출함

        // redirect VS forword
        // 리다이렉트는 어 리다이렉트네? 하고 서버와 클라이언트가 2번 통신함
        // forword는 con -> memRe -> view 한번 동작

    }
}

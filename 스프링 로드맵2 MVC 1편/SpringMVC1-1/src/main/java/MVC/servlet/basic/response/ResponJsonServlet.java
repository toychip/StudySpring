package MVC.servlet.basic.response;

import MVC.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responJsonServlet", urlPatterns = "/response-json")
public class ResponJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    //이 친구를 써야 json 형식으로 데이터를 바꿀 수 있음

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        HelloData helloData = new HelloData();
        helloData.setUsername("임준형");
        helloData.setAge(24);

        //{"username" : "임준형", "age" : 24}
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);

    }
}

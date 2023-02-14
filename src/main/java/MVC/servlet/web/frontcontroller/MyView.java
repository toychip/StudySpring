package MVC.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }
    // jsp로 이동하는 행위를 랜더링으로 칭함
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // model에 있는 값을 jsp에게
        // jsp는 request.getAttribute()를 쓴다 그렇기 때문에 model에 있는 data를 전부 꺼내야한다.
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
        //render가 오면 model에 있는 값을 전부 다 꺼내서 HttpServletRequest에다가
        // request.setAttribute(key, value)를 다 넣는다.
        // 그 다음에 끝나고 돌아와서 jsp forward가 되면 jsp가 request.getAttribute를 사용할 것이다.
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}

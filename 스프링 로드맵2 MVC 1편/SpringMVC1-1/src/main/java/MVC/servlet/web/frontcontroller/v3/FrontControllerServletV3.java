package MVC.servlet.web.frontcontroller.v3;

import MVC.servlet.web.frontcontroller.ModelView;
import MVC.servlet.web.frontcontroller.MyView;
import MVC.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import MVC.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import MVC.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        // 논리 이름을 물리적으로 바꿔야 한다. viewResolver라는 것이 이곳에 필요
        String viewName = mv.getViewName();//논리이름 ex) new-form

        //--------------------------------viewResolver 생성
        MyView view = viewResolver(viewName);
        //ex) view = /WEB-INF/views/new-form.jsp

        view.render(mv.getModel(), request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        // detail한 로직이 있는 것은 함수로 뽑는 것이 좋다.
        Map<String, String> paramMap = new HashMap<>();
        //paramMap을 넘겨받아야함
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        // getParameterNames()로 모든 파라미터의 이름을 다 가져오고 돌리면서 키의 변수명을 paramName으로 잡고 값은
        // paramName 이름에 각각 해당하는 값을 모두 갖고온다.
        return paramMap;
    }
}

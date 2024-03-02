package MVC.servlet.web.frontcontroller.v5;

import MVC.servlet.web.frontcontroller.ModelView;
import MVC.servlet.web.frontcontroller.MyView;
import MVC.servlet.web.frontcontroller.v3.ControllerV3;
import MVC.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import MVC.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import MVC.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import MVC.servlet.web.frontcontroller.v4.ControllerV4;
import MVC.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import MVC.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import MVC.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import MVC.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import MVC.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

//    기존 컨트롤러
//    private Map<String, ControllerV4> controllerMap = new HashMap<>();
//    어떤 것이던 들어가야하기 때문에 Object를 매개변수로 넣음.

    private final Map<String, Object> handlerMappingMap = new HashMap<>();

//    여러개의 어댑터 중에 한개를 꺼내서 찾아써야하기 때문에 List로 생성
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);   //핸들러 찾아와

        if (handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //핸들러 어댑터 목록에서 찾아오는 로직 필요 -> getHandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler);  // 핸들러 어댑터 찾아와

        ModelView mv = adapter.handel(request, response, handler);

        String viewName = mv.getViewName();//논리이름 ex) new-form

        MyView view = viewResolver(viewName);
        //ex) view = /WEB-INF/views/new-form.jsp

        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
    return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {  // 어댑터가 핸들러를 지원하는가?
                return adapter;    // support하면 값이 선택이 된다.
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. " + handler);
    }
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

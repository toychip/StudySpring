package MVC.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // Component scan의 대상이되고, RequestMappingHandelerMapping의 두가지 역할을 모두 수행한다.
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    // @RequestMapping -> RequestMappingHandlerMapping,
//                        RequestMappingHandlerAdapter
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }

}

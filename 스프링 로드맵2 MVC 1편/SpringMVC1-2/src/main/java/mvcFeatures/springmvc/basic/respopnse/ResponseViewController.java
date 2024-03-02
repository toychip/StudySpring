package mvcFeatures.springmvc.basic.respopnse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mov = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mov;
    }

    //     @Controller면서 String 이면 view를 반환하게 된다.
//    @ResponseBody를 쓰게 되면 view로 가지 않고 문자열로 출력이 된다. -> responseBody/hello 문자열 그대로 출력
//    그러나 있으면 뷰 리졸버를 실행되어서 뷰를 찾고, 렌더링한다.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello!");
        return "response/hello"; // view의 논리적인 이름이 된다.
    }
}

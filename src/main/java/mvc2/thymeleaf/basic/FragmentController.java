package mvc2.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class FragmentController {

    @GetMapping("/fragment")
    public String template(){
        return "template/fragment/fragmentMain";
    }
}

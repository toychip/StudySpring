package review.thymleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Review thymleaf!!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnexcaped(Model model) {
        model.addAttribute("data", "<b> Review thymleaf!! </b>");
        return "basic/text-unexcaped";
    }
    

}

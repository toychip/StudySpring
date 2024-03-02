package review.thymleaf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {  // String 전달
        model.addAttribute("data", "Review thymleaf!!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")  // String html 태그 excaped
    public String textUnexcaped(Model model) {
        model.addAttribute("data", "<b> Review thymleaf!! </b>");
        return "basic/text-unexcaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {       // List<Map<user닉네임, user객체>>
        User userA = User.builder()
                .username("userA")
                .age(10)
                .build();

        User userB = User.builder()
                .username("userB")
                .age(20)
                .build();

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put(userA.getUsername(), userA);
        map.put(userB.getUsername(), userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "First Session");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hi " + data;
        }
    }


    @Data
    static class User {
        private String username;
        private int age;

        @Builder
        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}

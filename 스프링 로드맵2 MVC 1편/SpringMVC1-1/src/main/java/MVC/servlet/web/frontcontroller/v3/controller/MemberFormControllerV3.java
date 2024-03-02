package MVC.servlet.web.frontcontroller.v3.controller;

import MVC.servlet.web.frontcontroller.ModelView;
import MVC.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
        //논리적 이름을 위와 같이 정함


    }
}

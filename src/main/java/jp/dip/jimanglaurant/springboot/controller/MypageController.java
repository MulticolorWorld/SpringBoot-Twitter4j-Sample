package jp.dip.jimanglaurant.springboot.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
@RequestMapping("/mypage")
public class MypageController {

    @RequestMapping(method = RequestMethod.GET)
    String index(ModelMap model) {
        return "mypage";
    }
}

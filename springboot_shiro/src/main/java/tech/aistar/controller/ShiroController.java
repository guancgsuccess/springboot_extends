package tech.aistar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2019/2/22 0022.
 */
@Controller
public class ShiroController {
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("testing","测试thymleaf...");
        return "index";
    }
}

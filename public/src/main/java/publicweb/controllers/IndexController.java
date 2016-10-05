package publicweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lejsz on 2016. 08. 02..
 */
@Controller
public class IndexController {

    @RequestMapping("/alma")
    @ResponseBody
    String home() {
        return "index";
    }
}

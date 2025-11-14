package io.github.bhuyanp.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/13/25
 */
@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "redirect:swagger-ui.html";
    }

}

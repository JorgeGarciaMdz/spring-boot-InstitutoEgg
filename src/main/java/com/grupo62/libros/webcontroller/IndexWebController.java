package com.grupo62.libros.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexWebController {
    
    @GetMapping("index")
    public String getIndex(){
        return "index";
    }
}

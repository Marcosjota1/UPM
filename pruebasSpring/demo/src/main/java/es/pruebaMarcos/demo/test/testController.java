package es.pruebaMarcos.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/test")
public class testController {
    
    @GetMapping
    public String helloWorld() {
        return "Hello, World!";
    }
}

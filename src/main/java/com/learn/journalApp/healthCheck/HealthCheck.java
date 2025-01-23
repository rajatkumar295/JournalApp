package com.learn.journalApp.healthCheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HealthCheck {

    @GetMapping("health-check")
    public String healthCheck(){
        return "Ok";
    }

}

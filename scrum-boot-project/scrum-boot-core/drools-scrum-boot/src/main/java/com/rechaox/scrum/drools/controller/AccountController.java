package com.rechaox.scrum.drools.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bing D. Yee
 * @since 2021/12/04
 */
@RestController
@RequestMapping("/v1/drools/account")
public class AccountController {

    @GetMapping
    public String hello() {
        return "hello";
    }

}

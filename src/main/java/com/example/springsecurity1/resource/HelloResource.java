package com.example.springsecurity1.resource;

import java.util.function.Consumer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
    public HelloResource() {
    }

    @GetMapping({"/rest/hello"})
    public String hello(@AuthenticationPrincipal final UserDetails userDetails) {
        return "Hello User, " + this.getUserDetails(userDetails);
    }

    @GetMapping({"/rest/helloadmin"})
    public String helloAdmin(@AuthenticationPrincipal final UserDetails userDetails) {
        return "Hello Admin, " + this.getUserDetails(userDetails);
    }

    @GetMapping({"/rest/helloall"})
    public String helloAll(@AuthenticationPrincipal final UserDetails userDetails) {
        return "Hello All, " + this.getUserDetails(userDetails);
    }

    private String getUserDetails(UserDetails userDetails) {
        StringBuilder userDetailsString = new StringBuilder("User Name : " + userDetails.getUsername() + ", Roles : ");
        userDetails.getAuthorities().forEach(userDetailsString::append);
        return userDetailsString.toString();
    }
}
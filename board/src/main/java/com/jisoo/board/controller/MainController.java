package com.jisoo.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jisoo.board.service.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userService;
	
    @GetMapping("/")
    String home(Model model) {
        return "views/home";
    }

    @GetMapping("/login")
    String goLogin() {
        return "views/login";
    }
    
    @GetMapping("/signup")
    String goSignup() {
    	return "views/signup";
    }
    
    @GetMapping("/signup/check-id")
    @ResponseBody
    public Map<String, Boolean> checkId(@RequestParam String id){
    	boolean available = userService.isAvailable(id);
		
		Map<String, Boolean> result = new HashMap<>();
		result.put("available", available);
    	
    	return result;
    }
    
    @PostMapping("/signup")
    @ResponseBody
    public Map<String, Boolean> signup(
            @RequestParam("inputId") String userId,
            @RequestParam("inputPw") String userPw
    ) {
        boolean success = userService.signup(userId, userPw);

        System.out.println("result : " + success);
        Map<String, Boolean> result = new HashMap<>();
        result.put("success", success);
        return result;
    }
}

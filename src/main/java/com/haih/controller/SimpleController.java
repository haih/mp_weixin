package com.haih.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
	@RequestMapping("/simple.do")
	public @ResponseBody String simple() {
		return "Hello world!";
	}
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
    public String toAddTest() {
        return "index";
    }
}

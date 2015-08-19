package com.mytest.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.service.Greeting;
import com.mytest.service.HelloMessage;

@Controller
public class GreetingController {

	@RequestMapping(value = "/echo.do", method = RequestMethod.GET)
    @MessageMapping("/echo.do")
    @SendTo("/test/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

}
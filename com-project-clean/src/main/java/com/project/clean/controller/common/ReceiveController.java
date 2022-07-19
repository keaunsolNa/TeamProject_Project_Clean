package com.project.clean.controller.common;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.project.clean.component.socketMessage.ReceiveMessage;
import com.project.clean.component.socketMessage.SendMessage;

@Controller
public class ReceiveController {


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public ReceiveMessage greeting(SendMessage message, Principal principal) throws Exception {
		Thread.sleep(1000); 
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println(principal.getName());
		System.out.println(message.getName());
		System.out.println("TEST");
		System.out.println("TEST");
			return new ReceiveMessage(HtmlUtils.htmlEscape(principal.getName() + "님, " + message.getMessage()));
			
			
	}
}

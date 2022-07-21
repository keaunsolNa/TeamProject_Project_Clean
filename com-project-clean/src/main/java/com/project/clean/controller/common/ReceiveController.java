package com.project.clean.controller.common;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.project.clean.component.socketMessage.ReceiveMessage;
import com.project.clean.component.socketMessage.SendMessage;

@Controller
public class ReceiveController {


	@MessageMapping("/hello")
	@SendTo("/queue/greetings")
	public ReceiveMessage greeting(SendMessage message, Principal principal) throws Exception {
		
		Thread.sleep(1000); 
		return new ReceiveMessage(HtmlUtils.htmlEscape(message.getName() +"님의, " + message.getMessage()));
		
	}
	
	@MessageMapping("/client")
	@SendTo("/queue/{id}")
	public ReceiveMessage receiveMessage(SendMessage message) throws Exception{
		
		return new ReceiveMessage();
		
	}
}

package com.project.clean.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.project.clean.component.socketMessage.ReceiveMessage;
import com.project.clean.component.socketMessage.SendMessage;

@Controller
public class ReceiveController {

	private HttpServletRequest request;
	
	@Autowired
	public ReceiveController(HttpServletRequest request) {
		this.request = request;
	}

	@MessageMapping("/hello{clientName}")
	@SendTo("/queue/greetings{clientName}")
	public ReceiveMessage greeting(SendMessage message, @DestinationVariable String clientName) throws InterruptedException {
		Thread.sleep(1000); 
			
			return new ReceiveMessage(HtmlUtils.htmlEscape(message.getMessage()));
	}
	
	
}

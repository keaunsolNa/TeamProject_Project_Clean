package com.project.clean.controller.common;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.project.clean.component.socketMessage.SendMessage;
import com.project.clean.component.socketMessage.ReceiveMessage;

@Controller
public class ReceiveController {


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public ReceiveMessage greeting(SendMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new ReceiveMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

}

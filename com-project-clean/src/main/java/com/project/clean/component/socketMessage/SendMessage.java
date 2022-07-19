package com.project.clean.component.socketMessage;

public class SendMessage {

	private String name;
	private String message;
	
	public SendMessage() {
	}

	public SendMessage(String name, String message) {
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SendMessage [name=" + name + ", message=" + message + "]";
	}


	
}

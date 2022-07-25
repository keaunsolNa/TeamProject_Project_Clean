package com.project.clean.component.socketMessage;

public class SendMessage {

	private String name;
	private String message;
	private String path;
	public SendMessage() {
	}
	public SendMessage(String name, String message, String path) {
		this.name = name;
		this.message = message;
		this.path = path;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "SendMessage [name=" + name + ", message=" + message + ", path=" + path + "]";
	}

	
	


	
}

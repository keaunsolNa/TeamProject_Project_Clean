package com.project.clean.component.socketMessage;

public class SendMessage {

	private String name;
	private String message;
	private String location;
	public SendMessage() {
	}
	public SendMessage(String name, String message, String location) {
		this.name = name;
		this.message = message;
		this.location = location;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "SendMessage [name=" + name + ", message=" + message + ", location=" + location + "]";
	}
	
	


	
}

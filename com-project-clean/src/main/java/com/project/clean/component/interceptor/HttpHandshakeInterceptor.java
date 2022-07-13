package com.project.clean.component.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest; 
import org.springframework.http.server.ServerHttpResponse; 
import org.springframework.http.server.ServletServerHttpRequest; 
import org.springframework.web.socket.WebSocketHandler; 
import org.springframework.web.socket.server.HandshakeInterceptor;

	public class HttpHandshakeInterceptor implements HandshakeInterceptor{

	private Object SESSION;

	@Override 
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler, Map attributes)
					throws Exception {
 
		if(request instanceof ServletServerHttpRequest) { 
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request; 
			HttpSession session = servletRequest.getServletRequest().getSession(); 
			attributes.put(SESSION,	session); 
			System.out.println("소켓 연결 확인");
		} 
		
		return true; 
	}
 
 @Override public void afterHandshake(ServerHttpRequest request,
 ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception)
 {
 
 }
 
 }

package com.project.clean.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan("com.project.clean")
@PropertySource("mailProperties.properties")
public class EmailConfiguration {
	
	@Value("${mail.protocol}")
    private String protocol;
    @Value("${mail.Host}")
    private String Host;
    @Value("${mail.smtp.port}")
    private int port;
    @Value("${mail.smtp.socketFactory.port}")
    private int socketPort;
    @Value("${mail.smtp.auth}")
    private boolean auth;
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${mail.smtp.starttls.required}")
    private boolean startlls_required;
    @Value("${mail.smtp.debug}")
    private boolean debug;
    @Value("${mail.smtp.socketFactory.fallback}")
    private boolean fallback;
    @Value("${mail.from}")
    private String from;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    
	@Bean
	public JavaMailSender mailSender() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		Properties mailProperties = new Properties();
		
		mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailProperties.put("mail.smtp.starttls.required", startlls_required);
        mailProperties.put("mail.smtp.socketFactory.port", socketPort);
        mailProperties.put("mail.smtp.debug", debug);
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.smtp.socketFactory.fallback", fallback);
        
        mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(Host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		mailSender.setDefaultEncoding("UTF-8");
		return mailSender;
	}
	
	public void sendEmail() throws Exception {
//		Email email = new SimpleEmail();
//		email.setHostName("smtp.naver.com");
//		email.setSmtpPort(465);
//		email.setCharset("UTF-8"); 
//		email.setAuthenticator(new DefaultAuthenticator("knsol1992@naver.com", "knsol6859!@!@"));
//		email.setSSL(true);
//		email.setFrom("knsol1992@naver.com", "ProjectClean");
//		email.setSubject("TEST");
//		email.setMsg("TEST 내용");
//		email.addTo("받는 사람 이메일", "이름");
//		email.send();
	}
}

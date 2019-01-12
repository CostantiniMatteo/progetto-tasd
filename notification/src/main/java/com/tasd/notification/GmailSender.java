package com.tasd.notification;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SmtpMailSender {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String to, String subject, String body)
		throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		// SSL Certhificate.
		helper = new MimeMessageHelper(message, true);
		// Multipart messages.
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		javaMailSender.send(message);
    }
}

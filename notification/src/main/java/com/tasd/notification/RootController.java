package com.tasd.notification;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	
		@Autowired
		private SmtpMailSender smtpMailSender;
		
		@RequestMapping(value = "api/send-notification", method = RequestMethod.POST)
		public ResponseEntity sendNotification(@RequestBody NotificationEntity destinationMail) throws MessagingException {
			if(checkFieldMail(destinationMail)) {
				smtpMailSender.send(destinationMail.getDestination(), destinationMail.getSubject(), destinationMail.getBody());	
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			else {
				return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
			}
				
		}
		
		
		public boolean checkFieldMail(NotificationEntity destinationMail) {
			if (destinationMail.getDestination().equals(null)) {
				return false;
			}
			if (destinationMail.getSubject().equals(null)) {
				return false;
			}
			if (destinationMail.getBody().equals(null)) {
				return false;
			}
			return true;
		}
}

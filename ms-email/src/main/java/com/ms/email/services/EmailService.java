package com.ms.email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.Email;
import com.ms.email.repos.EmailRepository;

@Service
public class EmailService {

	@Autowired
	EmailRepository er;
	
	@Autowired
	private JavaMailSender jms;

	@SuppressWarnings("finally")
	public Email sendEmail(Email email) {
		email.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(email.getEmailFrom());
			message.setTo(email.getEmailTo());
			message.setSubject(email.getSubject());
			message.setText(email.getText());
			jms.send(message);
			
			email.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			email.setStatusEmail(StatusEmail.ERROR);		
		} finally {
			return er.save(email);
		}
		
	}
	
}

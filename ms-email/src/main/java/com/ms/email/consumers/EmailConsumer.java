package com.ms.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.Email;
import com.ms.email.services.EmailService;

@Component
public class EmailConsumer {
	
	@Autowired
	EmailService emailService;
	
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void listen(@Payload EmailDto emailDto) {
		Email email = new Email();
		BeanUtils.copyProperties(emailDto, email);
		emailService.sendEmail(email);
		System.out.println("Email Status: " + email.getStatusEmail().toString());
	}

}

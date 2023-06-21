package com.ms.email.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.Email;
import com.ms.email.services.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService es;
	
	@PostMapping("/sending-email")
	public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDto emailDto){
		Email email = new Email();
		BeanUtils.copyProperties(emailDto, email);
		es.sendEmail(email);
		return new ResponseEntity<>(email, HttpStatus.CREATED);
	}
}

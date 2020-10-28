package com.lsolution.Library_demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String reci,String msg) throws MessagingException {
		MimeMessage mesg=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mesg);
		helper.setTo(reci);
		helper.setSubject("Successfull Registration");
		helper.setText(msg);
		mailSender.send(mesg);
	}
}

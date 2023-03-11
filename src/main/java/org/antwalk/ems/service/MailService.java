package org.antwalk.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    
    @Autowired
    private JavaMailSender mailsender;

    public void sendNewEmployeeMail(String receiverMailId, String name, String officialMailId, String username, String password){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiverMailId);
        message.setSubject(String.format("Welcome %s to the Company EMS Software",name));
        message.setText(String.format("Hi %s,\n\nPlease find the details below-\nUsername:   %s\nPassword:   %s\nOfficial Email Address:   %s\n\nPlease do not share the above credentials with anyone. To access your EMS Portal, go to http://www.localhost:8081\n\nWith regards,\nEMS\n---This email is auto-generated. Do not reply back to these email-----",name,username,password,officialMailId));

        mailsender.send(message);
    }

    public void sendDeactivationMail(String receiverOfficialMailId, String name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiverOfficialMailId);
        message.setSubject(String.format("EMS Update"));
        message.setText(String.format("Hi %s,\n\nYour EMS Portal is deactivated. Please contact HR for any issue.\nWith regards,\nEMS\n---This email is auto-generated. Do not reply back to these email-----",name));

        mailsender.send(message);
    }

    public void sendActivationMail(String receiverOfficialMailId, String name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiverOfficialMailId);
        message.setSubject(String.format("EMS Update"));
        message.setText(String.format("Hi %s,\n\nYour EMS Portal is activated. Please contact HR for any issue.\nWith regards,\nEMS\n---This email is auto-generated. Do not reply back to these email-----",name));

        mailsender.send(message);
    }
}

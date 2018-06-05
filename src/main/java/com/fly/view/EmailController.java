package com.fly.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/send")
    public String sendMail() {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1452592991@qq.com");
        mailMessage.setTo("929491854@qq.com");
        mailMessage.setSubject("邮件发送测试");
        mailMessage.setText("邮箱发送成功");

        mailSender.send(mailMessage);
        return "OK";
    }
}

package com.chen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TestApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	void contextLoads() {
		//这是一个简单的邮件发送
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject("小陈2021年1月18日测试spring boot邮件发送");
		mailMessage.setText("好好加油，争取早日实现自己的梦想");
		mailMessage.setTo("2417359111@qq.com");
		mailMessage.setFrom("2417359111@qq.com");
		mailSender.send(mailMessage);
	}

	@Test
	void contextLoads2() throws MessagingException {
		//这是一个复杂的邮件发送
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		//组装
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
		//正文
		helper.setSubject("小陈2021年1月18日测试spring boot邮件发送");
		helper.setText("<p style='color:red'>我想拥有健康的身体，五百万元和一个大大的房子和车子，还有一个幸福的家庭</p>",true);
		//附件
		helper.addAttachment("宝贝淳淳1.jpg",new File("E:\\娱乐区\\memoirs\\微信图片_20201120123243.jpg"));
		helper.addAttachment("宝贝淳淳2.jpg",new File("E:\\娱乐区\\memoirs\\微信图片_20201120123308.jpg"));

		helper.setTo("2417359111@qq.com");
		helper.setFrom("2417359111@qq.com");
		mailSender.send(mimeMessage);
	}
}

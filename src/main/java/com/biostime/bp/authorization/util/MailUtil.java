package com.biostime.bp.authorization.util;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 
 * 描述: TODO
 *
 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
 * @createDate 2019年5月23日 上午11:28:36
 */
@Slf4j
@Component
public class MailUtil {
	@Value("${repwdEmail.host}")
	private String host;
	@Value("${repwdEmail.port}")
	private String port;
	@Value("${repwdEmail.username}")
	private String email;
	@Value("${repwdEmail.pwd}")
	private String pwd;
	@Value("${repwdEmail.title}")
	private String title;
	@Value("${repwdEmail.content}")
	private String content;
	@Value("${repwdEmail.webSite}")
	private String webSite;
	@Value("${repwdEmail.expire}")
	private Long expire;
	
	/** 
	 * 方法描述: 发送全球防伪密码重置邮件
	 *
	 * @param data:{'toUser':user's email,'userName':user's nick name,'url':auth url}
	 * @author <a href="mailto:zhangguojian@hh.global">12552</a>
	 * @createDate 2019年5月23日 下午2:26:59
	 */
	public void sendAntifake(Map<String, String> data) {
		new Thread(new Runnable() {
			public void run() {
				String toUser = data.get("toUser");
				String userName = data.get("userName");
				String url = webSite + data.get("url");
				
				Properties prop = System.getProperties();
				prop.put("mail.host", host);
				prop.put("mail.smtp.auth", "true");
				
				prop.put("mail.transport.protocol", "smtp");
				prop.put("mail.smtp.socketFactory.port", port);
				prop.put("mail.smtp.starttls.enable", "true");
				Session session = Session.getDefaultInstance(prop, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email,pwd);
					}
				});
				session.setDebug(true);
				MimeMessage msg = new MimeMessage(session);
				try {
					msg.setFrom(new InternetAddress("HH Global Anti-counterfeiting System<"+email+">"));
					msg.setRecipient(RecipientType.TO, new InternetAddress(toUser));
					msg.setSubject(title);
					MimeMultipart mm = new MimeMultipart();
					MimeBodyPart body = new MimeBodyPart();
					body.setContent(MessageFormat.format(content, userName, url, url, expire / 3600), "text/html;charset=utf-8");
					mm.addBodyPart(body);
					msg.setContent(mm);
					Transport.send(msg);
				} catch (Exception e) {
					log.error("send mail error:{}", e);
				}
			}
		}).start();
	}
}

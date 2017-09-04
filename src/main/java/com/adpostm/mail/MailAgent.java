package com.adpostm.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author https://alvinalexander.com/blog/post/java/free-java-class-simplify-sending-email
 */
public class MailAgent {
	private String to;
	private String from;
	private String cc;
	private String bcc;
	private String subject;
	private String content;
	private String smtpHost;
	private Message message;
	
	public MailAgent(String to, String from, String cc, String bcc,
			String subject, String content, String smtpHost)
				throws AddressException, MessagingException{
		this.to = to;
		this.from = from;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.content = content;
		this.smtpHost = smtpHost;
		
		message = createMessage();
		message.setFrom(new InternetAddress(from));
		setToCcBccRecipients();
		message.setSentDate(new Date());
		message.setSubject(subject);
		message.setText(content);
	}
	
	public void sendMessage() throws MessagingException{
		try{
			Transport.send(message);
		}
		catch(MessagingException ex){
			System.out.println(ex);
			throw ex;
		}
	}
	private Message createMessage(){
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtpHost");
		Session session = Session.getDefaultInstance(properties, null);
		return new MimeMessage(session);
	}
	private void setToCcBccRecipients() 
			throws AddressException, MessagingException{
		if(to != null)
			setMessageRecipients(to, Message.RecipientType.TO);
		if(cc != null)
			setMessageRecipients(cc, Message.RecipientType.CC);
		if(bcc != null)
			setMessageRecipients(bcc, Message.RecipientType.BCC);
	}
	private void setMessageRecipients(String recipient, 
			Message.RecipientType recipientType)
					throws AddressException, MessagingException{
		InternetAddress[] addressArray = buildInternetAddressArray(recipient);
		if((addressArray != null) && (addressArray.length > 0)){
			message.setRecipients(recipientType, addressArray);
		}
	}
	private InternetAddress[] buildInternetAddressArray(String address)
		throws AddressException{
		InternetAddress[] internetAddressArray = null;
		try{
			internetAddressArray = InternetAddress.parse(address);
		}
		catch(AddressException ex){
			throw ex;
		}
		return internetAddressArray;
	}
}

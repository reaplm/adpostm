package com.adpostm.mail;

public class MailAgent {
	private String to;
	private String from;
	private String cc;
	private String bcc;
	private String subject;
	private String content;
	private String smtpHost;
	private String message;
	
	public MailAgent(String to, String from, String cc, String bcc,
			String subject, String content, String smtpHost, String message){
		this.to = to;
		this.from = from;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.content = content;
		this.smtpHost = smtpHost;
		this.message = message;
	}
}

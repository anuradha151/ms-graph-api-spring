package com.anuradha.msgraphapispring.service;

import com.microsoft.graph.models.*;
import com.microsoft.graph.requests.GraphServiceClient;
import com.slightinsight.emailapi.model.EmailRequest;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class EmailService {

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private GraphServiceClient<Request> mailClient;

	Logger log = LoggerFactory.getLogger(getClass());

	public void sendEmail(EmailRequest emailRequest) {

		log.info("Preparing email");

		Message message = new Message();

		message.subject = emailRequest.getSubject();

		ItemBody body = new ItemBody();
		body.contentType = BodyType.HTML;
		body.content = emailRequest.getMessage();
		message.body = body;

		LinkedList<Recipient> toRecipientsList = new LinkedList<Recipient>();
		Recipient toRecipients = new Recipient();
		EmailAddress emailAddress = new EmailAddress();
		emailAddress.address = emailRequest.getRecipient();
		toRecipients.emailAddress = emailAddress;
		toRecipientsList.add(toRecipients);
		message.toRecipients = toRecipientsList;

		// Send the message
		log.info("sending email");
		mailClient.users(sender).sendMail(UserSendMailParameterSet.newBuilder().withMessage(message).build())
				.buildRequest().post();

	}

}

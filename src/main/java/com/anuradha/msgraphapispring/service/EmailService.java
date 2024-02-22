package com.anuradha.msgraphapispring.service;

import com.anuradha.msgraphapispring.model.EmailRequest;
import com.microsoft.graph.models.*;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.graph.users.item.UserItemRequestBuilder;
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
    private GraphServiceClient mailClient;

    Logger log = LoggerFactory.getLogger(getClass());

    public void sendEmail(EmailRequest emailRequest) {

//        Message message = new Message();
//
//        message.subject = emailRequest.getSubject();
//
//        ItemBody body = new ItemBody();
//        body.contentType = BodyType.HTML;
//        body.content = emailRequest.getMessage();
//        message.body = body;
//
//        LinkedList<Recipient> toRecipientsList = new LinkedList<Recipient>();
//        Recipient toRecipients = new Recipient();
//        EmailAddress emailAddress = new EmailAddress();
//        emailAddress.address = emailRequest.getRecipient();
//        toRecipients.emailAddress = emailAddress;
//        toRecipientsList.add(toRecipients);
//        message.toRecipients = toRecipientsList;
//
//        // Send the message
//        mailClient.users(sender)
//                .sendMail(
//                        UserSendMailParameterSet.newBuilder()
//                                .withMessage(message)
//                                .build())
//                .buildRequest().post();
//

    }

    public void listMessages(){
        MessageCollectionResponse result = mailClient.users().byUserId(sender).messages().get();


    }

}

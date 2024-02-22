package com.anuradha.msgraphapispring.service;

import com.anuradha.msgraphapispring.dto.MessageResponseDto;
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
import java.util.List;

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


    }

    public List<MessageResponseDto> listMessages() {
        MessageCollectionResponse messageCollectionResponse = mailClient.users().byUserId(sender).messages().get();
        if (messageCollectionResponse == null || messageCollectionResponse.getValue() == null)
            throw new RuntimeException("No messages found");
        return messageCollectionResponse.getValue().stream()
                .map(this::toMessageResponseDto)
                .toList();


    }

    private MessageResponseDto toMessageResponseDto(Message message) {

        String sender = message.getSender() != null && message.getSender().getEmailAddress() != null
                ? message.getSender().getEmailAddress().getAddress()
                : "";
        String subject = message.getSubject() != null ? message.getSubject() : "";
        String content = message.getBody() != null ? message.getBody().getContent() : "";


        return new MessageResponseDto(
                sender,
                subject,
                content
        );
    }


}

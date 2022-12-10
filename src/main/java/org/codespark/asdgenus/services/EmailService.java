package org.codespark.asdgenus.services;

import com.sendgrid.*;
import org.codespark.asdgenus.dtos.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    public int sendMail(int uid, EmailDTO emailDTO) {

        SendGrid sendGridClient = new SendGrid("*");
        Mail mail = new Mail(new Email(emailDTO.getFrom()), emailDTO.getSubject(),
                new Email(emailDTO.getTo()), new Content("text/plain", emailDTO.getMessage()));
        mail.setReplyTo(new Email(emailDTO.getFrom()));
        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGridClient.api(request);
            return 1;
        } catch (IOException ex) {
            return 0;
        }
    }
}

package com.blockbank.service;

/**
 * @author hannahvd
 */

import com.blockbank.database.domain.Mail;
import org.apache.http.MessageConstraintException;

import javax.mail.MessagingException;

public interface SendMailService {

    void sendMail(Mail mail);
    void sendMailWithAttachments(Mail mail) throws MessageConstraintException, MessagingException;
}

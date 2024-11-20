package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.SimpleMailRequest;
import dev.lvpq.CS502052.Exception.MailException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class MailService {
    JavaMailSender mailSender;

    public List<String> sendMail(List<String> toEmails, String subject, String body, List<MultipartFile> attachments) {
        List<String> failedEmails = new ArrayList<>();

        for (String toEmail : toEmails) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(toEmail);
                helper.setSubject(subject);
                helper.setText(body, true);
                if (attachments != null) {
                    for (MultipartFile file : attachments) {
                        if (!file.isEmpty()) {
                            helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
                        }
                    }
                }

                mailSender.send(message);
            } catch (MailException e) {
                failedEmails.add(toEmail);
                throw new MailException("Error sending email to: " + toEmail, e);
            } catch (Exception e) {
                failedEmails.add(toEmail);
                throw new MailException("Error processing email to: " + toEmail, e);
            }
        }
        return failedEmails;
    }

    public void sendSimpleMail(SimpleMailRequest request) throws MessagingException {
        var helper = new MimeMessageHelper(mailSender.createMimeMessage(), false);
        helper.setTo(request.getReceiver());
        helper.setSubject(request.getSubject());
        helper.setText(request.getText());
        helper.setFrom(request.getSender());

        mailSender.send(helper.getMimeMessage());
    }
}

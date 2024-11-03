package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Exception.MailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public List<String> sendMail(List<String> toEmails, String subject, String body, List<MultipartFile> attachments) {
        List<String> failedEmails = new ArrayList<>();

        for (String toEmail : toEmails) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true); // true cho phép tệp đính kèm
                helper.setTo(toEmail);
                helper.setSubject(subject);
                helper.setText(body, true); // true nếu bạn muốn sử dụng HTML

                // Thêm các tệp đính kèm
                if (attachments != null) {
                    for (MultipartFile file : attachments) {
                        if (!file.isEmpty()) {
                            helper.addAttachment(file.getOriginalFilename(), file);
                        }
                    }
                }

                mailSender.send(message);
            } catch (MailException e) { // Sử dụng MailException từ Spring
                failedEmails.add(toEmail);
                throw new MailException("Error sending email to: " + toEmail, e);
            } catch (Exception e) {
                failedEmails.add(toEmail);
                throw new MailException("Error processing email to: " + toEmail, e);
            }
        }

        return failedEmails; // Trả về danh sách các email không gửi thành công
    }
}

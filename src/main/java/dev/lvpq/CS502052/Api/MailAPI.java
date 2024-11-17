package dev.lvpq.CS502052.Api;

import dev.lvpq.CS502052.Exception.MailException;
import dev.lvpq.CS502052.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/mail")
public class MailAPI {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(
            @RequestParam("toEmails") List<String> toEmails,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments) {

        List<String> failedEmails;

        try {
            failedEmails = mailService.sendMail(toEmails, subject, body, attachments);
        } catch (MailException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }

        if (failedEmails.isEmpty()) {
            return ResponseEntity.ok("Emails sent successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .body("Emails sent successfully with failures to: " + String.join(", ", failedEmails));
        }
    }
}

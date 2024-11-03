package dev.lvpq.CS502052.Dto.Request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class EmailRequest {
    private List<String> toEmails; // Danh sách các địa chỉ email
    private String subject;
    private String body;
    private List<MultipartFile> attachments; // Danh sách tệp đính kèm

    // Getters và Setters
}

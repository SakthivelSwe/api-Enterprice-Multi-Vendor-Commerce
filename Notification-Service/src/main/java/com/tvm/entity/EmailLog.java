package com.tvm.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_logs")
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toEmail;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    private String type;
    private LocalDateTime sentAt;
    private boolean success;
    private String errorMessage;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getToEmail() { return toEmail; }
    public void setToEmail(String toEmail) { this.toEmail = toEmail; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}


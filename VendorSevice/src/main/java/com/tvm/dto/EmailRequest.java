package com.tvm.dto;

import com.tvm.entity.EmailType;

public class EmailRequest {
    private String to;
    private String subject;
    private EmailType type;
    private String[] variables;

    public EmailRequest() {}

    public EmailRequest(String to, String subject, EmailType type, String[] variables) {
        this.to = to;
        this.subject = subject;
        this.type = type;
        this.variables = variables;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public EmailType getType() {
        return type;
    }

    public void setType(EmailType type) {
        this.type = type;
    }

    public String[] getVariables() {
        return variables;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }


}


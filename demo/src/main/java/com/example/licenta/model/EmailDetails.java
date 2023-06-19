package com.example.licenta.model;

import java.util.Objects;

public class EmailDetails {
    private String message;
    private String subject;
    private String recipient;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDetails that = (EmailDetails) o;
        return Objects.equals(message, that.message) && Objects.equals(subject, that.subject) && Objects.equals(recipient, that.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, subject, recipient);
    }
}

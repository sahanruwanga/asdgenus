package org.codespark.asdgenus.dtos;

public class EmailDTO {

    private String to;
    private String from;
    private String message;
    private String subject;

    public EmailDTO(String to, String from, String message, String subject) {
        this.to = to;
        this.from = from;
        this.message = message;
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

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
}

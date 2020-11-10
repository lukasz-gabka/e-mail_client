package project.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import project.controller.EmailSendingResult;
import project.model.EmailAccount;

import java.security.Provider;

public class EmailSenderService extends Service<EmailSendingResult> {

    private EmailAccount emailAccount;
    private String subject;
    private String recipient;
    private String content;

    public EmailSenderService(EmailAccount emailAccount, String subject, String recipient, String content) {
        this.emailAccount = emailAccount;
        this.subject = subject;
        this.recipient = recipient;
        this.content = content;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected EmailSendingResult call() throws Exception {
                return null;
            }
        };
    }
}

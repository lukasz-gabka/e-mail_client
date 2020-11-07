module e.mail.client {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires activation;
    requires java.mail;

    opens project;
    opens project.controller;
    opens project.model;
}
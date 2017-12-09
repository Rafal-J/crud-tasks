package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskController taskController;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(Mail mail) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        List<String> taskNames = taskController.getTasks().stream()
                .map(c -> c.getTitle())
                .collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("preview", "Powiadomienie od aplikacji CRUD Tasks");
        context.setVariable("message", mail.getMessage());
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Odwiedź stronę");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye", "Pozdrawiam, aplikacja CRUD TASKS");
        context.setVariable("company", companyConfig.getCompanyName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        context.setVariable("show_tasks_list", mail.getTemplate().equals("daily-mail"));
        context.setVariable("tasks", taskNames);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}

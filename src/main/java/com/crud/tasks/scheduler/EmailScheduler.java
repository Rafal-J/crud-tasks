package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Zadanie: codzienna wysyłka maila informacyjnego";

    @Scheduled(fixedDelay = 1000000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String taskWord = TaskWord.chooseRightWord(size);
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Dzisiaj masz do wykonania " + size + " " + taskWord,
                null,
                "daily-mail"
        ));
    }
}
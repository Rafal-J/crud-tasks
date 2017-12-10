package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Mail {
    final String mailTo;
    final String subject;
    final String message;
    final String toCc;
    final String template;
}

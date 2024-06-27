package com.restfulAPI.controllers;

import com.restfulAPI.MailSender.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail/")
@RequiredArgsConstructor
public class MailController {

    private final MailSenderService mailSenderService;


}

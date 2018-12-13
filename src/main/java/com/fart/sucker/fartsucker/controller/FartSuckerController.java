package com.fart.sucker.fartsucker.controller;

import com.fart.sucker.fartsucker.dto.FartRequest;
import com.fart.sucker.fartsucker.entity.FartEntity;
import com.fart.sucker.fartsucker.service.FartSuckerService;
import com.fart.sucker.fartsucker.service.MailSenderService;
import com.fart.sucker.fartsucker.service.S3LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RequestMapping("/${root-path}/farts")
@RestController
public class FartSuckerController {

    @Autowired
    private FartSuckerService fartSuckerService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private S3LinkService s3LinkService;

    @CrossOrigin
    @GetMapping("/random")
    public FartEntity getRandomFart() {
        return fartSuckerService.getRandomFart();
    }

    @PostMapping("/inhaleFarts")
    public void inhaleFarts(@RequestBody FartRequest fartRequest) {
        fartSuckerService.inhaleFarts(fartRequest);
    }

    @GetMapping("/sendEmail")
    public void sendEmail(@RequestParam("toEmail") String toEmail, @RequestParam("count") int count) {
        for (int i = 0; i < count; i++) {
            mailSenderService.sendSimpleMessage(toEmail, "Here is your inhaled fart!", fartSuckerService.getRandomFart().getFart());
        }
    }

    @GetMapping("/sendSlackMessage")
    public void sendSlackMessage(@RequestParam("count") int count, @RequestParam("mention") String mention) {
        IntStream.range(0, count).forEach(i -> fartSuckerService.sendSlackMessage(mention));
    }
}

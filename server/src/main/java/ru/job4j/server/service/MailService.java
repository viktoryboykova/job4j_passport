package ru.job4j.server.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.job4j.server.model.Passport;

@Component
public class MailService {

    private final Gson gson = new GsonBuilder().create();

    @Autowired
    private KafkaTemplate<Integer, String> template;

    private PassportService passportService;

    public MailService(PassportService passportService) {
        this.passportService = passportService;
    }

    @Scheduled(fixedRate = 3_000_000)
    public void scheduleTask() {
        Iterable<Passport> expiredPassports = passportService.findExpiredPassports();
        for (Passport passport : expiredPassports) {
            template.send("expiredPassport", gson.toJson(passport));
        }
    }
}

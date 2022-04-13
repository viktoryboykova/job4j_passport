package ru.job4j.server.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.server.model.Passport;
import ru.job4j.server.service.PassportService;
import ru.job4j.server.validation.Operation;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/save")
    @Validated(Operation.OnCreate.class)
    public Passport save(@Valid @RequestBody Passport passport) {
        return passportService.save(passport);
    }

    @PutMapping("/update")
    public void update(@RequestParam int id, @Valid @RequestBody Passport passport) {
        Passport passportFromDb = passportService.findById(id);
        passportFromDb.setNumber(passport.getNumber());
        passportFromDb.setSeria(passport.getSeria());
        passportFromDb.setExpiryDate(passport.getExpiryDate());
        passportService.update(passportFromDb);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam int id) {
        passportService.delete(id);
    }

    @GetMapping("/find")
    public List<Passport> findAll() {
        return passportService.findAll();
    }

    @GetMapping("/findBySeria")
    public List<Passport> findBySeria(@RequestParam String seria) {
        return passportService.findBySeria(seria);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findExpiredPassports() {
        return passportService.findExpiredPassports();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findSoonExpiredPassports() {
        return passportService.findSoonExpiredPassports();
    }

    @GetMapping("/findById")
    public Passport findById(@RequestParam int id) {
        return passportService.findById(id);
    }
}

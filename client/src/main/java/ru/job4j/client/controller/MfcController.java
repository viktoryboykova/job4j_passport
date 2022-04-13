package ru.job4j.client.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.client.model.Passport;
import ru.job4j.client.service.MfcService;
import ru.job4j.client.validation.Operation;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mfc")
public class MfcController {

    private final MfcService mfcService;

    public MfcController(MfcService mfcService) {
        this.mfcService = mfcService;
    }

    @PostMapping("/save")
    @Validated(Operation.OnCreate.class)
    public Passport save(@Valid @RequestBody Passport passport) {
        return mfcService.save(passport);
    }

    @PutMapping("/update")
    public void update(@RequestParam int id, @Valid @RequestBody Passport passport) {
        Passport passportFromDb = mfcService.findById(String.valueOf(id));
        passportFromDb.setNumber(passport.getNumber());
        passportFromDb.setSeria(passport.getSeria());
        passportFromDb.setExpiryDate(passport.getExpiryDate());
        mfcService.update(String.valueOf(passportFromDb.getId()), passportFromDb);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        mfcService.delete(String.valueOf(id));
    }

    @GetMapping("/find")
    public List<Passport> findAll() {
        return mfcService.findAll();
    }

    @GetMapping("/findBySeria")
    public List<Passport> findBySeria(@RequestParam String seria) {
        return mfcService.findBySeria(seria);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findExpiredPassports() {
        return mfcService.findExpiredPassports();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findSoonExpiredPassports() {
        return mfcService.findSoonExpiredPassports();
    }
}

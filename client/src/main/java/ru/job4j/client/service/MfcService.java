package ru.job4j.client.service;

import org.springframework.stereotype.Service;
import ru.job4j.client.model.Passport;
import ru.job4j.client.store.PassportAPIStore;

import java.util.List;

@Service
public class MfcService {

    private final PassportAPIStore passportAPIStore;

    public MfcService(PassportAPIStore passportAPIStore) {
        this.passportAPIStore = passportAPIStore;
    }

    public Passport save(Passport passport) {
        return passportAPIStore.save(passport);
    }

    public boolean update(String id, Passport passport) {
        return passportAPIStore.update(id, passport);
    }

    public boolean delete(String id) {
        return passportAPIStore.delete(id);
    }

    public List<Passport> findAll() {
        return passportAPIStore.findAll();
    }

    public List<Passport> findBySeria(String seria) {
        return passportAPIStore.findBySeria(seria);
    }

    public List<Passport> findExpiredPassports() {
        return passportAPIStore.findExpiredPassports();
    }

    public List<Passport> findSoonExpiredPassports() {
        return passportAPIStore.findSoonExpiredPassports();
    }

    public Passport findById(String id) {
        return passportAPIStore.findById(id);
    }
}

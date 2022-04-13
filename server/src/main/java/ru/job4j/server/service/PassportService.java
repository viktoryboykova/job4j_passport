package ru.job4j.server.service;

import org.springframework.stereotype.Service;
import ru.job4j.server.model.Passport;
import ru.job4j.server.repository.PassportRepository;

import java.util.List;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    public void update(Passport passport) {
        passportRepository.save(passport);
    }

    public Passport findById(int id) {
        return passportRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        passportRepository.deleteById(id);
    }

    public List<Passport> findAll() {
        return (List<Passport>) passportRepository.findAll();
    }

    public List<Passport> findBySeria(String seria) {
        return passportRepository.findBySeria(seria);
    }

    public List<Passport> findExpiredPassports() {
        return passportRepository.findExpiredPassports();
    }

    public List<Passport> findSoonExpiredPassports() {
        return passportRepository.findSoonExpiredPassports();
    }
}

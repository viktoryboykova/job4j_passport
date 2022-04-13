package ru.job4j.server.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.server.model.Passport;

import java.util.List;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Integer> {

    List<Passport> findBySeria(String seria);

    @Query(value = "select * from passport where expiry_date < current_date", nativeQuery = true)
    List<Passport> findExpiredPassports();

    @Query(value = "select * from passport where expiry_date < current_date + 90 and expiry_date > current_date", nativeQuery = true)
    List<Passport> findSoonExpiredPassports();
}

package ru.job4j.client.store;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ru.job4j.client.model.Passport;

import java.util.Collections;
import java.util.List;

@Repository
public class PassportAPIStore {

    private String url = "http://localhost:8080/passport";

    private final RestTemplate restTemplate;

    public PassportAPIStore(RestTemplate client) {
        this.restTemplate = client;
    }


    public Passport save(Passport passport) {
        return restTemplate.postForEntity(
                url + "/save", passport, Passport.class
        ).getBody();
    }

    public boolean update(String id, Passport passport) {
        return restTemplate.exchange(
                String.format("%s/update?id=%s", url, id),
                HttpMethod.PUT,
                new HttpEntity<>(passport),
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean delete(String id) {
        return restTemplate.exchange(
                String.format("%s/delete?id=%s", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public List<Passport> findAll() {
        return getList(String.format(
                "%s/find", url
        ));
    }

    public List<Passport> findBySeria(String seria) {
        return getList(String.format(
                "%s/findBySeria?seria=%s", url, seria
        ));
    }

    public List<Passport> findExpiredPassports() {
        return getList(String.format(
                "%s/unavaliabe", url
        ));
    }

    public List<Passport> findSoonExpiredPassports() {
        return getList(String.format(
                "%s/find-replaceable", url
        ));
    }

    private List<Passport> getList(String url) {
        List<Passport> body = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }

    public Passport findById(String id) {
        return restTemplate.getForEntity(
                String.format("%s/findById?id=%s", url, id),
                Passport.class
        ).getBody();
    }
}
